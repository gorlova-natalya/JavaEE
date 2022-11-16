package com.teachmeskills.properties;

public class HashProperties {

    private final String secret;

    public HashProperties(String secret) {
        this.secret = secret;
    }

    public String getSecret() {
        return secret;
    }
}
