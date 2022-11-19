package com.teachmeskills.dto;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class UserDto {

    @NotEmpty(message = "Login must not be empty")
    private final String login;

    @NotEmpty(message = "Password must not be empty")
    private final String password;

    private final String validPassword;
}
