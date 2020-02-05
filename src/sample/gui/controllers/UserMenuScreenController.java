package sample.gui.controllers;

import javafx.fxml.FXML;

public class UserMenuScreenController extends Screen {

    @FXML
    public void backButtonClick() {
        openScreenFromFXMLFilesPackage("StartScreen.fxml");
    }

}
