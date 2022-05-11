package com.para.tranzai.handler;

import net.mamoe.mirai.event.events.GroupMessageEvent;
import org.springframework.stereotype.Component;

import java.util.function.Consumer;
import java.util.regex.Pattern;

@Component
public class GroupMessageHandler implements Consumer<GroupMessageEvent> {
    /**
     * Performs this operation on the given argument.
     * @param event the input argument
     */
    @Override
    public void accept(GroupMessageEvent event) {
        String msg = event.getMessage().contentToString();
        if (Pattern.matches("^#发起群审核$|^#群审核$", msg)) {
            doProcessing(event);
        }
    }

    private void doProcessing(GroupMessageEvent event) {

    }

}
