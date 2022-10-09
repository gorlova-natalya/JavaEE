package com.example24.service;

import com.example24.model.User;
import com.example24.repository.UserRepository;

import java.util.List;

public class UserService {

  private final UserRepository userRepository;

  public UserService(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  public List<User> findUsers() {
    return userRepository.findUsers();
  }

}
