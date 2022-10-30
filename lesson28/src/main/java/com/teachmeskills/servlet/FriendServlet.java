package com.teachmeskills.servlet;


import com.teachmeskills.model.Friend;
import com.teachmeskills.model.User;
import com.teachmeskills.service.FriendService;
import com.teachmeskills.service.UserService;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@WebServlet ("/friends")
public class FriendServlet extends HttpServlet {

    private FriendService friendService;
    private UserService userService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        friendService = (FriendService) config.getServletContext().getAttribute("friendService");
        userService = (UserService) config.getServletContext().getAttribute("userService");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        final long userId = (long) req.getSession().getAttribute("loggedInUserId");
        List<User> friends = friendService.getUserFriends(userId).stream()
                .map(Friend::getFriendId)
                .map(userService::getUserById)
                .filter(Optional::isPresent)
                .map(Optional::get)
                .collect(Collectors.toList());
        req.setAttribute("friends", friends);
        getServletContext().getRequestDispatcher("/friendList").forward(req, resp);
    }
}
