package com.teachmeskills.repository;

import com.teachmeskills.model.Message;

import java.util.List;

public interface MessageRepository {

    void createMessage(long messageFrom, long messageTo, String messageText);

    List<Message> getMessages(long messageFrom, long messageTo);

    void deleteMessages(long requestFrom, long requestTo);
}
