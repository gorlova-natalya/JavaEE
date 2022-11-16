package com.teachmeskills.facade;

import com.teachmeskills.model.Message;
import com.teachmeskills.service.MessageService;
import lombok.AllArgsConstructor;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
public class MessageFacade {

    private final MessageService messageService;

    public void createMessage(long messageFrom, long messageTo, String messageText) {
        messageService.createMessage(messageFrom, messageTo, messageText);
    }

    public List<Message> getMessages(long messageFrom, long messageTo) {
        return messageService.getMessages(messageFrom, messageTo).stream()
                .sorted(Comparator.comparing(Message::getCreatedAt).reversed())
                .collect(Collectors.toList());
    }
}
