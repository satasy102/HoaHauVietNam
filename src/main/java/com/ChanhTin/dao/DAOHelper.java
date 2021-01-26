package com.ChanhTin.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DAOHelper{
    private static final String jdbcURL;
    private static final String jdbcUsername;
    private static final String jdbcPassword;
    protected Connection connection;

    static {
        jdbcURL = "jdbc:postgresql://ec2-52-44-235-121.compute-1.amazonaws.com:5432/d4t6b7mrqr9gt5?sslmode=require";
        jdbcUsername = "xoniitypbytqsj";
        jdbcPassword = "2a4337e610f8603e136baa7660f843d4944a3aab5969d368fa83c2b622047e1f";
    }

    protected void connection() {
        try {
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

    protected void disconnect() throws SQLException {
        if (connection != null && !connection.isClosed()) {
            connection.close();
        }
    }

}
