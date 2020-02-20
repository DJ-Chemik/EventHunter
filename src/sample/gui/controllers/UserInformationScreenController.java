package sample.gui.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import sample.database.controllers.*;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class UserInformationScreenController extends Screen{

    @FXML
    private Label eventLabel0,eventLabel1,eventLabel2,eventLabel3,eventLabel4,
            townLabel0,townLabel1,townLabel2,townLabel3,townLabel4,
            listLabel1,listLabel2,musicianSongsAmountLabel, musicianSongsAmountLabelText;
    @FXML
    private ListView<String> listView1, listView2;

    private double eventID;

    private String type = new String();

    private static Map<String, Double> objectsIdMap = new HashMap<>(); //musicians or performances

    public void initialize(){
        eventID=UserMenuScreenController.getEventID();
        try {
            loadLabelTexts();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void loadLabelTexts() throws SQLException {
        eventLabel0.setText(WydarzenieController.getNameFromWydarzenie(eventID));
        type = WydarzenieController.getTypeFromWydarzenie(eventID);
        eventLabel1.setText(type);
        musicianSongsAmountLabel.setVisible(false);
        musicianSongsAmountLabelText.setVisible(false);
        if (type.equals("Koncert")){
            listLabel1.setText("Lista muzyków:");
            listLabel2.setText("Lista utworów danego muzyka:");
            ArrayList<Double> musiciansIDs = WydarzenieController.getMusiciansIDsFromWydarzenie(eventID);
            ArrayList<String> musiciansStrs = new ArrayList<>();
            for (double id : musiciansIDs){
                MuzykController.getOneMuzyk(id);
                String s=  MuzykController.getListOfStrings().get(0);
                musiciansStrs.add(s);
                objectsIdMap.put(s,id);
            }
            listView1.getItems().setAll(musiciansStrs);
        }else if (type.equals("Kabaret")){
            listLabel1.setText("Lista przedstawień:");
            listLabel2.setText("Lista aktorów w wybranym przedstawieniu:");
            ArrayList<Double> performancesIDs = WydarzenieController.getPerformancesIDsFromKabaret(eventID);
            ArrayList<String> performancesStrs = new ArrayList<>();
            for (double id : performancesIDs){
                PrzedstawienieController.getOnePrzedstawienie(id);
                String s =PrzedstawienieController.getListOfStrings().get(0);
                performancesStrs.add(s);
                objectsIdMap.put(s,id);
            }
            listView1.getItems().setAll(performancesStrs);
        }else{
            listLabel1.setText("Lista przedstawień:");
            listLabel2.setText("Lista aktorów w wybranym przedstawieniu:");
            ArrayList<Double> performancesIDs = WydarzenieController.getPerformancesIDsFromTeatr(eventID);
            ArrayList<String> performancesStrs = new ArrayList<>();
            for (double id : performancesIDs){
                PrzedstawienieController.getOnePrzedstawienie(id);
                String s = PrzedstawienieController.getListOfStrings().get(0);
                performancesStrs.add(s);
                objectsIdMap.put(s,id);
            }
            listView1.getItems().setAll(performancesStrs);
        }
        eventLabel2.setText(WydarzenieController.getDateFromWydarzenie(eventID));
        eventLabel3.setText(WydarzenieController.getCostFromWydarzenie(eventID));
        eventLabel4.setText(WydarzenieController.getSeatsFromWydarzenie(eventID));
        double placeID = WydarzenieController.getPlaceIDFromWydarzenie(eventID);
        double townID = MiejsceController.getTownIdFromMiejsce(placeID);
        townLabel0.setText(MiejscowoscController.getNameFromMiejscowosc(townID));
        townLabel1.setText(MiejscowoscController.getStateFromMiejscowosc(townID));
        townLabel2.setText(MiejscowoscController.getZipCodeFromMiejscowosc(townID));
        townLabel3.setText(MiejsceController.getNameFromMiejsce(placeID));
        townLabel4.setText(MiejsceController.getTypeFromMiejsce(placeID));


    }

    @FXML
    public void listView1Change(){
        if (listView1.getSelectionModel().getSelectedIndex() != -1) {
            String objectStr = listView1.getSelectionModel().getSelectedItem();
            double objectID = objectsIdMap.get(objectStr);
            try {
                loadListView2(objectID);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    private void loadListView2(double objectID) throws SQLException {
        if (type.equals("Koncert")){
            ArrayList<Double> songsIDs = MuzykController.getAllSongsFromMuzyk(objectID);
            ArrayList<String> songsStrs = new ArrayList<>();
            for (double id : songsIDs){
                UtworController.getOneUtwor(id);
                songsStrs.add(UtworController.getListOfStrings().get(0));
            }
            listView2.getItems().setAll(songsStrs);
            int numberOfSongs = MuzykController.PoliczUtworyMuzyka(objectID);
            musicianSongsAmountLabel.setVisible(true);
            musicianSongsAmountLabelText.setVisible(true);
            musicianSongsAmountLabel.setText(String.valueOf(numberOfSongs));
        }else{
            ArrayList<Double> actorsIDs = PrzedstawienieController.getListOfActorsIDsFromPrzedstawienie(eventID);
            ArrayList<String> actorsStrs = new ArrayList<>();
            for (double id : actorsIDs){
                PrzedstawienieController.getOnePrzedstawienie(id);
                actorsStrs.add(PrzedstawienieController.getListOfStrings().get(0));
            }
            listView2.getItems().setAll(actorsStrs);
        }
    }


    @FXML
    public void backButtonClick() {
        openScreenFromFXMLFilesPackage("UserMenuScreen.fxml");
    }
}
