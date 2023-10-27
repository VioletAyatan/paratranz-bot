package org.paratranz.bot.tools;

import cn.hutool.core.io.FileUtil;
import lombok.extern.slf4j.Slf4j;
import org.paratranz.bot.properties.ExternalProperties;
import org.springframework.boot.context.event.ApplicationPreparedEvent;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.core.io.ClassPathResource;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

/**
 * pre-processing the configuration file.
 * if is not existed. configuration-file will be created.
 *
 * @author Ankol
 */
@Slf4j
public class ConfigInitializer implements ApplicationListener<ApplicationPreparedEvent> {
    /**
     * Handle an application event.
     *
     * @param event the event to respond to
     */
    @Override
    public void onApplicationEvent(ApplicationPreparedEvent event) {
        File file = new File("config/config.properties");
        if (!file.exists()) {
            log.warn("Warning! Configuration file not existed! creat it....");
            ClassPathResource classPathResource = new ClassPathResource("template/config.properties");
            try {
                InputStream inputStream = classPathResource.getInputStream();
                FileUtil.writeFromStream(inputStream, new File("config" + File.separator + "config.properties"));
            } catch (IOException e) {
                log.error("初始化配置文件出错，原因：" + e.getMessage(), e);
            }
        } else {
            event.getApplicationContext().addApplicationListener(event1 -> {
                if (event1 instanceof ApplicationReadyEvent readyEvent) {
                    ExternalProperties properties = readyEvent.getApplicationContext().getBean(ExternalProperties.class);
                    log.info("Load Configuration file from {}", properties);
                }
            });
        }
    }
}
