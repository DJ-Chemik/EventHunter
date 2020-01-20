package sample.gui.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

public class AdminDeleteScreenController extends Screen {

    @FXML
    ListView<Object> listView;
    @FXML
    ComboBox<String> comboBoxSpecificType;
    @FXML
    TextField searchTextField;


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
