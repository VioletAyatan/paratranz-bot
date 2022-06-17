package com.para.tranzai.command;

import com.para.tranzai.command.annotation.CommandProcessor;
import net.mamoe.mirai.event.events.GroupMessageEvent;

/**
 * 文件上传指令处理器
 */
@CommandProcessor("/upload")
public class UploadCommandProcessor extends AbstractCommandProcessor<GroupMessageEvent> {

    @Override
    protected void triggerNoArgs(GroupMessageEvent event) {

    }

    @Override
    protected void triggerArgsEvent(GroupMessageEvent event, String[] args) {

    }
}

