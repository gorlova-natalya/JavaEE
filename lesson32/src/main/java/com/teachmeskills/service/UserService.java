package com.teachmeskills.service;

import com.teachmeskills.model.User;
import com.teachmeskills.repository.HashPassword;
import com.teachmeskills.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class UserService {

    private final UserRepository userRepository;
    private final HashPassword hashPassword;

    public UserService(UserRepository userRepository, HashPassword hashPassword) {
        this.userRepository = userRepository;
        this.hashPassword = hashPassword;
    }

    public List<User> findUsers() {
        return userRepository.findUsers();
    }

    public String hashingPassword(String password) {
        return hashPassword.hashingPassword(password);
    }

    public boolean validatePassword(String password, String hashedPassword) {
        return hashPassword.validatePassword(password, hashedPassword);
    }

    public void createUser(String login, String password) {
        log.info("Creating user with login {}", login);
        if (userRepository.getUserByLogin(login).isPresent()) {
            throw new RuntimeException("User already exists");
        }
        if (password.isEmpty()) {
            log.info("User password is empty");
            throw new RuntimeException("User password is empty");
        }
        userRepository.createUser(login, password);
        log.info("User with login {} successfully create", login);
    }

    public Optional<User> getUser(String login) {
        return userRepository.getUserByLogin(login);
    }

    public Optional<User> getUserById(long userId) {
        return userRepository.getUserById(userId);
    }

    public List<User> findUsersStartWith(String login) {
        return userRepository.findUsersStartWith(login);
    }

    public List<User> getUsersById(List<Long> usersId) {
        return userRepository.getUsersById(usersId);
    }
}
