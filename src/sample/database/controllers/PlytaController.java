package sample.database.controllers;

import sample.database.ConnectionWithDatabase;

import java.sql.*;

public class PlytaController {

    //obiekt tworzący połączenie z bazą danych.
    private static Connection connection = ConnectionWithDatabase.getConnection();
    //obiekt pozwalający tworzyć nowe wyrażenia SQL
    private static Statement statement;
    private static ResultSet resultSet;
    private static PreparedStatement prepStat;
    private static int result;

    public PlytaController(Connection conn){
        connection = conn;
    }

    public static void AddPlyta(String tytul,int rok) throws SQLException {
        prepStat = connection.prepareStatement("INSERT INTO płyta(tytuł, rok_wydania) VALUES(?,?)");
        prepStat.setString(1,tytul);
        prepStat.setInt(2,rok);
        result = prepStat.executeUpdate();
    }

    public static void GetAllFromPlyta() throws SQLException{
        statement = connection.createStatement();
        resultSet = statement.executeQuery("SELECT * from płyta");
    }

    public static void GetOnePlyta(double id) throws SQLException {
        prepStat = connection.prepareStatement("SELECT * from płyta where id_plyty = ?");
        prepStat.setDouble(1,id);
        resultSet = prepStat.executeQuery();
    }

    public static void EditPlyta(double id,String tytul,int rok) throws SQLException{
        prepStat = connection.prepareStatement("UPDATE płyta SET tytuł = ? , rok_wydania = ? WHERE id_plyty = ?");
        prepStat.setString(1,tytul);
        prepStat.setInt(2,rok);
        prepStat.setDouble(3,id);
        result = prepStat.executeUpdate();
    }

    public static void DeletePlyta(double id) throws SQLException {
        prepStat = connection.prepareStatement("DELETE FROM płyta WHERE id_plyty = ?");
        prepStat.setDouble(1,id);
        result = prepStat.executeUpdate();
    }
}
