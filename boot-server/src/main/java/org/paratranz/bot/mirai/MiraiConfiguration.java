package org.paratranz.bot.mirai;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.mamoe.mirai.Bot;
import net.mamoe.mirai.BotFactory;
import net.mamoe.mirai.utils.BotConfiguration;
import net.mamoe.mirai.utils.LoggerAdapters;
import org.paratranz.bot.mirai.core.CommandProcessorBeanRegister;
import org.paratranz.bot.properties.ExternalProperties;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;



@Slf4j
@Configuration
@RequiredArgsConstructor
@Import(CommandProcessorBeanRegister.class)
@ConditionalOnProperty(name = "system.module-control.mirai-active", havingValue = "true", matchIfMissing = true)
public class MiraiConfiguration implements ApplicationListener<ApplicationReadyEvent> {

    private final ExternalProperties externalProperties;

    @PostConstruct
    private void init() {
        log.info("Mirai-module activated.");
    }

    @Bean
    public Bot miraiBot() {
        Bot bot = BotFactory.INSTANCE.newBot(
                externalProperties.getQq(),
                externalProperties.getPassword(),
                (config) -> {
                    config.setAutoReconnectOnForceOffline(true);
                    config.setProtocol(BotConfiguration.MiraiProtocol.ANDROID_PHONE);
                    config.setShowingVerboseEventLog(false);
                    config.setLoginCacheEnabled(true);
                    config.setReconnectionRetryTimes(10);
                    //覆盖Mirai内置的日志系统
                    config.setBotLoggerSupplier(it -> LoggerAdapters.asMiraiLogger(log));
                });
        bot.getEventChannel().registerListenerHost(new MiraiEventListener(bot.getCoroutineContext()));
        return bot;
    }

    @Override
    public void onApplicationEvent(ApplicationReadyEvent event) {
        event.getApplicationContext()
                .getBean(Bot.class)
                .login();
    }
}
