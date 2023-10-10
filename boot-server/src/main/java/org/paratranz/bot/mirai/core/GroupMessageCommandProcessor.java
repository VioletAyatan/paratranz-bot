package org.paratranz.bot.mirai.core;

import net.mamoe.mirai.event.events.GroupMessageEvent;
import net.mamoe.mirai.event.events.MessageEvent;

import java.util.Arrays;
import java.util.function.BiConsumer;

/**
 * 抽象消息处理器类，扩展了{@link BiConsumer}接口。
 * <br/>
 * 提供了基于参数解析操作进行不同调用的模板方法.
 * <br/>
 * 如果一个指令消息不携带参数，那么其会触发 onNoArgsEvent 方法.
 * 如果一个指令消息携带了参数，那么其会触发 onArgsEvent 方法.
 *
 * @param <E> Bot事件对象类型
 * @author Ankol
 */
public abstract class GroupMessageCommandProcessor implements BiConsumer<GroupMessageEvent, String[]> {
    /**
     * 对接收到的指令进行处理
     *
     * @param event 触发的消息事件
     * @param args  指令集参数，将会自动将传入指令作为数组分发.
     */
    @Override
    public void accept(GroupMessageEvent event, String[] args) {
        if (args.length > 1) {
            onArgsEvent(event, Arrays.copyOfRange(args, 1, args.length));
        } else {
            onNoArgsEvent(event);
        }
    }

    /**
     * 无参数调用的情况下触发此方法
     *
     * @param event Bot事件对象（继承自{@link MessageEvent}）
     */
    protected void onNoArgsEvent(GroupMessageEvent event) {

    }

    /**
     * 有参数调用的情况下触发此方法
     *
     * @param event Bot事件对象（继承自{@link MessageEvent}）
     * @param args  指令集参数，将会自动将传入指令作为数组分发.
     */
    protected void onArgsEvent(GroupMessageEvent event, String[] args) {

    }
}
