package com.teachmeskills.listener;

import com.teachmeskills.fasade.FriendFacade;
import com.teachmeskills.fasade.FriendRequestFacade;
import com.teachmeskills.fasade.MessageFacade;
import com.teachmeskills.fasade.UserFacade;
import com.teachmeskills.properties.HashProperties;
import com.teachmeskills.repository.BcryptHashPassword;
import com.teachmeskills.repository.FriendRepository;
import com.teachmeskills.repository.FriendRequestRepository;
import com.teachmeskills.repository.HashPassword;
import com.teachmeskills.repository.JdbcFriendRepository;
import com.teachmeskills.repository.JdbcFriendRequestRepository;
import com.teachmeskills.repository.JdbcMessageRepository;
import com.teachmeskills.repository.JdbcUserRepository;
import com.teachmeskills.repository.MessageRepository;
import com.teachmeskills.repository.UserRepository;
import com.teachmeskills.service.FriendRequestService;
import com.teachmeskills.service.FriendService;
import com.teachmeskills.service.MessageService;
import com.teachmeskills.service.UserService;
import lombok.extern.slf4j.Slf4j;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@WebListener
@Slf4j
public class DependencyInitializationContextListener implements ServletContextListener {

    @Override
    public void contextInitialized(final ServletContextEvent sce) {
        final String dbDriver = "org.postgresql.Driver";
        final String username = sce.getServletContext().getInitParameter("db_user");
        final String password = sce.getServletContext().getInitParameter("db_password");
        final String dbUrl = sce.getServletContext().getInitParameter("db_url");
        try {
            Class.forName(dbDriver);
            final Connection con = DriverManager.getConnection(dbUrl, username, password);
            UserRepository repository = new JdbcUserRepository(con);
            FriendRepository friendRepository = new JdbcFriendRepository(con);
            MessageRepository messageRepository = new JdbcMessageRepository(con);
            HashProperties hashProperties = new HashProperties();
            HashPassword hashPassword = new BcryptHashPassword(hashProperties);
            UserService userService = new UserService(repository, hashPassword);
            FriendRequestRepository friendRequestRepository = new JdbcFriendRequestRepository(con);
            FriendRequestService requestService =
                    new FriendRequestService(friendRequestRepository, friendRepository);
            FriendService friendService = new FriendService(friendRepository);
            MessageService messageService = new MessageService(messageRepository);
            UserFacade userFacade = new UserFacade(userService);
            FriendFacade friendFacade = new FriendFacade(friendService, userService, messageService);
            FriendRequestFacade friendRequestFacade =
                    new FriendRequestFacade(requestService, friendRequestRepository, userService);
            MessageFacade messageFacade = new MessageFacade(messageService, userService);
            sce.getServletContext().setAttribute("userFacade", userFacade);
            sce.getServletContext().setAttribute("friendFacade", friendFacade);
            sce.getServletContext().setAttribute("friendRequestFacade", friendRequestFacade);
            sce.getServletContext().setAttribute("messageFacade", messageFacade);
        } catch (Exception e) {
            log.error("Unable to establish connection with database");
            throw new RuntimeException(e + "Unable to establish connection with database");
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        try {
            final Connection connection = (Connection) sce.getServletContext().getAttribute("connection");
            connection.close();
        } catch (SQLException e) {
            log.error(e.getMessage());
        }
    }
}
