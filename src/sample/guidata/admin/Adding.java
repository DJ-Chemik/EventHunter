package sample.guidata.admin;

import sample.database.ConnectionWithDatabase;
import sample.database.controllers.*;

import java.awt.*;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Adding {

    private static DatabaseEnum.objectTypes typeOfObject; //All options in DatabaseEnum enum class
    private static Map<DatabaseEnum.fields, ArrayList<String>> tupleParameters = new HashMap<>();
    private static MiejscowoscController miejscowoscController;
    private static ConnectionWithDatabase connection;

    public static void addToDatabase() throws SQLException {
        //only for tests is this code below
        /*for (DatabaseEnum.fields field : tupleParameters.keySet()) {
            System.out.println(tupleParameters.get(field));;
        }*/
        if (typeOfObject==DatabaseEnum.objectTypes.CONCERT){
            String name = getElementFromTupleParameters(DatabaseEnum.eventFields.NAME).get(0);
            String date = getElementFromTupleParameters(DatabaseEnum.eventFields.DATE).get(0);
            String numberOfSeatsString = getElementFromTupleParameters(DatabaseEnum.eventFields.NUMBER_OF_SEATS).get(0);
            double numberOfSeats = Double.valueOf(numberOfSeatsString);
            String ticketCostString = getElementFromTupleParameters(DatabaseEnum.eventFields.TICKET_COST).get(0);
            double ticketCost = Double.valueOf(ticketCostString);
            String type = getElementFromTupleParameters(DatabaseEnum.eventFields.TYPE).get(0);
            String placeName = getElementFromTupleParameters(DatabaseEnum.eventFields.PLACE).get(0);
            String placeType = getElementFromTupleParameters(DatabaseEnum.eventFields.PLACE).get(1);
            ArrayList<String> musicians = getElementFromTupleParameters(DatabaseEnum.eventFields.MUSICIANS); // TODO: 11.02.2020
            try {
                WydarzenieController.AddWydarzenie(name,date, ticketCost, numberOfSeats, type, placeName, placeType);
            } catch (ParseException e) {
                e.printStackTrace();
            }


        }else if (typeOfObject==DatabaseEnum.objectTypes.CABARET){
            String name = getElementFromTupleParameters(DatabaseEnum.eventFields.NAME).get(0);
            String date = getElementFromTupleParameters(DatabaseEnum.eventFields.DATE).get(0);
            String numberOfSeatsString = getElementFromTupleParameters(DatabaseEnum.eventFields.NUMBER_OF_SEATS).get(0);
            double numberOfSeats = Double.valueOf(numberOfSeatsString);
            String ticketCostString = getElementFromTupleParameters(DatabaseEnum.eventFields.TICKET_COST).get(0);
            double ticketCost = Double.valueOf(ticketCostString);
            String type = getElementFromTupleParameters(DatabaseEnum.eventFields.TYPE).get(0);
            String placeName = getElementFromTupleParameters(DatabaseEnum.eventFields.PLACE).get(0);
            String placeType = getElementFromTupleParameters(DatabaseEnum.eventFields.PLACE).get(1);
            ArrayList<String> performances = getElementFromTupleParameters(DatabaseEnum.eventFields.PERFORMANCES); // TODO: 11.02.2020  
            try {
                WydarzenieController.AddWydarzenie(name,date, ticketCost, numberOfSeats, type, placeName, placeType);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }else if (typeOfObject==DatabaseEnum.objectTypes.THEATRE_SPECTACLE){
            String name = getElementFromTupleParameters(DatabaseEnum.eventFields.NAME).get(0);
            String date = getElementFromTupleParameters(DatabaseEnum.eventFields.DATE).get(0);
            String numberOfSeatsString = getElementFromTupleParameters(DatabaseEnum.eventFields.NUMBER_OF_SEATS).get(0);
            double numberOfSeats = Double.parseDouble(numberOfSeatsString);
            String ticketCostString = getElementFromTupleParameters(DatabaseEnum.eventFields.TICKET_COST).get(0);
            double ticketCost = Double.parseDouble(ticketCostString);
            String type = getElementFromTupleParameters(DatabaseEnum.eventFields.TYPE).get(0);
            String placeName = getElementFromTupleParameters(DatabaseEnum.eventFields.PLACE).get(0);
            String placeType = getElementFromTupleParameters(DatabaseEnum.eventFields.PLACE).get(1);
            ArrayList<String> performances = getElementFromTupleParameters(DatabaseEnum.eventFields.PERFORMANCES); // TODO: 11.02.2020
            try {
                WydarzenieController.AddWydarzenie(name,date, ticketCost, numberOfSeats, type, placeName, placeType);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }else if (typeOfObject==DatabaseEnum.objectTypes.PERFORMANCE){
            String title = getElementFromTupleParameters(DatabaseEnum.performanceFields.TITLE).get(0);
            String length = getElementFromTupleParameters(DatabaseEnum.performanceFields.LENGTH).get(0);
            String type = getElementFromTupleParameters(DatabaseEnum.performanceFields.TYPE).get(0);
            ArrayList<String> actors = getElementFromTupleParameters(DatabaseEnum.performanceFields.ACTORS);
            ArrayList<String> events = getElementFromTupleParameters(DatabaseEnum.performanceFields.EVENTS); // TODO: 11.02.2020 Are we need this option here?
            PrzedstawienieController.AddPrzedstawienie(title, length); // TODO: 11.02.2020 Need add a all actors AND add type od performance (CABARET or THEATHRE_SPECTACL)

        }else if (typeOfObject==DatabaseEnum.objectTypes.ACTOR){
            String name = getElementFromTupleParameters(DatabaseEnum.actorFields.NAME).get(0);
            String surname = getElementFromTupleParameters(DatabaseEnum.actorFields.SURNAME).get(0);
            String bandName = getElementFromTupleParameters(DatabaseEnum.actorFields.BAND_NAME).get(0);
            ArrayList<String> performances = getElementFromTupleParameters(DatabaseEnum.actorFields.PERFORMANCES);
            AktorController.AddAktor(name,surname,bandName); // TODO: 11.02.2020 Need add a all performances

        }else if (typeOfObject==DatabaseEnum.objectTypes.MUSICIAN){
            String name =getElementFromTupleParameters(DatabaseEnum.musicianFields.NAME).get(0);
            String surname =getElementFromTupleParameters(DatabaseEnum.musicianFields.SURNAME).get(0);
            String nickName =getElementFromTupleParameters(DatabaseEnum.musicianFields.NICKNAME).get(0);
            MuzykController.AddMuzyk(name,surname,nickName);

        }else if (typeOfObject==DatabaseEnum.objectTypes.MUSIC_DISC){
            String title = getElementFromTupleParameters(DatabaseEnum.musicDiscFields.TITLE).get(0);
            String releaseYearString = getElementFromTupleParameters(DatabaseEnum.musicDiscFields.RELEASE_YEAR).get(0);
            int releaseYear = Integer.parseInt(releaseYearString);
            ArrayList<String> musicians = getElementFromTupleParameters(DatabaseEnum.musicDiscFields.MUSICIANS);
            ArrayList<String> songs = getElementFromTupleParameters(DatabaseEnum.musicDiscFields.SONGS);

            PlytaController.AddPlyta(title,releaseYear); // TODO: 11.02.2020 Make possible to add a musicians and songs to the disk
        }else if (typeOfObject==DatabaseEnum.objectTypes.SONG){
            String title =getElementFromTupleParameters(DatabaseEnum.songFields.TITLE).get(0);
            String releaseYearString =getElementFromTupleParameters(DatabaseEnum.songFields.RELEASE_YEAR).get(0);
            int releaseYear = Integer.parseInt(releaseYearString);
            String genre =getElementFromTupleParameters(DatabaseEnum.songFields.GENRE).get(0);
            String youtubeViewsString =getElementFromTupleParameters(DatabaseEnum.songFields.YOUTUBE_VIEWS).get(0);
            double youtubeViews = Double.parseDouble(youtubeViewsString);
            String musicianName =getElementFromTupleParameters(DatabaseEnum.songFields.MUSICIAN).get(0);
            String musicianSurname =getElementFromTupleParameters(DatabaseEnum.songFields.MUSICIAN).get(0);
            String musicianNickName =getElementFromTupleParameters(DatabaseEnum.songFields.MUSICIAN).get(0);
            //UtworController.AddUtwor(title,releaseYear,genre,youtubeViews,musicianName,musicianSurname,musicianNickName,); // TODO: 11.02.2020 We must added songs to disc, nor disc to songs

        }else if (typeOfObject==DatabaseEnum.objectTypes.PLACE){
            String name = getElementFromTupleParameters(DatabaseEnum.placeFields.NAME).get(0);
            String type = getElementFromTupleParameters(DatabaseEnum.placeFields.TYPE).get(0);
            String town_name = getElementFromTupleParameters(DatabaseEnum.placeFields.TOWN).get(0);
            String state_name = getElementFromTupleParameters(DatabaseEnum.placeFields.TOWN).get(1);
            MiejsceController.AddMiejsce(name,type,town_name,state_name);
        }else if (typeOfObject==DatabaseEnum.objectTypes.TOWN){
            String name = getElementFromTupleParameters(DatabaseEnum.townFields.NAME).get(0);
            String state = getElementFromTupleParameters(DatabaseEnum.townFields.STATE).get(0);
            String zipCode = getElementFromTupleParameters(DatabaseEnum.townFields.ZIP_CODE).get(0);
            MiejscowoscController.AddMiejscowosc(name, state, zipCode);
        }
    }


    public static DatabaseEnum.objectTypes getTypeOfObject() {
        return typeOfObject;
    }

    public static void setTypeOfObject(DatabaseEnum.objectTypes typeOfObject) {
        Adding.typeOfObject = typeOfObject;
    }

    public static void addToTupleParameters(DatabaseEnum.fields field, ArrayList<String> value){
        tupleParameters.put(field, value);
    }

    public static ArrayList<String> getElementFromTupleParameters(DatabaseEnum.fields field){
        ArrayList<String> value = tupleParameters.get(field);
        if (tupleParameters.isEmpty()){
            System.out.println("Not found any parameters in tuples map");
            return null;
        }else if (value==null){
            System.out.println("Not found element of this field name");
            return null;
        }else{
            return value;
        }
    }

    public static void clearTupleParameters(){
        tupleParameters.clear();
    }

    public static int getSizeTupleParameters(){
        return tupleParameters.size();
    }
}
