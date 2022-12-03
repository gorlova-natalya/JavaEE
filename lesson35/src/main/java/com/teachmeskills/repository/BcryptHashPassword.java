package com.teachmeskills.repository;

import at.favre.lib.crypto.bcrypt.BCrypt;
import com.teachmeskills.properties.HashProperties;
import com.teachmeskills.service.HashPassword;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.nio.charset.StandardCharsets;
import java.security.SecureRandom;

@Repository
@RequiredArgsConstructor
public class BcryptHashPassword implements HashPassword {

    private final HashProperties hashProperties;

    @Override
    public String hashingPassword(String password) {
        final BCrypt.Hasher hasher =
                BCrypt.with(new SecureRandom(hashProperties.getSecret().getBytes(StandardCharsets.UTF_8)));
        return hasher.hashToString(hashProperties.getComplexity(), password.toCharArray());
    }

    @Override
    public boolean validatePassword(String password, String hashedPassword) {
        BCrypt.Result verify = BCrypt.verifyer().
                verify(password.getBytes(StandardCharsets.UTF_8), hashedPassword.getBytes(StandardCharsets.UTF_8));
        return verify.verified;
    }
}
