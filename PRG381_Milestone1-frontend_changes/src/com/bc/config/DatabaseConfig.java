package com.bc.config;

public class DatabaseConfig {
    
    // Database credentials - replace with your actual values
    public static final String DB_USER = "postgres";
    public static final String DB_PASSWORD = "password";
    
    // Database connection parameters
    public static final String DB_HOST = "localhost";
    public static final String DB_PORT = "5432";
    public static final String DB_NAME = "StudentWellnessManagement";
    public static final String DB_SCHEMA = "WellnessManagement";
    



    public static String getDbUser() {
        return DB_USER;
    }
    

    public static String getDbPassword() {
        return DB_PASSWORD;
    }
    

    public static String getDbUrl() {
        return String.format("jdbc:postgresql://%s:%s/%s?currentSchema=%s", 
                           DB_HOST, DB_PORT, DB_NAME, DB_SCHEMA);
    }
}
