package org.paratranz.bot.bot;

import kotlin.coroutines.CoroutineContext;
import lombok.extern.slf4j.Slf4j;
import net.mamoe.mirai.event.EventHandler;
import net.mamoe.mirai.event.SimpleListenerHost;
import net.mamoe.mirai.event.events.GroupMessageEvent;
import net.mamoe.mirai.event.events.MemberJoinEvent;
import org.jetbrains.annotations.NotNull;
import org.paratranz.bot.bot.core.CommandManger;
import org.paratranz.bot.tools.Utils;

@Slf4j
public class MiraiEventListener extends SimpleListenerHost {

    public MiraiEventListener(CoroutineContext coroutineContext) {
        super(coroutineContext);
    }

    /**
     * 群聊事件
     */
    @EventHandler
    public void onGroupMessage(GroupMessageEvent event) {
        log.info("[{}:{}] -> {}", event.getSender().getNick(), event.getSender().getId(), event.getMessage().contentToString());
        String[] args = Utils.getArgs(event.getMessage().contentToString());
        CommandManger.getIfPresent(args[0])
                .ifPresent(processor -> processor.accept(event, args));
    }

    /**
     * 加群事件
     */
    @EventHandler
    public void onMemberJoin(MemberJoinEvent joinEvent) {

    }

    @Override
    public void handleException(@NotNull CoroutineContext context, @NotNull Throwable exception) {
        super.handleException(context, exception);
    }
}
