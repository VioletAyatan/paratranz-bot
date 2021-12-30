package com.para.tranzai.mirai.handler;

import lombok.extern.slf4j.Slf4j;
import net.mamoe.mirai.event.events.FriendMessageEvent;
import org.springframework.stereotype.Component;

import java.util.function.Consumer;

@Slf4j
@Component
public class FriendEventHandler implements Consumer<FriendMessageEvent> {

    @Override
    public void accept(FriendMessageEvent event) {

    }
}
