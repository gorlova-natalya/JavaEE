package com.teachmeskills.repository;

import com.teachmeskills.model.Message;

import java.util.List;

public interface MessageRepository {

    void createMessage(long messageFrom, long messageTo, String messageText);

    List<Message> getDialogMessages(long messageFrom, long messageTo);

    void deleteDialog(long requestFrom, long requestTo);
}
