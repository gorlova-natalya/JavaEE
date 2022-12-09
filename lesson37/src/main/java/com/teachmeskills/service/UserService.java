package com.teachmeskills.service;

import com.teachmeskills.model.User;
import com.teachmeskills.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final HashPassword hashPassword;

    public List<User> findUsers() {
        return userRepository.findAll();
    }

    public Page<User> findPaginatedUsers(int pageNo, int pageSize) {
        Pageable pageable = PageRequest.of(pageNo - 1, pageSize);
        return userRepository.findAll(pageable);
    }

    private String hashingPassword(String password) {
        return hashPassword.hashingPassword(password);
    }

    public boolean validatePassword(String password, String hashedPassword) {
        return hashPassword.validatePassword(password, hashedPassword);
    }

    public User createUser(String login, String password) {
        log.info("Creating user with login {}", login);
        if (userRepository.findByLogin(login).isPresent()) {
            throw new RuntimeException("User already exists");
        }
        if (password.isEmpty()) {
            log.info("User password is empty");
            throw new RuntimeException("User password is empty");
        }
        final User user = new User(login, hashingPassword(password));
        userRepository.save(user);
        log.info("User with login {} successfully create", login);
        return user;
    }

    public Optional<User> getUser(String login) {
        return userRepository.findByLogin(login);
    }

    public Optional<User> getUserById(long userId) {
        return userRepository.getUserById(userId);
    }

    public List<User> findUsersStartWith(String login) {
        return userRepository.findByLoginStartsWith(login);
    }

    public List<User> getUsersById(List<Long> usersId) {
        return userRepository.findUsersByIdIn(usersId);
    }
}
