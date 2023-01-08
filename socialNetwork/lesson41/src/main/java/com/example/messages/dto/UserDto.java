package com.example.messages.dto;

import lombok.Data;
import javax.validation.constraints.NotEmpty;

@Data
public class UserDto {

    @NotEmpty(message = "Login must not be empty")
    private String login;

    @NotEmpty(message = "Password must not be empty")
    private String password;
}
