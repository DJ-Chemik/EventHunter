package sample.database.controllers;

import sample.database.ConnectionWithDatabase;

import java.sql.*;

public class KabaretPrzedstawieniaController {
    //obiekt tworzący połączenie z bazą danych.
    private static Connection connection = ConnectionWithDatabase.getConnection();
    //obiekt pozwalający tworzyć nowe wyrażenia SQL
    private static Statement statement;
    private static ResultSet resultSet;
    private static PreparedStatement prepStat;
    private static int result;

    public static void addKabaretPrzedstawienia(double idKabaretu, double idPrzedstawienia) throws SQLException {
        prepStat = connection.prepareStatement("INSERT INTO kabaret_przedstawienia(id_wydarzenia, id_przedstawienia) VALUES(?,?)");
        prepStat.setDouble(1,idKabaretu);
        prepStat.setDouble(2,idPrzedstawienia);
        result = prepStat.executeUpdate();
    }

    // TODO: 17.02.2020 Add a rest of function like is in AktorzyPrzedstawieniaController
}
