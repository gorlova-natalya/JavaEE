package com.teachmeskills.facade;

import com.teachmeskills.model.Message;
import com.teachmeskills.model.User;
import com.teachmeskills.service.MessageService;
import com.teachmeskills.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Component
@AllArgsConstructor
public class MessageFacade {

    private final MessageService messageService;
    private final UserService userService;

    public void createMessage(long messageFrom, long messageTo, String messageText) {
        User messageFromUser = userService.getUserById(messageFrom).orElseThrow();
        User messageToUser = userService.getUserById(messageTo).orElseThrow();
        messageService.createMessage(messageFromUser, messageToUser, messageText);
    }

    public List<Message> getMessages(long messageFrom, long messageTo) {
        return messageService.getMessages(messageFrom, messageTo).stream()
                .sorted(Comparator.comparing(Message::getCreatedAt).reversed())
                .collect(Collectors.toList());
    }
}
