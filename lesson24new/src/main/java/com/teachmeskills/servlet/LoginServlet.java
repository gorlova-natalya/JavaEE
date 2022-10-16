package com.teachmeskills.servlet;

import com.teachmeskills.model.User;
import com.teachmeskills.service.UserService;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Optional;


@WebServlet("/loginUser")
public class LoginServlet extends HttpServlet {

    private UserService userService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        userService = (UserService) config.getServletContext().getAttribute("userService");
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
        Optional<User> user = userService.getUser(login);

        if (user.isPresent() && user.get().getPassword().equals(password)) {
            request.getSession().setAttribute("loggedIn", true);
            response.sendRedirect("main");
        } else {
            PrintWriter out = response.getWriter();
            out.println("Username or password error");
            getServletContext().getRequestDispatcher("reg").forward(request, response);
        }
    }
}
