package com.para.tranzai.tools;

import cn.hutool.core.io.FileUtil;
import com.para.tranzai.properties.ExternalProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.event.ApplicationPreparedEvent;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.core.io.ClassPathResource;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

@Slf4j
public class PreProcessingListener implements ApplicationListener<ApplicationPreparedEvent> {
    /**
     * Handle an application event.
     * @param event the event to respond to
     */
    @Override
    public void onApplicationEvent(ApplicationPreparedEvent event) {
        File file = new File("config/config.properties");
        if (!file.exists()) {
            log.warn("Warning! Config-file not existed! creat it....");
            ClassPathResource classPathResource = new ClassPathResource("template/config.properties");
            try {
                InputStream inputStream = classPathResource.getInputStream();
                FileUtil.writeFromStream(inputStream, new File("config" + File.separator + "config.properties"));
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            event.getApplicationContext().addApplicationListener(event1 -> {
                if (event1 instanceof ApplicationReadyEvent) {
                    ApplicationReadyEvent readyEvent = (ApplicationReadyEvent) event1;
                    ExternalProperties properties = readyEvent.getApplicationContext().getBean(ExternalProperties.class);
                    log.info("Load configuration for {}", properties);
                }
            });
        }
    }
}
