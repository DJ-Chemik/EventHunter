package sample.gui.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import sample.database.controllers.MiejsceController;
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

    public void initialize(){
        downloadAllEventsFromDatabase();
        addStatesToComboBox();
        addEventTypesToComboBox();
        addTownsToComboBox();
        showMoreInfoButton.setDisable(true);
    }

    private void downloadAllEventsFromDatabase(){
        try {
            WydarzenieController.getAllFromWydarzenie();
            ArrayList<String> objectNames = WydarzenieController.getListOfStrings();
            ArrayList<Double> objectIDs = WydarzenieController.getListOfIDs();
            if (objectIDs!=null && objectNames!=null){
                for (int i=0;i<objectIDs.size();i++){
                    eventsIdMap.put(objectNames.get(i),objectIDs.get(i));
                }
                listView.getItems().setAll(objectNames);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void addStatesToComboBox(){
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

    private void addEventTypesToComboBox(){
        comboBoxTypeSelect.getItems().add("Koncert");
        comboBoxTypeSelect.getItems().add("Kabaraet");
        comboBoxTypeSelect.getItems().add("Występ Teatralny");
    }

    private void addTownsToComboBox(){
        try {
            MiejscowoscController.getAllFromMiejscowosc();
            ArrayList<String> objectNames = MiejscowoscController.getListOfStrings();
            ArrayList<Double> objectIDs = MiejscowoscController.getListOfIds();
            if (objectIDs!=null && objectNames!=null){
                for (int i=0;i<objectIDs.size();i++){
                    townsIdMap.put(objectNames.get(i),objectIDs.get(i));
                }
                comboBoxTownSelect.getItems().setAll(objectNames);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void comboBoxTypeSelectChange(){

    }

    @FXML
    public void comboBoxStateSelectChange(){

    }

    @FXML
    public void comboBoxTownSelectChange(){

    }

    @FXML
    public void showMoreInfoButtonClick(){

    }

    @FXML
    public void backButtonClick() {
        openScreenFromFXMLFilesPackage("StartScreen.fxml");
    }

}
