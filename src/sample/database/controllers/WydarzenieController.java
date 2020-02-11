package sample.database.controllers;

import sample.database.ConnectionWithDatabase;

import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class WydarzenieController {

    //obiekt tworzący połączenie z bazą danych.
    private static Connection connection = ConnectionWithDatabase.getConnection();
    //obiekt pozwalający tworzyć nowe wyrażenia SQL
    private static Statement statement;
    private static ResultSet resultSet;
    private static PreparedStatement prepStat;
    private static int result;
    private static SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
    // example of string: String dateInString = "07/06/2013";

    public WydarzenieController(Connection conn){
        connection = conn;
    }

    private static java.sql.Date ConvertDate(String date) throws ParseException {
        java.util.Date today = formatter.parse(date);
        return new java.sql.Date(today.getTime());
    }

    public static double GetIdFromMiejsce(String nazwa,String typ) throws SQLException {
        prepStat = connection.prepareStatement("SELECT id_obiektu FROM miejsce WHERE nazwa = ? AND typ_obiektu = ?");
        prepStat.setString(1,nazwa);
        prepStat.setString(2,typ);
        resultSet = prepStat.executeQuery();
        double temp = 0;
        while(resultSet.next()) {
            temp = resultSet.getDouble(1);
        }
        return temp;
    }

    public static void AddWydarzenie(String nazwa,String data,double cenaBiletu,double iloscMiejsc,String typ,String nazwaM,String typM) throws SQLException, ParseException {
        double idObiektu = GetIdFromMiejsce(nazwaM,typM);
        prepStat = connection.prepareStatement("INSERT INTO wydarzenie(nazwa, data, cena_biletu, ilosc_miejsc, typ, id_obiektu) VALUES(?,?,?,?,?,?)");
        prepStat.setString(1,nazwa);
        prepStat.setObject(2, ConvertDate(data));
        prepStat.setDouble(3,cenaBiletu);
        prepStat.setDouble(4,iloscMiejsc);
        prepStat.setString(5,typ);
        prepStat.setDouble(6,idObiektu);
        result = prepStat.executeUpdate();
        if(typ.toUpperCase().equals("KABARET")){
            statement = connection.createStatement();
            result = statement.executeUpdate("INSERT INTO kabaret(id_wydarzenia) VALUES (LAST_INSERT_ID())");
        } else if (typ.toUpperCase().equals("KONCERT")){
            statement = connection.createStatement();
            result = statement.executeUpdate("INSERT INTO koncert(id_wydarzenia) VALUES (LAST_INSERT_ID())");
        } else if (typ.toUpperCase().equals("WYSTĘP TEATRALNY")){
            statement = connection.createStatement();
            result = statement.executeUpdate("INSERT INTO wystep_teatralny(id_wydarzenia) VALUES (LAST_INSERT_ID())");
        }
    }

    public static void GetAllFromWydarzenie() throws SQLException{
        statement = connection.createStatement();
        resultSet = statement.executeQuery("SELECT * from wydarzenie");
    }

    public static void GetOneWydarzenie(double id) throws SQLException {
        prepStat = connection.prepareStatement("SELECT * from wydarzenie where id_wydarzenia = ?");
        prepStat.setDouble(1,id);
        resultSet = prepStat.executeQuery();
    }

    public static void EditWydarzenie(double id,String nazwa,String data,double cenaBiletu,double iloscMiejsc,String typ,double idObiektu) throws SQLException, ParseException {
        prepStat = connection.prepareStatement("UPDATE wydarzenie SET nazwa = ? , data = ?,cena_biletu = ?,ilosc_miejsc = ?,typ =?, id_obiektu = ? WHERE id_wydarzenia = ?");
        prepStat.setString(1,nazwa);
        prepStat.setDate(2,ConvertDate(data));
        prepStat.setDouble(3,cenaBiletu);
        prepStat.setDouble(4,iloscMiejsc);
        prepStat.setString(5,typ);
        prepStat.setDouble(6,idObiektu);
        prepStat.setDouble(7,id);
        result = prepStat.executeUpdate();
    }

    public static void DeleteWydarzenie(double id) throws SQLException {
        prepStat = connection.prepareStatement("DELETE FROM wydarzenie WHERE id_wydarzenia = ?");
        prepStat.setDouble(1,id);
        result = prepStat.executeUpdate();
    }

    public static ArrayList<String> GetResult() throws SQLException {
        ArrayList<String> temp = new ArrayList<>();
        while (resultSet.next()) {
            String idWydarzenia = String.valueOf(resultSet.getDouble(1));
            String nazwa = resultSet.getString(2);
            String data = String.valueOf(resultSet.getDate(3));
            String cena = String.valueOf(resultSet.getDouble(4));
            String ilosc = String.valueOf(resultSet.getDouble(5));
            String typ = resultSet.getString(6);
            String idObiektu = String.valueOf(resultSet.getDouble(7));
            temp.add(idWydarzenia);
            temp.add(nazwa);
            temp.add(data);
            temp.add(cena);
            temp.add(ilosc);
            temp.add(typ);
            temp.add(idObiektu);
        }
        return temp;
    }
}
