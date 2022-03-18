package com.para.tranzai.mirai.core;

import net.mamoe.mirai.event.Event;

public interface MiraiEventHandler {

    void processing(String message, Event event);
}
