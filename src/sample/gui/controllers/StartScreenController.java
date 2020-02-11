package sample.gui.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
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
    private TextField hostTextField, portTextField, schemeTextField, usernameTextField, passwordTextField;

    @FXML
    public void startAsUserClick(){
        openScreenFromFXMLFilesPackage("UserMenuScreen.fxml");
    }

    @FXML
    public void startAsAdminClick(){
        openScreenFromFXMLFilesPackage("AdminMenuScreen.fxml");
    }

    @FXML
    public void connectWithDatabaseButtonClick() {
        connectWithDatabase();
        pane.setStyle("-fx-background-color: #0000AA");

    }


    private void connectWithDatabase(){
        String host = hostTextField.getText();
        String port = portTextField.getText();
        String scheme = schemeTextField.getText();
        String user = usernameTextField.getText();
        String password = passwordTextField.getText();
        ConnectionWithDatabase db = new ConnectionWithDatabase(host,port,scheme,user,password);
        db.connect();
        System.out.println(db);
        System.out.println(db.getConnection());

    }
}
