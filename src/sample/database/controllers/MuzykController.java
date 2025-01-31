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
    private static CallableStatement callStat;
    private static int result;

    public MuzykController(Connection conn) {
        connection = conn;
    }

    public static void addMuzyk(String imie, String nazwisko, String pseudonim) throws SQLException {
        prepStat = connection.prepareStatement("INSERT INTO muzyk(imie, nazwisko, pseudonim) VALUES(?,?,?)");
        prepStat.setString(1, imie);
        prepStat.setString(2, nazwisko);
        prepStat.setString(3, pseudonim);
        result = prepStat.executeUpdate();
    }

    public static void getAllFromMuzyk() throws SQLException {
        statement = connection.createStatement();
        resultSet = statement.executeQuery("SELECT * from muzyk");
    }

    public static int PoliczUtworyMuzyka(double idMuzyka) throws SQLException {
        int count = -1;
        String query = "{? = call LiczbaUtworowMuzyka(?)}";
        callStat = connection.prepareCall(query);
        callStat.registerOutParameter(1, Types.INTEGER);
        callStat.setDouble(2, idMuzyka);
        callStat.execute();
        count = callStat.getInt(1);
        return count;
    }

    public static void getOneMuzyk(double id) throws SQLException {
        prepStat = connection.prepareStatement("SELECT * from muzyk where id_muzyka = ?");
        prepStat.setDouble(1, id);
        resultSet = prepStat.executeQuery();
    }

    public static ArrayList<Double> getAllSongsFromMuzyk(double id) throws SQLException {
        return UtworController.getAllIDsFromUtworByMusician(id);
    }

    public static String getNameFromMuzyk(double id) throws SQLException {
        return getOneParameterFromMuzyk(id, "imie");
    }

    public static String getSurameFromMuzyk(double id) throws SQLException {
        return getOneParameterFromMuzyk(id, "nazwisko");
    }

    public static String getNicknameFromMuzyk(double id) throws SQLException {
        return getOneParameterFromMuzyk(id, "pseudonim");
    }

    private static String getOneParameterFromMuzyk(double id, String whatDownload) throws SQLException {
        prepStat = connection.prepareStatement("SELECT " + whatDownload + " from muzyk where id_muzyka = ?");
        prepStat.setDouble(1, id);
        resultSet = prepStat.executeQuery();
        while (resultSet.next()) {
            return resultSet.getString(1);
        }
        return null;
    }

    public static void editMuzyk(double id, String imie, String nazwisko, String pseudonim) throws SQLException {
        prepStat = connection.prepareStatement("UPDATE muzyk SET imie = ? , nazwisko = ?, pseudonim = ? WHERE id_muzyka = ?");
        prepStat.setString(1, imie);
        prepStat.setString(2, nazwisko);
        prepStat.setString(3, pseudonim);
        prepStat.setDouble(4, id);
        result = prepStat.executeUpdate();
    }

    public static void deleteMuzyk(double id) throws SQLException {
        prepStat = connection.prepareStatement("DELETE FROM muzyk WHERE id_muzyka = ?");
        prepStat.setDouble(1, id);
        result = prepStat.executeUpdate();
    }

    public static ArrayList<String> getResult() throws SQLException {
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
        idList.clear();
        while (resultSet.next()) {
            Double id = resultSet.getDouble(1);
            idList.add(id);
            String idStr = String.valueOf(resultSet.getInt(1));
            String imie = resultSet.getString(2);
            String nazwisko = resultSet.getString(3);
            String pseudonim = resultSet.getString(4);
            String allString = imie + " \"" + pseudonim + "\" " + nazwisko + " (id: " + idStr + ")";
            temp.add(allString);
        }
        return temp;
    }

    private static ArrayList<Double> idList = new ArrayList<>();

    public static ArrayList<Double> getListOfIDs() {
        return idList;
    }
}
