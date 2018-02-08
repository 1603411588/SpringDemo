package com.liuyi;

import com.liuyi.schedulingtasks.SchedulingtasksConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Import;
import org.springframework.data.redis.core.StringRedisTemplate;

import java.util.concurrent.CountDownLatch;

@SpringBootApplication
public class SbootApplication {

    public static void main(String[] args) throws InterruptedException {
        SpringApplication.run(SbootApplication.class, args);
    }
}
