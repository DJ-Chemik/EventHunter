package sample.database.controllers;

import sample.database.ConnectionWithDatabase;

import java.sql.*;
import java.util.ArrayList;

public class AktorController {

    //obiekt tworzący połączenie z bazą danych.
    private static Connection connection = ConnectionWithDatabase.getConnection();
    //obiekt pozwalający tworzyć nowe wyrażenia SQL
    private static Statement statement;
    private static ResultSet resultSet;
    private static PreparedStatement prepStat;
    private static CallableStatement callStat;
    private static int result;

    public AktorController(Connection conn){
        connection = conn;
    }

    public static void AddAktor(String imie,String nazwisko,String grupa) throws SQLException {
        String query = "{ call AddAktor(?,?,?) }";
        callStat = connection.prepareCall(query);
        callStat.setString(1, imie);
        callStat.setString(2, nazwisko);
        callStat.setString(3, grupa);
        result = callStat.executeUpdate();
    }

    public static void getAllFromAktor() throws SQLException{
        statement = connection.createStatement();
        resultSet = statement.executeQuery("SELECT * from aktor");
    }

    public static void getOneAktor(double id) throws SQLException {
        prepStat = connection.prepareStatement("SELECT * from aktor where id_aktora = ?");
        prepStat.setDouble(1,id);
        resultSet = prepStat.executeQuery();
    }

    public static void editAktor(double id, String imie, String nazwisko, String grupa) throws SQLException{
        prepStat = connection.prepareStatement("UPDATE aktor SET imie = ? , nazwisko = ?, grupa = ? WHERE id_aktora = ?");
        prepStat.setString(1,imie);
        prepStat.setString(2,nazwisko);
        prepStat.setString(3,grupa);
        prepStat.setDouble(4,id);
        result = prepStat.executeUpdate();
    }

    public static void deleteAktor(double id) throws SQLException {
        prepStat = connection.prepareStatement("DELETE FROM aktor WHERE id_aktora = ?");
        prepStat.setDouble(1,id);
        result = prepStat.executeUpdate();
    }

    public static ArrayList<String> getResult() throws SQLException {
        ArrayList<String> temp = new ArrayList<>();
        while (resultSet.next()) {
            String id = String.valueOf(resultSet.getDouble(1));
            String imie = resultSet.getString(2);
            String nazwisko = resultSet.getString(3);
            String grupa = resultSet.getString(4);
            temp.add(id);
            temp.add(imie);
            temp.add(nazwisko);
            temp.add(grupa);
        }
        return temp;
    }
    public static ArrayList<String> getListOfStrings() throws SQLException {
        ArrayList<String> temp = new ArrayList<>();
        idList.clear();
        while (resultSet.next()) {
            Double id = resultSet.getDouble(1);
            idList.add(id);
            String idStr = String.valueOf(resultSet.getInt(1));
            String imie = resultSet.getString(2);
            String nazwisko = resultSet.getString(3);
            String grupa = resultSet.getString(4);
            String allString = imie + " " + nazwisko  + " (" + grupa + ") (id: " + idStr + ")";
            temp.add(allString);
        }
        return temp;
    }
    private static ArrayList<Double> idList = new ArrayList<>();
    public static ArrayList<Double> getListOfIDs(){
        return idList;
    }

    public static void close(){
        try {
            resultSet.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
