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

    public boolean validate(String login, String password) {
        return userRepository.findUsers().stream()
                .anyMatch(user -> user.getLogin().equals(login) && user.getPassword().equals(password));
    }

    public User getUserByLogin(String login) {
        Optional<User> user = userRepository.getUser(login);
        return user.orElseThrow(() -> new RuntimeException("User not found"));
    }
}
