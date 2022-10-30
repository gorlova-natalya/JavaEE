package com.teachmeskills.servlet;

import com.teachmeskills.model.FriendRequest;
import com.teachmeskills.model.User;
import com.teachmeskills.service.FriendRequestService;
import com.teachmeskills.service.UserService;
import lombok.extern.slf4j.Slf4j;

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

@Slf4j
@WebServlet("/incomingFriendRequests")
public class GetIncomingFriendRequestServlet extends HttpServlet {

    private FriendRequestService friendRequestService;
    private UserService userService;


    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        friendRequestService = (FriendRequestService) config.getServletContext()
                .getAttribute("friendRequestService");
        userService = (UserService) config.getServletContext().getAttribute("userService");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        final long requestTo = (long) req.getSession().getAttribute("loggedInUserId");
        List<User> users = friendRequestService.getIncomingRequests(requestTo).stream()
                .map(FriendRequest::getRequestFrom)
                .map(userService::getUserById)
                .filter(Optional::isPresent)
                .map(Optional::get)
                .collect(Collectors.toList());
        req.setAttribute("incomingRequests", users);
        getServletContext().getRequestDispatcher("/incomingRequests").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        final long requestFrom = Long.parseLong(req.getParameter("accept_fr"));
        final long requestTo = (long) req.getSession().getAttribute("loggedInUserId");
        try {
            friendRequestService.acceptRequest(requestFrom, requestTo);
        } catch (Exception ex) {
            resp.sendRedirect("incomingFriendRequests?error=" + ex.getMessage());
            return;
        }
        log.info("Friend request has been accepted from {}", requestFrom);
        resp.sendRedirect("incomingFriendRequests");
    }
}
