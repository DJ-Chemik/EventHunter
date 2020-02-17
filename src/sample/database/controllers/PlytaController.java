package sample.database.controllers;

import sample.database.ConnectionWithDatabase;

import java.sql.*;
import java.util.ArrayList;

public class PlytaController {

    //obiekt tworzący połączenie z bazą danych.
    private static Connection connection = ConnectionWithDatabase.getConnection();
    //obiekt pozwalający tworzyć nowe wyrażenia SQL
    private static Statement statement;
    private static ResultSet resultSet;
    private static PreparedStatement prepStat;
    private static int result;

    public PlytaController(Connection conn){
        connection = conn;
    }

    public static void addPlyta(String tytul, int rok, ArrayList<String> utwory) throws SQLException {
        prepStat = connection.prepareStatement("INSERT INTO płyta(tytuł, rok_wydania) VALUES(?,?)");
        prepStat.setString(1,tytul);
        prepStat.setInt(2,rok);
        result = prepStat.executeUpdate();
        statement = connection.createStatement();
        resultSet = statement.executeQuery("SELECT LAST_INSERT_ID()");
        double id;
        if (resultSet.next()) {
            id = resultSet.getDouble(1);
        }else{
            id = 0;
        }
        UtworController.setIdPlytyFromList(utwory,id);
    }

    public static void addPlyta(String tytul, int rok, ArrayList<String> utwory, ArrayList<String> musicians) throws SQLException {
        prepStat = connection.prepareStatement("INSERT INTO płyta(tytuł, rok_wydania) VALUES(?,?)");
        prepStat.setString(1,tytul);
        prepStat.setInt(2,rok);
        result = prepStat.executeUpdate();
        getOnePlyta(tytul,rok);
        double discID = -1;
        while (resultSet.next()){
            discID = resultSet.getDouble(1);
        }
        for (String strID : musicians){
            double musicianID = Double.parseDouble(strID);
            MuzycyPlytyController.addMuzycyPlyty(musicianID,discID);
        }
        for (String strID : utwory){
            double songID = Double.parseDouble(strID);
            UtworController.editUtwor(songID,discID);
        }
    }

    public static void getAllFromPlyta() throws SQLException{
        statement = connection.createStatement();
        resultSet = statement.executeQuery("SELECT * from płyta");
    }

    public static void getOnePlyta(double id) throws SQLException {
        prepStat = connection.prepareStatement("SELECT * from płyta where id_plyty = ?");
        prepStat.setDouble(1,id);
        resultSet = prepStat.executeQuery();
    }

    public static void getOnePlyta(String title, int year) throws SQLException {
        prepStat = connection.prepareStatement("SELECT * from płyta where tytuł = ?");
        prepStat.setString(1,title);
        resultSet = prepStat.executeQuery();
    }

    public static void editPlyta(double id, String tytul, int rok) throws SQLException{
        prepStat = connection.prepareStatement("UPDATE płyta SET tytuł = ? , rok_wydania = ? WHERE id_plyty = ?");
        prepStat.setString(1,tytul);
        prepStat.setInt(2,rok);
        prepStat.setDouble(3,id);
        result = prepStat.executeUpdate();
    }

    public static void deletePlyta(double id) throws SQLException {
        prepStat = connection.prepareStatement("DELETE FROM płyta WHERE id_plyty = ?");
        prepStat.setDouble(1,id);
        result = prepStat.executeUpdate();
    }

    public static ArrayList<String> getResult() throws SQLException {
        ArrayList<String> temp = new ArrayList<>();
        while (resultSet.next()) {
            String idPlyty = String.valueOf(resultSet.getDouble(1));
            String tytul = resultSet.getString(2);
            String rokWydania = String.valueOf(resultSet.getInt(3));
            temp.add(idPlyty);
            temp.add(tytul);
            temp.add(rokWydania);
        }
        return temp;
    }

    public static ArrayList<String> getListOfStrings() throws SQLException {
        ArrayList<String> temp = new ArrayList<>();
        while (resultSet.next()) {
            Double id = resultSet.getDouble(1);
            idList.clear();
            idList.add(id);
            String idStr = String.valueOf(resultSet.getInt(1));
            String tytul = resultSet.getString(2);
            String rokWydania = String.valueOf(resultSet.getInt(3));
            String allString = tytul + " " + rokWydania  + " (id: " + idStr + ")";
            temp.add(allString);
        }
        return temp;
    }
    private static ArrayList<Double> idList = new ArrayList<>();
    public static ArrayList<Double> getListOfIDs(){
        return idList;
    }
}
