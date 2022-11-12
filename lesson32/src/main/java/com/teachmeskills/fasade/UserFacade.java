package com.teachmeskills.fasade;

import com.teachmeskills.model.User;
import com.teachmeskills.service.UserService;
import lombok.AllArgsConstructor;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
public class UserFacade {

    private final UserService userService;

    public List<User> findUsers() {
        return userService.findUsers();
    }


    public String hashingPassword(String password) {
        return userService.hashingPassword(password);
    }

    public boolean validatePassword(String password, String hashedPassword) {
        return userService.validatePassword(password, hashedPassword);
    }

    public void createUser(String login, String password) {
        userService.createUser(login, password);
    }

    public Optional<User> getUser(String login) {
        return userService.getUser(login);
    }

    public List<User> findUsersStartWith(String login) {
        return userService.findUsersStartWith(login);
    }
}
