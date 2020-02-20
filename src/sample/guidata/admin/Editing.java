package sample.guidata.admin;

import sample.database.controllers.*;
import sample.gui.StaticData;

import java.sql.SQLException;
import java.util.ArrayList;

public class Editing {

    private static ArrayList<String> filledFields = new ArrayList<>();
    private static ArrayList<String> listOfStringsOne = new ArrayList<>();
    private static ArrayList<String> listOfStringsTwo = new ArrayList<>();
    private static ArrayList<Double> listOfIDsOne = new ArrayList<>();
    private static ArrayList<Double> listOfIDsTwo = new ArrayList<>();

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
                String name = AktorController.getBandNameFromAktor(objectID);
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
}
