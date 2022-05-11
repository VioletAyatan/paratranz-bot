package com.para.tranzai.para;

import com.para.tranzai.para.schedules.ApplicationSchedule;
import com.para.tranzai.para.server.ParaService;
import com.para.tranzai.properties.SystemProperties;
import lombok.extern.slf4j.Slf4j;
import net.mamoe.mirai.Bot;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Slf4j
@Configuration
@ConditionalOnProperty(name = "system.module-control.para-active", havingValue = "true", matchIfMissing = true)
public class ParaConfiguration {

    private final ParaService paraService;
    private final SystemProperties systemProperties;
    private final Bot bot;

    public ParaConfiguration(ParaService paraService, SystemProperties systemProperties, Bot bot) {
        log.info("ParaTranz-module activated.");
        this.paraService = paraService;
        this.systemProperties = systemProperties;
        this.bot = bot;
    }


    @Bean
    public ApplicationSchedule applicationSchedule() {
        return new ApplicationSchedule(paraService, systemProperties, bot);
    }
}
