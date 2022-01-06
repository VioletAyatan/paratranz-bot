package com.para.tranzai.mirai.handler;

import lombok.extern.slf4j.Slf4j;
import net.mamoe.mirai.event.events.GroupMessageEvent;

import javax.annotation.ManagedBean;
import java.util.function.Consumer;

@Slf4j
@ManagedBean
public class GroupEventHandler implements Consumer<GroupMessageEvent> {

    @Override
    public void accept(GroupMessageEvent event) {
    }
}
