package com.para.tranzai.command;

import com.para.tranzai.command.annotation.CommandProcessor;
import net.mamoe.mirai.event.events.GroupMessageEvent;

/**
 * @author Ankol
 */
@CommandProcessor("/help")
public class HelpCommanderProcessor extends AbstractCommandProcessor<GroupMessageEvent> {


    /**
     * 无参数调用的情况下触发此方法
     * @param event 事件对象
     */
    @Override
    protected void triggerNoArgs(GroupMessageEvent event) {
        super.triggerNoArgs(event);
    }
}
