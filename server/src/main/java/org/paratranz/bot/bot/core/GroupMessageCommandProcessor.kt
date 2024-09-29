package org.paratranz.bot.bot.core

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import net.mamoe.mirai.event.events.GroupMessageEvent
import java.util.*
import java.util.function.BiConsumer

abstract class GroupMessageCommandProcessor protected constructor(
    /**
     * 插件功能描述
     */
    val description: String,
    /**
     * 触发此指令的关键字
     */
    val key: String,
    /**
     * 其他次要关键字
     */
    val subKey: Array<String>,
) : BiConsumer<GroupMessageEvent, Array<String>> {

    private val scope = CoroutineScope(Dispatchers.IO)

    override fun accept(event: GroupMessageEvent, args: Array<String>) {
        if (args.size > 1) {
            scope.launch { onArgsEvent(event, Arrays.copyOfRange(args, 1, args.size)) }
        } else {
            scope.launch { onNoArgsEvent(event) }
        }
    }

    /**
     * 无参数调用的情况下触发此方法
     *
     * @param event Bot事件对象（[GroupMessageEvent]）
     */
    protected open suspend fun onNoArgsEvent(event: GroupMessageEvent) {
    }

    /**
     * 有参数调用的情况下触发此方法
     *
     * @param event Bot事件对象（[GroupMessageEvent]）
     * @param args  指令集参数，将会自动将传入指令作为数组分发.
     */
    protected open suspend fun onArgsEvent(event: GroupMessageEvent, args: Array<String>) {
    }
}
