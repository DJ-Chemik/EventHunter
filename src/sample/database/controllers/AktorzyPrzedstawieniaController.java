package sample.database.controllers;

import java.sql.*;

public class AktorzyPrzedstawieniaController {

    //obiekt tworzący połączenie z bazą danych.
    private static Connection connection;
    //obiekt pozwalający tworzyć nowe wyrażenia SQL
    private static Statement statement;
    private static ResultSet resultSet;
    private static PreparedStatement prepStat;
    private static int result;

    public AktorzyPrzedstawieniaController(Connection conn){
        connection = conn;
    }

    public static void AddAktorzyPrzedstawienia(double idAktora,double idPrzedstawienia) throws SQLException {
        prepStat = connection.prepareStatement("INSERT INTO aktorzy_przedstawienia(id_aktora, id_przedstawienia) VALUES(?,?)");
        prepStat.setDouble(1,idAktora);
        prepStat.setDouble(2,idPrzedstawienia);
        result = prepStat.executeUpdate();
    }

    public static void GetAllFromAktorzyPrzedstawienia() throws SQLException{
        statement = connection.createStatement();
        resultSet = statement.executeQuery("SELECT * from aktorzy_przedstawienia");
    }

    public static void GetAktorzyByIdPrzedstawienia(double idPrzedstawienia) throws SQLException {
        prepStat = connection.prepareStatement("SELECT * from aktorzy_przedstawienia where id_przedstawienia = ?");
        prepStat.setDouble(1,idPrzedstawienia);
        resultSet = prepStat.executeQuery();
    }

    public static void GetPrzedstawieniaByIdAktor(double idAktora) throws SQLException {
        prepStat = connection.prepareStatement("SELECT * from aktorzy_przedstawienia where id_aktora = ?");
        prepStat.setDouble(1,idAktora);
        resultSet = prepStat.executeQuery();
    }

    public static void EditAktorByIdPrzedstawienia(double idPrzedstawienia,double idAktora) throws SQLException{
        prepStat = connection.prepareStatement("UPDATE aktorzy_przedstawienia SET id_aktora = ? WHERE id_przedstawienia = ?");
        prepStat.setDouble(1,idAktora);
        prepStat.setDouble(2,idPrzedstawienia);
        result = prepStat.executeUpdate();
    }

    public static void EditPrzedstawienieByIdAktora(double idAktora,double idPrzedstawienia) throws SQLException{
        prepStat = connection.prepareStatement("UPDATE aktorzy_przedstawienia SET id_przedstawienia = ? WHERE id_aktora = ?");
        prepStat.setDouble(1,idPrzedstawienia);
        prepStat.setDouble(2,idAktora);
        result = prepStat.executeUpdate();
    }

    public static void DeleteByIdPrzedstawienia(double idPrzedstawienia) throws SQLException {
        prepStat = connection.prepareStatement("DELETE FROM aktorzy_przedstawienia WHERE id_przedstawienia = ?");
        prepStat.setDouble(1,idPrzedstawienia);
        result = prepStat.executeUpdate();
    }

    public static void DeleteByIdAktora(double idAktora) throws SQLException {
        prepStat = connection.prepareStatement("DELETE FROM aktorzy_przedstawienia WHERE id_aktora = ?");
        prepStat.setDouble(1,idAktora);
        result = prepStat.executeUpdate();
    }

    public static void DeleteFromAktorzyPrzedstawienia(double idAktora,double idPrzedstawienia) throws SQLException {
        prepStat = connection.prepareStatement("DELETE FROM aktorzy_przedstawienia WHERE id_przedstawienia = ? AND id_aktora = ?");
        prepStat.setDouble(1,idPrzedstawienia);
        prepStat.setDouble(2,idAktora);
        result = prepStat.executeUpdate();
    }
}
