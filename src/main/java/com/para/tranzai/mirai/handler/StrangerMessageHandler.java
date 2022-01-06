package com.para.tranzai.mirai.handler;

import lombok.extern.slf4j.Slf4j;
import net.mamoe.mirai.event.events.StrangerMessageEvent;

import javax.annotation.ManagedBean;
import java.util.function.Consumer;

@Slf4j
@ManagedBean
public class StrangerMessageHandler implements Consumer<StrangerMessageEvent> {

    @Override
    public void accept(StrangerMessageEvent event) {
    }
}
