package sample.gui.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import sample.database.controllers.*;
import sample.gui.StaticData;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class AdminDeleteScreenController extends Screen {

    @FXML
    private ListView<Object> listView;
    @FXML
    private ComboBox<String> comboBoxSpecificType;
    @FXML
    private TextField searchTextField;


    /**
     * Map with key: String from DTBS object
     * and value: this object id;
     */
    private static Map<String, Double> objectsIdMap = new HashMap<>();

    public void initialize() {
        try {
            setSpecificTypeData();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    private void setSpecificTypeData() throws SQLException {
        if (StaticData.getTypeOfIngerention() == "Delete") {
            ArrayList<String> objectNames = null;
            ArrayList<Double> objectsIDs = null;
            if (StaticData.getElementOfIngerention() == "Event") {
                WydarzenieController.getAllFromWydarzenie();
                objectNames = WydarzenieController.getListOfStrings();
                objectsIDs = WydarzenieController.getListOfIDs();
                comboBoxSpecificType.getItems().add("Występ Teatralny");
                comboBoxSpecificType.getItems().add("Kabaret");
                comboBoxSpecificType.getItems().add("Koncert");
            }
            if (StaticData.getElementOfIngerention() == "Person") {
                comboBoxSpecificType.getItems().add("Aktor");
                comboBoxSpecificType.getItems().add("Muzyk");
                listView.setVisible(false);
            }
            if (StaticData.getElementOfIngerention() == "Place") {
                MiejsceController.getAllFromMiejsce();
                objectNames = MiejsceController.getListOfStrings();
                objectsIDs = MiejsceController.getListOfIDs();
                comboBoxSpecificType.setVisible(false);
            }
            if (StaticData.getElementOfIngerention() == "Town") {
                MiejscowoscController.getAllFromMiejscowosc();
                objectNames = MiejscowoscController.getListOfStrings();
                objectsIDs = MiejscowoscController.getListOfIds();
                comboBoxSpecificType.setVisible(false);
            }
            if (StaticData.getElementOfIngerention() == "MusicDisc") {
                PlytaController.getAllFromPlyta();
                objectNames = PlytaController.getListOfStrings();
                objectsIDs = PlytaController.getListOfIDs();
                comboBoxSpecificType.setVisible(false);
            }
            if (StaticData.getElementOfIngerention() == "Song") {
                UtworController.getAllFromUtwor();
                objectNames = UtworController.getListOfStrings();
                objectsIDs = UtworController.getListOfIDs();
                comboBoxSpecificType.setVisible(false);
            }
            if (StaticData.getElementOfIngerention() == "Performance") {
                PrzedstawienieController.getAllFromPrzedstawienie();
                objectNames = PrzedstawienieController.getListOfStrings();
                objectsIDs = PrzedstawienieController.getListOfIDs();
                comboBoxSpecificType.setVisible(false);
            }

            if (objectNames != null) {
                listView.getItems().addAll(objectNames);
                for (int i = 0; i < objectNames.size(); i++) {
                    objectsIdMap.put(objectNames.get(i), objectsIDs.get(i));
                }
            }
        }
    }


    @FXML
    public void comboBoxSpecificTypeChange() {
        int index = comboBoxSpecificType.getSelectionModel().getSelectedIndex();
        ArrayList<String> objectsNames = null;
        ArrayList<Double> objectsIDs = null;
        if (StaticData.getElementOfIngerention() == "Person") {
            if (comboBoxSpecificType.getItems().get(index) == "Aktor") {
                try {
                    AktorController.getAllFromAktor();
                    objectsNames = AktorController.getListOfStrings();
                    objectsIDs = AktorController.getListOfIDs();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            } else if (comboBoxSpecificType.getItems().get(index) == "Muzyk") {
                try {
                    MuzykController.getAllFromMuzyk();
                    objectsNames=MuzykController.getListOfStrings();
                    objectsIDs=MuzykController.getListOfIDs();
                } catch (SQLException e) {
                    e.printStackTrace();
                }

            }
            if (objectsIDs!=null && objectsNames!=null){
                for (int i=0;i<objectsIDs.size();i++){
                    objectsIdMap.put(objectsNames.get(i),objectsIDs.get(i));
                }
                listView.setVisible(true);
                listView.getItems().setAll(objectsNames);
            }

        }
        if (StaticData.getElementOfIngerention() == "Event") {
            try {
                WydarzenieController.getAllFromWydarzenie();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            if (comboBoxSpecificType.getItems().get(index) == "Występ Teatralny") {
                try {
                    objectsNames=WydarzenieController.getListOfTheathreSpectaclStrings();
                    objectsIDs=WydarzenieController.getListOfIDs();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }else if (comboBoxSpecificType.getItems().get(index) == "Kabaret") {
                try {
                    objectsNames=WydarzenieController.getListOfCabaretsStrings();
                    objectsIDs=WydarzenieController.getListOfIDs();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }else if (comboBoxSpecificType.getItems().get(index).equals("Koncert")) {
                try {
                    objectsNames=WydarzenieController.getListOfConcertStrings();
                    objectsIDs=WydarzenieController.getListOfIDs();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (objectsIDs!=null && objectsNames!=null){
                objectsIdMap.clear();
                for (int i=0;i<objectsIDs.size();i++){
                    objectsIdMap.put(objectsNames.get(i),objectsIDs.get(i));
                }
                listView.setVisible(true);
                listView.getItems().setAll(objectsNames);
            }

        }
    }

    @FXML
    public void searchTextFieldChange() {

    }

    @FXML
    public void deleteButtonClick() {

    }

    @FXML
    public void backButtonClick() {
        openScreenFromFXMLFilesPackage("AdminMenuScreen.fxml");
    }

}
