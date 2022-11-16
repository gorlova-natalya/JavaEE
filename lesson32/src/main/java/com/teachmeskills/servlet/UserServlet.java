package com.teachmeskills.servlet;

import com.teachmeskills.facade.UserFacade;
import com.teachmeskills.model.User;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/users")
public class UserServlet extends HttpServlet {

    private UserFacade userFacade;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        userFacade = (UserFacade) config.getServletContext().getAttribute("userFacade");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        String loginParameter = req.getParameter("login");
        if (loginParameter != null && !loginParameter.isEmpty()) {
            List<User> users = userFacade.findUsersStartWith(loginParameter);
            req.setAttribute("users", users);
        } else {
            final List<User> users = userFacade.findUsers();
            req.setAttribute("users", users);
        }
        getServletContext().getRequestDispatcher("/main").forward(req, resp);
    }
}
