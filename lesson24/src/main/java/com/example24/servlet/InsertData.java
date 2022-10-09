package com.example24.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


// Servlet Name
@WebServlet("/InsertData")
public class InsertData extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //getting parameters
        String pointName = request.getParameter("name");

        String pointPassword = request.getParameter("password");
        String query = "INSERT INTO users (name, password) VALUES ('" + pointName + "','" + pointPassword +"')";

        Connection connection;
        Statement statement;
        String url = "jdbc:postgresql://localhost:5432/postgres";
        String user = "user";
        String password = "password";
        try {
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection(url, user, password);
            statement = connection.createStatement();
            statement.execute(query);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }

        request.setAttribute("name", pointName);
        request.setAttribute("password", pointPassword);



        String page;
        try {
            page = request.getParameter("page");
        } catch (Exception e) {
            page = "error";
        }
        if (page != null) {
            switch (page) {
                case "point_added":
                    RequestDispatcher rd = getServletContext().getRequestDispatcher("/admin_point_added.jsp");
                    PrintWriter out = response.getWriter();
                    rd.include(request, response);
                    break;
                default:
                    request.getRequestDispatcher("/admin_point_error.jsp").forward(request, response);
                    break;
            }
        }
    }
}
