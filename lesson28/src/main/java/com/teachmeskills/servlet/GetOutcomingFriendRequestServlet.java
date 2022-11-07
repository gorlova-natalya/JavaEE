package com.teachmeskills.servlet;

import com.teachmeskills.model.User;
import com.teachmeskills.service.FriendRequestService;
import lombok.extern.slf4j.Slf4j;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@Slf4j
@WebServlet("/outcomingFriendRequests")
public class GetOutcomingFriendRequestServlet extends HttpServlet {

    private FriendRequestService friendRequestService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        friendRequestService = (FriendRequestService) config.getServletContext()
                .getAttribute("friendRequestService");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        final long requestFrom = (long) req.getSession().getAttribute("loggedInUserId");
        List<User> users = friendRequestService.getUsersByOutcomingRequests(requestFrom);
        req.setAttribute("outcomingRequests", users);
        getServletContext().getRequestDispatcher("/outcomingRequests").forward(req, resp);
    }
}
