package com.teachmeskills.servlet;

import com.teachmeskills.fasade.UserFacade;
import com.teachmeskills.model.User;
import lombok.extern.slf4j.Slf4j;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Optional;

@Slf4j
@WebServlet("/loginUser")
public class LoginServlet extends HttpServlet {

    private UserFacade userFacade;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        userFacade = (UserFacade) config.getServletContext().getAttribute("userFacade");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        getServletContext().getRequestDispatcher("/login").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        final String login = request.getParameter("login");
        final String password = request.getParameter("password");
        String hashedPassword = userFacade.hashingPassword(password);
        Optional<User> user = userFacade.getUser(login);
        if (user.isPresent() && userFacade.validatePassword(password, hashedPassword)) {
            request.getSession().setAttribute("loggedInUserId", user.get().getId());
            log.info("User {} logged in", login);
            response.sendRedirect("users");
        } else {
            PrintWriter out = response.getWriter();
            out.println("Username or password error");
            getServletContext().getRequestDispatcher("reg").forward(request, response);
        }
    }
}
