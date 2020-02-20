package sample.guidata.admin;

import sample.database.controllers.*;
import sample.gui.StaticData;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;

public class Editing {

    private static ArrayList<String> filledFields = new ArrayList<>();
    private static ArrayList<String> listOfStringsOne = new ArrayList<>();
    private static ArrayList<String> listOfStringsTwo = new ArrayList<>();
    private static ArrayList<Double> listOfIDsOne = new ArrayList<>();
    private static ArrayList<Double> listOfIDsTwo = new ArrayList<>();

    private static double objectIDToUpdate;

    public static void updateInDatabase() throws SQLException {
        //only for tests is this code below
        /*for (DatabaseEnum.fields field : tupleParameters.keySet()) {
            System.out.println(tupleParameters.get(field));;
        }*/
        if (AddingOrPrepareToUpdate.getTypeOfObject()==DatabaseEnum.objectTypes.CONCERT){
            String name = AddingOrPrepareToUpdate.getElementFromTupleParameters(DatabaseEnum.eventFields.NAME).get(0);
            String date = AddingOrPrepareToUpdate.getElementFromTupleParameters(DatabaseEnum.eventFields.DATE).get(0);
            String numberOfSeatsString = AddingOrPrepareToUpdate.getElementFromTupleParameters(DatabaseEnum.eventFields.NUMBER_OF_SEATS).get(0);
            double numberOfSeats = Double.valueOf(numberOfSeatsString);
            String ticketCostString = AddingOrPrepareToUpdate.getElementFromTupleParameters(DatabaseEnum.eventFields.TICKET_COST).get(0);
            double ticketCost = Double.valueOf(ticketCostString);
            String type = AddingOrPrepareToUpdate.getElementFromTupleParameters(DatabaseEnum.eventFields.TYPE).get(0);
            String placeID = AddingOrPrepareToUpdate.getElementFromTupleParameters(DatabaseEnum.eventFields.PLACE).get(0);
            ArrayList<String> musiciansIDs = AddingOrPrepareToUpdate.getElementFromTupleParameters(DatabaseEnum.eventFields.MUSICIANS);
            try {
                WydarzenieController.deleteWydarzenie(objectIDToUpdate);
                WydarzenieController.addWydarzenie(name,date, ticketCost, numberOfSeats, type, placeID, musiciansIDs);
            } catch (ParseException e) {
                e.printStackTrace();
            }


        }else if (AddingOrPrepareToUpdate.getTypeOfObject()==DatabaseEnum.objectTypes.CABARET){
            String name = AddingOrPrepareToUpdate.getElementFromTupleParameters(DatabaseEnum.eventFields.NAME).get(0);
            String date = AddingOrPrepareToUpdate.getElementFromTupleParameters(DatabaseEnum.eventFields.DATE).get(0);
            String numberOfSeatsString = AddingOrPrepareToUpdate.getElementFromTupleParameters(DatabaseEnum.eventFields.NUMBER_OF_SEATS).get(0);
            double numberOfSeats = Double.valueOf(numberOfSeatsString);
            String ticketCostString = AddingOrPrepareToUpdate.getElementFromTupleParameters(DatabaseEnum.eventFields.TICKET_COST).get(0);
            double ticketCost = Double.valueOf(ticketCostString);
            String type = AddingOrPrepareToUpdate.getElementFromTupleParameters(DatabaseEnum.eventFields.TYPE).get(0);
            String placeID = AddingOrPrepareToUpdate.getElementFromTupleParameters(DatabaseEnum.eventFields.PLACE).get(0);
            ArrayList<String> performancesIDs = AddingOrPrepareToUpdate.getElementFromTupleParameters(DatabaseEnum.eventFields.PERFORMANCES);
            try {
                WydarzenieController.deleteWydarzenie(objectIDToUpdate);
                WydarzenieController.addWydarzenie(name,date, ticketCost, numberOfSeats, type, placeID, performancesIDs);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }else if (AddingOrPrepareToUpdate.getTypeOfObject()==DatabaseEnum.objectTypes.THEATRE_SPECTACLE){
            String name = AddingOrPrepareToUpdate.getElementFromTupleParameters(DatabaseEnum.eventFields.NAME).get(0);
            String date = AddingOrPrepareToUpdate.getElementFromTupleParameters(DatabaseEnum.eventFields.DATE).get(0);
            String numberOfSeatsString = AddingOrPrepareToUpdate.getElementFromTupleParameters(DatabaseEnum.eventFields.NUMBER_OF_SEATS).get(0);
            double numberOfSeats = Double.parseDouble(numberOfSeatsString);
            String ticketCostString = AddingOrPrepareToUpdate.getElementFromTupleParameters(DatabaseEnum.eventFields.TICKET_COST).get(0);
            double ticketCost = Double.parseDouble(ticketCostString);
            String type = AddingOrPrepareToUpdate.getElementFromTupleParameters(DatabaseEnum.eventFields.TYPE).get(0);
            String placeID = AddingOrPrepareToUpdate.getElementFromTupleParameters(DatabaseEnum.eventFields.PLACE).get(0);
            ArrayList<String> performancesIDs = AddingOrPrepareToUpdate.getElementFromTupleParameters(DatabaseEnum.eventFields.PERFORMANCES);
            try {
                WydarzenieController.deleteWydarzenie(objectIDToUpdate);
                WydarzenieController.addWydarzenie(name,date, ticketCost, numberOfSeats, type, placeID, performancesIDs);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }else if (AddingOrPrepareToUpdate.getTypeOfObject()==DatabaseEnum.objectTypes.PERFORMANCE){
            String title = AddingOrPrepareToUpdate.getElementFromTupleParameters(DatabaseEnum.performanceFields.TITLE).get(0);
            String length = AddingOrPrepareToUpdate.getElementFromTupleParameters(DatabaseEnum.performanceFields.LENGTH).get(0);
            String type = AddingOrPrepareToUpdate.getElementFromTupleParameters(DatabaseEnum.performanceFields.TYPE).get(0);
            ArrayList<String> actors = AddingOrPrepareToUpdate.getElementFromTupleParameters(DatabaseEnum.performanceFields.ACTORS);
            PrzedstawienieController.deletePrzedstawienie(objectIDToUpdate);
            PrzedstawienieController.addPrzedstawienie(title, length, type, actors);

        }else if (AddingOrPrepareToUpdate.getTypeOfObject()==DatabaseEnum.objectTypes.ACTOR){
            String name = AddingOrPrepareToUpdate.getElementFromTupleParameters(DatabaseEnum.actorFields.NAME).get(0);
            String surname = AddingOrPrepareToUpdate.getElementFromTupleParameters(DatabaseEnum.actorFields.SURNAME).get(0);
            String bandName = AddingOrPrepareToUpdate.getElementFromTupleParameters(DatabaseEnum.actorFields.BAND_NAME).get(0);
            ArrayList<String> performances = AddingOrPrepareToUpdate.getElementFromTupleParameters(DatabaseEnum.actorFields.PERFORMANCES);
            AktorController.deleteAktor(objectIDToUpdate);
            AktorController.AddAktor(name,surname,bandName);

        }else if (AddingOrPrepareToUpdate.getTypeOfObject()==DatabaseEnum.objectTypes.MUSICIAN){
            String name = AddingOrPrepareToUpdate.getElementFromTupleParameters(DatabaseEnum.musicianFields.NAME).get(0);
            String surname = AddingOrPrepareToUpdate.getElementFromTupleParameters(DatabaseEnum.musicianFields.SURNAME).get(0);
            String nickName = AddingOrPrepareToUpdate.getElementFromTupleParameters(DatabaseEnum.musicianFields.NICKNAME).get(0);
            MuzykController.deleteMuzyk(objectIDToUpdate);
            MuzykController.addMuzyk(name,surname,nickName);

        }else if (AddingOrPrepareToUpdate.getTypeOfObject()==DatabaseEnum.objectTypes.MUSIC_DISC){
            String title = AddingOrPrepareToUpdate.getElementFromTupleParameters(DatabaseEnum.musicDiscFields.TITLE).get(0);
            String releaseYearString = AddingOrPrepareToUpdate.getElementFromTupleParameters(DatabaseEnum.musicDiscFields.RELEASE_YEAR).get(0);
            int releaseYear = Integer.parseInt(releaseYearString);
            ArrayList<String> musicians = AddingOrPrepareToUpdate.getElementFromTupleParameters(DatabaseEnum.musicDiscFields.MUSICIANS);
            ArrayList<String> songs = AddingOrPrepareToUpdate.getElementFromTupleParameters(DatabaseEnum.musicDiscFields.SONGS);
            PlytaController.deletePlyta(objectIDToUpdate);
            PlytaController.addPlyta(title,releaseYear,songs, musicians);
        }else if (AddingOrPrepareToUpdate.getTypeOfObject()==DatabaseEnum.objectTypes.SONG){
            String title = AddingOrPrepareToUpdate.getElementFromTupleParameters(DatabaseEnum.songFields.TITLE).get(0);
            String releaseYearString = AddingOrPrepareToUpdate.getElementFromTupleParameters(DatabaseEnum.songFields.RELEASE_YEAR).get(0);
            int releaseYear = Integer.parseInt(releaseYearString);
            String genre = AddingOrPrepareToUpdate.getElementFromTupleParameters(DatabaseEnum.songFields.GENRE).get(0);
            String youtubeViewsString = AddingOrPrepareToUpdate.getElementFromTupleParameters(DatabaseEnum.songFields.YOUTUBE_VIEWS).get(0);
            Double youtubeViews = Double.valueOf(youtubeViewsString);
            String musicianID = AddingOrPrepareToUpdate.getElementFromTupleParameters(DatabaseEnum.songFields.MUSICIAN).get(0);
            String discID = AddingOrPrepareToUpdate.getElementFromTupleParameters(DatabaseEnum.songFields.MUSIC_DISC).get(0);
            UtworController.deleteUtwor(objectIDToUpdate);
            UtworController.addUtwor(title,releaseYear,genre,youtubeViews,musicianID,discID);

        }else if (AddingOrPrepareToUpdate.getTypeOfObject()==DatabaseEnum.objectTypes.PLACE){
            String name = AddingOrPrepareToUpdate.getElementFromTupleParameters(DatabaseEnum.placeFields.NAME).get(0);
            String type = AddingOrPrepareToUpdate.getElementFromTupleParameters(DatabaseEnum.placeFields.TYPE).get(0);
            String town_id = AddingOrPrepareToUpdate.getElementFromTupleParameters(DatabaseEnum.placeFields.TOWN).get(0);
            MiejsceController.deleteMiejsce(objectIDToUpdate);
            MiejsceController.AddMiejsce(name,type,town_id);
        }else if (AddingOrPrepareToUpdate.getTypeOfObject()==DatabaseEnum.objectTypes.TOWN){
            String name = AddingOrPrepareToUpdate.getElementFromTupleParameters(DatabaseEnum.townFields.NAME).get(0);
            String state = AddingOrPrepareToUpdate.getElementFromTupleParameters(DatabaseEnum.townFields.STATE).get(0);
            String zipCode = AddingOrPrepareToUpdate.getElementFromTupleParameters(DatabaseEnum.townFields.ZIP_CODE).get(0);
            MiejscowoscController.editMiejscowosc(objectIDToUpdate,name, state, zipCode);
        }
    }

    public static void directToEditScreen(DatabaseEnum.objectTypes type, double objectID) {
        ArrayList<Integer> numberOfFileds = StaticData.getActiveFieldsNumbers();
        try {
            if (type == DatabaseEnum.objectTypes.CONCERT || type == DatabaseEnum.objectTypes.CABARET || type == DatabaseEnum.objectTypes.THEATRE_SPECTACLE) {
                String name = WydarzenieController.getNameFromWydarzenie(objectID);
                String date = WydarzenieController.getDateFromWydarzenie(objectID);
                String cost = WydarzenieController.getCostFromWydarzenie(objectID);
                String seats = WydarzenieController.getSeatsFromWydarzenie(objectID);
                String eventType = WydarzenieController.getTypeFromWydarzenie(objectID);
                double placeID = WydarzenieController.getPlaceIDFromWydarzenie(objectID);
                MiejsceController.getOneMiejsce(placeID);
                String placeStr = MiejsceController.getListOfStrings().get(0);
                filledFields.clear();
                filledFields.add(name);
                filledFields.add(date);
                filledFields.add(cost);
                filledFields.add(seats);
                filledFields.add(eventType);
                filledFields.add(placeStr);
                ArrayList<Double> ids = new ArrayList<>();
                if (eventType.equals("Koncert")) {
                    ids = WydarzenieController.getMusiciansIDsFromWydarzenie(objectID);
                    for (double d : ids){
                        MuzykController.getOneMuzyk(d);
                        listOfStringsOne.add(MuzykController.getListOfStrings().get(0));
                    }
                } else if (eventType.equals("Kabaret")){
                    ids = WydarzenieController.getPerformancesIDsFromKabaret(objectID);
                    for (double d : ids){
                        PrzedstawienieController.getOnePrzedstawienie(d);
                        listOfStringsOne.add(PrzedstawienieController.getListOfStrings().get(0));
                    }
                }else{
                    ids = WydarzenieController.getPerformancesIDsFromTeatr(objectID);
                    for (double d : ids){
                        PrzedstawienieController.getOnePrzedstawienie(d);
                        listOfStringsOne.add(PrzedstawienieController.getListOfStrings().get(0));
                    }
                }
                listOfIDsOne = ids;
            } else if (type == DatabaseEnum.objectTypes.ACTOR) {
                String name = AktorController.getNameFromAktor(objectID);
                String surname = AktorController.getSurameFromAktor(objectID);
                String bandname = AktorController.getBandNameFromAktor(objectID);
                String specialization = "Aktor";
                filledFields.clear();
                filledFields.add(name);
                filledFields.add(surname);
                filledFields.add(bandname);
                filledFields.add(specialization);

            } else if (type == DatabaseEnum.objectTypes.MUSICIAN) {
                String name = MuzykController.getNameFromMuzyk(objectID);
                String surname = MuzykController.getSurameFromMuzyk(objectID);
                String nickname = MuzykController.getNicknameFromMuzyk(objectID);
                String specialization = "Muzyk";
                filledFields.clear();
                filledFields.add(name);
                filledFields.add(surname);
                filledFields.add(nickname);
                filledFields.add(specialization);

            } else if (type == DatabaseEnum.objectTypes.MUSIC_DISC) {
                String title = PlytaController.getTitleFromPlyta(objectID);
                String year = PlytaController.getReleaseYearFromPlyta(objectID);
                ArrayList<Double> musiciansIDs = PlytaController.getListOfMusiciansIDsFromPlyta(objectID);
                ArrayList<Double> songsIDs = PlytaController.getListOfSongsIDsFromPlyta(objectID);
                filledFields.clear();
                filledFields.add(title);
                filledFields.add(year);
                listOfStringsOne.clear();
                for (double d : musiciansIDs) {
                    MuzykController.getOneMuzyk(d);
                    listOfStringsOne.add(MuzykController.getListOfStrings().get(0));
                }
                listOfStringsTwo.clear();
                for (double d : songsIDs) {
                    UtworController.getOneUtwor(d);
                    listOfStringsTwo.add(UtworController.getListOfStrings().get(0));
                }
                listOfIDsOne = musiciansIDs;
                listOfIDsTwo = songsIDs;

            } else if (type == DatabaseEnum.objectTypes.SONG) {
                String title = UtworController.getTitleFromUtwor(objectID);
                String year = UtworController.getReleaseYearFromUtwor(objectID);
                String genre = UtworController.getGenreFromUtwor(objectID);
                String ytViews = UtworController.getYouTubeViewsFromUtwor(objectID);
                double musicianId = UtworController.getMusicianIdFromUtwor(objectID);
                double musicDiscId = UtworController.getMusicDiscIdFromUtwor(objectID);
                MuzykController.getOneMuzyk(musicianId);
                PlytaController.getOnePlyta(musicDiscId);
                String musicianStr = MuzykController.getListOfStrings().get(0);
                String musicDiscStr;
                if (PlytaController.getListOfStrings().size() != 0) {
                    musicDiscStr = PlytaController.getListOfStrings().get(0);
                } else {
                    musicDiscStr = null;
                }
                filledFields.clear();
                filledFields.add(title);
                filledFields.add(year);
                filledFields.add(genre);
                filledFields.add(ytViews);
                filledFields.add(musicianStr);
                filledFields.add(musicDiscStr);

            } else if (type == DatabaseEnum.objectTypes.PERFORMANCE) {
                String title = PrzedstawienieController.getTitleFromPrzedstawienie(objectID);
                String length = PrzedstawienieController.getLenghtFromPrzedstawienie(objectID);
                ArrayList<Double> actorsIDs = new ArrayList<>();
                if (PrzedstawienieController.getListOfActorsIDsFromPrzedstawienie(objectID).size() > 0) {
                    actorsIDs = PrzedstawienieController.getListOfActorsIDsFromPrzedstawienie(objectID);
                }
                filledFields.clear();
                filledFields.add(title);
                filledFields.add(length);
                for (double d : actorsIDs) {
                    UtworController.getOneUtwor(d);
                    listOfStringsTwo.add(UtworController.getListOfStrings().get(0));
                }
                listOfIDsOne = actorsIDs;

            } else if (type == DatabaseEnum.objectTypes.PLACE) {
                String name = MiejsceController.getNameFromMiejsce(objectID);
                String placeType = MiejsceController.getTypeFromMiejsce(objectID);
                double townID = MiejsceController.getTownIdFromMiejsce(objectID);
                MiejscowoscController.getOneMiejscowosc(townID);
                String townStr = MiejscowoscController.getListOfStrings().get(0);
                filledFields.clear();
                filledFields.add(name);
                filledFields.add(placeType);
                filledFields.add(townStr);

            } else if (type == DatabaseEnum.objectTypes.TOWN) {
                String name = MiejscowoscController.getNameFromMiejscowosc(objectID);
                String zipCode = MiejscowoscController.getZipCodeFromMiejscowosc(objectID);
                String state = MiejscowoscController.getStateFromMiejscowosc(objectID);
                filledFields.clear();
                filledFields.add(name);
                filledFields.add(zipCode);
                filledFields.add(state);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        objectIDToUpdate=objectID;
    }

    public static ArrayList<String> getFilledFields() {
        return filledFields;
    }

    public static ArrayList<String> getListOfStringsOne() {
        return listOfStringsOne;
    }

    public static ArrayList<String> getListOfStringsTwo() {
        return listOfStringsTwo;
    }

    public static ArrayList<Double> getListOfIDsOne() {
        return listOfIDsOne;
    }

    public static ArrayList<Double> getListOfIDsTwo() {
        return listOfIDsTwo;
    }

    public static double getObjectIDToUpdate() {
        return objectIDToUpdate;
    }

    public static void setObjectIDToUpdate(double objectIDToUpdate) {
        Editing.objectIDToUpdate = objectIDToUpdate;
    }
}
