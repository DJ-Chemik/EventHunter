package sample.database.controllers;

import sample.database.ConnectionWithDatabase;

import java.sql.*;

public class TeatrPrzedstawieniaController {
    //obiekt tworzący połączenie z bazą danych.
    private static Connection connection = ConnectionWithDatabase.getConnection();
    //obiekt pozwalający tworzyć nowe wyrażenia SQL
    private static Statement statement;
    private static ResultSet resultSet;
    private static PreparedStatement prepStat;
    private static int result;

    public static void addTeatrPrzedstawienia(double idWystepuTeatralnego, double idPrzedstawienia) throws SQLException {
        prepStat = connection.prepareStatement("INSERT INTO teatr_przedstawienia(id_wydarzenia, id_przedstawienia) VALUES(?,?)");
        prepStat.setDouble(1,idWystepuTeatralnego);
        prepStat.setDouble(2,idPrzedstawienia);
        result = prepStat.executeUpdate();
    }

    public static void GetAllFromTeatrPrzedstawienia() throws SQLException{
        statement = connection.createStatement();
        resultSet = statement.executeQuery("SELECT * from teatr_przedstawienia");
    }

    public static void GetPrzedstawieniaByIdWydarzenia(double idWydarzenia) throws SQLException {
        prepStat = connection.prepareStatement("SELECT * from teatr_przedstawienia where id_wydarzenia = ?");
        prepStat.setDouble(1,idWydarzenia);
        resultSet = prepStat.executeQuery();
    }

    public static void GetWydarzeniaByIdPrzedstawienia(double idPrzedstawienia) throws SQLException {
        prepStat = connection.prepareStatement("SELECT * from teatr_przedstawienia where id_przedstawienia = ?");
        prepStat.setDouble(1,idPrzedstawienia);
        resultSet = prepStat.executeQuery();
    }

    public static void EditWydarzenieByIdPrzedstawienia(double idPrzedstawienia,double idWydarzenia) throws SQLException{
        prepStat = connection.prepareStatement("UPDATE teatr_przedstawienia SET id_wydarzenia = ? WHERE id_przedstawienia = ?");
        prepStat.setDouble(1,idWydarzenia);
        prepStat.setDouble(2,idPrzedstawienia);
        result = prepStat.executeUpdate();
    }

    public static void EditPrzedstawienieByIdWydarzenia(double idWydarzenia,double idPrzedstawienia) throws SQLException{
        prepStat = connection.prepareStatement("UPDATE teatr_przedstawienia SET id_przedstawienia = ? WHERE id_wydarzenia = ?");
        prepStat.setDouble(1,idPrzedstawienia);
        prepStat.setDouble(2,idWydarzenia);
        result = prepStat.executeUpdate();
    }

    public static void DeleteByIdPrzedstawienia(double idPrzedstawienia) throws SQLException {
        prepStat = connection.prepareStatement("DELETE FROM teatr_przedstawienia WHERE id_przedstawienia = ?");
        prepStat.setDouble(1,idPrzedstawienia);
        result = prepStat.executeUpdate();
    }

    public static void DeleteByIdWydarzenia(double idWydarzenia) throws SQLException {
        prepStat = connection.prepareStatement("DELETE FROM teatr_przedstawienia WHERE id_wydarzenia = ?");
        prepStat.setDouble(1,idWydarzenia);
        result = prepStat.executeUpdate();
    }

    public static void DeleteFromTeatrPrzedstawienia(double idWydarzenia,double idPrzedstawienia) throws SQLException {
        prepStat = connection.prepareStatement("DELETE FROM teatr_przedstawienia WHERE id_przedstawienia = ? AND id_wydarzenia = ?");
        prepStat.setDouble(1,idPrzedstawienia);
        prepStat.setDouble(2,idWydarzenia);
        result = prepStat.executeUpdate();
    }
}
