package com.example.command

import com.example.MiraiPluginPara
import com.example.api.ParaApi
import com.example.api.entity.Application
import com.example.constant.ParaConstant
import net.mamoe.mirai.console.command.CommandSender
import net.mamoe.mirai.console.command.RawCommand
import net.mamoe.mirai.message.data.MessageChain

/**
 * 审核指令处理
 * @author ankol
 */
class AuditCommandHandler : RawCommand(
    owner = MiraiPluginPara,
    primaryName = "audit",
    secondaryNames = arrayOf("审核", "群审核"),
    usage = "/audit",
    description = "使用 /audit 或 audit 指令直接发起审核请求，会抓取项目里目前最新一个待审核人员的申请请求。",
    prefixOptional = true
) {
    /**
     * 待审核
     */
    private val waitConfirm = 0

    override suspend fun CommandSender.onCommand(args: MessageChain) {
        val (page, pageCount, pageSize, results, rowCount) = ParaApi.queryProjectApplications(
            967,
            ParaConstant.Application.AWAITING_REVIEW
        )
        for (result in results) {
            //待审核的人员
            if (waitConfirm == result.status) {

            }
        }
    }
}