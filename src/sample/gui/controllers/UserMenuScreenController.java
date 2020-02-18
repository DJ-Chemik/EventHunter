package sample.gui.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import sample.database.controllers.MiejscowoscController;
import sample.database.controllers.WydarzenieController;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class UserMenuScreenController extends Screen {

    @FXML
    private ListView<String> listView;
    @FXML
    private ComboBox<String> comboBoxTypeSelect, comboBoxStateSelect, comboBoxTownSelect;
    @FXML
    private Button showMoreInfoButton;

    private static Map<String, Double> eventsIdMap = new HashMap<>();
    private static Map<String, Double> townsIdMap = new HashMap<>();

    public void initialize() {
        downloadAllEventsFromDatabase();
        addStatesToComboBox();
        addEventTypesToComboBox();
        addTownsToComboBox();
        showMoreInfoButton.setDisable(true);
    }

    private void downloadAllEventsFromDatabase() {
        try {
            WydarzenieController.getAllFromWydarzenie();
            ArrayList<String> objectNames = WydarzenieController.getListOfStrings();
            ArrayList<Double> objectIDs = WydarzenieController.getListOfIDs();
            if (objectIDs != null && objectNames != null) {
                for (int i = 0; i < objectIDs.size(); i++) {
                    eventsIdMap.put(objectNames.get(i), objectIDs.get(i));
                }
                listView.getItems().setAll(objectNames);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void addStatesToComboBox() {
        comboBoxStateSelect.getItems().add("Dolnośląskie");
        comboBoxStateSelect.getItems().add("Kujawsko-Pomorskie");
        comboBoxStateSelect.getItems().add("Lubelskie");
        comboBoxStateSelect.getItems().add("Lubuskie");
        comboBoxStateSelect.getItems().add("Łódzkie");
        comboBoxStateSelect.getItems().add("Małopolskie");
        comboBoxStateSelect.getItems().add("Mazowieckie");
        comboBoxStateSelect.getItems().add("Opolskie");
        comboBoxStateSelect.getItems().add("Podkarpackie");
        comboBoxStateSelect.getItems().add("Podlaskie");
        comboBoxStateSelect.getItems().add("Pomorskie");
        comboBoxStateSelect.getItems().add("Śląskie");
        comboBoxStateSelect.getItems().add("Świętokrzyskie");
        comboBoxStateSelect.getItems().add("Warmińsko-Mazurskie");
        comboBoxStateSelect.getItems().add("Wielkopolskie");
        comboBoxStateSelect.getItems().add("Zachodniopomorskie");
    }

    private void addEventTypesToComboBox() {
        comboBoxTypeSelect.getItems().add("Koncert");
        comboBoxTypeSelect.getItems().add("Kabaret");
        comboBoxTypeSelect.getItems().add("Występ Teatralny");
    }

    private void addTownsToComboBox() {
        try {
            MiejscowoscController.getAllFromMiejscowosc();
            ArrayList<String> objectNames = MiejscowoscController.getListOfStrings();
            ArrayList<Double> objectIDs = MiejscowoscController.getListOfIds();
            if (objectIDs != null && objectNames != null) {
                for (int i = 0; i < objectIDs.size(); i++) {
                    townsIdMap.put(objectNames.get(i), objectIDs.get(i));
                }
                comboBoxTownSelect.getItems().setAll(objectNames);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void listViewChange() {
        if (listView.getSelectionModel().getSelectedIndex() != -1) {
            showMoreInfoButton.setDisable(false);
        }
    }

    @FXML
    public void comboBoxTypeSelectChange() {
        type=comboBoxTypeSelect.getSelectionModel().getSelectedItem();
        try {
            restrictEventsRecords();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @FXML
    public void comboBoxStateSelectChange() {
        state=comboBoxStateSelect.getSelectionModel().getSelectedItem();
        try {
            //restrictEventsRecords();
            MiejscowoscController.getAllSelectedFromMiejscowoscByState(state);
            ArrayList<String> objectNames = MiejscowoscController.getListOfStrings();
            ArrayList<Double> objectIDs = MiejscowoscController.getListOfIds();
            //townsIdMap.clear();
            if (objectIDs != null && objectNames != null) {
                for (int i = 0; i < objectIDs.size(); i++) {
                    townsIdMap.put(objectNames.get(i), objectIDs.get(i));
                }
                comboBoxTownSelect.getItems().setAll(objectNames);
            }else{
                comboBoxTownSelect.getItems().clear();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        comboBoxTownSelect.getSelectionModel().selectFirst();
        //comboBoxTownSelect.getSelectionModel().clearSelection();

    }

    @FXML
    public void comboBoxTownSelectChange() {
        townID=townsIdMap.get(comboBoxTownSelect.getSelectionModel().getSelectedItem());
        try {
            if (comboBoxTownSelect.getItems().isEmpty()==false){
                comboBoxStateSelect.getSelectionModel().select(MiejscowoscController.getStateFromMiejscowosc(townID));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private String type = null;
    private String state = null;
    private Double townID = null;

    private void restrictEventsRecords() throws SQLException {
        ArrayList<Double> objectIDs;
        ArrayList<String> objectNames;
        if (type != null) {

            if (townID!=null){
                WydarzenieController.getSelectedWydarzenieByTypeAndTown(type,townID);
            }else if (state!=null){
                WydarzenieController.getSelectedWydarzenieByTypeAndState(type, state);
            }else{ //only type
                WydarzenieController.getSelectedWydarzenieByType(type);
            }
        }else{
            if (townID!=null){
                WydarzenieController.getSelectedWydarzeniebyTown(townID);
            }else if (state!=null){ //only state
                WydarzenieController.getSelectedWydarzenieByState(state);
            }else{ //nothing selected

            }
        }

        objectNames = WydarzenieController.getListOfStrings();
        objectIDs = WydarzenieController.getListOfIDs();
        if (objectIDs != null && objectNames != null) {
            for (int i = 0; i < objectIDs.size(); i++) {
                eventsIdMap.put(objectNames.get(i), objectIDs.get(i));
            }
            listView.getItems().setAll(objectNames);
        }

    }

    @FXML
    public void showMoreInfoButtonClick() {
        // TODO: 18.02.2020 Keep data about actual view in static field when user show more info
        // TODO: 18.02.2020 Open new panel with information about event
    }

    @FXML
    public void backButtonClick() {
        openScreenFromFXMLFilesPackage("StartScreen.fxml");
    }

}
