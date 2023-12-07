package org.paratranz.bot.bot.command

import net.mamoe.mirai.event.events.GroupMessageEvent
import org.paratranz.bot.bot.core.GroupMessageCommandProcessor
import org.springframework.stereotype.Component

/**
 * /help指令处理器
 *
 * @author Ankol
 */
@Component
class HelpCommanderProcessor :
    GroupMessageCommandProcessor("description", arrayOf("/help", "/帮助")) {
    /**
     * 无参数调用的情况下触发此方法
     *
     * @param event 事件对象
     */
    override fun onNoArgsEvent(event: GroupMessageEvent) {
        super.onNoArgsEvent(event)
    }
}
