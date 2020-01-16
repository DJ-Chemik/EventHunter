package sample.gui.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class StartScreenController extends Screen {

    @FXML
    private Button startButton;

    @FXML
    public void startClick(){
        openScreenFromFXMLFilesPackage("MenuScreen.fxml");
    }
}
