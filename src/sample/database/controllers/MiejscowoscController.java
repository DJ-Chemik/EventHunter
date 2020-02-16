package sample.database.controllers;

import sample.database.ConnectionWithDatabase;

import java.sql.*;
import java.util.ArrayList;

public class MiejscowoscController {

    //obiekt tworzący połączenie z bazą danych.
    private static Connection connection = ConnectionWithDatabase.getConnection();
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

    public static void getAllFromMiejscowosc() throws SQLException{
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

    public static ArrayList<String> getResult() throws SQLException {
        ArrayList<String> temp = new ArrayList<>();
        while (resultSet.next()) {
            String id = String.valueOf(resultSet.getDouble(1));
            String nazwa = resultSet.getString(2);
            String woj = resultSet.getString(3);
            String kod = resultSet.getString(4);
            temp.add(id);
            temp.add(nazwa);
            temp.add(woj);
            temp.add(kod);
        }
        return temp;
    }

    public static ArrayList<String> getListOfStrings() throws SQLException {
        ArrayList<String> temp = new ArrayList<>();
        ResultSet rs = resultSet;
        while (rs.next()) {

            Double id = resultSet.getDouble(1);
            idList.add(id);
            String idStr = String.valueOf(resultSet.getInt(1));
            String nazwa = resultSet.getString(2);
            String woj = resultSet.getString(3);
            String kod = resultSet.getString(4);
            temp.add(nazwa  + " [" + woj + "] (" + kod + ") (id: " + idStr + ")" );
        }
        return temp;
    }

    private static ArrayList<Double> idList = new ArrayList<>();

    public static ArrayList<Double> getListOfIds() throws SQLException {
//        ArrayList<Double> temp = new ArrayList<>();
//        ResultSet rs = resultSet;
//        while (rs.next()) {
//            Double id = resultSet.getDouble(1);
//            temp.add(id);
//        }

        return idList;
    }

}
