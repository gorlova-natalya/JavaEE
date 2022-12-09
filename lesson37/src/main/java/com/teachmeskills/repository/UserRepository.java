package com.teachmeskills.repository;

import com.teachmeskills.model.User;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
@Transactional
public interface UserRepository extends PagingAndSortingRepository<User, Long> {

    @Override
    List<User> findAll();

    Optional<User> findByLogin(String login);

    List<User> findByLoginStartsWith(String login);

    Optional<User> getUserById(long userId);

    List<User> findUsersByIdIn(List<Long> usersId);
}
