package server.db;

import java.sql.*;

public class DBConnection {

    private static final String URL = "jdbc:mysql://127.0.0.1:3306/sport?serverTimezone=Europe/Helsinki";
    private static final String USER = "root";
    private static final String PASSWORD = "toor";

    private Connection connection;

    public DBConnection() {
        try {
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException sqlEx) {
            sqlEx.printStackTrace();
        }
    }

    public Connection getConnection() {
        return connection;
    }

}
