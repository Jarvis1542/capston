package com.fivekm_home.charge;

import com.fivekm_home.charge.config.StorageConfig;
import com.fivekm_home.charge.service.StorageService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableConfigurationProperties(StorageConfig.class)
@EnableJpaAuditing
@SpringBootApplication
public class ChargeApplication {

    public static void main(String[] args) {
        SpringApplication.run(ChargeApplication.class, args);
    }

    @Bean
    CommandLineRunner init(StorageService storageService) {
        return (args) -> {
            storageService.deleteAll();
            storageService.init();
        };
    }
}
