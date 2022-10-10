package com.example24.model;

public class User {

  private String name;
  private String password;

  public User(String login, String password) {
    this.name = login;
    this.password = password;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

}
