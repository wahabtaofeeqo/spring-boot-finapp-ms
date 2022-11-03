package com.taoltech.finapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@EnableEurekaServer
@SpringBootApplication
public class FinappRegistryServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(FinappRegistryServerApplication.class, args);
    }

}
