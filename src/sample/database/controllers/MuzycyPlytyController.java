package sample.database.controllers;

import sample.database.ConnectionWithDatabase;

import java.sql.*;
import java.util.ArrayList;

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

    public static void getAllFromMuzycyPlyty() throws SQLException{
        statement = connection.createStatement();
        resultSet = statement.executeQuery("SELECT * from muzyk_plyty");
    }

    public static void getMuzycyByIdPlyty(double idPlyty) throws SQLException {
        prepStat = connection.prepareStatement("SELECT * from muzyk_plyty where id_plyty = ?");
        prepStat.setDouble(1,idPlyty);
        resultSet = prepStat.executeQuery();
    }

    public static ArrayList<Double> getAllMusiciansIDsByMusicDiscID(double musicDiscID) throws SQLException {
        prepStat = connection.prepareStatement("SELECT id_muzyka from muzyk_plyty where id_plyty = ?");
        prepStat.setDouble(1,musicDiscID);
        resultSet = prepStat.executeQuery();
        ArrayList<Double> tmp = new ArrayList<>();
        while (resultSet.next()) {
            tmp.add(resultSet.getDouble(1));
        }
        return tmp;
    }

    public static void getPlytyByIdMuzyka(double idMuzyka) throws SQLException {
        prepStat = connection.prepareStatement("SELECT * from muzyk_plyty where id_muzyka = ?");
        prepStat.setDouble(1,idMuzyka);
        resultSet = prepStat.executeQuery();
    }

    public static void editPlytaByIdMuzyka(double idMuzyka, double idPlyty) throws SQLException{
        prepStat = connection.prepareStatement("UPDATE muzyk_plyty SET id_plyty = ? WHERE id_muzyka = ?");
        prepStat.setDouble(1,idPlyty);
        prepStat.setDouble(2,idMuzyka);
        result = prepStat.executeUpdate();
    }

    public static void editMuzykByIdPlyty(double idPlyty, double idMuzyka) throws SQLException{
        prepStat = connection.prepareStatement("UPDATE muzyk_plyty SET id_muzyka = ? WHERE id_plyty = ?");
        prepStat.setDouble(1,idMuzyka);
        prepStat.setDouble(2,idPlyty);
        result = prepStat.executeUpdate();
    }

    public static void deleteByIdMuzyka(double idMuzyka) throws SQLException {
        prepStat = connection.prepareStatement("DELETE FROM muzyk_plyty WHERE id_muzyka = ?");
        prepStat.setDouble(1,idMuzyka);
        result = prepStat.executeUpdate();
    }

    public static void deleteByIdPlyty(double idPlyty) throws SQLException {
        prepStat = connection.prepareStatement("DELETE FROM muzyk_plyty WHERE id_plyty = ?");
        prepStat.setDouble(1,idPlyty);
        result = prepStat.executeUpdate();
    }

    public static void deleteFromMuzycyPlyty(double idPlyty, double idMuzyka) throws SQLException {
        prepStat = connection.prepareStatement("DELETE FROM muzyk_plyty WHERE id_muzyka = ? AND id_plyty = ?");
        prepStat.setDouble(1,idMuzyka);
        prepStat.setDouble(2,idPlyty);
        result = prepStat.executeUpdate();
    }
}
