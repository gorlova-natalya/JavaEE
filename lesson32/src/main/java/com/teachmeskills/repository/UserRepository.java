package com.teachmeskills.repository;

import com.teachmeskills.model.User;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository {

    List<User> findUsers();

    Optional<User> getUserByLogin(String login);

    void createUser(String login, String password);

    List<User> findUsersStartWith(String login);

    Optional<User> getUserById(long userId);

    List<User> getUsersById(List<Long> usersId);
}
