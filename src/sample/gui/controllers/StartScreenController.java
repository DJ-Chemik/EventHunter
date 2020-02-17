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
    private Pane pane, connectPane;
    @FXML
    private TextField hostTextField, portTextField, schemeTextField, usernameTextField, passwordTextField;

    private static boolean isAppConnectedToDatabase = false;
    private static String styleWhenAppIsConnectedToDatabase = "-fx-background-color: #0000AA";
    private static String styleWhenAppIsNotConnectedToDatabase = "-fx-background-color: #880000";


    public void initialize(){
        if  (isAppConnectedToDatabase){
            pane.setStyle(styleWhenAppIsConnectedToDatabase);
            connectPane.setVisible(false);
        }else{
            pane.setStyle(styleWhenAppIsNotConnectedToDatabase);
        }
    }

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
        isAppConnectedToDatabase=true;
        pane.setStyle(styleWhenAppIsConnectedToDatabase);
        connectPane.setVisible(false);
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
