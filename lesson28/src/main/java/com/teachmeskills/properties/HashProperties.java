package com.teachmeskills.properties;

import lombok.Getter;
import lombok.SneakyThrows;

import java.util.Properties;

@Getter
public class HashProperties {

    private final String secret;

    @SneakyThrows
    public HashProperties() {
        Properties properties = new Properties();
        properties.load(this.getClass().getResourceAsStream("/app.properties"));
        this.secret = properties.getProperty("hashSecret");
    }
}
