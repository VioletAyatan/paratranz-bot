package com.example.command

import com.example.MiraiPluginPara
import net.mamoe.mirai.console.command.CommandSender
import net.mamoe.mirai.console.command.RawCommand
import net.mamoe.mirai.message.data.MessageChain

/**
 * 审核指令处理
 * @author ankol
 */
class AuditCommandHandler : RawCommand(
    owner = MiraiPluginPara,
    primaryName = "审核",
    secondaryNames = arrayOf(""),
    usage = "/审核",
    description = "使用'/审核'指令直接发起审核请求，会抓取项目里目前最新一个待审核人员的申请请求。"
) {
    override suspend fun CommandSender.onCommand(args: MessageChain) {
        TODO("Not yet implemented")
    }
}