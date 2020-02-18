package sample.database;

import sample.gui.StaticData;

import javax.print.DocFlavor;
import java.sql.*;

public class ConnectionWithDatabase {

    private static String DATABASE_URL;// = "jdbc:mysql://localhost:3306/projekt?useTimezone=true&serverTimezone=UTC";
    private static String DATABASE_USER = "root";
    private static String DATABASE_PASSWORD = "mcdj";
    private static String DATABASE_DRIVER = "com.mysql.cj.jdbc.Driver";

    //obiekt tworzący połączenie z bazą danych.
    private static Connection connection;
    //obiekt pozwalający tworzyć nowe wyrażenia SQL
    private static Statement statement;
    private static ResultSet resultSet;

    public ConnectionWithDatabase(String host, String port, String schemeName, String user, String password) {
        DATABASE_USER = user;
        DATABASE_PASSWORD = password;
        DATABASE_URL = "jdbc:mysql://" + host + ":" + port + "/" + schemeName + "?useTimezone=true&serverTimezone=UTC";
    }

    public static Connection getConnection() {
        return connection;
    }

    public static void connect() throws SQLException {

        try {
            Class.forName(DATABASE_DRIVER);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        connection = DriverManager.getConnection(DATABASE_URL, DATABASE_USER, DATABASE_PASSWORD);

    }

    public static ResultSet queryToDatabase(String sqlQuery) throws SQLException {
        statement = connection.createStatement();
        resultSet = statement.executeQuery(sqlQuery);
        return resultSet;

    }

    public static void close() {
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
