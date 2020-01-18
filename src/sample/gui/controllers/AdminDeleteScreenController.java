package sample.gui.controllers;

import javafx.fxml.FXML;

public class AdminDeleteScreenController extends Screen{

    @FXML
    public void backButtonClick(){
        openScreenFromFXMLFilesPackage("AdminMenuScreen.fxml");
    }
}
