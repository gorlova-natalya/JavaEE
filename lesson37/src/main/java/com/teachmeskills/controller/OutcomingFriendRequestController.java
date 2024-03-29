package com.teachmeskills.controller;

import com.teachmeskills.facade.FriendRequestFacade;
import com.teachmeskills.model.User;
import com.teachmeskills.session.AuthContext;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/outcomingFriendRequests")
@RequiredArgsConstructor
@Tag(name = "friend requests")
public class OutcomingFriendRequestController {

    private final FriendRequestFacade friendRequestFacade;
    private final AuthContext authContext;

    @Tag(name = "friend requests")
    @GetMapping
    protected String getRequests(Model model) {
        final long requestFrom = authContext.getLoggedInUserId();
        List<User> users = friendRequestFacade.getUsersByOutcomingRequests(requestFrom);
        model.addAttribute("usersOR", users);
        return "outcomingRequests";
    }

    @Tag(name = "friend requests")
    @DeleteMapping("/{friendId}")
    protected String deleteFriendRequest(@PathVariable("friendId") Long requestTo) {
        final long requestFrom = authContext.getLoggedInUserId();
        friendRequestFacade.deleteRequest(requestFrom, requestTo);
        return "redirect:/outcomingFriendRequests";
    }
}
