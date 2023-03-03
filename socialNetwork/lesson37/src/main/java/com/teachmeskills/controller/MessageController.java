package com.teachmeskills.controller;

import com.teachmeskills.dto.GetMessageDto;
import com.teachmeskills.dto.MessageDto;
import com.teachmeskills.facade.MessageFacade;
import com.teachmeskills.facade.UserFacade;
import com.teachmeskills.session.AuthContext;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Slf4j
@Controller
@RequestMapping("/sendMessage")
@RequiredArgsConstructor
public class MessageController {

    private final MessageFacade messageFacade;
    private final UserFacade userFacade;
    private final AuthContext authContext;

    @GetMapping("/{friendId}")
    protected String getMessages(Model model, @PathVariable("friendId") Long messageTo) {
        final long messageFrom = authContext.getLoggedInUserId();
        final GetMessageDto messageDto = GetMessageDto.builder().messageFrom(messageFrom).messageTo(messageTo).build();
        List<MessageDto> messages = messageFacade.getMessages(messageDto);
        model.addAttribute("messages", messages);
        model.addAttribute("user", userFacade.getUserById(messageTo).orElseThrow());
        return "dialog";
    }

    @PostMapping(path = "/{friendId}", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    protected String sendMessage(@PathVariable("friendId") Long messageTo, MessageDto dto) {
        final long messageFrom = authContext.getLoggedInUserId();
        final String messageText = dto.getMessageText();
        messageFacade.createMessage(messageFrom, messageTo, messageText);
        log.info("Message has been sent to {}", messageTo);
        return "redirect:/sendMessage/" + messageTo;
    }
}
