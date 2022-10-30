package com.teachmeskills.filter;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Set;
import javax.servlet.ServletException;
import javax.servlet.http.HttpSession;

@WebFilter("/*")
public class SecurityFilter implements Filter {

    private static final Set<String> ALLOWED_PATHS = Set.of("/login", "/reg", "/loginUser");

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        final HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpSession session = httpRequest.getSession(false);
        boolean loggedIn = session != null && session.getAttribute("loggedInUserId") != null;
        String path = httpRequest.getRequestURI().substring(httpRequest.getContextPath().length()).replaceAll("[/]+$", "");
        boolean allowedPath = ALLOWED_PATHS.contains(path);
        if (loggedIn || allowedPath) {
            chain.doFilter(request, response);
        } else {
            request.getServletContext().getRequestDispatcher("/accessDenied.jsp").forward(request, response);
        }
    }
}
