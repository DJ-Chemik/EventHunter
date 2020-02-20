package sample.gui.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;

public class UserInformationScreenController extends Screen{

    @FXML
    private Label eventLabel0,eventLabel1,eventLabel2,eventLabel3,eventLabel4,
            townLabel0,townLabel1,townLabel2,townLabel3,townLabel4,
            listLabel1,listLabel2,musicianSongsAmountLabel;
    @FXML
    private ListView<String> listView1, listView2;

    @FXML
    public void listView1Change(){

    }

    @FXML
    public void backButtonClick() {
        openScreenFromFXMLFilesPackage("UserMenuScreen.fxml");
    }
}
