package org.paratranz.bot.bot.schedules

import cn.hutool.core.collection.CollUtil
import cn.hutool.core.util.StrUtil
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import net.mamoe.mirai.Bot
import net.mamoe.mirai.contact.Group
import net.mamoe.mirai.contact.MemberPermission
import net.mamoe.mirai.message.data.AtAll
import net.mamoe.mirai.message.data.MessageChain
import net.mamoe.mirai.message.data.MessageChainBuilder
import org.paratranz.bot.api.ParatranzApi
import org.paratranz.bot.api.entity.data.Application
import org.paratranz.bot.properties.ExternalProperties
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Component
import java.util.concurrent.TimeUnit
import java.util.stream.Collectors

@Component
class ApplicationSchedule {
    private val log = LoggerFactory.getLogger(this::class.java)

    @Autowired
    private lateinit var paraTranzApi: ParatranzApi

    @Autowired
    private lateinit var externalProperties: ExternalProperties

    @Autowired
    private lateinit var bot: Bot

    /**
     * 此变量用于记录当前已经推送过的申请信息（防止重复推送）.
     * 每日零点清空(也就意味着如果一个申请一天都没人处理，那么第二天会重新进行推送).
     */
    private var isChecked: MutableSet<Int> = HashSet()

    /**
     * 定时任务，每隔2分钟检测一次申请列表，如果有待审核的申请则通知指定群组.
     */
    @Scheduled(fixedDelay = 2, timeUnit = TimeUnit.MINUTES)
    fun schedulingApplication() {
        //获取当前项目的申请列表
        val pageResult = paraTranzApi.apply.listApply(externalProperties.projectId)
        // 待审核列表.
        val applicationList = pageResult.results
            .stream() //条件，状态0为待审核.并且不包含在已推送列表中.
            .filter { item: Application -> item.status == 0 && !isChecked.contains(item.id) }
            .collect(Collectors.toList())
        //如果待审核列表不为空，执行q群通知...
        if (CollUtil.isNotEmpty(applicationList)) {
            log.info("New application detected. send to group [${externalProperties.groups.contentToString()}]")
            for (groupId in externalProperties.groups) {
                val group = bot.getGroup(groupId!!)
                if (group != null) {
                    for (application in applicationList) {
                        CoroutineScope(Dispatchers.Default).launch {
                            group.sendMessage(buildMessage(group, application))
                        }
                    }
                }
            }
            isChecked.addAll(applicationList.stream().map { it.id }.toList())
        }
    }

    /**
     * 每日0点运行
     *
     * 清空重复标记
     */
    @Scheduled(cron = "0 0 0 * * ? ")
    fun resetChecked() {
        isChecked.clear()
    }

    private fun buildMessage(group: Group, application: Application?): MessageChain {
        val builder = MessageChainBuilder()
        //如果是管理员，加入艾特全员操作.
        if (group.botPermission.level == MemberPermission.ADMINISTRATOR.level) {
            builder.append(AtAll).append("\n")
        }
        val user = application!!.user
        builder.append(StrUtil.format("{} 申请加入项目组，请及时审核\n", user.username))
        //            builder.append(StrUtil.format("游戏时间 {}，英语等级：{}\n",
//                    application.getDetail().getGameTime(),
//                    application.getDetail().getEnglish().getLevel())
//            );
        if (StrUtil.isNotBlank(application.content)) {
            builder.append(StrUtil.format(application.content))
        }
        return builder.build()
    }
}
