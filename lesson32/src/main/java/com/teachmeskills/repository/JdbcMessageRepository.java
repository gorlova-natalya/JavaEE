package com.teachmeskills.repository;

import com.teachmeskills.model.Message;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
@Slf4j
public class JdbcMessageRepository implements MessageRepository {

    private final Connection connection;
    private static final String CREATE_MESSAGES_SQL =
            "insert into messages (user_id, friend_id, message) values (?, ?, ?)";
    private static final String GET_MESSAGES_SQL =
            "select * from messages where user_id = ? and friend_id = ? order by created_at DESC ";
    private static final String DELETE_MESSAGES_SQL = "delete from messages where user_id = ? and friend_id = ?";

    public JdbcMessageRepository(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void createMessage(long messageFrom, long messageTo, String messageText) {
        try (PreparedStatement statement = connection.prepareStatement(CREATE_MESSAGES_SQL)) {
            statement.setLong(1, messageFrom);
            statement.setLong(2, messageTo);
            statement.setString(3, messageText);
            log.info("Creating message {}:{}", messageFrom, messageTo);
            statement.execute();
        } catch (SQLException e) {
            log.info("Create message fail");
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Message> getMessages(long messageFrom, long messageTo) {
        try (PreparedStatement statement = connection.prepareStatement(GET_MESSAGES_SQL)) {
            final List<Message> messages = new ArrayList<>();
            statement.setLong(1, messageFrom);
            statement.setLong(2, messageTo);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                messages.add(buildMessage(rs));
            }
            statement.setLong(1, messageTo);
            statement.setLong(2, messageFrom);
            ResultSet rs1 = statement.executeQuery();
            while (rs1.next()) {
                messages.add(buildMessage(rs1));
            }
            return messages;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private Message buildMessage(ResultSet rs) throws SQLException {
        return new Message(
                rs.getLong("message_id"),
                rs.getLong("user_id"),
                rs.getLong("friend_id"),
                rs.getString("message"),
                rs.getTimestamp("created_at").toLocalDateTime()
        );
    }

    @Override
    public void deleteMessages(long requestFrom, long requestTo) {
        try (PreparedStatement statement = connection.prepareStatement(DELETE_MESSAGES_SQL)) {
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
}
