package com.teachmeskills.servlet;

import com.teachmeskills.facade.FriendRequestFacade;
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

    private FriendRequestFacade friendRequestFacade;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        friendRequestFacade = (FriendRequestFacade) config.getServletContext().getAttribute("friendRequestFacade");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        final long requestTo = Long.parseLong(req.getParameter("sendFr"));
        final long requestFrom = (long) req.getSession().getAttribute("loggedInUserId");
        log.info("Friend request has been sent to {}", requestTo);
        try {
            friendRequestFacade.createRequest(requestFrom, requestTo);
        } catch (Exception ex) {
            log.error("Friend request hasn't been sent to {}", requestTo, ex);
            resp.sendRedirect("users?error=" + ex.getMessage());
            return;
        }
        log.info("Friend request has been sent to {}", requestTo);
        resp.sendRedirect("users");
    }
}
