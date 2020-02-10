package sample.gui.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import sample.database.ConnectionWithDatabase;
import sample.database.controllers.AktorController;
import sample.database.controllers.MiejscowoscController;

import java.sql.SQLException;

public class StartScreenController extends Screen {

    @FXML
    private Button startButton;
    @FXML
    private Pane pane;

    @FXML
    public void startAsUserClick(){
        openScreenFromFXMLFilesPackage("UserMenuScreen.fxml");
    }

    @FXML
    public void startAsAdminClick(){
        openScreenFromFXMLFilesPackage("AdminMenuScreen.fxml");
    }

    @FXML
    public void connectWithDatabaseButtonClick(){

        try {
            connectWithDatabase();
            pane.setStyle("-fx-background-color: #0000AA");
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("CONNECTION ERROR");
            pane.setStyle("-fx-background-color: #AA0000");
        }
    }


    private void connectWithDatabase(){
        ConnectionWithDatabase db = new ConnectionWithDatabase();
        db.connect();
        System.out.println(db);
        System.out.println(db.getConnection());

    }
}
