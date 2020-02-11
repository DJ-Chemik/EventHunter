package sample.database.controllers;

import java.sql.*;
import java.util.ArrayList;

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

    public static void setConnection(Connection conn){
        connection = conn;
    }

    public static double GetIdFromMuzyk(String imie,String nazwisko,String pseudonim) throws SQLException {
        prepStat = connection.prepareStatement("SELECT id_muzyka FROM muzyk WHERE imie = ? AND nazwisko = ? AND pseudonim = ?");
        prepStat.setString(1,imie);
        prepStat.setString(2,nazwisko);
        prepStat.setString(3,pseudonim);
        resultSet = prepStat.executeQuery();
        double temp = 0;
        while(resultSet.next()) {
            temp = resultSet.getDouble(1);
        }
        return temp;
    }

    public static double GetIdFromPlyta(String tytuł,int rokWydania ) throws SQLException {
        prepStat = connection.prepareStatement("SELECT id_plyty FROM płyta WHERE tytuł = ? AND rok_wydania = ?");
        prepStat.setString(1,tytuł);
        prepStat.setInt(2,rokWydania);
        resultSet = prepStat.executeQuery();
        double temp = 0;
        while(resultSet.next()) {
            temp = resultSet.getDouble(1);
        }
        return temp;
    }

    public static void AddUtwor(String tytul,int rok,String gatunek,double wyswietlenia,String imieM,String nazwiskoM,String pseudonimM,String tytułP,int rokWydaniaP) throws SQLException {
        double idMuzyka = GetIdFromMuzyk(imieM,nazwiskoM,pseudonimM);
        double idPlyty = GetIdFromPlyta(tytułP,rokWydaniaP);
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

    public static ArrayList<String> GetResult() throws SQLException {
        ArrayList<String> temp = new ArrayList<>();
        while (resultSet.next()) {
            String idUtworu = String.valueOf(resultSet.getDouble(1));
            String tytul = resultSet.getString(2);
            String rokWydania = String.valueOf(resultSet.getInt(3));
            String gatunek = resultSet.getString(4);
            String wyswietlenia = String.valueOf(resultSet.getDouble(5));
            String idMuzyka = String.valueOf(resultSet.getDouble(6));
            String idPlyty = String.valueOf(resultSet.getDouble(7));
            temp.add(idUtworu);
            temp.add(tytul);
            temp.add(rokWydania);
            temp.add(gatunek);
            temp.add(wyswietlenia);
            temp.add(idMuzyka);
            temp.add(idPlyty);
        }
        return temp;
    }
}
