package com.teachmeskills.controller;


import com.teachmeskills.dto.DeleteRequestDto;
import com.teachmeskills.facade.FriendRequestFacade;
import com.teachmeskills.session.AuthContext;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.view.RedirectView;


@Controller
@RequestMapping("/deleteFriendRequest")
@RequiredArgsConstructor
public class DeleteFriendRequestController {

    private final FriendRequestFacade friendRequestFacade;
    private final AuthContext authContext;

    @PostMapping(consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    protected RedirectView deleteFriendRequest(Model model, DeleteRequestDto dto) {
        final long requestFrom = authContext.getLoggedInUserId();
        final long requestTo = Long.parseLong(dto.getRevokeFr());
        friendRequestFacade.deleteRequest(requestFrom, requestTo);
        return new RedirectView(dto.getRedirect());
    }
}
