package com.para.tranzai.mirai;

import com.para.tranzai.command.core.CommandManger;
import com.para.tranzai.properties.SystemProperties;
import com.para.tranzai.tools.Utils;
import kotlin.coroutines.CoroutineContext;
import lombok.extern.slf4j.Slf4j;
import net.mamoe.mirai.Bot;
import net.mamoe.mirai.BotFactory;
import net.mamoe.mirai.event.EventHandler;
import net.mamoe.mirai.event.SimpleListenerHost;
import net.mamoe.mirai.event.events.GroupMessageEvent;
import net.mamoe.mirai.utils.BotConfiguration;
import net.mamoe.mirai.utils.LoggerAdapters;
import org.jetbrains.annotations.NotNull;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Optional;

@Slf4j
@Configuration
@EnableConfigurationProperties(SystemProperties.class)
@ConditionalOnProperty(name = "system.module-control.mirai-active", havingValue = "true", matchIfMissing = true)
public class MiraiConfiguration implements ApplicationListener<ApplicationReadyEvent> {

    private final SystemProperties properties;

    public MiraiConfiguration(SystemProperties properties) {
        this.properties = properties;
        log.info("Mirai-module activated.");
    }

    @Bean
    public Bot miraiBot() {
        Bot bot = BotFactory.INSTANCE.newBot(
                properties.getBotConfig().getQqNumber(),
                properties.getBotConfig().getPassword(),
                (config) -> {
                    config.setAutoReconnectOnForceOffline(true);
                    config.setProtocol(BotConfiguration.MiraiProtocol.ANDROID_PHONE);
                    config.setShowingVerboseEventLog(false);
                    config.setLoginCacheEnabled(true);
                    config.setReconnectionRetryTimes(10);
                    //覆盖Mirai内置的日志系统
                    config.setBotLoggerSupplier(it -> LoggerAdapters.asMiraiLogger(log));
                });
        addEventListeners(bot);
        return bot;
    }

    private void addEventListeners(Bot bot) {
        bot.getEventChannel().registerListenerHost(new SimpleListenerHost(bot.getCoroutineContext()) {
            @EventHandler
            @SuppressWarnings("unchecked")
            public void onGroupMessage(GroupMessageEvent event) {
                String msg = event.getMessage().contentToString();
                Optional.ofNullable(CommandManger.getCommandProcessor(msg))
                        .ifPresent(processor -> processor.accept(event, Utils.getArgs(msg)));
            }

            @Override
            public void handleException(@NotNull CoroutineContext context, @NotNull Throwable exception) {
                super.handleException(context, exception);
            }
        });
    }


    @Override
    public void onApplicationEvent(ApplicationReadyEvent event) {
        event.getApplicationContext()
                .getBean(Bot.class)
                .login();
    }
}
