package sample.gui.controllers;

import javafx.fxml.FXML;

public class AdminEditScreenController extends Screen{

    @FXML
    public void backButtonClick(){
        openScreenFromFXMLFilesPackage("AdminMenuScreen.fxml");
    }
}
