package com.bc.controller;

import com.bc.model.Student;
import com.bc.model.StudentDAO;
import com.bc.config.DatabaseConfig;
import com.bc.util.PasswordUtil;

import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.IOException;

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
        String plainPassword = request.getParameter("password");
        String hashedPassword = PasswordUtil.hashPassword(plainPassword);

        String digitRegex = "^[0-9]+$";
        String nameRegex = "^[a-zA-Z\\s-]+$";

        //Checking for empty fields
        if (studentNumber == null || name == null || surname == null || email == null || phone == null || hashedPassword == null ||
                studentNumber.isEmpty() || surname.isEmpty() || email.isEmpty() || phone.isEmpty() || hashedPassword.isEmpty()){

            request.setAttribute("errorMessage", "Please fill in all the fields.");
            request.setAttribute("studentNumber", studentNumber);
            request.setAttribute("name", name);
            request.setAttribute("surname", surname);
            request.setAttribute("email", email);
            request.setAttribute("phone", phone);
            request.getRequestDispatcher("register.jsp").forward(request, response);
            return;
        }

        //Checking for valid phone number
        if (phone.length() != 10 || !phone.matches(digitRegex)) {
            request.setAttribute("errorMessage", "Please enter a valid phone number.");
            request.setAttribute("studentNumber", studentNumber);
            request.setAttribute("name", name);
            request.setAttribute("surname", surname);
            request.setAttribute("email", email);
            request.setAttribute("phone", phone);
            request.getRequestDispatcher("register.jsp").forward(request, response);
            return;
        }

        //Checking for special characters in name and surname
        if (!name.matches(nameRegex) || !surname.matches(nameRegex)) {
            request.setAttribute("errorMessage", "Name and surname must not contain special characters or numbers.");
            request.setAttribute("studentNumber", studentNumber);
            request.setAttribute("name", name);
            request.setAttribute("surname", surname);
            request.setAttribute("email", email);
            request.setAttribute("phone", phone);
            request.getRequestDispatcher("register.jsp").forward(request, response);
            return;
        }

        //Checking that student number only contains digits
        if (!studentNumber.matches(digitRegex)) {
            request.setAttribute("errorMessage", "Student number can only contain digits.");
            request.setAttribute("studentNumber", studentNumber);
            request.setAttribute("name", name);
            request.setAttribute("surname", surname);
            request.setAttribute("email", email);
            request.setAttribute("phone", phone);
            request.getRequestDispatcher("register.jsp").forward(request, response);
            return;
        }

        StudentDAO studentDAO = new StudentDAO(DatabaseConfig.getDbUser(), DatabaseConfig.getDbPassword());

        //Checking for duplicate email
        if (studentDAO.emailExists(email)){
            //Email already exists
            request.setAttribute("errorMessage", "Email already exists");
            request.setAttribute("studentNumber", studentNumber);
            request.setAttribute("name", name);
            request.setAttribute("surname", surname);
            request.setAttribute("phone", phone);
            request.getRequestDispatcher("register.jsp").forward(request, response);
            return;
        }

        //Checking for duplicate student number
        if (studentDAO.studentNumberExists(studentNumber)){
            request.setAttribute("errorMessage", "Student number already exists");
            request.setAttribute("studentNumber", studentNumber);
            request.setAttribute("name", name);
            request.setAttribute("surname", surname);
            request.setAttribute("email", email);
            request.setAttribute("phone", phone);
            request.getRequestDispatcher("register.jsp").forward(request, response);
            return;
        }

        //Create user and save
        Student newStudent = new Student(studentNumber, name, surname, email, phone, hashedPassword);
        boolean success = studentDAO.registerStudent(newStudent);

        if (success) {
            request.setAttribute("successMessage", "Student registered successfully");
            request.getRequestDispatcher("login.jsp").forward(request, response);
        } else {
            request.setAttribute("errorMessage", "Registration Failed. Please try again");
            request.getRequestDispatcher("register.jsp").forward(request, response);
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.sendRedirect("register.jsp");
    }

}

