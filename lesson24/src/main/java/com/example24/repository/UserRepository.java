package com.example24.repository;

import com.example24.model.User;

import java.util.List;

public interface UserRepository {

  List<User> findUsers();

}
