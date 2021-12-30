package com.para.tranzai.mirai.handler;

import lombok.extern.slf4j.Slf4j;
import net.mamoe.mirai.event.events.StrangerMessageEvent;
import org.springframework.stereotype.Component;

import java.util.function.Consumer;

@Slf4j
@Component
public class StrangerMessageHandler implements Consumer<StrangerMessageEvent> {

    @Override
    public void accept(StrangerMessageEvent event) {
    }
}
