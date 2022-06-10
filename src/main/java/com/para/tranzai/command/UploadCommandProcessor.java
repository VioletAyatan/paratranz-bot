package com.para.tranzai.command;

import com.para.tranzai.command.core.AbstractCommandProcessor;
import net.mamoe.mirai.event.events.GroupMessageEvent;

/**
 * 文件上传指令处理器
 */
//@CommandProcessor("/upload")
public class UploadCommandProcessor extends AbstractCommandProcessor<GroupMessageEvent> {

    @Override
    public void accept(GroupMessageEvent event, String[] args) {

    }
}

