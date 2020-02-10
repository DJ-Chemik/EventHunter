package sample.database.controllers;

import java.sql.*;

public class MiejscowoscController {

    //obiekt tworzący połączenie z bazą danych.
    private static Connection connection;
    //obiekt pozwalający tworzyć nowe wyrażenia SQL
    private static Statement statement;
    private static ResultSet resultSet;
    private static PreparedStatement prepStat;
    private static int result;

    public MiejscowoscController(Connection conn){
        connection = conn;
    }

    public static void setConnection(Connection connection) {
        MiejscowoscController.connection = connection;
    }

    public static void AddMiejscowosc(String nazwa, String wojewodztwo, String kodPocztowy) throws SQLException {
        prepStat = connection.prepareStatement("INSERT INTO miejscowość(nazwa, województwo, kod_pocztowy) VALUES(?,?,?)");
        prepStat.setString(1,nazwa);
        prepStat.setString(2,wojewodztwo);
        prepStat.setString(3,kodPocztowy);
        result = prepStat.executeUpdate();
    }

    public static void GetAllFromMiejscowosc() throws SQLException{
        statement = connection.createStatement();
        resultSet = statement.executeQuery("SELECT * from miejscowość");
    }

    public static void GetOneMiejscowosc(double id) throws SQLException {
        prepStat = connection.prepareStatement("SELECT * from miejscowość where id_miasta = ?");
        prepStat.setDouble(1,id);
        resultSet = prepStat.executeQuery();
    }

    public static void EditMiejscowosc(double id,String nazwa,String wojewodztwo,String kodPocztowy) throws SQLException{
        prepStat = connection.prepareStatement("UPDATE miejscowość SET nazwa = ? , województwo = ?, kod_pocztowy = ? WHERE id_miasta = ?");
        prepStat.setString(1,nazwa);
        prepStat.setString(2,wojewodztwo);
        prepStat.setString(3,kodPocztowy);
        prepStat.setDouble(4,id);
        result = prepStat.executeUpdate();
    }

    public static void DeleteMiejscowosc(double id) throws SQLException {
        prepStat = connection.prepareStatement("DELETE FROM miejscowość WHERE id_miasta = ?");
        prepStat.setDouble(1,id);
        result = prepStat.executeUpdate();
    }
}
