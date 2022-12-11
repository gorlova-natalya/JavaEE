package com.teachmeskills.dto;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class CreateUserDto {

    @NotEmpty
    private String login;
    @NotEmpty
    private String password;
}
