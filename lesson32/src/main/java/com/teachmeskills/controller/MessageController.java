package com.teachmeskills.controller;

import com.teachmeskills.dto.MessageDto;
import com.teachmeskills.facade.MessageFacade;
import com.teachmeskills.facade.UserFacade;
import com.teachmeskills.model.Message;
import com.teachmeskills.session.AuthContext;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.view.RedirectView;

import java.util.List;

@Slf4j
@Controller
@RequestMapping("/sendMessage")
@RequiredArgsConstructor
public class MessageController {

    private final MessageFacade messageFacade;
    private final UserFacade userFacade;
    private final AuthContext authContext;


    @GetMapping
    protected String getMessages(Model model, final MessageDto dto) {
        final long messageTo = dto.getMessageTo();
        final long messageFrom = authContext.getLoggedInUserId();
        List<Message> messages = messageFacade.getMessages(messageFrom, messageTo);
        model.addAttribute("messageTo", messageTo);
        model.addAttribute("messages", messages);
        model.addAttribute("userName", userFacade.getUserById(messageTo).orElseThrow().getLogin());
        return "dialog";
    }

    @PostMapping(consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    protected RedirectView sendMessage(final MessageDto dto, @RequestHeader String referer) {
        final long messageTo = dto.getMessageTo();
        final long messageFrom = authContext.getLoggedInUserId();
        final String messageText = dto.getMessage();
        messageFacade.createMessage(messageFrom, messageTo, messageText);
        log.info("Message has been sent to {}", messageTo);
        return new RedirectView(referer);
    }
}
