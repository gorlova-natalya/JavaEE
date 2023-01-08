package com.teachmeskills.service;

import com.teachmeskills.model.Message;
import com.teachmeskills.model.User;
import com.teachmeskills.repository.MessageRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class MessageService {

    private final MessageRepository messageRepository;

    public MessageService(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

    public void createMessage(User messageFromUser, User messageToUser, String messageText) {
        final Message message = Message.builder().messageFrom(messageFromUser)
                .messageTo(messageToUser).messageText(messageText).createdAt(LocalDateTime.now()).build();
        messageRepository.save(message);
    }

    public List<Message> getMessages(long messageFrom, long messageTo) {
        return Stream.of(messageRepository.findMessageByMessageFromIdAndMessageToId(messageFrom, messageTo),
                        messageRepository.findMessageByMessageFromIdAndMessageToId(messageTo, messageFrom))
                .flatMap(Collection::stream)
                .sorted(Comparator.comparing(Message::getCreatedAt).reversed())
                .collect(Collectors.toList());
    }

    public void deleteDialog(long requestFrom, long requestTo) {
        messageRepository.deleteByMessageFromIdAndMessageToId(requestFrom, requestTo);
        messageRepository.deleteByMessageFromIdAndMessageToId(requestTo, requestFrom);
    }
}
