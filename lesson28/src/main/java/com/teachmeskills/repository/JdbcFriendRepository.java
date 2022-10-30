package com.teachmeskills.repository;

import com.teachmeskills.model.Friend;
import lombok.extern.slf4j.Slf4j;

import java.sql.Connection;
import java.sql.PreparedStatement;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Slf4j
public class JdbcFriendRepository implements FriendRepository {
    private final Connection connection;

    public JdbcFriendRepository(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void addFriend(long requestFrom, long requestTo) {
        try (PreparedStatement statement = connection.prepareStatement(
                "insert into friends (user_id, friend_id) values (?, ?)")) {
            statement.setLong(1, requestFrom);
            statement.setLong(2, requestTo);
            statement.execute();
            statement.setLong(1, requestTo);
            statement.setLong(2, requestFrom);
            statement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Optional<Friend> getFriend(long requestFrom, long requestTo) {
        try (PreparedStatement statement = connection.prepareStatement(
                "select user_id, friend_id from friends where user_id = ? and friend_id = ?")) {
            statement.setLong(1, requestFrom);
            statement.setLong(2, requestTo);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                return Optional.of(buildFriend(rs));
            }
            return Optional.empty();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private Friend buildFriend(ResultSet rs) throws SQLException {
        return new Friend(
                rs.getLong("user_id"),
                rs.getLong("friend_id")
        );
    }

    @Override
    public List<Friend> getUserFriends(long userId) {
        try (PreparedStatement statement = connection.prepareStatement(
                "select user_id, friend_id from friends where user_id = ?")) {
            statement.setLong(1, userId);
            ResultSet rs = statement.executeQuery();
            final List<Friend> friends = new ArrayList<>();
            while (rs.next()) {
                friends.add(buildFriend(rs));
            }
            return friends;
        } catch (SQLException e) {
            log.info("Friends not found");
            throw new RuntimeException(e);
        }
    }



}
