package com.teachmeskills.servlet;

import com.teachmeskills.service.FriendRequestService;
import lombok.extern.slf4j.Slf4j;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/addFriend")
@Slf4j
public class SendFriendRequestServlet extends HttpServlet {

    private FriendRequestService friendRequestService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        friendRequestService = (FriendRequestService) config.getServletContext().getAttribute("friendRequestService");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        final long requestTo = Long.parseLong(req.getParameter("send_fr"));
        final long requestFrom = (long) req.getSession().getAttribute("loggedInUserId");
        log.info("Friend request has been sent to {}", requestTo);
        try {
            friendRequestService.createRequest(requestFrom, requestTo);
        } catch (Exception ex) {
            log.error("не создан");
            resp.sendRedirect("users?error=" + ex.getMessage());
            return;
        }
        log.info("Friend request has been sent to {}", requestTo);
        resp.sendRedirect("users");
    }
}
