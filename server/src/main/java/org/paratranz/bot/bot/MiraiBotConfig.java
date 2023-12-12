package org.paratranz.bot.bot;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.mamoe.mirai.Bot;
import net.mamoe.mirai.BotFactory;
import net.mamoe.mirai.utils.BotConfiguration;
import net.mamoe.mirai.utils.LoggerAdapters;
import org.paratranz.bot.api.ParatranzApi;
import org.paratranz.bot.bot.core.CommandProcessorBeanPostProcessor;
import org.paratranz.bot.bot.schedules.ApplicationSchedule;
import org.paratranz.bot.properties.ExternalProperties;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Slf4j
@Configuration
@RequiredArgsConstructor
@ConditionalOnProperty(name = "system.module-control.mirai-active", havingValue = "true", matchIfMissing = true)
public class MiraiBotConfig implements ApplicationListener<ApplicationReadyEvent> {

    @Bean
    public ParatranzApi paratranzClient() {
        return new ParatranzApi("5828fb55756dbf6ebb4f76937c16e530");
    }

    @Bean
    public Bot miraiBot(
            ExternalProperties externalProperties
    ) {
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
        //群聊事件处理
        bot.getEventChannel().registerListenerHost(new MiraiEventListener(bot.getCoroutineContext()));
        return bot;
    }

    @Bean
    public ApplicationSchedule applicationSchedule(
            ParatranzApi paraTranzApi,
            ExternalProperties externalProperties,
            Bot bot
    ) {
        return new ApplicationSchedule(paraTranzApi, externalProperties, bot);
    }

    @Bean
    public CommandProcessorBeanPostProcessor commandProcessorHandler() {
        return new CommandProcessorBeanPostProcessor();
    }

    @Override
    public void onApplicationEvent(ApplicationReadyEvent event) {
        event.getApplicationContext()
                .getBean(Bot.class)
                .login();
    }
}
