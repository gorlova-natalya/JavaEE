package com.example.messages.service;

import com.example.messages.model.Message;
import com.example.messages.repository.MessageRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class MessageService {
    private final MessageRepository messageRepository;

    public MessageService(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

    public void createMessage(Long messageFromUser, Long messageToUser, String messageText) {
        final Message message = Message.builder().messageFrom(messageFromUser)
                .messageTo(messageToUser).messageText(messageText).createdAt(LocalDateTime.now()).build();
        messageRepository.save(message);
    }

    public List<Message> getMessages(long messageFrom, long messageTo) {
        return messageRepository.findMessageDtoByMessageFromAndMessageTo(messageFrom, messageTo);
    }

    public void deleteDialog(long requestFrom, long requestTo) {
        messageRepository.deleteByMessageFromAndMessageTo(requestFrom, requestTo);
        messageRepository.deleteByMessageFromAndMessageTo(requestTo, requestFrom);
    }
}
