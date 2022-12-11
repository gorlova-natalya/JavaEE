package com.teachmeskills.config.service;

import com.teachmeskills.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
@RequiredArgsConstructor
public class AuthService implements UserDetailsService {

    private final UserService userService;

    @Override
    public UserDetails loadUserByUsername(final String login) throws UsernameNotFoundException {
        final com.teachmeskills.model.User user = userService.getUserByLogin(login);
        return new User(user.getLogin(), user.getPassword(), Collections.emptyList());
    }
}
