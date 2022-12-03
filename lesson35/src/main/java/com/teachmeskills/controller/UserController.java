package com.teachmeskills.controller;

import com.teachmeskills.facade.UserFacade;
import com.teachmeskills.model.User;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserFacade userFacade;

    @GetMapping()
    protected String getUsers(@RequestParam(name = "page", required = false) Integer pageNo,
                              @RequestParam(name = "pageSize", required = false) Integer pageSize, Model model) {
        Integer pageNumber = Optional.ofNullable(pageNo).orElse(1);
        Integer size = Optional.ofNullable(pageSize).orElse(5);
        final Page<User> page = userFacade.findPaginatedUsers(pageNumber, size);
        List<User> listUsers = page.getContent();
        model.addAttribute("users", listUsers);
        model.addAttribute("currentPage", pageNumber);
        model.addAttribute("pageSize", size);
        model.addAttribute("totalPages", page.getTotalPages());
        model.addAttribute("totalItems", page.getTotalElements());
        return "main";
    }

    @GetMapping("/{login}")
    protected String getUsersByLogin(Model model, @PathVariable("login") String login) {
        if (StringUtils.isNotBlank(login)) {
            List<User> users = userFacade.findUsersStartWith(login);
            model.addAttribute("users", users);
        }
        return "main";
    }
}
