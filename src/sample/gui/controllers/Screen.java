package sample.gui.controllers;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Pane;
import java.io.IOException;

public abstract class Screen {

    private MainScreenController mainScreenController;

    public void setMainScreenController(MainScreenController mainScreenController) {
        this.mainScreenController = mainScreenController;
    }

    public void openScreen(String localization){
        FXMLLoader loader = new FXMLLoader(this.getClass().getResource(localization));
        Pane pane = null;
        try {
            pane = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Screen screen = loader.getController();
        screen.setMainScreenController(mainScreenController);
        mainScreenController.setScreen(pane);
    }

    public void openScreenFromFXMLFilesPackage(String nameOfFXMLFile){
        try{
            openScreen("/sample/gui/fxmlFiles/" + nameOfFXMLFile);
        }catch (Exception e){
            System.out.println("Error in open FXML FILE. Unresolved name: <" + nameOfFXMLFile + "] :-(");
        }
    }




}
