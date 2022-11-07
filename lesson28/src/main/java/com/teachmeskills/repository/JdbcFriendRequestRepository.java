package com.teachmeskills.repository;

import com.teachmeskills.model.FriendRequest;
import lombok.extern.slf4j.Slf4j;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
public class JdbcFriendRequestRepository implements FriendRequestRepository {

    private final Connection connection;

    public JdbcFriendRequestRepository(Connection connection) {
        this.connection = connection;
    }

    @Override
    public Optional<FriendRequest> getRequest(long requestFrom, long requestTo) {
        try (PreparedStatement statement = connection.prepareStatement(
                "select inviter_id, user_id from invitations where inviter_id = ? and user_id = ?")) {
            statement.setLong(1, requestFrom);
            statement.setLong(2, requestTo);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                return Optional.of(buildRequest(rs));
            }
            return Optional.empty();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private FriendRequest buildRequest(ResultSet rs) throws SQLException {
        return new FriendRequest(
                rs.getLong("inviter_id"),
                rs.getLong("user_id")
        );
    }

    @Override
    public void createRequest(long requestFrom, long requestTo) {
        try (PreparedStatement statement = connection.prepareStatement(
                "insert into invitations (inviter_id, user_id) values (?, ?)")) {
            statement.setLong(1, requestFrom);
            statement.setLong(2, requestTo);
            log.info("Creating request {}:{}", requestFrom, requestTo);
            statement.execute();
        } catch (SQLException e) {
            log.error("Create request fail");
            throw new RuntimeException(e);
        }
    }

    @Override
    public void deleteRequest(long requestFrom, long requestTo) {
        try (PreparedStatement statement = connection.prepareStatement(
                "delete from invitations where inviter_id = ? and user_id = ?")) {
            statement.setLong(1, requestFrom);
            statement.setLong(2, requestTo);
            statement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<FriendRequest> getIncomingRequests(long userId) {
        try (PreparedStatement statement = connection.prepareStatement(
                "select inviter_id, user_id from invitations where user_id = ?")) {
            statement.setLong(1, userId);
            ResultSet rs = statement.executeQuery();
            final List<FriendRequest> friendRequests = new ArrayList<>();
            while (rs.next()) {
                friendRequests.add(buildRequest(rs));
            }
            return friendRequests;
        } catch (SQLException e) {
            log.error("Requests for {} not found", userId, e);
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<FriendRequest> getOutcomingRequests(long inviterId) {
        try (PreparedStatement statement = connection.prepareStatement(
                "select inviter_id, user_id from invitations where inviter_id = ?")) {
            statement.setLong(1, inviterId);
            ResultSet rs = statement.executeQuery();
            final List<FriendRequest> friendRequests = new ArrayList<>();
            while (rs.next()) {
                friendRequests.add(buildRequest(rs));
            }
            return friendRequests;
        } catch (SQLException e) {
            log.error("Requests from {} not found", inviterId, e);
            throw new RuntimeException(e);
        }
    }
}
