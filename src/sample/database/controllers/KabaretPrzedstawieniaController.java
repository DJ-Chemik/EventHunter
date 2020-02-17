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
    public static void GetAllFromKabaretPrzedstawienia() throws SQLException{
        statement = connection.createStatement();
        resultSet = statement.executeQuery("SELECT * from kabaret_przedstawienia");
    }

    public static void GetKabaretyByIdPrzedstawienia(double idPrzedstawienia) throws SQLException {
        prepStat = connection.prepareStatement("SELECT * from kabaret_przedstawienia where id_przedstawienia = ?");
        prepStat.setDouble(1,idPrzedstawienia);
        resultSet = prepStat.executeQuery();
    }

    public static void GetPrzedstawieniaByIdKabaretu(double idKabaretu) throws SQLException {
        prepStat = connection.prepareStatement("SELECT * from kabaret_przedstawienia where id_wydarzenia = ?");
        prepStat.setDouble(1,idKabaretu);
        resultSet = prepStat.executeQuery();
    }

    public static void EditKabaretByIdPrzedstawienia(double idPrzedstawienia,double idKabaretu) throws SQLException{
        prepStat = connection.prepareStatement("UPDATE kabaret_przedstawienia SET id_wydarzenia = ? WHERE id_przedstawienia = ?");
        prepStat.setDouble(1,idKabaretu);
        prepStat.setDouble(2,idPrzedstawienia);
        result = prepStat.executeUpdate();
    }

    public static void EditPrzedstawienieByIdKabaretu(double idKabaretu,double idPrzedstawienia) throws SQLException{
        prepStat = connection.prepareStatement("UPDATE kabaret_przedstawienia SET id_przedstawienia = ? WHERE id_wydarzenia = ?");
        prepStat.setDouble(1,idPrzedstawienia);
        prepStat.setDouble(2,idKabaretu);
        result = prepStat.executeUpdate();
    }

    public static void DeleteByIdPrzedstawienia(double idPrzedstawienia) throws SQLException {
        prepStat = connection.prepareStatement("DELETE FROM kabaret_przedstawienia WHERE id_przedstawienia = ?");
        prepStat.setDouble(1,idPrzedstawienia);
        result = prepStat.executeUpdate();
    }

    public static void DeleteByIdKabaretu(double idKabaretu) throws SQLException {
        prepStat = connection.prepareStatement("DELETE FROM kabaret_przedstawienia WHERE id_wydarzenia = ?");
        prepStat.setDouble(1,idKabaretu);
        result = prepStat.executeUpdate();
    }

    public static void DeleteFromKabaretPrzedstawienia(double idKabaretu,double idPrzedstawienia) throws SQLException {
        prepStat = connection.prepareStatement("DELETE FROM kabaret_przedstawienia WHERE id_przedstawienia = ? AND id_wydarzenia = ?");
        prepStat.setDouble(1,idPrzedstawienia);
        prepStat.setDouble(2,idKabaretu);
        result = prepStat.executeUpdate();
    }
}
