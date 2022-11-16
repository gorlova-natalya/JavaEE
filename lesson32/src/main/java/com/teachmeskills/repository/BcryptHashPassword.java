package com.teachmeskills.repository;

import at.favre.lib.crypto.bcrypt.BCrypt;
import com.teachmeskills.properties.HashProperties;
import lombok.RequiredArgsConstructor;

import java.nio.charset.StandardCharsets;
import java.security.SecureRandom;


@RequiredArgsConstructor
public class BcryptHashPassword implements HashPassword {

    private final HashProperties hashProperties;

    private static final int COMPLEXITY = 12;

    @Override
    public String hashingPassword(String password) {
        final BCrypt.Hasher hasher =
                BCrypt.with(new SecureRandom(hashProperties.getSecret().getBytes(StandardCharsets.UTF_8)));
        return hasher.hashToString(COMPLEXITY, password.toCharArray());
    }

    @Override
    public boolean validatePassword(String password, String hashedPassword) {
        BCrypt.Result verify = BCrypt.verifyer().
                verify(password.getBytes(StandardCharsets.UTF_8), hashedPassword.getBytes(StandardCharsets.UTF_8));
        return verify.verified;
    }
}
