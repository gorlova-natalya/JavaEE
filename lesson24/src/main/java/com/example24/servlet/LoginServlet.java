package com.example24.servlet;

import com.example24.model.User;
import com.example24.service.DataDAO;
import com.example24.utils.AppUtils;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

@WebServlet("/login")
    public class LoginServlet extends HttpServlet {
        private static final long serialVersionUID = 1L;

        public LoginServlet() {
            super();
        }

        @Override
        protected void doGet(HttpServletRequest request, HttpServletResponse response)
                throws ServletException, IOException {

            RequestDispatcher dispatcher //
                    = this.getServletContext().getRequestDispatcher("/WEB-INF/views/loginView.jsp");

            dispatcher.forward(request, response);
        }

        @Override
        protected void doPost(HttpServletRequest request, HttpServletResponse response)
                throws ServletException, IOException {

            String name = request.getParameter("name");
            String password = request.getParameter("password");
            User userAccount = DataDAO.findUser(name, password);

            if (userAccount == null) {
                String errorMessage = "Invalid userName or Password";

                request.setAttribute("errorMessage", errorMessage);

                RequestDispatcher dispatcher //
                        = this.getServletContext().getRequestDispatcher("/WEB-INF/views/loginView.jsp");

                dispatcher.forward(request, response);
                return;
            }

            AppUtils.storeLoginedUser(request.getSession(), userAccount);

            //
            int redirectId = -1;
            try {
                redirectId = Integer.parseInt(request.getParameter("redirectId"));
            } catch (Exception e) {
            }
            String requestUri = AppUtils.getRedirectAfterLoginUrl(request.getSession(), redirectId);
            if (requestUri != null) {
                response.sendRedirect(requestUri);
            } else {
                // По умолчанию после успешного входа в систему
                // перенаправить на страницу /userInfo
                response.sendRedirect(request.getContextPath() + "/users");
            }

        }

//    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        //getting parameters
//        String pointName = request.getParameter("name");
//
//        String pointPassword = request.getParameter("password");
//        String query = "INSERT INTO users (name, password) VALUES ('" + pointName + "','" + pointPassword +"')";
//
//        Connection connection;
//        Statement statement;
//        String url = "jdbc:postgresql://localhost:5432/postgres";
//        String user = "db_user";
//        String password = "db_password";
//
//        try {
//            Class.forName("org.postgresql.Driver");
//            connection = DriverManager.getConnection(url, user, password);
//            statement = connection.createStatement();
//            statement.execute(query);
//        } catch (ClassNotFoundException | SQLException e) {
//            e.printStackTrace();
//        }
//
//        request.setAttribute("name", pointName);
//        request.setAttribute("password", pointPassword);
//
//        String page;
//        try {
//            page = request.getParameter("page");
//        } catch (Exception e) {
//            page = "error";
//        }
//        if (page != null) {
//            switch (page) {
//                case "point_added":
//                    RequestDispatcher rd = getServletContext().getRequestDispatcher("/admin_point_added.jsp");
//                    PrintWriter out = response.getWriter();
//                    rd.include(request, response);
//                    break;
//                default:
//                    request.getRequestDispatcher("/admin_point_error.jsp").forward(request, response);
//                    break;
//            }
//        }
//    }


}
