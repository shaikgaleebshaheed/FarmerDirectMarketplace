package com.farmerdirect.util;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnection {

    private static final String URL =
            "jdbc:mysql://localhost:3306/farmer";

    private static final String USER = "root";

    private static final String PASSWORD = "jahe2506";

    public static Connection getConnection() {

        try {

            Class.forName("com.mysql.cj.jdbc.Driver");

            Connection connection = DriverManager.getConnection(
                    URL,
                    USER,
                    PASSWORD
            );

            System.out.println("Database connected successfully!");

            return connection;

        } catch (Exception e) {

            System.out.println("Database connection failed!");

            e.printStackTrace();

            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {

        getConnection();

    }
}