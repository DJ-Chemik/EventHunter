package sample.gui.controllers;

import javafx.fxml.FXML;
import sample.gui.StaticData;

import java.util.ArrayList;

public class AdminMenuScreenController extends Screen {

    @FXML
    public void addEventClick(){
        Integer[] numbers = {0,1,2,3,4,6,7,8};
        StaticData.setActiveFieldsNumbers(numbers);
        openScreenFromFXMLFilesPackage("AdminAddScreen.fxml");
    }
    @FXML
    public void addPersonClick(){
        Integer[] numbers = {0,1,2,3,6,7,8};
        StaticData.setActiveFieldsNumbers(numbers);
        openScreenFromFXMLFilesPackage("AdminAddScreen.fxml");
    }
    @FXML
    public void addPlaceClick(){
        Integer[] numbers = {0,1,2,6,7,8,9};
        StaticData.setActiveFieldsNumbers(numbers);
        openScreenFromFXMLFilesPackage("AdminAddScreen.fxml");
    }
    @FXML
    public void addTownClick(){
        Integer[] numbers = {0,1,2,6};
        StaticData.setActiveFieldsNumbers(numbers);
        openScreenFromFXMLFilesPackage("AdminAddScreen.fxml");
    }
    @FXML
    public void addSongClick(){
        Integer[] numbers = {0,1,2,3,4,6,7,8};
        StaticData.setActiveFieldsNumbers(numbers);
        openScreenFromFXMLFilesPackage("AdminAddScreen.fxml");
    }
    @FXML
    public void addMusicDiscClick(){
        Integer[] numbers = {0,1,2,6,7};
        StaticData.setActiveFieldsNumbers(numbers);
        openScreenFromFXMLFilesPackage("AdminAddScreen.fxml");
    }
    @FXML
    public void addPerformanceClick(){
        Integer[] numbers = {0,1,2,6,7};
        StaticData.setActiveFieldsNumbers(numbers);
        openScreenFromFXMLFilesPackage("AdminAddScreen.fxml");
    }


    @FXML
    public void editEventClick(){
        openScreenFromFXMLFilesPackage("AdminEditScreen.fxml");
    }
    @FXML
    public void editPersonClick(){
        openScreenFromFXMLFilesPackage("AdminEditScreen.fxml");
    }
    @FXML
    public void editPlaceClick(){
        openScreenFromFXMLFilesPackage("AdminEditScreen.fxml");
    }
    @FXML
    public void editTownClick(){
        openScreenFromFXMLFilesPackage("AdminEditScreen.fxml");
    }
    @FXML
    public void editSongClick(){
        openScreenFromFXMLFilesPackage("AdminEditScreen.fxml");
    }
    @FXML
    public void editMusicDiscClick(){
        openScreenFromFXMLFilesPackage("AdminEditScreen.fxml");
    }
    @FXML
    public void editPerformanceClick(){
        openScreenFromFXMLFilesPackage("AdminEditScreen.fxml");
    }


    @FXML
    public void deleteEventClick(){
        openScreenFromFXMLFilesPackage("AdminDeleteScreen.fxml");
    }
    @FXML
    public void deletePersonClick(){
        openScreenFromFXMLFilesPackage("AdminDeleteScreen.fxml");
    }
    @FXML
    public void deletePlaceClick(){
        openScreenFromFXMLFilesPackage("AdminDeleteScreen.fxml");
    }
    @FXML
    public void deleteTownClick(){
        openScreenFromFXMLFilesPackage("AdminDeleteScreen.fxml");
    }
    @FXML
    public void deleteSongClick(){
        openScreenFromFXMLFilesPackage("AdminDeleteScreen.fxml");
    }
    @FXML
    public void deleteMusicDiscClick(){
        openScreenFromFXMLFilesPackage("AdminDeleteScreen.fxml");
    }
    @FXML
    public void deletePerformanceClick(){
        openScreenFromFXMLFilesPackage("AdminDeleteScreen.fxml");
    }
}
