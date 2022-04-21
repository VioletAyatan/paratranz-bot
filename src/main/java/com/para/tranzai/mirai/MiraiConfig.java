package com.para.tranzai.mirai;

import com.para.tranzai.properties.SystemProperties;
import lombok.extern.slf4j.Slf4j;
import net.mamoe.mirai.Bot;
import net.mamoe.mirai.BotFactory;
import net.mamoe.mirai.utils.BotConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Slf4j
@Configuration
@ConditionalOnProperty(name = "system.module-control.mirai-active", havingValue = "true")
public class MiraiConfig {

    private final SystemProperties properties;

    public MiraiConfig(SystemProperties properties) {
        this.properties = properties;
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


}
