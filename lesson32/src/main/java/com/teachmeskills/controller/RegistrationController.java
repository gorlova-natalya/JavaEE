package com.teachmeskills.controller;

import com.teachmeskills.dto.UserDto;
import com.teachmeskills.facade.UserFacade;
import com.teachmeskills.session.AuthContext;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Slf4j
@Controller
@RequestMapping("/registration")
@RequiredArgsConstructor
public class RegistrationController {

    private final UserFacade userFacade;
    private final AuthContext authContext;

    @GetMapping
    protected String doGet(final Model model) {
        model.addAttribute("dto", new UserDto());
        return "reg";
    }

    @PostMapping(consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    protected String createUser(@Valid @ModelAttribute("dto") final UserDto dto) {
        final String hashedPassword = userFacade.hashingPassword(dto.getPassword());
        userFacade.createUser(dto.getLogin(), hashedPassword);
        authContext.setAuthorized(true);
        log.info("User {} registered", dto.getLogin());
        return "redirect:/registration";
    }
}
