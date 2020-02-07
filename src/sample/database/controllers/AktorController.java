package sample.database.controllers;

import java.sql.*;

public class AktorController {

    //obiekt tworzący połączenie z bazą danych.
    private static Connection connection;
    //obiekt pozwalający tworzyć nowe wyrażenia SQL
    private static Statement statement;
    private static ResultSet resultSet;
    private static PreparedStatement prepStat;
    private static int result;

    public AktorController(Connection conn){
        connection = conn;
    }

    public static void AddAktor(String imie,String nazwisko,String grupa) throws SQLException {
        prepStat = connection.prepareStatement("INSERT INTO aktor(imie, nazwisko, nazwa_grupy) VALUES(?,?,?)");
        prepStat.setString(1,imie);
        prepStat.setString(2,nazwisko);
        prepStat.setString(3,grupa);
        result = prepStat.executeUpdate();
    }

    public static void GetAllFromAktor() throws SQLException{
        statement = connection.createStatement();
        resultSet = statement.executeQuery("SELECT * from aktor");
    }

    public static void GetOneAktor(double id) throws SQLException {
        prepStat = connection.prepareStatement("SELECT * from aktor where id_aktora = ?");
        prepStat.setDouble(1,id);
        resultSet = prepStat.executeQuery();
    }

    public static void EditAktor(double id,String imie,String nazwisko,String grupa) throws SQLException{
        prepStat = connection.prepareStatement("UPDATE aktor SET imie = ? , nazwisko = ?, grupa = ? WHERE id_aktora = ?");
        prepStat.setString(1,imie);
        prepStat.setString(2,nazwisko);
        prepStat.setString(3,grupa);
        prepStat.setDouble(4,id);
        result = prepStat.executeUpdate();
    }

    public static void DeleteAktor(double id) throws SQLException {
        prepStat = connection.prepareStatement("DELETE FROM aktor WHERE id_aktora = ?");
        prepStat.setDouble(1,id);
        result = prepStat.executeUpdate();
    }

    public static void displayResultOfQuery() throws SQLException {
        while (resultSet.next()) {
            int id = resultSet.getInt(1);
            String name = resultSet.getString(2);
            String surname = resultSet.getString(3);
            String band = resultSet.getString(4);
            System.out.println("Id: " + id);
            System.out.println("Name: " + name);
            System.out.println("Surname: " + surname);
            System.out.println("Band: " + band);
            System.out.println("-------------------");
        }
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
