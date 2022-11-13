package com.teachmeskills.service;

import com.teachmeskills.model.Message;
import com.teachmeskills.repository.MessageRepository;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;


public class MessageService {

    private final MessageRepository messageRepository;

    public MessageService(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

    public void createMessage(long messageFrom, long messageTo, String messageText) {
        messageRepository.createMessage(messageFrom, messageTo, messageText);
    }

    public List<Message> getMessages(long messageFrom, long messageTo) {
        return messageRepository.getMessages(messageFrom, messageTo).stream()
                .sorted(Comparator.comparing(Message::getCreatedAt).reversed())
                .collect(Collectors.toList());
    }

    public void deleteDialog(long requestFrom, long requestTo) {
        messageRepository.deleteDialog(requestFrom, requestTo);
    }
}
