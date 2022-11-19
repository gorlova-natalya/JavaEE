package com.teachmeskills.session;

import lombok.Data;

@Data
public class AuthContext {

    private boolean authorized;
    private long loggedInUserId;
    private String username;
}
