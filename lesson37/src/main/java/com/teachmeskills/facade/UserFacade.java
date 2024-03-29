package com.teachmeskills.facade;

import com.teachmeskills.model.User;
import com.teachmeskills.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@AllArgsConstructor
public class UserFacade {

    private final UserService userService;

    public List<User> findUsers() {
        return userService.findUsers();
    }

    public boolean validatePassword(String password, String hashedPassword) {
        return userService.validatePassword(password, hashedPassword);
    }

    public User createUser(String login, String password) {
        return userService.createUser(login, password);
    }

    public Optional<User> getUser(String login) {
        return userService.getUser(login);
    }

    public List<User> findUsersStartWith(String login) {
        return userService.findUsersStartWith(login);
    }

    public Optional<User> getUserById(long userId) {
        return userService.getUserById(userId);
    }

    public Page<User> findPaginatedUsers(int pageNo, int pageSize) {
        return userService.findPaginatedUsers(pageNo, pageSize);
    }

    public User getUserByLoginAndPassword(String login, String password) {
        return userService.getUserByLoginAndPassword(login, password);
    }

    public User getUserByLogin(String login) {
        return userService.getUserByLogin(login);
    }
}
