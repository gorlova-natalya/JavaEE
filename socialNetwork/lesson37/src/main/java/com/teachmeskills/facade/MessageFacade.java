package com.teachmeskills.facade;

import com.teachmeskills.dto.CreateMessageDto;
import com.teachmeskills.dto.GetMessageDto;
import com.teachmeskills.dto.MessageDto;
import com.teachmeskills.model.User;
import com.teachmeskills.service.MessageService;
import com.teachmeskills.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@AllArgsConstructor
public class MessageFacade {

    private final MessageService messageService;
    private final UserService userService;

    public void createMessage(Long messageFrom, Long messageTo, String messageText) {
        User messageFromUser = userService.getUserById(messageFrom).orElseThrow();
        User messageToUser = userService.getUserById(messageTo).orElseThrow();
        final CreateMessageDto createMessageDto = CreateMessageDto.builder().messageFrom(messageFromUser)
                        .messageTo(messageToUser).messageText(messageText).build();
        messageService.createMessage(createMessageDto);
    }

    public List<MessageDto> getMessages(final GetMessageDto getMessageDto) {
        return messageService.getMessages(getMessageDto);
    }
}
