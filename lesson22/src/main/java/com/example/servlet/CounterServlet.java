package com.example.servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.concurrent.atomic.AtomicInteger;

public class CounterServlet extends HttpServlet {

    private AtomicInteger count;

    @Override
    public void init() throws ServletException {
        count = new AtomicInteger(0);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        try (PrintWriter writer = resp.getWriter()) {
            writer.write("Count " + Integer.toString(count.get()));
            writer.write(" ContextPath " + req.getContextPath());
            writer.write(" URI " + req.getRequestURI());
            writer.write(" ServletPath " + req.getServletPath());
            writer.write(" LocalAddr " + req.getLocalAddr());
            writer.write(" Method " + req.getMethod());
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try (PrintWriter writer = resp.getWriter()) {
            writer.write(Integer.toString(count.incrementAndGet()));
        }
    }
}
