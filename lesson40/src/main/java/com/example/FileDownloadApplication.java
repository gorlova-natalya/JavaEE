package com.example;

import com.example.properties.FileProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@EnableConfigurationProperties({FileProperties.class})
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class })
public class FileDownloadApplication {
    public static void main(String[] args) {
        SpringApplication.run(FileDownloadApplication.class, args);
    }
}
