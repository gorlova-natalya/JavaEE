package com.teachmeskills.controller;

import com.teachmeskills.facade.FriendRequestFacade;
import com.teachmeskills.session.AuthContext;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.view.RedirectView;

@Slf4j
@Controller
@RequestMapping("/addFriend")
@RequiredArgsConstructor
@Tag(name = "friend requests")
public class FriendRequestController {

    private final FriendRequestFacade friendRequestFacade;
    private final AuthContext authContext;

    @Tag(name = "friend requests")
    @PostMapping(path = "/{friendId}", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    protected RedirectView sendRequest(@PathVariable("friendId") Long friendId) {
        final long requestFrom = authContext.getLoggedInUserId();
        friendRequestFacade.createRequest(requestFrom, friendId);
        log.info("Friend request has been sent to {}", friendId);
        return new RedirectView("/users");
    }
}
