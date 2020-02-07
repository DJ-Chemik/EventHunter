package sample.database;

import sample.gui.StaticData;

import javax.print.DocFlavor;
import java.sql.*;

public class ConnectionWithDatabase {
    //private final static String DATABASE_URL = "jdbc:mysql://127.0.0.1:3306/projekt";
    private final static String DATABASE_URL = "jdbc:mysql://localhost:3306/projekt?useTimezone=true&serverTimezone=UTC";

    private final static String DATABASE_USER = "root";
    private final static String DATABASE_PASSWORD = "BaZyDaNyCh2019";
    private final static String DATABASE_DRIVER = "com.mysql.cj.jdbc.Driver";

    //obiekt tworzący połączenie z bazą danych.
    private static Connection connection;
    //obiekt pozwalający tworzyć nowe wyrażenia SQL
    private static Statement statement;
    private static ResultSet resultSet;

    public Connection GetConnection(){
        return this.connection;
    }

    public static void connect(){
        try {
            Class.forName(DATABASE_DRIVER);
            connection = DriverManager.getConnection( DATABASE_URL, DATABASE_USER, DATABASE_PASSWORD);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static ResultSet queryToDatabase(String sqlQuery) throws SQLException {
        statement = connection.createStatement();
        resultSet = statement.executeQuery(sqlQuery);
        return resultSet;

    }

    public static void close(){
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
