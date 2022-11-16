package com.teachmeskills.repository;

import com.teachmeskills.model.User;
import lombok.extern.slf4j.Slf4j;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.sql.PreparedStatement;
import java.util.stream.Collectors;

@Slf4j
public class JdbcUserRepository implements UserRepository {

    private final Connection connection;
    private static final String FIND_USERS_SQL = "select id, login, password from users";
    private static final String GET_USER_BY_LOGIN_SQL = "select id, login, password from users where login = ?";
    private static final String CREATE_USER_SQL = "insert into users (login, password) values (?, ?)";
    private static final String FIND_USERS_START_WITH_SQL =
            "select id, login, password from users where login like concat('%', ?, '%')";
    private static final String GET_USER_BY_ID_SQL = "select id, login, password from users where id = ?";


    public JdbcUserRepository(Connection connection) {
        this.connection = connection;
    }


    @Override
    public List<User> findUsers() {
        try (Statement statement = connection.createStatement()) {
            ResultSet rs = statement.executeQuery(FIND_USERS_SQL);
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
    public Optional<User> getUserByLogin(String login) {
        try (PreparedStatement statement = connection.prepareStatement(GET_USER_BY_LOGIN_SQL)) {
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
        try (PreparedStatement statement = connection.prepareStatement(CREATE_USER_SQL)) {
            statement.setString(1, login);
            statement.setString(2, password);
            statement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private User buildUser(ResultSet rs) throws SQLException {
        return new User(
                rs.getLong("id"),
                rs.getString("login"),
                rs.getString("password")
        );
    }

    public List<User> findUsersStartWith(String login) {
        try (PreparedStatement statement = connection.prepareStatement(FIND_USERS_START_WITH_SQL)) {
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

    @Override
    public Optional<User> getUserById(long userId) {
        try (PreparedStatement statement = connection.prepareStatement(GET_USER_BY_ID_SQL)) {
            statement.setLong(1, userId);
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
    public List<User> getUsersById(List<Long> usersId) {
        try (PreparedStatement statement = connection.prepareStatement(
                String.format("select id, login, password from users where id in (%s)",
                        usersId.stream().map(userId -> "?").collect(Collectors.joining(", "))))) {
            for (int i = 0; i < usersId.size(); i++) {
                statement.setLong(i + 1, usersId.get(i));
            }
            if (usersId.isEmpty()) {
                return Collections.emptyList();
            }
            ResultSet rs = statement.executeQuery();
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
}
