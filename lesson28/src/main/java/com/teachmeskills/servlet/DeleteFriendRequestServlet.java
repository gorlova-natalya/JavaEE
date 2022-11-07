package com.teachmeskills.servlet;

import com.teachmeskills.service.FriendRequestService;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/deleteFriendRequest")
public class DeleteFriendRequestServlet extends HttpServlet {

    private FriendRequestService friendRequestService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        friendRequestService = (FriendRequestService) config.getServletContext()
                .getAttribute("friendRequestService");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        final long requestFrom = (long) req.getSession().getAttribute("loggedInUserId");
        final long requestTo = Long.parseLong(req.getParameter("revoke_fr"));
        friendRequestService.deleteRequest(requestFrom, requestTo);
        resp.sendRedirect(req.getParameter("redirect"));
    }
}
