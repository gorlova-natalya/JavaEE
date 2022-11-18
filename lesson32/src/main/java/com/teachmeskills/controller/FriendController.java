package com.teachmeskills.controller;

import com.teachmeskills.dto.DeleteFriendDto;
import com.teachmeskills.facade.FriendFacade;
import com.teachmeskills.model.User;
import com.teachmeskills.session.AuthContext;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.view.RedirectView;

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

    @PostMapping(consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    protected RedirectView deleteFriend(final DeleteFriendDto dto) {
        final long requestFrom = authContext.getLoggedInUserId();
        final long requestTo = dto.getDelete();
        friendFacade.deleteFriendAndDialog(requestFrom, requestTo);
        return new RedirectView("friends");
    }
}
