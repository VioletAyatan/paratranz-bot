package com.para.tranzai.command.core;

import net.mamoe.mirai.event.events.MessageEvent;

import java.util.function.BiConsumer;

public abstract class AbstractCommandProcessor<E extends MessageEvent> implements BiConsumer<E, String[]> {
    @Override
    public abstract void accept(E event, String[] args);
}
