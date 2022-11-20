package com.teachmeskills.repository;

import com.teachmeskills.model.Message;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface MessageRepository {

    void createMessage(long messageFrom, long messageTo, String messageText);

    List<Message> getMessages(long messageFrom, long messageTo);

    void deleteMessages(long requestFrom, long requestTo);
}
