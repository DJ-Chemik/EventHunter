package sample.gui.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import sample.gui.StaticData;

public class AdminDeleteScreenController extends Screen {

    @FXML
    ListView<Object> listView;
    @FXML
    ComboBox<String> comboBoxSpecificType;
    @FXML
    TextField searchTextField;

    public void initialize() {
        setComboBoxSpecificType();

    }

    private void setComboBoxSpecificType(){
        if (StaticData.getTypeOfIngerention() == "Delete") {

            if (StaticData.getElementOfIngerention() == "Event") {

            }
            if (StaticData.getElementOfIngerention() == "Person") {

            }
            if (StaticData.getElementOfIngerention() == "Place") {

            }
            if (StaticData.getElementOfIngerention() == "Town") {

            }
            if (StaticData.getElementOfIngerention() == "MusicDisc") {

            }
            if (StaticData.getElementOfIngerention() == "Song") {

            }
            if (StaticData.getElementOfIngerention() == "Performance") {

            }
        }
    }


    @FXML
    public void comboBoxSpecificTypeChange() {

    }

    @FXML
    public void searchTextFieldChange() {

    }

    @FXML
    public void backButtonClick() {
        openScreenFromFXMLFilesPackage("AdminMenuScreen.fxml");
    }

}
