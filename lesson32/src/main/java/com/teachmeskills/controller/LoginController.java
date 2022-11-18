package com.teachmeskills.controller;

import com.teachmeskills.dto.UserDto;
import com.teachmeskills.facade.UserFacade;
import com.teachmeskills.model.User;
import com.teachmeskills.session.AuthContext;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.PrintWriter;
import java.util.Optional;

@Slf4j
@Controller
@RequestMapping("/loginUser")
@RequiredArgsConstructor
public class LoginController {

    private final UserFacade userFacade;
    private final AuthContext authContext;

    @GetMapping
    protected String doGet() {
        return "login";
    }

    @SneakyThrows
    @PostMapping(consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    protected String doPost(@Valid final UserDto dto, HttpServletResponse response) {
        final String login = dto.getLogin();
        final String password = dto.getPassword();
        String hashedPassword = userFacade.hashingPassword(password);
        Optional<User> user = userFacade.getUser(login);
        if (user.isPresent() && userFacade.validatePassword(password, hashedPassword)) {
            authContext.setLoggedInUserId(user.get().getId());
            log.info("User {} logged in", login);
            return "redirect:/users";
        } else {
            PrintWriter out = response.getWriter();
            out.println("Username or password error");
            return "reg";
        }
    }
}
