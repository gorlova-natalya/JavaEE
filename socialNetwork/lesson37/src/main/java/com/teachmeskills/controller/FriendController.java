package com.teachmeskills.controller;

import com.teachmeskills.facade.FriendFacade;
import com.teachmeskills.model.User;
import com.teachmeskills.session.AuthContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/friends")
@RequiredArgsConstructor
public class FriendController {

    private final FriendFacade friendFacade;
    private final AuthContext authContext;

    @GetMapping
    protected String getFriendsList(Model model) {
        final long userId = authContext.getLoggedInUserId();
        List<User> friends = friendFacade.getFriends(userId);
        model.addAttribute("friends", friends);
        return "friendList";
    }

    @DeleteMapping("/{friendId}")
    protected String deleteFriend(@PathVariable("friendId") Long friendId) {
        final long requestFrom = authContext.getLoggedInUserId();
        friendFacade.deleteFriendAndDialog(requestFrom, friendId);
        return "redirect:/friends";
    }
}
