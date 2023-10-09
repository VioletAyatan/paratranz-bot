package org.paratranz.bot.para;

import org.paratranz.bot.api.ParaTranzClient;
import org.paratranz.bot.para.schedules.ApplicationSchedule;
import org.paratranz.bot.para.server.ParaApiService;
import org.paratranz.bot.properties.ExternalProperties;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.mamoe.mirai.Bot;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Slf4j
@Configuration
@RequiredArgsConstructor
@ConditionalOnProperty(name = "system.module-control.para-active", havingValue = "true", matchIfMissing = true)
public class ParaConfiguration {

    private final ParaApiService paraApiService;
    private final ExternalProperties externalProperties;
    private final Bot bot;

    @Bean
    public ApplicationSchedule applicationSchedule() {
        return new ApplicationSchedule(paraApiService, externalProperties, bot);
    }

    @Bean
    public ParaTranzClient paraTranzClient() {
        return new ParaTranzClient("5828fb55756dbf6ebb4f76937c16e530");
    }
}
