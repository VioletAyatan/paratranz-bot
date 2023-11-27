package org.paratranz.bot.bot.command;

import org.paratranz.bot.bot.core.GroupMessageCommandProcessor;
import org.springframework.stereotype.Component;

@Component
public class MyTestCommandProcessor extends GroupMessageCommandProcessor {
    public MyTestCommandProcessor() {
        super("placeholder for descriptions...", new String[]{"/audit", "/群审核", "/审核"});
    }

}
