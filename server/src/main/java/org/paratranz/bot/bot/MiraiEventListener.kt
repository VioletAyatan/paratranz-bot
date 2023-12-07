package org.paratranz.bot.bot

import lombok.extern.slf4j.Slf4j
import net.mamoe.mirai.event.EventHandler
import net.mamoe.mirai.event.SimpleListenerHost
import net.mamoe.mirai.event.events.GroupMessageEvent
import net.mamoe.mirai.event.events.MemberJoinEvent
import org.paratranz.bot.bot.core.CommandManger
import org.paratranz.bot.tools.Utils
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import kotlin.coroutines.CoroutineContext

@Slf4j
class MiraiEventListener(coroutineContext: CoroutineContext) : SimpleListenerHost(coroutineContext) {
    private val log: Logger = LoggerFactory.getLogger(MiraiEventListener::class.java)

    /**
     * 群聊事件
     */
    @EventHandler
    fun onGroupMessage(event: GroupMessageEvent) {
        log.info(
            "[{}:{}] -> {}",
            event.sender.nick,
            event.sender.id,
            event.message.contentToString()
        )
        //处理群聊消息
        val args = Utils.getArgs(event.message.contentToString())
        //根据第一个指令获取对应的指令处理器进行处理
        CommandManger.getIfPresent(args[0])
            .ifPresent { it.accept(event, args) }
    }

    /**
     * 加群事件
     */
    @EventHandler
    fun onMemberJoin(joinEvent: MemberJoinEvent?) {
    }

    override fun handleException(context: CoroutineContext, exception: Throwable) {
        super.handleException(context, exception)
    }
}
