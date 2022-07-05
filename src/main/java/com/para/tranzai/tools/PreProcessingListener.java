package com.para.tranzai.tools;

import cn.hutool.core.io.FileUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.event.ApplicationPreparedEvent;
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
        if (!FileUtil.exist("config/config.properties")) {
            log.warn("Warning! Config-file not existed! creat it....");
            ClassPathResource classPathResource = new ClassPathResource("template/config.properties");
            try {
                InputStream inputStream = classPathResource.getInputStream();
                File file = new File("config" + File.separator + "config.properties");
                FileUtil.writeFromStream(inputStream, file);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
