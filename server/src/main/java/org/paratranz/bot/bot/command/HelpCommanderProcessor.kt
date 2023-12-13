package org.paratranz.bot.bot.command

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import net.mamoe.mirai.event.events.GroupMessageEvent
import org.paratranz.bot.bot.core.CommandManger
import org.paratranz.bot.bot.core.GroupMessageCommandProcessor
import org.springframework.stereotype.Component

/**
 * /help指令处理器
 *
 * @author Ankol
 */
@Component
class HelpCommanderProcessor :
    GroupMessageCommandProcessor("description", "/help", arrayOf("/帮助")) {
    private val scope = CoroutineScope(Dispatchers.IO)

    /**
     * 无参数调用的情况下触发此方法
     *
     * @param event 事件对象
     */
    override fun onNoArgsEvent(event: GroupMessageEvent) {
        super.onNoArgsEvent(event)
    }
}
