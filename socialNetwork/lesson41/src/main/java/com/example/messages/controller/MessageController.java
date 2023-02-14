package com.example.messages.controller;

import com.example.messages.converter.MessageConverter;
import com.example.messages.dto.CreateMessageDto;
import com.example.messages.dto.GetMessageDto;
import com.example.messages.dto.MessageDto;
import com.example.messages.service.MessageService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/v1/messages")
@RequiredArgsConstructor
public class MessageController {

    private final MessageService messageService;

    private final MessageConverter messageConverter;

    @RequestMapping(method = RequestMethod.GET, value = "/all")
    protected List<MessageDto> getMessages(@Valid @RequestBody final GetMessageDto getMessageDto) {
        return messageConverter.toDto(messageService.getMessages(getMessageDto.getMessageFrom(), getMessageDto.getMessageTo()));
    }

    @PostMapping(path = "/send", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    protected void sendMessage(@Valid @RequestBody final CreateMessageDto createMessageDto) {
        messageService.createMessage(createMessageDto);
    }

    @PostMapping(path = "/delete")
    protected void deleteMessage(@Valid @RequestBody final GetMessageDto getMessageDto) {
        messageService.deleteDialog(getMessageDto.getMessageFrom(), getMessageDto.getMessageTo());
    }
}

