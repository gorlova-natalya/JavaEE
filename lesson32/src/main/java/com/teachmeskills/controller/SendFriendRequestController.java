package com.teachmeskills.controller;

import com.teachmeskills.dto.CreateFriendRequestDto;
import com.teachmeskills.facade.FriendRequestFacade;
import com.teachmeskills.session.AuthContext;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.view.RedirectView;


@Slf4j
@Controller
@RequestMapping("/addFriend")
@RequiredArgsConstructor
public class SendFriendRequestController {

    private final FriendRequestFacade friendRequestFacade;
    private final AuthContext authContext;

    @PostMapping(consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    protected RedirectView sendRequest(final CreateFriendRequestDto dto) {
        final long requestTo = dto.getSendFr();
        final long requestFrom = authContext.getLoggedInUserId();
        log.info("Friend request has been sent to {}", requestTo);
        friendRequestFacade.createRequest(requestFrom, requestTo);
        log.info("Friend request has been sent to {}", requestTo);
        return new RedirectView("users");
    }
}
