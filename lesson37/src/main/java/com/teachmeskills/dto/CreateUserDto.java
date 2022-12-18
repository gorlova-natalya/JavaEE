package com.teachmeskills.dto;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.validation.constraints.NotEmpty;

@Data
@RequiredArgsConstructor
public class CreateUserDto {

    @NotEmpty
    private String login;
    @NotEmpty
    private String password;
}
