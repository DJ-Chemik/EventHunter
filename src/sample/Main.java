package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    private int windowWidth = 1280;
    private int windowHeight = 720;
    private String title = "Event Hunter - your place to search something new in your town";

    public void setResolution(int width, int height) {
        windowWidth = width;
        windowHeight = height;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("gui/fxmlFiles/sample.fxml"));
        primaryStage.setTitle(title);
        primaryStage.setScene(new Scene(root, windowWidth, windowHeight));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
