package com.example.messages.service;

import com.example.messages.dto.CreateMessageDto;
import com.example.messages.model.Message;
import com.example.messages.repository.MessageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MessageService {
    private final MessageRepository messageRepository;

    public void createMessage(final CreateMessageDto createMessageDto) {
        final Message message = Message.builder().messageFrom(createMessageDto.getMessageFrom())
                .messageTo(createMessageDto.getMessageTo()).messageText(createMessageDto.getMessageText())
                .createdAt(LocalDateTime.now()).build();
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
