package com.teachmeskills.controller;

import com.teachmeskills.facade.FriendRequestFacade;
import com.teachmeskills.model.User;
import com.teachmeskills.session.AuthContext;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import java.util.List;

@Slf4j
@Controller
@RequestMapping("/incomingFriendRequests")
@RequiredArgsConstructor
@Tag(name = "friend requests")
public class IncomingFriendRequestController {

    private final FriendRequestFacade friendRequestFacade;
    private final AuthContext authContext;

    @Tag(name = "friend requests")
    @GetMapping
    protected String getIncomingRequests(Model model) {
        final long requestTo = authContext.getLoggedInUserId();
        List<User> users = friendRequestFacade.getUsersByIncomingRequests(requestTo);
        model.addAttribute("usersIR", users);
        return "incomingRequests";
    }

    @Tag(name = "friend requests")
    @PostMapping(path = "/{friendId}", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    protected String acceptFriendRequest(@PathVariable("friendId") Long requestFrom) {
        final long requestTo = authContext.getLoggedInUserId();
        friendRequestFacade.acceptRequest(requestFrom, requestTo);
        log.info("Friend request has been accepted from {}", requestFrom);
        return "redirect:/incomingFriendRequests";
    }

    @Tag(name = "friend requests")
    @DeleteMapping("/{friendId}")
    protected String deleteFriendRequest(@PathVariable("friendId") Long requestFrom) {
        final long requestTo = authContext.getLoggedInUserId();
        friendRequestFacade.deleteRequest(requestFrom, requestTo);
        return "redirect:/incomingFriendRequests";
    }
}
