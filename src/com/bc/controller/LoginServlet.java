package com.bc.controller;

import com.bc.model.Student;
import com.bc.model.StudentDAO;
import com.bc.config.DatabaseConfig;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {

        //Getting paramaters from the login.jsp
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        StudentDAO studentDAO = new StudentDAO(DatabaseConfig.getDbUser(), DatabaseConfig.getDbPassword());
        Student student = studentDAO.validateLogin(email, password);

        if (student != null) {
            //Create session for successful login
            HttpSession session = request.getSession();
            session.setAttribute("studentName", student.getName());

            response.sendRedirect("dashboard.jsp");
        } else {
            //Setting error message and going back on failed login
            request.setAttribute("errorMessage", "Invalid email or password");
            request.getRequestDispatcher("login.jsp").forward(request, response);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
        response.sendRedirect("login.jsp");
    }
}
