package com.para.tranzai.para;

import com.para.tranzai.para.schedules.ApplicationSchedule;
import com.para.tranzai.para.server.ParaApiService;
import com.para.tranzai.properties.ExternalProperties;
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
}
