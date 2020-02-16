package sample.database.controllers;

import sample.database.ConnectionWithDatabase;

import java.sql.*;
import java.util.ArrayList;

public class MiejsceController {
    //obiekt tworzący połączenie z bazą danych.
    private static Connection connection = ConnectionWithDatabase.getConnection();
    //obiekt pozwalający tworzyć nowe wyrażenia SQL
    private static Statement statement;
    private static ResultSet resultSet;
    private static PreparedStatement prepStat;
    private static int result;

    public MiejsceController(Connection conn){
        connection = conn;
    }


    public static double GetIdFromMiejscowosc(String nazwa,String wojewodztwo) throws SQLException {
        prepStat = connection.prepareStatement("SELECT id_miasta FROM miejscowość WHERE nazwa = ? AND województwo = ?");
        prepStat.setString(1,nazwa);
        prepStat.setString(2,wojewodztwo);
        resultSet = prepStat.executeQuery();
        double temp = 0;
        while(resultSet.next()) {
            temp = resultSet.getDouble(1);
        }
        return temp;
    }

    public static void AddMiejsce(String nazwa,String typ,String nazwaMiasta,String wojewodztwo) throws SQLException {
        double idMiasta = GetIdFromMiejscowosc(nazwaMiasta,wojewodztwo);
        prepStat = connection.prepareStatement("INSERT INTO miejsce(nazwa, typ_obiektu, id_miasta) VALUES(?,?,?)");
        prepStat.setString(1,nazwa);
        prepStat.setString(2,typ);
        prepStat.setDouble(3,idMiasta);
        result = prepStat.executeUpdate();
    }

    public static void AddMiejsce(String nazwa,String typ,String id) throws SQLException {
        double idMiasta = Double.parseDouble(id);
        prepStat = connection.prepareStatement("INSERT INTO miejsce(nazwa, typ_obiektu, id_miasta) VALUES(?,?,?)");
        prepStat.setString(1,nazwa);
        prepStat.setString(2,typ);
        prepStat.setDouble(3,idMiasta);
        result = prepStat.executeUpdate();
    }

    public static void GetAllFromMiejsce() throws SQLException{
        statement = connection.createStatement();
        resultSet = statement.executeQuery("SELECT * from miejsce");
    }

    public static void GetOneMiejsce(double id) throws SQLException {
        prepStat = connection.prepareStatement("SELECT * from miejsce where id_obiektu = ?");
        prepStat.setDouble(1,id);
        resultSet = prepStat.executeQuery();
    }

    public static void EditMiejsce(double id,String nazwa,String typ,String idMiasta) throws SQLException{
        prepStat = connection.prepareStatement("UPDATE miejsce SET nazwa = ? , typ_obiektu = ?, id_miasta = ? WHERE id_obiektu = ?");
        prepStat.setString(1,nazwa);
        prepStat.setString(2,typ);
        prepStat.setString(3,idMiasta);
        prepStat.setDouble(4,id);
        result = prepStat.executeUpdate();
    }

    public static void DeleteMiejsce(double id) throws SQLException {
        prepStat = connection.prepareStatement("DELETE FROM miejsce WHERE id_obiektu = ?");
        prepStat.setDouble(1,id);
        result = prepStat.executeUpdate();
    }

    public static ArrayList<String> GetResult() throws SQLException {
        ArrayList<String> temp = new ArrayList<>();
        while (resultSet.next()) {
            String idObiektu = String.valueOf(resultSet.getDouble(1));
            String nazwa = resultSet.getString(2);
            String typ = resultSet.getString(3);
            String idMiasta = String.valueOf(resultSet.getDouble(4));
            temp.add(idObiektu);
            temp.add(nazwa);
            temp.add(typ);
            temp.add(idMiasta);
        }
        return temp;
    }
}
