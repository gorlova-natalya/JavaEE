package com.teachmeskills.model;

import lombok.Value;
import lombok.experimental.NonFinal;

@Value
@NonFinal
public class User {

    long id;
    String login;
    String password;
}
