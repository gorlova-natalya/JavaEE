package com.example.messages.controller;

import com.example.messages.converter.MessageConverter;
import com.example.messages.dto.MessageDto;
import com.example.messages.service.MessageService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/message")
@RequiredArgsConstructor
public class MessageController {

    private final MessageService messageService;

    private final MessageConverter messageConverter;

    @GetMapping("/{friendId}")
    protected List<MessageDto> getMessages(@PathVariable("friendId") Long messageTo,
                                           @RequestParam Long messageFrom) {
        return messageConverter.toDto(messageService.getMessages(messageFrom, messageTo));
    }

    @PostMapping(path = "/send/{friendId}", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    protected void sendMessage(MessageDto dto,
                               @PathVariable("friendId") Long messageTo,
                               @RequestParam Long messageFrom,
                               @RequestParam String message) {
        messageService.createMessage(messageFrom, messageTo, message);
    }

    @PostMapping(path = "/message/delete/{friendId}")
    protected void deleteMessage(@PathVariable int friendId,
                                 @RequestParam int userId) {
        messageService.deleteDialog(userId, friendId);
    }
}

