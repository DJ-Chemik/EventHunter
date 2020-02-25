package sample.database.controllers;

import sample.database.ConnectionWithDatabase;

import java.sql.*;

public class KoncertMuzycyController {
    //obiekt tworzący połączenie z bazą danych.
    private static Connection connection = ConnectionWithDatabase.getConnection();
    //obiekt pozwalający tworzyć nowe wyrażenia SQL
    private static Statement statement;
    private static ResultSet resultSet;
    private static PreparedStatement prepStat;
    private static int result;

    public static void addKoncertMuzycy(double idKoncertu, double idMuzyka) throws SQLException {
        prepStat = connection.prepareStatement("INSERT INTO koncert_muzycy(id_wydarzenia, id_muzyka) VALUES(?,?)");
        prepStat.setDouble(1,idKoncertu);
        prepStat.setDouble(2,idMuzyka);
        result = prepStat.executeUpdate();
    }

    public static void GetAllFromKoncertMuzycy() throws SQLException{
        statement = connection.createStatement();
        resultSet = statement.executeQuery("SELECT * from koncert_muzycy");
    }

    public static void GetMuzycyByIdKoncertu(double idKoncertu) throws SQLException {
        prepStat = connection.prepareStatement("SELECT * from koncert_muzycy where id_wydarzenia = ?");
        prepStat.setDouble(1,idKoncertu);
        resultSet = prepStat.executeQuery();
    }

    public static void GetKoncertyByIdMuzyka(double idMuzyka) throws SQLException {
        prepStat = connection.prepareStatement("SELECT * from koncert_muzycy where id_muzyka = ?");
        prepStat.setDouble(1,idMuzyka);
        resultSet = prepStat.executeQuery();
    }

    public static void EditKoncertByIdMuzyka(double idMuzyka,double idKoncertu) throws SQLException{
        prepStat = connection.prepareStatement("UPDATE koncert_muzycy SET id_wydarzenia = ? WHERE id_muzyka = ?");
        prepStat.setDouble(1,idKoncertu);
        prepStat.setDouble(2,idMuzyka);
        result = prepStat.executeUpdate();
    }

    public static void EditMuzykByIdKoncertu(double idKoncertu,double idMuzyka) throws SQLException{
        prepStat = connection.prepareStatement("UPDATE koncert_muzycy SET id_muzyka = ? WHERE id_wydarzenia = ?");
        prepStat.setDouble(1,idMuzyka);
        prepStat.setDouble(2,idKoncertu);
        result = prepStat.executeUpdate();
    }

    public static void DeleteByIdMuzyka(double idMuzyka) throws SQLException {
        prepStat = connection.prepareStatement("DELETE FROM koncert_muzycy WHERE id_muzyka = ?");
        prepStat.setDouble(1,idMuzyka);
        result = prepStat.executeUpdate();
    }

    public static void DeleteByIdKoncertu(double idKoncertu) throws SQLException {
        prepStat = connection.prepareStatement("DELETE FROM koncert_muzycy WHERE id_wydarzenia = ?");
        prepStat.setDouble(1,idKoncertu);
        result = prepStat.executeUpdate();
    }

    public static void DeleteFromKoncertMuzycy(double idKoncertu,double idMuzyka) throws SQLException {
        prepStat = connection.prepareStatement("DELETE FROM koncert_muzycy WHERE id_muzyka = ? AND id_wydarzenia = ?");
        prepStat.setDouble(1,idMuzyka);
        prepStat.setDouble(2,idKoncertu);
        result = prepStat.executeUpdate();
    }
}
