package com.teachmeskills.controller;

import com.teachmeskills.dto.UserDto;
import com.teachmeskills.facade.UserFacade;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.view.RedirectView;


@Slf4j
@Controller
@RequestMapping("/registration")
@RequiredArgsConstructor
public class RegistrationController {

    private final UserFacade userFacade;

    @PostMapping(consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    protected RedirectView createUser(final UserDto dto) {
        final String hashedPassword = userFacade.hashingPassword(dto.getPassword());
        userFacade.createUser(dto.getLogin(), hashedPassword);
        log.info("User {} registered", dto.getLogin());
        return new RedirectView("reg");
    }
}
