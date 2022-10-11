package com.teachmeskills.repository;

import com.teachmeskills.model.User;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class JdbcUserRepository implements UserRepository {

  private final Connection connection;

  public JdbcUserRepository(Connection connection) {
    this.connection = connection;
  }

  @Override
  public List<User> findUsers() {
    try {
      Statement statement = connection.createStatement();
      String sql = "select login, password from users";
      ResultSet rs = statement.executeQuery(sql);
      final List<User> users = new ArrayList<>();
      while (rs.next()) {
        final User user = new User(rs.getString("login"), rs.getString("password"));;
        users.add(user);
      }

      return users;
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
  }
}
