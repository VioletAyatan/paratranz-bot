package com.para.tranzai.mirai;

import com.para.tranzai.mirai.core.DynamicDispatcher;
import com.para.tranzai.properties.SystemProperties;
import lombok.extern.slf4j.Slf4j;
import net.mamoe.mirai.Bot;
import net.mamoe.mirai.BotFactory;
import net.mamoe.mirai.event.GlobalEventChannel;
import net.mamoe.mirai.event.events.GroupMessageEvent;
import net.mamoe.mirai.utils.BotConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Slf4j
@Configuration
@ConditionalOnProperty(name = "system.module-control.mirai-active", havingValue = "true")
public class MiraiConfig {

    private final SystemProperties properties;
    private final DynamicDispatcher dynamicDispatcher;

    public MiraiConfig(SystemProperties properties, DynamicDispatcher dynamicDispatcher) {
        this.properties = properties;
        this.dynamicDispatcher = dynamicDispatcher;
        log.info("Mirai-module activated.");
    }

    @Bean
    public Bot miraiBot() {
        Bot bot = BotFactory.INSTANCE.newBot(properties.getBotConfig().getQqNumber(), properties.getBotConfig().getPassword(), botConfiguration());
        bot.login();
//        addEventListeners();
        return bot;
    }

    private BotConfiguration botConfiguration() {
        BotConfiguration configuration = new BotConfiguration();
        configuration.setAutoReconnectOnForceOffline(true);
        configuration.setProtocol(BotConfiguration.MiraiProtocol.ANDROID_PHONE);
        configuration.setShowingVerboseEventLog(false);
        configuration.setLoginCacheEnabled(true);
        configuration.setReconnectionRetryTimes(10);
        return configuration;
    }

    private void addEventListeners() {
        GlobalEventChannel eventChannel = GlobalEventChannel.INSTANCE;
        //群聊消息...
        eventChannel.subscribeAlways(GroupMessageEvent.class, dynamicDispatcher);
    }

}
