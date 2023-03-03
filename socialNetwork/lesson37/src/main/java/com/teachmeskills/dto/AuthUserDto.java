package com.teachmeskills.dto;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class AuthUserDto {

    @NotEmpty(message = "Login cannot be empty")
    private String login;
    @NotEmpty(message = "Password cannot be empty")
    private String password;
    private String confirmationPassword;
}
