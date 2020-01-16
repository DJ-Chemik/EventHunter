package sample.gui.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;

import java.io.IOException;

public class MainScreenController extends Screen{

    @FXML
    private StackPane mainStackPane;

    public void initialize(){
        loadStartPane();
    }

    public void loadStartPane() {
        FXMLLoader loader = new FXMLLoader(this.getClass().getResource("../fxmlFiles/StartScreen.fxml"));
        Pane pane = null;
        try {
            pane = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Screen screen = loader.getController();
        screen.setMainScreenController(this);
        setScreen(pane);
    }


    public void setScreen(Pane pane) {
        mainStackPane.getChildren().clear();
        mainStackPane.getChildren().add(pane);
    }

}
