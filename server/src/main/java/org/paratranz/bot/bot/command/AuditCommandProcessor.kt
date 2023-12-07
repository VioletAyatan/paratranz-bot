package org.paratranz.bot.bot.command

import cn.hutool.core.util.StrUtil
import kotlinx.coroutines.runBlocking
import net.mamoe.mirai.event.events.GroupMessageEvent
import net.mamoe.mirai.message.data.MessageChainBuilder
import org.paratranz.bot.api.ParatranzApi
import org.paratranz.bot.api.constant.ApplyStatus
import org.paratranz.bot.api.entity.Page
import org.paratranz.bot.api.entity.PageResult
import org.paratranz.bot.api.entity.data.Application
import org.paratranz.bot.api.entity.data.Audit
import org.paratranz.bot.bot.core.GroupMessageCommandProcessor
import org.paratranz.bot.properties.ExternalProperties
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

/**
 * /audit指令处理器
 *
 * @author Ankol
 */
@Component
class AuditCommandProcessor :
    GroupMessageCommandProcessor("群审核placeholder", arrayOf("/audit", "/群审核")) {

    private val log: Logger = LoggerFactory.getLogger(AuditCommandProcessor::class.java)

    @Autowired
    private lateinit var paraTranzApi: ParatranzApi

    @Autowired
    private lateinit var properties: ExternalProperties
    override fun onNoArgsEvent(event: GroupMessageEvent) {
        val pageResult: PageResult<Application> = paraTranzApi.apply.listApply(properties.projectId, Page.of(), ApplyStatus.UN_CONFIRM)
        if (!pageResult.results.isNullOrEmpty()) {
            val results = pageResult.results
            //有多个待审核人的情况
            if (results.size > 1) {
                log.error("Error. application count > 1, {}", results)
            } else {
                val application = results[0]
                //状态0为待审核.这里做一个申请状态验证.
                if (application.status != 0) {
                    return
                }
                //获取其测试内容
                val testContent = paraTranzApi.apply.getTestContent(
                    application.id, properties.projectId
                )
                sendMessage(event, testContent)
            }
        } else {
            runBlocking { event.group.sendMessage("抱歉，没找到需要审核人员呢~") }
        }
    }

    override fun onArgsEvent(event: GroupMessageEvent, args: Array<String>) {
        val applicationId = args[0]
        if (StrUtil.isNotEmpty(applicationId)) {
            try {
                val content = paraTranzApi.apply.getTestContent(applicationId.toInt(), properties.projectId)
                sendMessage(event, content)
            } catch (e: NumberFormatException) {
                runBlocking { event.group.sendMessage("无法解析参数，确保查询参数为正确的数字用户id。例：/群审核 [用户id]") }

            }
        } else {
            runBlocking { event.group.sendMessage("查询的用户id不可为空！例：/群审核 [用户id]") }
        }
    }

    private fun sendMessage(event: GroupMessageEvent, testContent: List<Audit>) {
        if (testContent.isEmpty()) {
            runBlocking { event.group.sendMessage("未找到相关测试内容呢~") }

        } else {
            for (audit in testContent) {
                val builder = MessageChainBuilder()
                    .append(audit.original).append("\n\n")
                //旧词条翻译者用户信息可能为空
                val user = audit.origin.user
                if (user != null) {
                    builder.append(if (user.nickname != null) user.nickname else user.username).append("：").append("\n")
                }
                //继续拼接
                builder.append(audit.origin.translation).append("\n\n")
                    .append("申请者翻译：").append("\n")
                    .append(audit.translation)
                //发送消息
                runBlocking { event.group.sendMessage(builder.build()) }
            }
        }
    }
}
