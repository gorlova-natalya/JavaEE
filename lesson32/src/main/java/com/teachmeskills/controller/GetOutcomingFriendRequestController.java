package com.teachmeskills.controller;


import com.teachmeskills.facade.FriendRequestFacade;
import com.teachmeskills.model.User;
import com.teachmeskills.session.AuthContext;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/outcomingFriendRequests")
@RequiredArgsConstructor
public class GetOutcomingFriendRequestController {

    private final FriendRequestFacade friendRequestFacade;
    private final AuthContext authContext;

    @PostMapping(consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    protected String getRequests(Model model) {
        final long requestFrom = authContext.getLoggedInUserId();
        List<User> users = friendRequestFacade.getUsersByOutcomingRequests(requestFrom);
        model.addAttribute("usersOR", users);
        return "outcomingRequests";
    }
}
