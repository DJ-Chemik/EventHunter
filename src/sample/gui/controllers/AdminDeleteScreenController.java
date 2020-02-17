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
                objectNames=MiejscowoscController.getListOfStrings();
                objectsIDs=MiejscowoscController.getListOfIds();
                comboBoxSpecificType.setVisible(false);
            }
            if (StaticData.getElementOfIngerention() == "MusicDisc") {
                PlytaController.getAllFromPlyta();
                objectNames=PlytaController.getListOfStrings();
                objectsIDs=PlytaController.getListOfIDs();
                comboBoxSpecificType.setVisible(false);
            }
            if (StaticData.getElementOfIngerention() == "Song") {
                UtworController.getAllFromUtwor();
                objectNames=UtworController.getListOfStrings();
                objectsIDs=UtworController.getListOfIDs();
                comboBoxSpecificType.setVisible(false);
            }
            if (StaticData.getElementOfIngerention() == "Performance") {
                PrzedstawienieController.getAllFromPrzedstawienie();
                objectNames=PrzedstawienieController.getListOfStrings();
                objectsIDs=PrzedstawienieController.getListOfIDs();
                comboBoxSpecificType.setVisible(false);
            }

            if (objectNames!=null){
                listView.getItems().addAll(objectNames);
                for (int i=0; i<objectNames.size();i++){
                    objectsIdMap.put(objectNames.get(i),objectsIDs.get(i));
                }
            }
        }
    }


    @FXML
    public void comboBoxSpecificTypeChange() {
        if (StaticData.getElementOfIngerention()=="Person"){
            
        }
        if (StaticData.getElementOfIngerention()=="Event"){

        }
    }

    @FXML
    public void searchTextFieldChange() {

    }

    @FXML
    public void backButtonClick() {
        openScreenFromFXMLFilesPackage("AdminMenuScreen.fxml");
    }

}
