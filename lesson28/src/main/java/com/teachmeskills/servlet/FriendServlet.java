package com.teachmeskills.servlet;

import com.teachmeskills.fasade.FriendFacade;
import com.teachmeskills.model.User;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;


@WebServlet("/friends")
public class FriendServlet extends HttpServlet {

    private FriendFacade friendFacade;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        friendFacade = (FriendFacade) config.getServletContext().getAttribute("friendFacade");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        final long userId = (long) req.getSession().getAttribute("loggedInUserId");
        List<User> friends = friendFacade.getFriends(userId);
        req.setAttribute("friends", friends);
        getServletContext().getRequestDispatcher("/friendList").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        final long requestFrom = (long) req.getSession().getAttribute("loggedInUserId");
        final long requestTo = Long.parseLong(req.getParameter("delete"));
        friendFacade.deleteFriendAndDialog(requestFrom, requestTo);
        resp.sendRedirect("friends");
    }
}
