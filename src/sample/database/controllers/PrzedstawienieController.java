package sample.database.controllers;

import sample.database.ConnectionWithDatabase;

import java.sql.*;
import java.util.ArrayList;

public class PrzedstawienieController {

    //obiekt tworzący połączenie z bazą danych.
    private static Connection connection = ConnectionWithDatabase.getConnection();
    //obiekt pozwalający tworzyć nowe wyrażenia SQL
    private static Statement statement;
    private static ResultSet resultSet;
    private static PreparedStatement prepStat;
    private static int result;

    public PrzedstawienieController(Connection conn){
        connection = conn;
    }

    public static void addPrzedstawienie(String tytul, String dlugosc, String typ, ArrayList<String> actorsIDs) throws SQLException {
        prepStat = connection.prepareStatement("INSERT INTO przedstawienie(tytul, dlugosc) VALUES(?,?)");
        prepStat.setString(1,tytul);
        prepStat.setString(2,dlugosc);
        result = prepStat.executeUpdate();

        getOnePrzedstawienie(tytul,dlugosc);
        double performanceID = -1;
        while (resultSet.next()) {
            performanceID = resultSet.getDouble(1);
        }
        for (String strID: actorsIDs){
            double actorId = Double.parseDouble(strID);
            AktorzyPrzedstawieniaController.AddAktorzyPrzedstawienia(actorId, performanceID);
        }
        // TODO: 16.02.2020 We must use a type of Performance (CABARET / THEATHRE SPECTACLE)
    }

    public static void getAllFromPrzedstawienie() throws SQLException{
        statement = connection.createStatement();
        resultSet = statement.executeQuery("SELECT * from przedstawienie");
    }

    public static void getOnePrzedstawienie(double id) throws SQLException {
        prepStat = connection.prepareStatement("SELECT * from przedstawienie where id_przedstawienia = ?");
        prepStat.setDouble(1,id);
        resultSet = prepStat.executeQuery();
    }

    public static void getOnePrzedstawienie(String title, String dlugosc) throws SQLException {
        prepStat = connection.prepareStatement("SELECT * from przedstawienie where tytul = ?");
        prepStat.setString(1,title);
        resultSet = prepStat.executeQuery();
    }

    public static ArrayList<Double> getListOfActorsIDsFromPrzedstawienie(double performanceId) throws SQLException {
        return AktorzyPrzedstawieniaController.getAllIDsFromAktorByPerforenceID(performanceId);
    }

    public static String getTitleFromPrzedstawienie(double id) throws SQLException {
        return getOneParameterFromPrzedstawienie(id,"tytul");
    }

    public static String getLenghtFromPrzedstawienie(double id) throws SQLException {
        return getOneParameterFromPrzedstawienie(id,"dlugosc");
    }

    private static String getOneParameterFromPrzedstawienie(double id, String whatDownload) throws SQLException {
        prepStat = connection.prepareStatement("SELECT " + whatDownload + " from przedstawienie where id_przedstawienia = ?");
        prepStat.setDouble(1,id);
        resultSet = prepStat.executeQuery();
        while (resultSet.next()) {
            return resultSet.getString(1);
        }
        return null;
    }

    public static void EditPrzedstawienie(double id,String tytul,double dlugosc) throws SQLException{
        prepStat = connection.prepareStatement("UPDATE przedstawienie SET tytul = ? , dlugosc = ? WHERE id_przedstawienia = ?");
        prepStat.setString(1,tytul);
        prepStat.setDouble(2,dlugosc);
        prepStat.setDouble(3,id);
        result = prepStat.executeUpdate();
    }

    public static void deletePrzedstawienie(double id) throws SQLException {
        prepStat = connection.prepareStatement("DELETE FROM przedstawienie WHERE id_przedstawienia = ?");
        prepStat.setDouble(1,id);
        result = prepStat.executeUpdate();
    }

    public static ArrayList<String> GetResult() throws SQLException {
        ArrayList<String> temp = new ArrayList<>();
        while (resultSet.next()) {
            String idPrzedstawienia = String.valueOf(resultSet.getDouble(1));
            String tytul = resultSet.getString(2);
            String dlugosc = String.valueOf(resultSet.getDouble(3));
            temp.add(idPrzedstawienia);
            temp.add(tytul);
            temp.add(dlugosc);
        }
        return temp;
    }

    public static ArrayList<String> getListOfStrings() throws SQLException {
        ArrayList<String> temp = new ArrayList<>();
        idList.clear();
        while (resultSet.next()) {
            Double id = resultSet.getDouble(1);
            idList.add(id);
            String idPrzedstawienia = String.valueOf(resultSet.getInt(1));
            String tytul = resultSet.getString(2);
            String dlugosc = String.valueOf(resultSet.getDouble(3));
            String allString = tytul + " (time: " + dlugosc  + ") " + " (id: " + idPrzedstawienia + ")";
            temp.add(allString);
        }
        return temp;
    }
    private static ArrayList<Double> idList = new ArrayList<>();
    public static ArrayList<Double> getListOfIDs(){
        return idList;
    }
}
