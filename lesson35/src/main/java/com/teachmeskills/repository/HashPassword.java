package com.teachmeskills.repository;

public interface HashPassword {

    String hashingPassword(String password);

    boolean validatePassword(String password, String hashedPassword);
}
