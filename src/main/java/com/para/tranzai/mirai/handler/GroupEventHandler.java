package com.para.tranzai.mirai.handler;

import lombok.extern.slf4j.Slf4j;
import net.mamoe.mirai.event.events.GroupMessageEvent;
import org.springframework.stereotype.Component;

import java.util.function.Consumer;

@Slf4j
@Component
public class GroupEventHandler implements Consumer<GroupMessageEvent> {

    @Override
    public void accept(GroupMessageEvent event) {
        System.out.println("event = " + event);
    }
}
