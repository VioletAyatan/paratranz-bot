package com.para.tranzai;

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
        SpringApplication.run(TranzaiApplication.class, args);
        log.info("Started Spring Boot. Version {}", SpringBootVersion.getVersion());
    }

}
