package sample.gui.controllers;

import javafx.fxml.FXML;
import sample.database.ConnectionWithDatabase;

import java.sql.SQLException;

public class UserMenuScreenController extends Screen {

    @FXML
    public void backButtonClick() {
        openScreenFromFXMLFilesPackage("StartScreen.fxml");
    }



}
