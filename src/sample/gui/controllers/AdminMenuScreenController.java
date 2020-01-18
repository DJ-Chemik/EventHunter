package sample.gui.controllers;

import javafx.fxml.FXML;
import sample.gui.StaticData;

import java.util.ArrayList;

public class AdminMenuScreenController extends Screen {

    @FXML
    public void backButtonClick(){
        openScreenFromFXMLFilesPackage("StartScreen.fxml");
    }

    @FXML
    public void addEventClick(){
        Integer[] numbers = {0,1,2,3,4,6};
        String[] names = {"ID Występu", "Nazwa", "Data", "Cena biletu","Ilość miejsc", "Rodzaj imprezy"};
        StaticData.setActiveFieldsNumbers(numbers);
        StaticData.setActiveLabelsNames(names);
        openScreenFromFXMLFilesPackage("AdminAddScreen.fxml");
    }
    @FXML
    public void addPersonClick(){
        Integer[] numbers = {0,1,2,3,6,7,8};
        String[] names = {"ID Wykonawcy", "Imię", "Nazwisko", "Pseudonim", "Płyty", "Utwory", "Koncerty"};
        StaticData.setActiveFieldsNumbers(numbers);
        StaticData.setActiveLabelsNames(names);
        openScreenFromFXMLFilesPackage("AdminAddScreen.fxml");
    }
    @FXML
    public void addPlaceClick(){
        Integer[] numbers = {0,1,2,6,7};
        String[] names = {"ID Obiektu", "Nazwa", "Typ Obiektu", "Miejscowość", "Impreza"};
        StaticData.setActiveFieldsNumbers(numbers);
        StaticData.setActiveLabelsNames(names);
        openScreenFromFXMLFilesPackage("AdminAddScreen.fxml");
    }
    @FXML
    public void addTownClick(){
        Integer[] numbers = {0,1,2,6};
        String[] names = {"ID Miasta", "Nazwa", "Kod pocztowy", "Województwo"};
        StaticData.setActiveFieldsNumbers(numbers);
        StaticData.setActiveLabelsNames(names);
        openScreenFromFXMLFilesPackage("AdminAddScreen.fxml");
    }
    @FXML
    public void addSongClick(){
        Integer[] numbers = {0,1,2,3,4,6,7,8};
        String[] names = {"ID Utworu", "Tytuł", "Rok wydania", "Gatunek", "Ilość wyświetleń na YouTube", "Muzycy", "Płyta", "Koncerty"};
        StaticData.setActiveFieldsNumbers(numbers);
        StaticData.setActiveLabelsNames(names);
        openScreenFromFXMLFilesPackage("AdminAddScreen.fxml");
    }
    @FXML
    public void addMusicDiscClick(){
        Integer[] numbers = {0,1,2,6,7};
        String[] names = {"ID Płyty", "Tytuł", "Rok wydania", "Muzycy", "Utwory"};
        StaticData.setActiveFieldsNumbers(numbers);
        StaticData.setActiveLabelsNames(names);
        openScreenFromFXMLFilesPackage("AdminAddScreen.fxml");
    }
    @FXML
    public void addPerformanceClick(){
        Integer[] numbers = {0,1,2,6,7,8};
        String[] names = {"ID Przedstawienia", "Tytuł", "Długość", "Aktorzy", "Rodzaj występu", "Konkretny występ"};
        StaticData.setActiveFieldsNumbers(numbers);
        StaticData.setActiveLabelsNames(names);
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
