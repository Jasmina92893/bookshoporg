package com.example.library;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseUtil {

    private static final String DATABASE_URL = "jdbc:sqlite:library.db";

    public static Connection getConnection() {
        try {
            return DriverManager.getConnection(DATABASE_URL);
        } catch (SQLException e) {
            throw new RuntimeException("Error connecting to the database", e);
        }
    }

    public static void createDatabase() {
        String createTableSQL = "CREATE TABLE IF NOT EXISTS Book (" +
                "Book_ID TEXT PRIMARY KEY, " +
                "Book_Name TEXT NOT NULL, " +
                "Book_Author TEXT, " +
                "Book_Category TEXT, " +
                "Book_Price REAL NOT NULL);";

        try (Connection connection = getConnection(); Statement stmt = connection.createStatement()) {
            stmt.execute(createTableSQL);
        } catch (SQLException e) {
            throw new RuntimeException("Error creating database", e);
        }
    }
}