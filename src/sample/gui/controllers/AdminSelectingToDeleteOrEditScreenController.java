package sample.gui.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import sample.database.controllers.*;
import sample.gui.StaticData;
import sample.guidata.admin.DatabaseEnum;
import sample.guidata.admin.Deleting;
import sample.guidata.admin.Editing;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class AdminSelectingToDeleteOrEditScreenController extends Screen {

    @FXML
    private ListView<String> listView;
    @FXML
    private ComboBox<String> comboBoxSpecificType;
    @FXML
    private TextField searchTextField;
    @FXML
    private Button mainButton;


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
        if (StaticData.getTypeOfIngerention() == "Delete" || StaticData.getTypeOfIngerention() == "Edit") {
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

            if (StaticData.getTypeOfIngerention() == "Edit") {
                mainButton.setText("EDIT");
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
                    objectsNames = MuzykController.getListOfStrings();
                    objectsIDs = MuzykController.getListOfIDs();
                } catch (SQLException e) {
                    e.printStackTrace();
                }

            }
            if (objectsIDs != null && objectsNames != null) {
                for (int i = 0; i < objectsIDs.size(); i++) {
                    objectsIdMap.put(objectsNames.get(i), objectsIDs.get(i));
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
                    objectsNames = WydarzenieController.getListOfTheathreSpectaclStrings();
                    objectsIDs = WydarzenieController.getListOfIDs();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            } else if (comboBoxSpecificType.getItems().get(index) == "Kabaret") {
                try {
                    objectsNames = WydarzenieController.getListOfCabaretsStrings();
                    objectsIDs = WydarzenieController.getListOfIDs();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            } else if (comboBoxSpecificType.getItems().get(index).equals("Koncert")) {
                try {
                    objectsNames = WydarzenieController.getListOfConcertStrings();
                    objectsIDs = WydarzenieController.getListOfIDs();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (objectsIDs != null && objectsNames != null) {
                objectsIdMap.clear();
                for (int i = 0; i < objectsIDs.size(); i++) {
                    objectsIdMap.put(objectsNames.get(i), objectsIDs.get(i));
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
    public void mainButtonClick() throws SQLException {

        if (listView.getSelectionModel().getSelectedIndex() == -1) {
            return;
        }
        String string = listView.getSelectionModel().getSelectedItem();
        double id = objectsIdMap.get(string);

        if (StaticData.getTypeOfIngerention() == "Delete") {
            if (StaticData.getElementOfIngerention() == "Event") {
                if (comboBoxSpecificType.getSelectionModel().getSelectedItem() == "Występ Teatralny") {
                    Deleting.deleteFromDatabase(DatabaseEnum.objectTypes.THEATRE_SPECTACLE, id);
                } else if (comboBoxSpecificType.getSelectionModel().getSelectedItem() == "Kabaret") {
                    Deleting.deleteFromDatabase(DatabaseEnum.objectTypes.CABARET, id);
                } else if (comboBoxSpecificType.getSelectionModel().getSelectedItem() == "Koncert") {
                    Deleting.deleteFromDatabase(DatabaseEnum.objectTypes.CONCERT, id);
                }
            }
            if (StaticData.getElementOfIngerention() == "Person") {

                if (comboBoxSpecificType.getSelectionModel().getSelectedItem() == "Aktor") {
                    Deleting.deleteFromDatabase(DatabaseEnum.objectTypes.ACTOR, id);
                } else if (comboBoxSpecificType.getSelectionModel().getSelectedItem() == "Muzyk") {
                    Deleting.deleteFromDatabase(DatabaseEnum.objectTypes.MUSICIAN, id);
                }
            }
            if (StaticData.getElementOfIngerention() == "Place") {
                Deleting.deleteFromDatabase(DatabaseEnum.objectTypes.PLACE, id);
            }
            if (StaticData.getElementOfIngerention() == "Town") {
                Deleting.deleteFromDatabase(DatabaseEnum.objectTypes.TOWN, id);
            }
            if (StaticData.getElementOfIngerention() == "MusicDisc") {
                Deleting.deleteFromDatabase(DatabaseEnum.objectTypes.MUSIC_DISC, id);
            }
            if (StaticData.getElementOfIngerention() == "Song") {
                Deleting.deleteFromDatabase(DatabaseEnum.objectTypes.SONG, id);
            }
            if (StaticData.getElementOfIngerention() == "Performance") {
                Deleting.deleteFromDatabase(DatabaseEnum.objectTypes.PERFORMANCE, id);
            }
            refresh();

        } else if (StaticData.getTypeOfIngerention() == "Edit") {
            if (StaticData.getElementOfIngerention() == "Event") {
                if (comboBoxSpecificType.getSelectionModel().getSelectedItem() == "Występ Teatralny") {
                    Editing.directToEditScreen(DatabaseEnum.objectTypes.THEATRE_SPECTACLE, id);
                } else if (comboBoxSpecificType.getSelectionModel().getSelectedItem() == "Kabaret") {
                    Editing.directToEditScreen(DatabaseEnum.objectTypes.CABARET, id);
                } else if (comboBoxSpecificType.getSelectionModel().getSelectedItem() == "Koncert") {
                    Editing.directToEditScreen(DatabaseEnum.objectTypes.CONCERT, id);
                }
            }
            if (StaticData.getElementOfIngerention() == "Person") {

                if (comboBoxSpecificType.getSelectionModel().getSelectedItem() == "Aktor") {
                    Editing.directToEditScreen(DatabaseEnum.objectTypes.ACTOR, id);
                } else if (comboBoxSpecificType.getSelectionModel().getSelectedItem() == "Muzyk") {
                    Editing.directToEditScreen(DatabaseEnum.objectTypes.MUSICIAN, id);
                }
            }
            if (StaticData.getElementOfIngerention() == "Place") {
                Editing.directToEditScreen(DatabaseEnum.objectTypes.PLACE, id);
            }
            if (StaticData.getElementOfIngerention() == "Town") {
                Editing.directToEditScreen(DatabaseEnum.objectTypes.TOWN, id);
            }
            if (StaticData.getElementOfIngerention() == "MusicDisc") {
                Editing.directToEditScreen(DatabaseEnum.objectTypes.MUSIC_DISC, id);
            }
            if (StaticData.getElementOfIngerention() == "Song") {
                Editing.directToEditScreen(DatabaseEnum.objectTypes.SONG, id);
            }
            if (StaticData.getElementOfIngerention() == "Performance") {
                Editing.directToEditScreen(DatabaseEnum.objectTypes.PERFORMANCE, id);
            }
            openScreenFromFXMLFilesPackage("AdminEditScreen.fxml");
        }


    }

    private void refresh() {
        openScreenFromFXMLFilesPackage("AdminSelectingToDeleteOrEditScreen.fxml");
    }

    @FXML
    public void backButtonClick() {
        openScreenFromFXMLFilesPackage("AdminMenuScreen.fxml");
    }

}
