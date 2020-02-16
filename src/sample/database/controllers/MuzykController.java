package sample.database.controllers;

import sample.database.ConnectionWithDatabase;

import java.sql.*;
import java.util.ArrayList;

public class MuzykController {

    //obiekt tworzący połączenie z bazą danych.
    private static Connection connection = ConnectionWithDatabase.getConnection();
    //obiekt pozwalający tworzyć nowe wyrażenia SQL
    private static Statement statement;
    private static ResultSet resultSet;
    private static PreparedStatement prepStat;
    private static int result;

    public MuzykController(Connection conn){
        connection = conn;
    }

    public static void addMuzyk(String imie, String nazwisko, String pseudonim) throws SQLException {
        prepStat = connection.prepareStatement("INSERT INTO muzyk(imie, nazwisko, pseudonim) VALUES(?,?,?)");
        prepStat.setString(1,imie);
        prepStat.setString(2,nazwisko);
        prepStat.setString(3,pseudonim);
        result = prepStat.executeUpdate();
    }

    public static void getAllFromMuzyk() throws SQLException{
        statement = connection.createStatement();
        resultSet = statement.executeQuery("SELECT * from muzyk");
    }

    public static void getOneMuzyk(double id) throws SQLException {
        prepStat = connection.prepareStatement("SELECT * from muzyk where id_muzyka = ?");
        prepStat.setDouble(1,id);
        resultSet = prepStat.executeQuery();
    }

    public static void EditMuzyk(double id,String imie,String nazwisko,String pseudonim) throws SQLException{
        prepStat = connection.prepareStatement("UPDATE muzyk SET imie = ? , nazwisko = ?, pseudonim = ? WHERE id_muzyka = ?");
        prepStat.setString(1,imie);
        prepStat.setString(2,nazwisko);
        prepStat.setString(3,pseudonim);
        prepStat.setDouble(4,id);
        result = prepStat.executeUpdate();
    }

    public static void DeleteMuzyk(double id) throws SQLException {
        prepStat = connection.prepareStatement("DELETE FROM muzyk WHERE id_muzyka = ?");
        prepStat.setDouble(1,id);
        result = prepStat.executeUpdate();
    }

    public static ArrayList<String> GetResult() throws SQLException {
        ArrayList<String> temp = new ArrayList<>();
        while (resultSet.next()) {
            String idMuzyka = String.valueOf(resultSet.getDouble(1));
            String imie = resultSet.getString(2);
            String nazwisko = resultSet.getString(3);
            String pseudonim = resultSet.getString(4);
            temp.add(idMuzyka);
            temp.add(imie);
            temp.add(nazwisko);
            temp.add(pseudonim);
        }
        return temp;
    }

    public static ArrayList<String> getListOfStrings() throws SQLException {
        ArrayList<String> temp = new ArrayList<>();
        while (resultSet.next()) {
            Double id = resultSet.getDouble(1);
            idList.add(id);
            String idStr = String.valueOf(resultSet.getInt(1));
            String imie = resultSet.getString(2);
            String nazwisko = resultSet.getString(3);
            String pseudonim = resultSet.getString(4);
            String allString = imie + " \"" + pseudonim + "\" " + nazwisko  + " (id: " + idStr + ")";
            temp.add(allString);
        }
        return temp;
    }
    private static ArrayList<Double> idList = new ArrayList<>();
    public static ArrayList<Double> getListOfIDs(){
        return idList;
    }
}
