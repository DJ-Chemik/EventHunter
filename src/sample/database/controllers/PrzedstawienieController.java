package sample.database.controllers;

import java.sql.*;

public class PrzedstawienieController {

    //obiekt tworzący połączenie z bazą danych.
    private static Connection connection;
    //obiekt pozwalający tworzyć nowe wyrażenia SQL
    private static Statement statement;
    private static ResultSet resultSet;
    private static PreparedStatement prepStat;
    private static int result;

    public PrzedstawienieController(Connection conn){
        connection = conn;
    }

    public static void AddPrzedstawienie(String tytul,String dlugosc) throws SQLException {
        prepStat = connection.prepareStatement("INSERT INTO przedstawienie(tytul, dlugosc) VALUES(?,?)");
        prepStat.setString(1,tytul);
        prepStat.setString(2,dlugosc);
        result = prepStat.executeUpdate();
    }

    public static void GetAllFromPrzedstawienie() throws SQLException{
        statement = connection.createStatement();
        resultSet = statement.executeQuery("SELECT * from przedstawienie");
    }

    public static void GetOnePrzedstawienie(double id) throws SQLException {
        prepStat = connection.prepareStatement("SELECT * from przedstawienie where id_przedstawienia = ?");
        prepStat.setDouble(1,id);
        resultSet = prepStat.executeQuery();
    }

    public static void EditPrzedstawienie(double id,String tytul,double dlugosc) throws SQLException{
        prepStat = connection.prepareStatement("UPDATE przedstawienie SET tytul = ? , dlugosc = ? WHERE id_przedstawienia = ?");
        prepStat.setString(1,tytul);
        prepStat.setDouble(2,dlugosc);
        prepStat.setDouble(3,id);
        result = prepStat.executeUpdate();
    }

    public static void DeletePrzedstawienie(double id) throws SQLException {
        prepStat = connection.prepareStatement("DELETE FROM przedstawienie WHERE id_przedstawienia = ?");
        prepStat.setDouble(1,id);
        result = prepStat.executeUpdate();
    }
}
