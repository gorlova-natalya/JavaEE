package com.teachmeskills.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@WebFilter(value = "/examples.jsp")
public class SecurityFilter implements Filter {


    @Override
        public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
                throws IOException, ServletException {
            final HttpServletRequest httpRequest = (HttpServletRequest) request;
            final Boolean loggedIn = (Boolean) httpRequest.getSession().getAttribute("loggedIn");
            if (loggedIn == null || !loggedIn) {
                request.getServletContext().getRequestDispatcher("/accessDenied.jsp").forward(request, response);
                return;
            }
            chain.doFilter(request, response);
        }

}
