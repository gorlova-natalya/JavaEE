package com.teachmeskills;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication

@EnableFeignClients(basePackages = "com.teachmeskills.client")
public class NetworkApplicationExample {
    public static void main(final String[] args) {
        SpringApplication.run(NetworkApplicationExample.class, args);
    }
}
