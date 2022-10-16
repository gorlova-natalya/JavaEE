package com.teachmeskills.repository;

import com.teachmeskills.model.User;

import java.util.List;
import java.util.Optional;

public interface UserRepository {

    List<User> findUsers();

    Optional<User> getUser(String login);

    void createUser(String login, String password);
}
