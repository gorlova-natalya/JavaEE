package com.teachmeskills.session;

import lombok.Data;

@Data
public class AuthContext {

    private Long loggedInUserId;
    private String username;
}
