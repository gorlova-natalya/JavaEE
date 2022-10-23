package com.teachmeskills.repository;

import com.teachmeskills.model.User;
import lombok.extern.slf4j.Slf4j;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.sql.PreparedStatement;

@Slf4j
public class JdbcUserRepository implements UserRepository {

    private final Connection connection;

    public JdbcUserRepository(Connection connection) {
        this.connection = connection;
    }

    @Override
    public List<User> findUsers() {
        try (Statement statement = connection.createStatement()) {
            String sql = "select login, password from users";
            ResultSet rs = statement.executeQuery(sql);
            final List<User> users = new ArrayList<>();
            while (rs.next()) {
                users.add(buildUser(rs));
            }
            return users;
        } catch (SQLException e) {
            log.error("Users not found");
            throw new RuntimeException(e);
        }
    }

    @Override
    public Optional<User> getUser(String login) {
        try (PreparedStatement statement = connection.prepareStatement(
                "select login, password from users where login = ?")) {
            statement.setString(1, login);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                return Optional.of(buildUser(rs));
            }
            return Optional.empty();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void createUser(String login, String password) {
        try {
            PreparedStatement statement = connection.prepareStatement(
                    "insert into users (login, password) values (?, ?)");
            statement.setString(1, login);
            statement.setString(2, password);
            statement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private User buildUser(ResultSet rs) throws SQLException {
        return new User(
                rs.getString("login"),
                rs.getString("password")
        );
    }

    public List<User> findUsersStartWith(String login) {
        try (PreparedStatement statement = connection.prepareStatement(
                "select login, password from users where login like concat('%', ?, '%')")) {
            statement.setString(1, login);
            ResultSet rs = statement.executeQuery();
            final List<User> users = new ArrayList<>();
            while (rs.next()) {
                users.add(buildUser(rs));
            }
            return users;
        } catch (SQLException e) {
            log.info("Users not found");
            throw new RuntimeException(e);
        }
    }
}
