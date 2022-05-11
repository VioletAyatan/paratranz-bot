package com.para.tranzai.mirai;

import com.para.tranzai.handler.GroupMessageHandler;
import com.para.tranzai.properties.SystemProperties;
import lombok.extern.slf4j.Slf4j;
import net.mamoe.mirai.Bot;
import net.mamoe.mirai.BotFactory;
import net.mamoe.mirai.event.EventChannel;
import net.mamoe.mirai.event.events.BotEvent;
import net.mamoe.mirai.event.events.GroupMessageEvent;
import net.mamoe.mirai.utils.BotConfiguration;
import net.mamoe.mirai.utils.LoggerAdapters;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Slf4j
@Configuration
@ConditionalOnProperty(name = "system.module-control.mirai-active", havingValue = "true")
public class MiraiConfig {

    private final SystemProperties properties;
    private final GroupMessageHandler groupMessageHandler;

    public MiraiConfig(SystemProperties properties, GroupMessageHandler groupMessageHandler) {
        this.properties = properties;
        this.groupMessageHandler = groupMessageHandler;
        log.info("Mirai-module activated.");
    }

    @Bean
    public Bot miraiBot() {
        Bot bot = BotFactory.INSTANCE.newBot(properties.getBotConfig().getQqNumber(), properties.getBotConfig().getPassword(), botConfiguration());
        bot.login();
        addEventListeners(bot);
        return bot;
    }

    private BotConfiguration botConfiguration() {
        BotConfiguration configuration = new BotConfiguration();
        configuration.setAutoReconnectOnForceOffline(true);
        configuration.setProtocol(BotConfiguration.MiraiProtocol.ANDROID_PHONE);
        configuration.setShowingVerboseEventLog(false);
        configuration.setLoginCacheEnabled(true);
        configuration.setReconnectionRetryTimes(10);
        //覆盖Mirai内置的日志系统
        configuration.setBotLoggerSupplier(bot -> LoggerAdapters.asMiraiLogger(log));
        return configuration;
    }

    private void addEventListeners(Bot bot) {
        EventChannel<BotEvent> eventChannel = bot.getEventChannel();
        //处理群聊消息事件...
        eventChannel.subscribeAlways(GroupMessageEvent.class, groupMessageHandler);
    }


}
