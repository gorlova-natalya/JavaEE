package com.teachmeskills.fasade;

import com.teachmeskills.model.Message;
import com.teachmeskills.model.User;
import com.teachmeskills.service.MessageService;
import com.teachmeskills.service.UserService;
import lombok.AllArgsConstructor;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@AllArgsConstructor
public class MessageFacade {

    private final MessageService messageService;
    private final UserService userService;

    public void createMessage(long messageFrom, long messageTo, String messageText) {
        messageService.createMessage(messageFrom, messageTo, messageText);
    }

    public List<Message> getMessages(long messageFrom, long messageTo) {
        return messageService.getMessages(messageFrom, messageTo).stream()
                .sorted(Comparator.comparing(Message::getCreatedAt).reversed())
                .collect(Collectors.toList());
    }

    public Optional<User> getUserById(long userId) {
        return userService.getUserById(userId);
    }
}
