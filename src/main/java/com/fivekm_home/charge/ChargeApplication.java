package com.fivekm_home.charge;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class ChargeApplication {

    public static void main(String[] args) {
        SpringApplication.run(ChargeApplication.class, args);
    }

}
