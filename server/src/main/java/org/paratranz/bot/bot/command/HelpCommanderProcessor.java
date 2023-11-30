package org.paratranz.bot.bot.command;

import net.mamoe.mirai.event.events.GroupMessageEvent;
import org.paratranz.bot.bot.core.GroupMessageCommandProcessor;
import org.springframework.stereotype.Component;

/**
 * /help指令处理器
 *
 * @author Ankol
 */
@Component
public class HelpCommanderProcessor extends GroupMessageCommandProcessor {


    public HelpCommanderProcessor() {
        super("description", new String[]{"/help", "/帮助"});
    }

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
