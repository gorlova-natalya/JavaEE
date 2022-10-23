package com.teachmeskills.service;

import com.teachmeskills.model.User;
import com.teachmeskills.repository.UserRepository;

import java.util.List;
import java.util.Optional;


public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> findUsers() {
        return userRepository.findUsers();
    }

    public void createUser(String login, String password) {
        if (userRepository.getUser(login).isPresent()) {
            throw new RuntimeException("User already exists");
        }
        userRepository.createUser(login, password);
    }

    public Optional<User> getUser(String login) {
        return userRepository.getUser(login);
    }

    public List<User> findUsersStartWith(String login) {
        return userRepository.findUsersStartWith(login);
    }
}
