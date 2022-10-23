package com.teachmeskills.service;

import com.teachmeskills.model.User;
import com.teachmeskills.repository.UserRepository;
import lombok.extern.log4j.Log4j2;

import java.util.List;
import java.util.Optional;

@Log4j2
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> findUsers() {
        return userRepository.findUsers();
    }

    public void createUser(String login, String password) {
        log.info("Creating user with login {}", login);
        if (userRepository.getUser(login).isPresent()) {
            throw new RuntimeException("User already exists");
        }
        if (password.isEmpty()) {
            log.info("User password is empty");
            throw new RuntimeException("User password is empty");
        }
        log.info("User with login {} successfully create", login);
        userRepository.createUser(login, password);
    }

    public Optional<User> getUser(String login) {
        return userRepository.getUser(login);
    }

    public List<User> findUsersStartWith(String login) {
        return userRepository.findUsersStartWith(login);
    }
}
