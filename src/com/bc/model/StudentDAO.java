package com.bc.model;

import java.sql.*;

public class StudentDAO {
    
    private final DBConnection dbConnection;
    private final String dbUser;
    private final String dbPassword;
    
    // Constructor with database credentials
    public StudentDAO(String dbUser, String dbPassword) {
        this.dbConnection = new DBConnection();
        this.dbUser = dbUser;
        this.dbPassword = dbPassword;
    }

    //Register new student
    public boolean registerStudent(Student student) {
        String sql = "INSERT INTO WellnessManagement.students (student_number, name, surname, email, phone, password) VALUES (?,?,?,?,?,?)";

        try (Connection conn = dbConnection.getConnection(dbUser, dbPassword);
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, student.getStudentNumber());
            stmt.setString(2, student.getName());
            stmt.setString(3, student.getSurname());
            stmt.setString(4, student.getEmail());
            stmt.setString(5, student.getPhone());
            stmt.setString(6, student.getPassword());

            int rowsInserted = stmt.executeUpdate();
            return rowsInserted > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    //Validate login
    public Student validateLogin(String email, String password) {
        String sql = "SELECT * FROM WellnessManagement.students WHERE email = ? AND password = ?";
        try (Connection conn = dbConnection.getConnection(dbUser, dbPassword);
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, email);
            stmt.setString(2, password);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return new Student(
                        rs.getString("student_number"),
                        rs.getString("name"),
                        rs.getString("surname"),
                        rs.getString("email"),
                        rs.getString("phone"),
                        rs.getString("password")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    //Check for duplicate email
    public boolean emailExists(String email) {
        String sql = "SELECT * FROM WellnessManagement.students WHERE email = ?";
        try (Connection conn = dbConnection.getConnection(dbUser, dbPassword);
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, email);
            ResultSet rs = stmt.executeQuery();
            return rs.next();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
