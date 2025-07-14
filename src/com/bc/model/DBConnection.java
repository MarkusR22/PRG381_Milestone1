package com.bc.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class DBConnection {
    
    // Database configuration constants - UPDATE THESE WITH YOUR ACTUAL VALUES
    private static final String DB_HOST = "localhost";
    private static final String DB_PORT = "5432";
    private static final String DB_NAME = "StudentWellnessManagement";
    private static final String DB_SCHEMA = "WellnessManagement";

    

    public Connection connection(String dbname, String user, String pass) {
        Connection connection = null;
        try {
            // Load PostgreSQL JDBC driver
            Class.forName("org.postgresql.Driver");
            
            // Build connection URL with proper schema
            String url = String.format("jdbc:postgresql://%s:%s/%s?currentSchema=%s", 
                                     DB_HOST, DB_PORT, DB_NAME + dbname, DB_SCHEMA);
            
            // Establish connection
            connection = DriverManager.getConnection(url, user, pass);
            
            if (connection != null && !connection.isClosed()) {
                System.out.println("Database connection successful to: " + DB_NAME + dbname);
                // Set the schema for this session
                connection.createStatement().execute("SET search_path TO " + DB_SCHEMA);
            } else {
                System.err.println("Failed to establish database connection");
            }
            
        } catch (ClassNotFoundException e) {
            System.err.println("PostgreSQL JDBC Driver not found: " + e.getMessage());
            throw new RuntimeException("Database driver not found", e);
        } catch (SQLException e) {
            System.err.println("Database connection error: " + e.getMessage());
            throw new RuntimeException("Database connection failed", e);
        }
        return connection;
    }
    

    public Connection getConnection(String user, String pass) {
        return connection("", user, pass);
    }
    

    public boolean testConnection(String user, String pass) {
        try (Connection conn = connection("", user, pass)) {
            return conn != null && !conn.isClosed();
        } catch (Exception e) {
            System.err.println("Connection test failed: " + e.getMessage());
            return false;
        }
    }


}
