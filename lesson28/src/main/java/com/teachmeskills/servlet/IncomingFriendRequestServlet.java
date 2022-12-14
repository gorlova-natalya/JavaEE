package com.teachmeskills.servlet;

import com.teachmeskills.fasade.FriendRequestFacade;
import com.teachmeskills.model.User;
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
@WebServlet("/incomingFriendRequests")
public class IncomingFriendRequestServlet extends HttpServlet {

    private FriendRequestFacade friendRequestFacade;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        friendRequestFacade = (FriendRequestFacade) config.getServletContext().getAttribute("friendRequestFacade");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        final long requestTo = (long) req.getSession().getAttribute("loggedInUserId");
        List<User> users = friendRequestFacade.getUsersByIncomingRequests(requestTo);
        req.setAttribute("incomingRequests", users);
        getServletContext().getRequestDispatcher("/incomingRequests").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        final long requestFrom = Long.parseLong(req.getParameter("accept_fr"));
        final long requestTo = (long) req.getSession().getAttribute("loggedInUserId");
        try {
            friendRequestFacade.acceptRequest(requestFrom, requestTo);
        } catch (Exception ex) {
            resp.sendRedirect("incomingFriendRequests?error=" + ex.getMessage());
            return;
        }
        log.info("Friend request has been accepted from {}", requestFrom);
        resp.sendRedirect("incomingFriendRequests");
    }
}
