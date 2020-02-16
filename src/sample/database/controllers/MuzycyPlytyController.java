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
}
