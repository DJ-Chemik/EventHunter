package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class Main extends Application {

    private int windowWidth = 1280;
    private int windowHeight = 720;
    private String title = "Event Hunter";

    public void setResolution(int width, int height) {
        windowWidth = width;
        windowHeight = height;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader loader = new FXMLLoader(this.getClass().getResource("gui/fxmlFiles/MainScreen.fxml"));
        StackPane stackPane = loader.load();
        Scene scene = new Scene(stackPane, windowWidth, windowHeight);
        primaryStage.setTitle(title);
        primaryStage.setScene(scene);
        primaryStage.show();
    }




    public static void main(String[] args) {
        launch(args);
    }
}
