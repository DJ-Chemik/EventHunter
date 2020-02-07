package sample.database.controllers;

import java.sql.*;

public class UtworController {

    //obiekt tworzący połączenie z bazą danych.
    private static Connection connection;
    //obiekt pozwalający tworzyć nowe wyrażenia SQL
    private static Statement statement;
    private static ResultSet resultSet;
    private static PreparedStatement prepStat;
    private static int result;

    public UtworController(Connection conn){
        connection = conn;
    }

    public static void AddUtwor(String tytul,int rok,String gatunek,double wyswietlenia,double idMuzyka,double idPlyty) throws SQLException {
        prepStat = connection.prepareStatement("INSERT INTO utwór(tytuł, rok_wydania, gatunek, ilość_wyświetleń_na_yt, id_muzyka, id_plyty) VALUES(?,?,?,?,?,?)");
        prepStat.setString(1,tytul);
        prepStat.setInt(2,rok);
        prepStat.setString(3,gatunek);
        prepStat.setDouble(4,wyswietlenia);
        prepStat.setDouble(5,idMuzyka);
        prepStat.setDouble(6,idPlyty);
        result = prepStat.executeUpdate();
    }

    public static void GetAllFromUtwor() throws SQLException{
        statement = connection.createStatement();
        resultSet = statement.executeQuery("SELECT * from utwór");
    }

    public static void GetOneUtwor(double id) throws SQLException {
        prepStat = connection.prepareStatement("SELECT * from utwór where id_utworu = ?");
        prepStat.setDouble(1,id);
        resultSet = prepStat.executeQuery();
    }

    public static void EditUtwor(double id,String tytul,int rok,String gatunek,double wyswietlenia,double idMuzyka,double idPlyty) throws SQLException{
        prepStat = connection.prepareStatement("UPDATE utwór SET tytuł = ? , rok_wydania = ?,gatunek = ?,ilość_wyświetleń_na_yt = ?,id_muzyka =?, id_plyty = ? WHERE id_utworu = ?");
        prepStat.setString(1,tytul);
        prepStat.setInt(2,rok);
        prepStat.setString(3,gatunek);
        prepStat.setDouble(4,wyswietlenia);
        prepStat.setDouble(5,idMuzyka);
        prepStat.setDouble(6,idPlyty);
        prepStat.setDouble(7,id);
        result = prepStat.executeUpdate();
    }

    public static void DeleteUtwor(double id) throws SQLException {
        prepStat = connection.prepareStatement("DELETE FROM utwór WHERE id_utworu = ?");
        prepStat.setDouble(1,id);
        result = prepStat.executeUpdate();
    }
}
