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
import java.util.List;

@WebServlet("/users")
public class UserServlet extends HttpServlet {

  private UserService userService;

  @Override
  public void init(ServletConfig config) throws ServletException {
    super.init(config);
    userService = (UserService) config.getServletContext().getAttribute("userService");
  }

  @Override
  protected void doGet(
    HttpServletRequest req,
    HttpServletResponse resp
  ) throws IOException, ServletException {
    final List<User> users = userService.findUsers();
    req.setAttribute("users", users);
    getServletContext().getRequestDispatcher("/users.jsp").forward(req, resp);
  }

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
    String login = req.getParameter("login");
    String password = req.getParameter("password");

    try {
      userService.createUser(login, password);
    } catch (Exception ex) {
      resp.sendRedirect("users?error=" + ex.getMessage());
      return;
    }
    req.getSession().setAttribute("loggedIn", true);
    resp.sendRedirect("main.jsp");
  }
}
