package com.teachmeskills.dto;

import lombok.RequiredArgsConstructor;
import lombok.Value;

@Value
@RequiredArgsConstructor
public class UserDtoRest {
    long id;
    String login;
    String password;
}
