package com.bc.controller;

import com.bc.model.Student;
import com.bc.model.StudentDAO;
import com.bc.config.DatabaseConfig;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet("/register")
public class RegisterServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Get form inputs from register.jsp
        String studentNumber = request.getParameter("studentNumber");
        String name = request.getParameter("name");
        String surname = request.getParameter("surname");
        String email = request.getParameter("email");
        String phone = request.getParameter("phone");
        String password = request.getParameter("password");

        //Input validation
        if (studentNumber == null || name == null || surname == null || email == null || phone == null || password == null ||
                studentNumber.isEmpty() || surname.isEmpty() || email.isEmpty() || phone.isEmpty() || password.isEmpty()){

            request.setAttribute("errorMessage", "Please fill in all the fields.");
            request.getRequestDispatcher("register.jsp").forward(request, response);
            return;
        }

        StudentDAO studentDAO = new StudentDAO(DatabaseConfig.getDbUser(), DatabaseConfig.getDbPassword());

        if (studentDAO.emailExists(email)){
            //Email already exists
            request.setAttribute("errorMessage", "Email already exists");
            request.getRequestDispatcher("register.jsp").forward(request, response);
        } else {
            //Create user and save
            Student newStudent = new Student(studentNumber, name, surname, email, phone, password);
            boolean success = studentDAO.registerStudent(newStudent);

            if (success) {
                request.setAttribute("successMessage", "Student registered successfully");
                request.getRequestDispatcher("login.jsp").forward(request, response);
            } else {
                request.setAttribute("errorMessage", "Registration Failed. Please try again");
                request.getRequestDispatcher("register.jsp").forward(request, response);
            }
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.sendRedirect("register.jsp");
    }

}

