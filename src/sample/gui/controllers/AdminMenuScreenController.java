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
        Integer[] numbers = {1,2,3,4,6,7};
        String[] names = {"Nazwa", "Data", "Cena biletu","Ilość miejsc", "Rodzaj imprezy", "Miejsce Imprezy"};
        StaticData.setActiveFieldsNumbers(numbers);
        StaticData.setActiveLabelsNames(names);
        StaticData.setTypeAndElementOfIngeretion("Add", "Event");
        openScreenFromFXMLFilesPackage("AdminAddScreen.fxml");
    }
    @FXML
    public void addPersonClick(){
        Integer[] numbers = {1,2,6};
        String[] names = {"Imię", "Nazwisko", "Zawód"};
        StaticData.setActiveFieldsNumbers(numbers);
        StaticData.setActiveLabelsNames(names);
        StaticData.setTypeAndElementOfIngeretion("Add", "Person");
        openScreenFromFXMLFilesPackage("AdminAddScreen.fxml");
    }
    @FXML
    public void addPlaceClick(){
        Integer[] numbers = {1,2,6};
        String[] names = {"Nazwa", "Typ Obiektu", "Miejscowość"};
        StaticData.setActiveFieldsNumbers(numbers);
        StaticData.setActiveLabelsNames(names);
        StaticData.setTypeAndElementOfIngeretion("Add", "Place");
        openScreenFromFXMLFilesPackage("AdminAddScreen.fxml");
    }
    @FXML
    public void addTownClick(){
        Integer[] numbers = {1,2,6};
        String[] names = {"Nazwa", "Kod pocztowy", "Województwo"};
        StaticData.setActiveFieldsNumbers(numbers);
        StaticData.setActiveLabelsNames(names);
        StaticData.setTypeAndElementOfIngeretion("Add", "Town");
        openScreenFromFXMLFilesPackage("AdminAddScreen.fxml");
    }
    @FXML
    public void addSongClick(){
        Integer[] numbers = {1,2,3,4,6,7};
        String[] names = {"Tytuł", "Rok wydania", "Gatunek", "Ilość wyświetleń na YouTube", "Muzyk", "Płyta"};
        StaticData.setActiveFieldsNumbers(numbers);
        StaticData.setActiveLabelsNames(names);
        StaticData.setTypeAndElementOfIngeretion("Add", "Song");
        openScreenFromFXMLFilesPackage("AdminAddScreen.fxml");
    }
    @FXML
    public void addMusicDiscClick(){
        Integer[] numbers = {1,2,6,7};
        String[] names = {"Tytuł", "Rok wydania", "Muzycy", "Utwory"};
        StaticData.setActiveFieldsNumbers(numbers);
        StaticData.setActiveLabelsNames(names);
        StaticData.setTypeAndElementOfIngeretion("Add", "MusicDisc");
        openScreenFromFXMLFilesPackage("AdminAddScreen.fxml");
    }
    @FXML
    public void addPerformanceClick(){
        Integer[] numbers = {1,2,6,7};
        String[] names = {"Tytuł", "Długość", "Rodzaj występu","Aktorzy"};
        StaticData.setActiveFieldsNumbers(numbers);
        StaticData.setActiveLabelsNames(names);
        StaticData.setTypeAndElementOfIngeretion("Add", "Performance");
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
        StaticData.setTypeAndElementOfIngeretion("Delete", "Event");
        openScreenFromFXMLFilesPackage("AdminDeleteScreen.fxml");
    }
    @FXML
    public void deletePersonClick(){
        StaticData.setTypeAndElementOfIngeretion("Delete", "Person");
        openScreenFromFXMLFilesPackage("AdminDeleteScreen.fxml");
    }
    @FXML
    public void deletePlaceClick(){
        StaticData.setTypeAndElementOfIngeretion("Delete", "Place");
        openScreenFromFXMLFilesPackage("AdminDeleteScreen.fxml");
    }
    @FXML
    public void deleteTownClick(){
        StaticData.setTypeAndElementOfIngeretion("Delete", "Town");
        openScreenFromFXMLFilesPackage("AdminDeleteScreen.fxml");
    }
    @FXML
    public void deleteSongClick(){
        StaticData.setTypeAndElementOfIngeretion("Delete", "Song");
        openScreenFromFXMLFilesPackage("AdminDeleteScreen.fxml");
    }
    @FXML
    public void deleteMusicDiscClick(){
        StaticData.setTypeAndElementOfIngeretion("Delete", "MusicDisc");
        openScreenFromFXMLFilesPackage("AdminDeleteScreen.fxml");
    }
    @FXML
    public void deletePerformanceClick(){
        StaticData.setTypeAndElementOfIngeretion("Delete", "Performance");
        openScreenFromFXMLFilesPackage("AdminDeleteScreen.fxml");
    }
}
