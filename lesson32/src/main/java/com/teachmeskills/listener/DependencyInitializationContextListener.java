package com.teachmeskills.listener;

import com.teachmeskills.facade.FriendFacade;
import com.teachmeskills.facade.FriendRequestFacade;
import com.teachmeskills.facade.MessageFacade;
import com.teachmeskills.facade.UserFacade;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
@Slf4j
public class DependencyInitializationContextListener implements ServletContextListener {

    @Override
    public void contextInitialized(final ServletContextEvent sce) {
        final ApplicationContext context = new ClassPathXmlApplicationContext("lesson32.xml");
        final UserFacade userFacade = context.getBean(UserFacade.class);
        final FriendFacade friendFacade = context.getBean(FriendFacade.class);
        final FriendRequestFacade friendRequestFacade = context.getBean(FriendRequestFacade.class);
        final MessageFacade messageFacade = context.getBean(MessageFacade.class);
        sce.getServletContext().setAttribute("userFacade", userFacade);
        sce.getServletContext().setAttribute("friendFacade", friendFacade);
        sce.getServletContext().setAttribute("friendRequestFacade", friendRequestFacade);
        sce.getServletContext().setAttribute("messageFacade", messageFacade);
    }
}
