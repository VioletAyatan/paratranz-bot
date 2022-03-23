package com.para.tranzai.mirai;

import com.para.tranzai.mirai.handler.FriendEventHandler;
import com.para.tranzai.mirai.handler.GroupEventHandler;
import com.para.tranzai.mirai.handler.StrangerMessageHandler;
import com.para.tranzai.mirai.server.MiraiService;
import com.para.tranzai.mirai.server.impl.MiraiServiceImpl;
import com.para.tranzai.properties.SystemProperties;
import lombok.extern.slf4j.Slf4j;
import net.mamoe.mirai.Bot;
import net.mamoe.mirai.BotFactory;
import net.mamoe.mirai.event.GlobalEventChannel;
import net.mamoe.mirai.event.events.FriendMessageEvent;
import net.mamoe.mirai.event.events.GroupMessageEvent;
import net.mamoe.mirai.event.events.StrangerMessageEvent;
import net.mamoe.mirai.utils.BotConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Slf4j
@Configuration
@ConditionalOnProperty(name = "system.module-control.mirai-active", havingValue = "true")
public class MiraiConfig {

    private final FriendEventHandler friendEventHandler;
    private final GroupEventHandler groupEventHandler;
    private final StrangerMessageHandler strangerMessageHandler;

    private final SystemProperties properties;

    public MiraiConfig(FriendEventHandler friendEventHandler, GroupEventHandler groupEventHandler,
                       StrangerMessageHandler strangerMessageHandler, SystemProperties properties) {
        this.friendEventHandler = friendEventHandler;
        this.groupEventHandler = groupEventHandler;
        this.strangerMessageHandler = strangerMessageHandler;
        this.properties = properties;
        log.info("Mirai-module activated.");
    }

    @Bean
    public Bot miraiBot() {
        Bot bot = BotFactory.INSTANCE.newBot(properties.getBotConfig().getQqNumber(), properties.getBotConfig().getPassword(), botConfiguration());
        bot.login();
        addEventListeners();
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
        eventChannel.subscribeAlways(GroupMessageEvent.class, groupEventHandler);
        //好友消息...
        eventChannel.subscribeAlways(FriendMessageEvent.class, friendEventHandler);
        //陌生人消息...
        eventChannel.subscribeAlways(StrangerMessageEvent.class, strangerMessageHandler);
    }

    @Bean
    public MiraiService miraiService() {
        return new MiraiServiceImpl(miraiBot());
    }

}
