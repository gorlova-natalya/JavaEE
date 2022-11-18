package com.teachmeskills.controller;

import com.teachmeskills.dto.IncomingRequestDto;
import com.teachmeskills.facade.FriendRequestFacade;
import com.teachmeskills.model.User;
import com.teachmeskills.session.AuthContext;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.view.RedirectView;

import java.util.List;

@Slf4j
@Controller
@RequestMapping("/incomingFriendRequests")
@RequiredArgsConstructor
public class IncomingFriendRequestController {

    private final FriendRequestFacade friendRequestFacade;
    private final AuthContext authContext;

    @GetMapping
    protected String getIncomingRequests(Model model) {
        final long requestTo = authContext.getLoggedInUserId();
        List<User> users = friendRequestFacade.getUsersByIncomingRequests(requestTo);
        model.addAttribute("usersIR", users);
        return "incomingRequests";
    }

    @PostMapping(consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    protected RedirectView acceptFriendRequest(final IncomingRequestDto dto) {
        final long requestFrom = dto.getAcceptFr();
        final long requestTo = authContext.getLoggedInUserId();
        friendRequestFacade.acceptRequest(requestFrom, requestTo);
        log.info("Friend request has been accepted from {}", requestFrom);
        return new RedirectView("incomingFriendRequests");
    }
}
