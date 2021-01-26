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
        jdbcURL = "jdbc:postgresql://localhost:5432/hoahau";
        jdbcUsername = "postgres";
        jdbcPassword = "123123";
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
