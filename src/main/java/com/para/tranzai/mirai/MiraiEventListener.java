package com.para.tranzai.mirai;

import com.para.tranzai.mirai.command.core.CommandManger;
import com.para.tranzai.tools.Utils;
import kotlin.coroutines.CoroutineContext;
import lombok.extern.slf4j.Slf4j;
import net.mamoe.mirai.event.EventHandler;
import net.mamoe.mirai.event.SimpleListenerHost;
import net.mamoe.mirai.event.events.GroupMessageEvent;
import net.mamoe.mirai.event.events.MemberJoinEvent;
import org.jetbrains.annotations.NotNull;

import java.util.Optional;

@Slf4j
public class MiraiEventListener extends SimpleListenerHost {

    public MiraiEventListener(CoroutineContext coroutineContext) {
        super(coroutineContext);
    }

    @EventHandler
    @SuppressWarnings("unchecked")
    public void onGroupMessage(GroupMessageEvent event) {
        log.info("[{}:{}] -> {}", event.getSender().getNick(), event.getSender().getId(), event.getMessage().contentToString());
        String[] args = Utils.getArgs(event.getMessage().contentToString());
        Optional.ofNullable(CommandManger.getCommandProcessor(args[0]))
                .ifPresent(processor -> processor.accept(event, args));
    }

    @EventHandler
    public void onMemberJoin(MemberJoinEvent joinEvent) {

    }

    @Override
    public void handleException(@NotNull CoroutineContext context, @NotNull Throwable exception) {
        super.handleException(context, exception);
    }
}
