package com.para.tranzai.mirai;

import com.para.tranzai.mirai.command.core.CommandProcessorBeanRegister;
import com.para.tranzai.properties.SystemProperties;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.mamoe.mirai.Bot;
import net.mamoe.mirai.BotFactory;
import net.mamoe.mirai.utils.BotConfiguration;
import net.mamoe.mirai.utils.LoggerAdapters;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import javax.annotation.PostConstruct;


@Slf4j
@Configuration
@RequiredArgsConstructor
@Import(CommandProcessorBeanRegister.class)
@ConditionalOnProperty(name = "system.module-control.mirai-active", havingValue = "true", matchIfMissing = true)
public class MiraiConfiguration implements ApplicationListener<ApplicationReadyEvent> {

    private final SystemProperties properties;

    @PostConstruct
    private void init() {
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
