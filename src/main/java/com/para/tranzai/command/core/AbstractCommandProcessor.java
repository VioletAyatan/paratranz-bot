package com.para.tranzai.command.core;

import net.mamoe.mirai.event.events.MessageEvent;

import java.util.function.BiConsumer;

public abstract class AbstractCommandProcessor<E extends MessageEvent> implements BiConsumer<E, String[]> {
    /**
     * 对接收到的指令进行处理
     * @param event 触发的消息事件
     * @param args 指令参数集.
     */
    @Override
    public abstract void accept(E event, String[] args);
}
