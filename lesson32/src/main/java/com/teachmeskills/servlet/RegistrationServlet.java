package com.teachmeskills.servlet;

import com.teachmeskills.facade.UserFacade;
import lombok.extern.slf4j.Slf4j;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
@WebServlet("/registration")
public class RegistrationServlet extends HttpServlet {

    private UserFacade userFacade;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        userFacade = (UserFacade) config.getServletContext().getAttribute("userFacade");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        final String hashedPassword = userFacade.hashingPassword(password);
        try {
            userFacade.createUser(login, hashedPassword);
        } catch (Exception ex) {
            log.error("User {} not created", login, ex);
            resp.sendRedirect("reg?error=" + ex.getMessage());
            return;
        }
        log.info("User {} registered", login);
        resp.sendRedirect("reg");
    }
}
