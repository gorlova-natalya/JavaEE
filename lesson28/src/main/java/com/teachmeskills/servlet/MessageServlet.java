package com.teachmeskills.servlet;

import com.teachmeskills.fasade.MessageFacade;
import com.teachmeskills.model.Message;
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
@WebServlet("/sendMessage")
public class MessageServlet extends HttpServlet {

    private MessageFacade messageFacade;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        messageFacade = (MessageFacade) config.getServletContext().getAttribute("messageFacade");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        final long messageTo = Long.parseLong(request.getParameter("message_to"));
        final long messageFrom = (long) request.getSession().getAttribute("loggedInUserId");
        List<Message> messages = messageFacade.getMessages(messageFrom, messageTo);
        request.setAttribute("message_to", messageTo);
        request.setAttribute("messages", messages);
        request.setAttribute("userName", messageFacade.getUserById(messageTo).orElseThrow().getLogin());
        getServletContext().getRequestDispatcher("/dialog").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        final long messageTo = Long.parseLong(req.getParameter("message_to"));
        final long messageFrom = (long) req.getSession().getAttribute("loggedInUserId");
        final String messageText = req.getParameter("message");
        try {
            messageFacade.createMessage(messageFrom, messageTo, messageText);
        } catch (Exception ex) {
            resp.sendRedirect("dialog?error=" + ex.getMessage());
            return;
        }
        log.info("Message has been sent to {}", messageTo);
        resp.sendRedirect(req.getHeader("Referer"));
    }
}
