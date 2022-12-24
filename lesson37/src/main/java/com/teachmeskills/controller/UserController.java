package com.teachmeskills.controller;

import com.teachmeskills.facade.UserFacade;
import com.teachmeskills.model.User;
import io.swagger.v3.oas.annotations.tags.Tag;
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

@Controller
@Tag(name = "users", description = "Users API")
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserFacade userFacade;

    @Tag(name = "users")
    @GetMapping()
    protected String getUsers(@RequestParam(defaultValue = "1", name = "page", required = false) Integer pageNo,
                              @RequestParam(defaultValue = "5", name = "pageSize", required = false) Integer pageSize,
                              Model model) {
        final Page<User> page = userFacade.findPaginatedUsers(pageNo, pageSize);
        List<User> listUsers = page.getContent();
        model.addAttribute("users", listUsers);
        model.addAttribute("currentPage", pageNo);
        model.addAttribute("pageSize", pageSize);
        model.addAttribute("totalPages", page.getTotalPages());
        model.addAttribute("totalItems", page.getTotalElements());
        return "main";
    }

    @Tag(name = "users")
    @GetMapping("/{login}")
    protected String getUsersByLogin(Model model, @PathVariable("login") String login) {
        if (StringUtils.isNotBlank(login)) {
            List<User> users = userFacade.findUsersStartWith(login);
            model.addAttribute("users", users);
        }
        return "main";
    }
}
