package com.para.tranzai.mirai.core;

import com.para.tranzai.mirai.handler.AuditHandlerMirai;
import net.mamoe.mirai.event.events.GroupMessageEvent;

import javax.annotation.ManagedBean;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

@ManagedBean
public class DynamicDispatcher implements Consumer<GroupMessageEvent> {

    private static final Map<IKey, MiraiEventHandler> handlers = new HashMap<>();

    static {
        handlers.put(IKey.create("/审核"), new AuditHandlerMirai());
        handlers.put(IKey.create("/audit"), new AuditHandlerMirai());
    }

    @Override
    public void accept(GroupMessageEvent event) {
//        MessageSource source = event.getMessage().get(MessageSource.Key);
//        String message = source.toString();
//        MiraiEventHandler handler = handlers.get(IKey.create(message));
//        if (handler != null) {
//            handler.processing(message, event);
//        }
    }


    public static void main(String[] args) {
        MiraiEventHandler handler = handlers.get(IKey.create("/审核"));
        System.out.println("handler = " + handler);
    }
}
