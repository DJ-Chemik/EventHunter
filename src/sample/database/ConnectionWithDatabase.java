package sample.database;

import sample.gui.StaticData;

import javax.print.DocFlavor;
import java.sql.*;

public class ConnectionWithDatabase {
    //private final static String DATABASE_URL = "jdbc:mysql://127.0.0.1:3306/projekt";
    private final static String DATABASE_URL = "jdbc:mysql://localhost:3306/projekt";

    private final static String DATABASE_USER = "root";
    private final static String DATABASE_PASSWORD = "mcdj";
    private final static String DATABASE_DRIVER = "com.mysql.jdbc.Driver";

    //obiekt tworzący połączenie z bazą danych.
    private static Connection connection;
    //obiekt pozwalający tworzyć nowe wyrażenia SQL
    private static Statement statement;
    private static ResultSet resultSet;

    public static void connect(){
        try {
            connection = DriverManager.getConnection( DATABASE_URL, DATABASE_USER, DATABASE_PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static ResultSet queryToDatabase(String sqlQuery) throws SQLException {
        statement = connection.createStatement();
        resultSet = statement.executeQuery(sqlQuery);
        return resultSet;

    }

    public static void displayResultOfQuery() throws SQLException {
        while (resultSet.next()) {
            int id = resultSet.getInt(1);
            String name = resultSet.getString(2);
            String surname = resultSet.getString(3);
            String band = resultSet.getString(4);
            System.out.println("Id: " + id);
            System.out.println("Name: " + name);
            System.out.println("Surname: " + surname);
            System.out.println("Band: " + band);
            System.out.println("-------------------");
        }
        resultSet.close();
        statement.close();
    }

    public static void close(){
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
