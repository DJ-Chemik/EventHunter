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

    // TODO: 17.02.2020 Add a rest of function like is in AktorzyPrzedstawieniaController
}
