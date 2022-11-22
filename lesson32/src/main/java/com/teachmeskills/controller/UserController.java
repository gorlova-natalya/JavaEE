package com.teachmeskills.controller;

import com.teachmeskills.facade.UserFacade;
import com.teachmeskills.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserFacade userFacade;

    @GetMapping()
    protected String getUsers(Model model) {
        final List<User> users = userFacade.findUsers();
        model.addAttribute("users", users);
        return "main";
    }

    @GetMapping("/{login}")
    protected String getUsersByLogin(Model model, @PathVariable("login") String login) {
        if (login != null && !login.isEmpty()) {
            List<User> users = userFacade.findUsersStartWith(login);
            model.addAttribute("users", users);
        }
        return "main";
    }
}
