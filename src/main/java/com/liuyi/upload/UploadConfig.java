package com.liuyi.upload;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;

@EnableConfigurationProperties(StorageProperties.class)
@Configuration
public class UploadConfig {

    @Bean("initFileUploadRunner")
    @Order(3)
    public CommandLineRunner init(StorageService storageService) {
        return args -> {
            storageService.deleteAll();
            storageService.init();
        };
    }
}
