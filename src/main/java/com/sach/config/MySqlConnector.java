package com.sach.config;

import java.sql.Connection;
import java.sql.DriverManager;

public class MySqlConnector {
    static final String URL = "jdbc:mysql://localhost:3306/book_store";
    static final String USER = "root";
    static final String PASSWORD = "Doantinh";
    public static Connection getConnection() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            return DriverManager.getConnection(URL,USER,PASSWORD);
        }catch(Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
