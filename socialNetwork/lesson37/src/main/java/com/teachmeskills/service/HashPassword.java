package com.teachmeskills.service;

public interface HashPassword {

    String hashingPassword(String password);

    boolean validatePassword(String password, String hashedPassword);
}
