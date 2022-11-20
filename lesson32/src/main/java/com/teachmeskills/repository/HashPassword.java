package com.teachmeskills.repository;

import org.springframework.stereotype.Repository;

@Repository
public interface HashPassword {

    String hashingPassword(String password);
    boolean validatePassword(String password, String hashedPassword);
}
