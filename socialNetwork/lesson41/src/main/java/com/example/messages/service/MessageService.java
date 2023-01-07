package com.example.messages.service;

import com.example.messages.dto.MessageDto;
import com.example.messages.repository.MessageRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MessageService {
    private final MessageRepository messageRepository;

    public MessageService(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

    public void createMessage(Long messageFromUser, Long messageToUser, String messageText) {
        messageRepository.saveMessage(messageFromUser, messageToUser, messageText);
    }

    public List<MessageDto> getMessages(long messageFrom, long messageTo) {
        return messageRepository.getMessages(messageFrom, messageTo);
    }

    public void deleteDialog(long requestFrom, long requestTo) {
        messageRepository.deleteByMessageFromAndMessageTo(requestFrom, requestTo);
        messageRepository.deleteByMessageFromAndMessageTo(requestTo, requestFrom);
    }
}
