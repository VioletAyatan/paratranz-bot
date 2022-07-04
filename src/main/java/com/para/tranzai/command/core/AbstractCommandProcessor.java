package com.para.tranzai.command.core;

import net.mamoe.mirai.event.events.MessageEvent;

import java.util.Arrays;
import java.util.function.BiConsumer;

public abstract class AbstractCommandProcessor<E extends MessageEvent> implements BiConsumer<E, String[]> {
    /**
     * 对接收到的指令进行处理
     * @param event 触发的消息事件
     * @param args 指令参数集.
     */
    @Override
    public void accept(E event, String[] args) {
        if (args.length > 1) {
            triggerArgsEvent(event, Arrays.copyOfRange(args, 1, args.length));
        } else {
            triggerNoArgs(event);
        }
    }

    /**
     * 无参数调用的情况下触发此方法
     * @param event 事件对象
     */
    protected void triggerNoArgs(E event) {

    }

    /**
     * 有参数调用的情况下触发此方法
     */
    protected void triggerArgsEvent(E event, String[] args) {

    }
}
