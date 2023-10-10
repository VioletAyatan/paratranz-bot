package org.paratranz.bot.mirai.command;

import org.paratranz.bot.mirai.core.CommandProcessor;
import org.paratranz.bot.mirai.core.AbstractCommandProcessor;
import net.mamoe.mirai.event.events.GroupMessageEvent;

/**
 * @author Ankol
 */
@CommandProcessor(key = {"/help", "/帮助"})
public class HelpCommanderProcessor extends AbstractCommandProcessor<GroupMessageEvent> {


    /**
     * 无参数调用的情况下触发此方法
     *
     * @param event 事件对象
     */
    @Override
    protected void onNoArgsEvent(GroupMessageEvent event) {
        super.onNoArgsEvent(event);
    }
}
