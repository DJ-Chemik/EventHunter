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
    private static SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

    // example of string: String dateInString = "2013-07-06";
    public WydarzenieController(Connection conn) {
        connection = conn;
    }

    private static java.sql.Date convertDate(String date) throws ParseException {
        java.util.Date today = formatter.parse(date);
        return new java.sql.Date(today.getTime());
    }

    public static double getIdFromMiejsce(String nazwa, String typ) throws SQLException {
        prepStat = connection.prepareStatement("SELECT id_obiektu FROM miejsce WHERE nazwa = ? AND typ_obiektu = ?");
        prepStat.setString(1, nazwa);
        prepStat.setString(2, typ);
        resultSet = prepStat.executeQuery();
        double temp = 0;
        while (resultSet.next()) {
            temp = resultSet.getDouble(1);
        }
        return temp;
    }

    public static void addWydarzenie(String nazwa, String data, double cenaBiletu, double iloscMiejsc, String typ, String nazwaM, String typM) throws SQLException, ParseException {
        double idObiektu = getIdFromMiejsce(nazwaM, typM);
        prepStat = connection.prepareStatement("INSERT INTO wydarzenie(nazwa, data, cena_biletu, ilosc_miejsc, typ, id_obiektu) VALUES(?,?,?,?,?,?)");
        prepStat.setString(1, nazwa);
        prepStat.setDate(2, convertDate(data));
        prepStat.setDouble(3, cenaBiletu);
        prepStat.setDouble(4, iloscMiejsc);
        prepStat.setString(5, typ);
        prepStat.setDouble(6, idObiektu);
        result = prepStat.executeUpdate();
        if (typ.toUpperCase().equals("KABARET")) {
            statement = connection.createStatement();
            result = statement.executeUpdate("INSERT INTO kabaret(id_wydarzenia) VALUES (LAST_INSERT_ID())");
        } else if (typ.toUpperCase().equals("KONCERT")) {
            statement = connection.createStatement();
            result = statement.executeUpdate("INSERT INTO koncert(id_wydarzenia) VALUES (LAST_INSERT_ID())");
        } else if (typ.toUpperCase().equals("WYSTĘP TEATRALNY")) {
            statement = connection.createStatement();
            result = statement.executeUpdate("INSERT INTO wystep_teatralny(id_wydarzenia) VALUES (LAST_INSERT_ID())");
        }
    }

    public static void addWydarzenie(String nazwa, String data, double cenaBiletu, double iloscMiejsc, String typ, String placeID, ArrayList<String> showsIDs) throws SQLException, ParseException {
        double placeIdDouble = Double.parseDouble(placeID);
        prepStat = connection.prepareStatement("INSERT INTO wydarzenie(nazwa, data, cena_biletu, ilosc_miejsc, typ, id_obiektu) VALUES(?,?,?,?,?,?)");
        prepStat.setString(1, nazwa);
        prepStat.setDate(2, convertDate(data));
        prepStat.setDouble(3, cenaBiletu);
        prepStat.setDouble(4, iloscMiejsc);
        prepStat.setString(5, typ);
        prepStat.setDouble(6, placeIdDouble);
        result = prepStat.executeUpdate();
        if (typ.toUpperCase().equals("KABARET")) {
            statement = connection.createStatement();
            result = statement.executeUpdate("INSERT INTO kabaret(id_wydarzenia) VALUES (LAST_INSERT_ID())");
        } else if (typ.toUpperCase().equals("KONCERT")) {
            statement = connection.createStatement();
            result = statement.executeUpdate("INSERT INTO koncert(id_wydarzenia) VALUES (LAST_INSERT_ID())");
        } else if (typ.toUpperCase().equals("WYSTĘP TEATRALNY")) {
            statement = connection.createStatement();
            result = statement.executeUpdate("INSERT INTO wystep_teatralny(id_wydarzenia) VALUES (LAST_INSERT_ID())");
        }

        // getOneWydarzenie(nazwa,cenaBiletu,iloscMiejsc);
        double eventID = -1;
        statement = connection.createStatement();
        resultSet = statement.executeQuery("SELECT LAST_INSERT_ID()");
        while (resultSet.next()) {
            eventID = resultSet.getDouble(1);
        }
        for (String strID : showsIDs) {
            double showId = Double.parseDouble(strID);
            if (typ.toUpperCase().equals("KABARET")) {
                KabaretPrzedstawieniaController.addKabaretPrzedstawienia(eventID, showId);
            } else if (typ.toUpperCase().equals("KONCERT")) {
                KoncertMuzycyController.addKoncertMuzycy(eventID, showId);
            } else if (typ.toUpperCase().equals("WYSTĘP TEATRALNY")) {
                TeatrPrzedstawieniaController.addTeatrPrzedstawienia(eventID, showId);
            }
        }
    }

    public static void editWydarzenie(double id, String nazwa, String data, double cenaBiletu, double iloscMiejsc, String typ, String placeID, ArrayList<String> showsIDs) throws SQLException, ParseException {
        double placeIdDouble = Double.parseDouble(placeID);
        String oldType = getTypeFromWydarzenie(id);
        prepStat = connection.prepareStatement("UPDATE wydarzenie SET nazwa = ? , data = ?,cena_biletu = ?,ilosc_miejsc = ?,typ =?, id_obiektu = ? WHERE id_wydarzenia = ?");
        prepStat.setString(1, nazwa);
        prepStat.setDate(2, convertDate(data));
        prepStat.setDouble(3, cenaBiletu);
        prepStat.setDouble(4, iloscMiejsc);
        prepStat.setString(5, typ);
        prepStat.setDouble(6, placeIdDouble);
        prepStat.setDouble(7, id);
        result = prepStat.executeUpdate();
        if (!oldType.toUpperCase().equals(typ.toUpperCase())) {
            //  Usuwanie starego wpisu
            if (oldType.toUpperCase().equals("KABARET")) {
                prepStat = connection.prepareStatement("DELETE FROM kabaret WHERE id_wydarzenia = ?");
                prepStat.setDouble(1, id);
                result = prepStat.executeUpdate();
            } else if (typ.toUpperCase().equals("KONCERT")) {
                prepStat = connection.prepareStatement("DELETE FROM koncert WHERE id_wydarzenia = ?");
                prepStat.setDouble(1, id);
                result = prepStat.executeUpdate();
            } else if (typ.toUpperCase().equals("WYSTĘP TEATRALNY")) {
                prepStat = connection.prepareStatement("DELETE FROM wystep_teatralny WHERE id_wydarzenia = ?");
                prepStat.setDouble(1, id);
                result = prepStat.executeUpdate();
            }
            //Dodanie nowego wpisu
            if (typ.toUpperCase().equals("KABARET")) {
                prepStat = connection.prepareStatement("INSERT INTO kabaret(id_wydarzenia) VALUES (?)");
                prepStat.setDouble(1, id);
                result = prepStat.executeUpdate();
            } else if (typ.toUpperCase().equals("KONCERT")) {
                prepStat = connection.prepareStatement("INSERT INTO koncert(id_wydarzenia) VALUES (?)");
                prepStat.setDouble(1, id);
                result = prepStat.executeUpdate();
            } else if (typ.toUpperCase().equals("WYSTĘP TEATRALNY")) {
                prepStat = connection.prepareStatement("INSERT INTO wystep_teatralny(id_wydarzenia) VALUES (?)");
                prepStat.setDouble(1, id);
                result = prepStat.executeUpdate();
            }
        }
        /*statement = connection.createStatement();
        resultSet = statement.executeQuery("SELECT LAST_INSERT_ID()");
        while (resultSet.next()){
            eventID=resultSet.getDouble(1);
        }*/

        deleteMusicianOrShowByEventId(id, oldType);
        for (String strID : showsIDs) {
            double showId = Double.parseDouble(strID);
            if (typ.toUpperCase().equals("KABARET")) {
                KabaretPrzedstawieniaController.addKabaretPrzedstawienia(id, showId);
            } else if (typ.toUpperCase().equals("KONCERT")) {
                KoncertMuzycyController.addKoncertMuzycy(id, showId);
            } else if (typ.toUpperCase().equals("WYSTĘP TEATRALNY")) {
                TeatrPrzedstawieniaController.addTeatrPrzedstawienia(id, showId);
            }
        }
    }

    public static void editWydarzenie(double id, String nazwa, String data, double cenaBiletu, double iloscMiejsc, String typ, double idObiektu) throws SQLException, ParseException {
        prepStat = connection.prepareStatement("UPDATE wydarzenie SET nazwa = ? , data = ?,cena_biletu = ?,ilosc_miejsc = ?,typ =?, id_obiektu = ? WHERE id_wydarzenia = ?");
        prepStat.setString(1, nazwa);
        prepStat.setDate(2, convertDate(data));
        prepStat.setDouble(3, cenaBiletu);
        prepStat.setDouble(4, iloscMiejsc);
        prepStat.setString(5, typ);
        prepStat.setDouble(6, idObiektu);
        prepStat.setDouble(7, id);
        result = prepStat.executeUpdate();
    }

    public static void getAllFromWydarzenie() throws SQLException {
        statement = connection.createStatement();
        resultSet = statement.executeQuery("SELECT * from wydarzenie");
    }

    public static void getSelectedWydarzenieByType(String type) throws SQLException {
        prepStat = connection.prepareStatement("SELECT * from wydarzenie where typ = ?");
        prepStat.setString(1, type);
        resultSet = prepStat.executeQuery();
    }

    public static void getSelectedWydarzeniebyTown(double townID) throws SQLException {
        prepStat = connection.prepareStatement("SELECT * from wydarzenie where id_obiektu " +
                "IN (SELECT id_obiektu from miejsce where id_miasta = ?)");
        prepStat.setDouble(1, townID);
        resultSet = prepStat.executeQuery();
    }

    public static void getSelectedWydarzenieByState(String state) throws SQLException {
        prepStat = connection.prepareStatement("SELECT * from wydarzenie where id_obiektu " +
                "IN(SELECT id_obiektu from miejsce z JOIN miejscowość m on z.id_miasta = m.id_miasta WHERE m.województwo = ?)");
        prepStat.setString(1, state);
        resultSet = prepStat.executeQuery();
    }

    public static void getSelectedWydarzenieByTypeAndTown(String type, double townID) throws SQLException {
        prepStat = connection.prepareStatement("SELECT * from wydarzenie where typ = ? AND id_obiektu IN (SELECT id_obiektu FROM miejsce WHERE id_miasta = ?)");
        prepStat.setString(1, type);
        prepStat.setDouble(2, townID);
        resultSet = prepStat.executeQuery();
    }

    public static void getSelectedWydarzenieByTypeAndState(String type, String state) throws SQLException {
        prepStat = connection.prepareStatement("SELECT * from wydarzenie where typ = ? AND id_obiektu " +
                "IN(SELECT id_obiektu from miejsce z JOIN miejscowość m on z.id_miasta = m.id_miasta WHERE m.województwo = ?)");
        prepStat.setString(1, type);
        prepStat.setString(2, state);
        resultSet = prepStat.executeQuery();
    }

    public static void getSelectedWydarzenie(ArrayList<Double> eventsIDs) throws SQLException {
        // statement = connection.createStatement();
        prepStat = connection.prepareStatement("SELECT * from wydarzenie WHERE id_wydarzenia IN ?");
        prepStat.setObject(1, eventsIDs);
        resultSet = prepStat.executeQuery();
        //resultSet = statement.executeQuery("SELECT * from wydarzenie where id_wydarzenia = ?");
        //prepStat.setString(1,eventsIDs);
    }

    public static void getOneWydarzenie(String nazwa, double cenaBiletu, double iloscMiejsc) throws SQLException {
        prepStat = connection.prepareStatement("SELECT * from wydarzenie where nazwa = ? AND cena_biletu = ? AND ilosc_miejsc = ?");
        prepStat.setString(1, nazwa);
        prepStat.setDouble(2, cenaBiletu);
        prepStat.setDouble(3, iloscMiejsc);
        resultSet = prepStat.executeQuery();
    }

    public static void getOneWydarzenie(double id) throws SQLException {
        prepStat = connection.prepareStatement("SELECT * from wydarzenie where id_wydarzenia = ?");
        prepStat.setDouble(1, id);
        resultSet = prepStat.executeQuery();
    }

    public static String getNameFromWydarzenie(double id) throws SQLException {
        return getOneParameterFromWydarzenie(id, "nazwa");
    }

    public static String getDateFromWydarzenie(double id) throws SQLException {
        return getOneParameterFromWydarzenie(id, "data");
    }

    public static String getCostFromWydarzenie(double id) throws SQLException {
        return getOneParameterFromWydarzenie(id, "cena_biletu");
    }

    public static String getSeatsFromWydarzenie(double id) throws SQLException {
        return getOneParameterFromWydarzenie(id, "ilosc_miejsc");
    }

    public static String getTypeFromWydarzenie(double id) throws SQLException {
        return getOneParameterFromWydarzenie(id, "typ");
    }

    public static double getPlaceIDFromWydarzenie(double id) throws SQLException {
        return getOneIDFromWydarzenie(id, "id_obiektu");
    }

    public static ArrayList<Double> getMusiciansIDsFromWydarzenie(double concertID) throws SQLException {
        prepStat = connection.prepareStatement("SELECT id_muzyka from koncert_muzycy where id_wydarzenia = ?");
        prepStat.setDouble(1, concertID);
        resultSet = prepStat.executeQuery();
        ArrayList<Double> tmp = new ArrayList<>();
        while (resultSet.next()) {
            tmp.add(resultSet.getDouble(1));
        }
        return tmp;
    }

    public static ArrayList<Double> getPerformancesIDsFromTeatr(double theathreID) throws SQLException {
        prepStat = connection.prepareStatement("SELECT id_przedstawienia from teatr_przedstawienia where id_wydarzenia = ?");
        prepStat.setDouble(1, theathreID);
        resultSet = prepStat.executeQuery();
        ArrayList<Double> tmp = new ArrayList<>();
        while (resultSet.next()) {
            tmp.add(resultSet.getDouble(1));
        }
        return tmp;
    }

    public static ArrayList<Double> getPerformancesIDsFromKabaret(double cabaretID) throws SQLException {
        prepStat = connection.prepareStatement("SELECT id_przedstawienia from kabaret_przedstawienia where id_wydarzenia = ?");
        prepStat.setDouble(1, cabaretID);
        resultSet = prepStat.executeQuery();
        ArrayList<Double> tmp = new ArrayList<>();
        while (resultSet.next()) {
            tmp.add(resultSet.getDouble(1));
        }
        return tmp;
    }

    private static double getOneIDFromWydarzenie(double id, String whatDownload) throws SQLException {
        prepStat = connection.prepareStatement("SELECT " + whatDownload + " from wydarzenie where id_wydarzenia = ?");
        prepStat.setDouble(1, id);
        resultSet = prepStat.executeQuery();
        while (resultSet.next()) {
            return resultSet.getDouble(1);
        }
        return -1;
    }

    private static String getOneParameterFromWydarzenie(double id, String whatDownload) throws SQLException {
        prepStat = connection.prepareStatement("SELECT " + whatDownload + " from wydarzenie where id_wydarzenia = ?");
        prepStat.setDouble(1, id);
        resultSet = prepStat.executeQuery();
        while (resultSet.next()) {
            if (whatDownload.equals("ilosc_miejsc")) {
                return String.valueOf(resultSet.getInt(1));
            } else if (whatDownload.equals("data")) {
                String allDate = resultSet.getString(1);
                return allDate.split(" ")[0];
            } else {
                return resultSet.getString(1);

            }
        }
        return null;
    }

    public static void deleteMusicianOrShowByEventId(double id, String oldType) throws SQLException {
        if (oldType.toUpperCase().equals("KONCERT")) {
            prepStat = connection.prepareStatement("DELETE FROM koncert_muzycy WHERE id_wydarzenia = ?");
            prepStat.setDouble(1, id);
            result = prepStat.executeUpdate();
        } else if (oldType.toUpperCase().equals("KABARET")) {
            prepStat = connection.prepareStatement("DELETE FROM kabaret_przedstawienia WHERE id_wydarzenia = ?");
            prepStat.setDouble(1, id);
            result = prepStat.executeUpdate();
        } else if (oldType.toUpperCase().equals("WYSTĘP TEATRALNY")) {
            prepStat = connection.prepareStatement("DELETE FROM teatr_przedstawienia WHERE id_wydarzenia = ?");
            prepStat.setDouble(1, id);
            result = prepStat.executeUpdate();
        }
    }

    public static void deleteWydarzenie(double id) throws SQLException {
        prepStat = connection.prepareStatement("DELETE FROM wydarzenie WHERE id_wydarzenia = ?");
        prepStat.setDouble(1, id);
        result = prepStat.executeUpdate();
    }

    public static ArrayList<String> getResult() throws SQLException {
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

    public static ArrayList<String> getListOfCabaretsStrings() throws SQLException {
        ArrayList<String> temp = new ArrayList<>();
        idList.clear();
        while (resultSet.next()) {
            if (resultSet.getString(6).toUpperCase().equals("KABARET")) {
                temp.add(makeStringFromObject());
            }
        }
        return temp;
    }

    public static ArrayList<String> getListOfConcertStrings() throws SQLException {
        ArrayList<String> temp = new ArrayList<>();
        idList.clear();
        while (resultSet.next()) {
            if (resultSet.getString(6).toUpperCase().equals("KONCERT")) {
                temp.add(makeStringFromObject());
            }
        }
        return temp;
    }

    public static ArrayList<String> getListOfTheathreSpectaclStrings() throws SQLException {
        ArrayList<String> temp = new ArrayList<>();
        idList.clear();
        while (resultSet.next()) {
            if (resultSet.getString(6).toUpperCase().equals("WYSTĘP TEATRALNY")) {
                temp.add(makeStringFromObject());
            }
        }
        return temp;
    }

    private static String makeStringFromObject() throws SQLException {
        Double id = resultSet.getDouble(1);
        idList.add(id);
        String idStr = String.valueOf(resultSet.getInt(1));
        String nazwa = resultSet.getString(2);
        String data = String.valueOf(resultSet.getDate(3));
        String cena = String.valueOf(resultSet.getDouble(4));
        String ilosc = String.valueOf(resultSet.getDouble(5));
        String typ = resultSet.getString(6);
        String idObiektu = String.valueOf(resultSet.getDouble(7));
        ;
        String allString = nazwa + " " + data + " (" + cena + "zł) (" + typ + ") [id: " + id + "]";
        return allString;
    }

    public static ArrayList<String> getListOfStrings() throws SQLException {
        ArrayList<String> temp = new ArrayList<>();
        idList.clear();
        while (resultSet.next()) {
            Double id = resultSet.getDouble(1);
            idList.add(id);
            String idStr = String.valueOf(resultSet.getInt(1));
            String nazwa = resultSet.getString(2);
            String data = String.valueOf(resultSet.getDate(3));
            String cena = String.valueOf(resultSet.getDouble(4));
            String ilosc = String.valueOf(resultSet.getDouble(5));
            String typ = resultSet.getString(6);
            String idObiektu = String.valueOf(resultSet.getDouble(7));
            ;
            String allString = nazwa + " " + data + " (" + cena + "zł) (" + typ + ") [id: " + id + "]";
            temp.add(allString);
        }
        return temp;
    }

    private static ArrayList<Double> idList = new ArrayList<>();

    public static ArrayList<Double> getListOfIDs() {
        return idList;
    }
}
