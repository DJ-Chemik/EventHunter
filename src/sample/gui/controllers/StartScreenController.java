package sample.gui.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class StartScreenController extends Screen {

    @FXML
    private Button startButton;

    @FXML
    public void startAsUserClick(){
        openScreenFromFXMLFilesPackage("UserMenuScreen.fxml");
    }

    @FXML
    public void startAsAdminClick(){
        openScreenFromFXMLFilesPackage("AdminMenuScreen.fxml");
    }
}
