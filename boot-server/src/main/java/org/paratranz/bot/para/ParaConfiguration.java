package org.paratranz.bot.para;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.mamoe.mirai.Bot;
import org.paratranz.bot.api.ParaTranzApi;
import org.paratranz.bot.para.schedules.ApplicationSchedule;
import org.paratranz.bot.properties.ExternalProperties;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Slf4j
@Configuration
@RequiredArgsConstructor
@ConditionalOnProperty(name = "system.module-control.para-active", havingValue = "true", matchIfMissing = true)
public class ParaConfiguration {

    @Bean
    public ApplicationSchedule applicationSchedule(
            ParaTranzApi paraTranzApi,
            ExternalProperties externalProperties,
            Bot bot
    ) {
        return new ApplicationSchedule(paraTranzApi, externalProperties, bot);
    }

    @Bean
    public ParaTranzApi paraTranzClient() {
        return new ParaTranzApi("5828fb55756dbf6ebb4f76937c16e530");
    }
}
