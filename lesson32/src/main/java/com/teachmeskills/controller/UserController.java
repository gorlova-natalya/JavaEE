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

    @GetMapping("/users/{login}")
    protected String getUsers(Model model, @PathVariable("login") String login) {
        String loginParameter = String.valueOf(model.getAttribute(login));
        if (loginParameter != null && !loginParameter.isEmpty()) {
            List<User> users = userFacade.findUsersStartWith(loginParameter);
            model.addAttribute("users", users);
        } else {
            final List<User> users = userFacade.findUsers();
            model.addAttribute("users", users);
        }
        return "main";
    }
}
