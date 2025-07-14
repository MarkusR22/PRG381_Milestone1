package com.bc.util;

import com.bc.model.DBConnection;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.SQLException;



public class DatabaseInitializer {
    
    private final DBConnection dbConnection;
    private final String dbUser;
    private final String dbPassword;
    
    public DatabaseInitializer(String dbUser, String dbPassword) {
        this.dbConnection = new DBConnection();
        this.dbUser = dbUser;
        this.dbPassword = dbPassword;
    }
    

    public boolean initializeDatabase() {
        try {
            // Test connection first
            if (!dbConnection.testConnection(dbUser, dbPassword)) {
                System.err.println("Cannot connect to database. Please check your credentials and PostgreSQL server.");
                return false;
            }
            
            // Check if table already exists using tableExists() function
            System.out.println("Checking if students table exists...");
            if (tableExists()) {
                System.out.println("✓ Students table already exists - skipping creation");
                return true;
            } else {
                System.out.println("ℹ Students table does not exist - creating it now...");
                return createSchemaAndTable();
            }

        } catch (Exception e) {
            System.err.println("Error during database initialization: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }


    private boolean createSchemaAndTable() {
        try (Connection conn = dbConnection.getConnection(dbUser, dbPassword);
             Statement stmt = conn.createStatement()) {

            // Step 1: Create schema if it doesn't exist
            String createSchemaSQL = "CREATE SCHEMA IF NOT EXISTS WellnessManagement";
            stmt.executeUpdate(createSchemaSQL);
            System.out.println("✓ Schema 'WellnessManagement' created or already exists");

            // Step 2: Set the search path to use our schema
            String setSchemaSQL = "SET search_path TO WellnessManagement";
            stmt.executeUpdate(setSchemaSQL);
            System.out.println("✓ Schema search path set to 'WellnessManagement'");

            // Step 3: Create the students table in the schema
            String createTableSQL = "CREATE TABLE IF NOT EXISTS WellnessManagement.students (" +
                    "student_number VARCHAR(20) PRIMARY KEY NOT NULL, " +
                    "name VARCHAR(100) NOT NULL, " +
                    "surname VARCHAR(100) NOT NULL, " +
                    "email VARCHAR(255) UNIQUE NOT NULL, " +
                    "phone VARCHAR(20) NOT NULL, " +
                    "password VARCHAR(255) NOT NULL, " +
                    "created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP, " +
                    "updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP" +
                    ")";

            stmt.executeUpdate(createTableSQL);
            System.out.println("✓ Students table created or already exists with constraints:");
            System.out.println("  - student_number: PRIMARY KEY, NOT NULL");
            System.out.println("  - name: NOT NULL");
            System.out.println("  - surname: NOT NULL");
            System.out.println("  - email: UNIQUE, NOT NULL");
            System.out.println("  - phone: NOT NULL");
            System.out.println("  - password: NOT NULL");
            System.out.println("  - created_at: TIMESTAMP DEFAULT NOW");
            System.out.println("  - updated_at: TIMESTAMP DEFAULT NOW");

            // Step 4: Create index for faster email lookups
            String createIndexSQL = "CREATE INDEX IF NOT EXISTS idx_students_email ON WellnessManagement.students(email)";
            stmt.executeUpdate(createIndexSQL);
            System.out.println("✓ Email index created for faster lookups");

            return true;

        } catch (SQLException e) {
            System.err.println("Failed to create schema and students table: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }


    public boolean tableExists() {
        try (Connection conn = dbConnection.getConnection(dbUser, dbPassword);
             Statement stmt = conn.createStatement()) {
            stmt.executeUpdate("SET search_path TO WellnessManagement");
            java.sql.DatabaseMetaData meta = conn.getMetaData();
            java.sql.ResultSet resultSet = meta.getTables(null, "wellnessmanagement", "students", new String[]{"TABLE"});
            return resultSet.next();
        } catch (SQLException e) {
            System.err.println("Error checking if table exists: " + e.getMessage());
            return false;
        }
    }


    public boolean testConnection() {
        try (Connection conn = dbConnection.getConnection(dbUser, dbPassword);
             Statement stmt = conn.createStatement()) {

            stmt.executeUpdate("SET search_path TO WellnessManagement");

            // Test query using schema-qualified table name
            java.sql.ResultSet rs = stmt.executeQuery("SELECT COUNT(*) FROM WellnessManagement.students");
            if (rs.next()) {
                System.out.println("✓ Connection test successful. Students table contains " + rs.getInt(1) + " records.");
                return true;
            }
            return false;
            
        } catch (SQLException e) {
            System.err.println("Connection test failed: " + e.getMessage());
            return false;
        }
    }
    

    public static void main(String[] args) {

        String dbUser = "postgres";      // Change to your PostgreSQL username
        String dbPassword = "password";  // Change to your PostgreSQL password
        
        System.out.println("=== Database Initialization ===");
        System.out.println("Using credentials: " + dbUser + " / [password hidden]");
        
        DatabaseInitializer initializer = new DatabaseInitializer(dbUser, dbPassword);
        
        System.out.println("\nStarting database initialization...");
        
        if (initializer.initializeDatabase()) {
            System.out.println("✓ Database initialization completed successfully!");
            
            // Test the connection and table
            if (initializer.testConnection()) {
                System.out.println("✓ Students table is ready for use");
                System.out.println("\n=== Setup Complete ===");
                System.out.println("Your database is now ready for the Student Wellness Management System!");
            }
        } else {
            System.err.println("✗ Database initialization failed!");
            System.err.println("Please check your PostgreSQL server and credentials.");
        }
    }

}
