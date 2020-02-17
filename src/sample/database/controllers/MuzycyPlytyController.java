package sample.database.controllers;

import sample.database.ConnectionWithDatabase;

import java.sql.*;

public class MuzycyPlytyController {
    //obiekt tworzący połączenie z bazą danych.
    private static Connection connection = ConnectionWithDatabase.getConnection();
    //obiekt pozwalający tworzyć nowe wyrażenia SQL
    private static Statement statement;
    private static ResultSet resultSet;
    private static PreparedStatement prepStat;
    private static int result;

    public MuzycyPlytyController(Connection conn) {
        connection=conn;
    }

    public static void addMuzycyPlyty(double idMuzyka, double idPlyty) throws SQLException {
        prepStat = connection.prepareStatement("INSERT INTO muzyk_plyty(id_plyty, id_muzyka) VALUES(?,?)");
        prepStat.setDouble(1,idPlyty);
        prepStat.setDouble(2,idMuzyka);
        result = prepStat.executeUpdate();
    }

    // TODO: 16.02.2020 Add a rest of function like is in AktorzyPrzedstawieniaController
    public static void GetAllFromMuzycyPlyty() throws SQLException{
        statement = connection.createStatement();
        resultSet = statement.executeQuery("SELECT * from muzyk_plyty");
    }

    public static void GetMuzycyByIdPlyty(double idPlyty) throws SQLException {
        prepStat = connection.prepareStatement("SELECT * from muzyk_plyty where id_plyty = ?");
        prepStat.setDouble(1,idPlyty);
        resultSet = prepStat.executeQuery();
    }

    public static void GetPlytyByIdMuzyka(double idMuzyka) throws SQLException {
        prepStat = connection.prepareStatement("SELECT * from muzyk_plyty where id_muzyka = ?");
        prepStat.setDouble(1,idMuzyka);
        resultSet = prepStat.executeQuery();
    }

    public static void EditPlytaByIdMuzyka(double idMuzyka,double idPlyty) throws SQLException{
        prepStat = connection.prepareStatement("UPDATE muzyk_plyty SET id_plyty = ? WHERE id_muzyka = ?");
        prepStat.setDouble(1,idPlyty);
        prepStat.setDouble(2,idMuzyka);
        result = prepStat.executeUpdate();
    }

    public static void EditMuzykByIdPlyty(double idPlyty,double idMuzyka) throws SQLException{
        prepStat = connection.prepareStatement("UPDATE muzyk_plyty SET id_muzyka = ? WHERE id_plyty = ?");
        prepStat.setDouble(1,idMuzyka);
        prepStat.setDouble(2,idPlyty);
        result = prepStat.executeUpdate();
    }

    public static void DeleteByIdMuzyka(double idMuzyka) throws SQLException {
        prepStat = connection.prepareStatement("DELETE FROM muzyk_plyty WHERE id_muzyka = ?");
        prepStat.setDouble(1,idMuzyka);
        result = prepStat.executeUpdate();
    }

    public static void DeleteByIdPlyty(double idPlyty) throws SQLException {
        prepStat = connection.prepareStatement("DELETE FROM muzyk_plyty WHERE id_plyty = ?");
        prepStat.setDouble(1,idPlyty);
        result = prepStat.executeUpdate();
    }

    public static void DeleteFromMuzycyPlyty(double idPlyty,double idMuzyka) throws SQLException {
        prepStat = connection.prepareStatement("DELETE FROM muzyk_plyty WHERE id_muzyka = ? AND id_plyty = ?");
        prepStat.setDouble(1,idMuzyka);
        prepStat.setDouble(2,idPlyty);
        result = prepStat.executeUpdate();
    }
}
