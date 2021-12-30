package com.para.tranzai.mirai;

import com.para.tranzai.mirai.handler.FriendEventHandler;
import com.para.tranzai.mirai.handler.GroupEventHandler;
import com.para.tranzai.mirai.handler.StrangerMessageHandler;
import com.para.tranzai.properties.TranzaiProperties;
import net.mamoe.mirai.Bot;
import net.mamoe.mirai.BotFactory;
import net.mamoe.mirai.event.GlobalEventChannel;
import net.mamoe.mirai.event.events.FriendMessageEvent;
import net.mamoe.mirai.event.events.GroupMessageEvent;
import net.mamoe.mirai.event.events.StrangerMessageEvent;
import net.mamoe.mirai.utils.BotConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MiraiConfig {

    private final FriendEventHandler friendEventHandler;
    private final GroupEventHandler groupEventHandler;
    private final StrangerMessageHandler strangerMessageHandler;

    private final TranzaiProperties properties;

    public MiraiConfig(FriendEventHandler friendEventHandler, GroupEventHandler groupEventHandler,
                       StrangerMessageHandler strangerMessageHandler, TranzaiProperties properties) {
        this.friendEventHandler = friendEventHandler;
        this.groupEventHandler = groupEventHandler;
        this.strangerMessageHandler = strangerMessageHandler;
        this.properties = properties;
    }

    @Bean
    public Bot miraiBot() {
        Bot bot = BotFactory.INSTANCE.newBot(properties.getMiraiBotConfig().getqq(), properties.getMiraiBotConfig().getPassword(), botConfiguration());
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

}
