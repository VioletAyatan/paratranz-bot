package org.paratranz.bot;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringBootVersion;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@Slf4j
@EnableScheduling
@SpringBootApplication
public class TranzaiApplication {

    public static void main(String[] args) {
        log.info("Starting Spring-Boot. Version {}", SpringBootVersion.getVersion());
        SpringApplication.run(TranzaiApplication.class, args);
    }

}
