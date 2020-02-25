package sample.gui.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import sample.database.controllers.*;
import sample.gui.StaticData;
import sample.guidata.admin.AddingOrPrepareToUpdate;
import sample.guidata.admin.DatabaseEnum;
import sample.guidata.admin.Editing;

import java.sql.SQLException;
import java.util.ArrayList;

public class AdminEditScreenController extends Screen{

    @FXML
    private Label label0, label1, label2, label3, label4, label5, label6, label7, label8, label9;
    @FXML
    private TextField textField0, textField1, textField2, textField3, textField4, textField5;
    @FXML
    private ComboBox<String> comboBox6, comboBox7, comboBox8, comboBox9;
    @FXML
    private Button addButton6, addButton7, addButton8, addButton9, updateButtonMain;
    @FXML
    private ListView<String> mainListView;

    private ArrayList<Label> labels = new ArrayList<>();
    private ArrayList<TextField> textFields = new ArrayList<>();
    private ArrayList<ComboBox> comboBoxes = new ArrayList<>();
    private ArrayList<Button> addButtons = new ArrayList<>();
    private ArrayList<Control> fields = new ArrayList<>();

    private ArrayList<Double> idListOfElementsToComboBoxes1 = new ArrayList<>();
    private ArrayList<Double> idListOfElementsToComboBoxes2 = new ArrayList<>();

    private ArrayList<Double> idInListView1 = new ArrayList<>();
    private ArrayList<Double> idInListView2 = new ArrayList<>();

    public void initialize() {
        addElementsofViewToArrays();
        specificTypeDataInitialize();
        displayOnlyNeededFieldsAndLabels();
        fillListsInView();
    }

    private void addElementsofViewToArrays(){
        labels.add(label0);
        labels.add(label1);
        labels.add(label2);
        labels.add(label3);
        labels.add(label4);
        labels.add(label5);
        labels.add(label6);
        labels.add(label7);
        labels.add(label8);
        labels.add(label9);
        textFields.add(textField0);
        textFields.add(textField1);
        textFields.add(textField2);
        textFields.add(textField3);
        textFields.add(textField4);
        textFields.add(textField5);
        comboBoxes.add(comboBox6);
        comboBoxes.add(comboBox7);
        comboBoxes.add(comboBox8);
        comboBoxes.add(comboBox9);
        addButtons.add(addButton6);
        addButtons.add(addButton7);
        addButtons.add(addButton8);
        addButtons.add(addButton9);


        labels.forEach(label -> label.setVisible(false));
        textFields.forEach(textField -> textField.setVisible(false));
        comboBoxes.forEach(comboBox -> comboBox.setVisible(false));
        addButtons.forEach(addButton -> addButton.setVisible(false));
        fields.addAll(textFields);
        fields.addAll(comboBoxes);
    }

    private void displayOnlyNeededFieldsAndLabels(){
        int[] j= {0};
        StaticData.getActiveFieldsNumbers().forEach(
                (i) -> {
                    labels.get(i).setVisible(true);
                    labels.get(i).setText(StaticData.getActiveLabelsNames().get(j[0]));
                    fields.get(i).setVisible(true);
                    if (i <= 5) { //indexes of text fields
                        String s = Editing.getFilledFields().get(j[0]);
                        textFields.get(i).setText(s);
                    }
                    if (i>=6){ //indexes of comboBoxes
                        if (Editing.getFilledFields().size()>j[0]){
                            String s= Editing.getFilledFields().get(j[0]);
                            comboBoxes.get(i-6).getSelectionModel().select(s);
                        }else{
                            //comboBoxes.get(i-6).getSelectionModel().selectFirst();
                        }
                    }
                    j[0]++;
                });
    }

    private void fillListsInView(){
        if (StaticData.getElementOfIngerention()=="MusicDisc"){
            idInListView1.addAll(Editing.getListOfIDsOne());
            mainListView.getItems().setAll(Editing.getListOfStringsOne());
            idInListView2.addAll(Editing.getListOfIDsTwo());
            additionalListViewData.addAll(Editing.getListOfStringsTwo());
        }
        if (StaticData.getElementOfIngerention()=="Performance"){
            idInListView1.addAll(Editing.getListOfIDsOne());
            mainListView.getItems().setAll(Editing.getListOfStringsOne());
        }
        if (StaticData.getElementOfIngerention()=="Person") {
            comboBox6.getSelectionModel().select(Editing.getFilledFields().get(3));
            textField3.setText(Editing.getFilledFields().get(2));
            label3.setVisible(true);
            textField3.setVisible(true);
            if (comboBox6.getValue() == "Muzyk") {
                label0.setText("ID Muzyka");
                label3.setText("Pseudonim");
            } else if (comboBox6.getValue() == "Aktor") {
                label0.setText("ID Aktora");
                label3.setText("Nazwa Grupy");
            }
        }
        if (StaticData.getElementOfIngerention()=="Event"){
            idInListView1.addAll(Editing.getListOfIDsOne());
            mainListView.getItems().setAll(Editing.getListOfStringsOne());
            label8.setVisible(true);
            comboBox8.setVisible(true);
            addButton8.setVisible(true);
            addButton8.setDisable(true);
            comboBox8.setEditable(false);
            if (comboBox6.getValue()=="Koncert"){
                label8.setText("Muzycy");
                comboBox8.getItems().clear();
                addMusiciansToComboBox8();
                cabaretOrPerformanceInComboBox6=0;
            }else if (comboBox6.getValue()=="Kabaret" || comboBox6.getValue()=="Występ Teatralny"){
                label8.setText("Przedstawienia");
                comboBox8.getItems().clear();
                addPerformancesToComboBox();
                cabaretOrPerformanceInComboBox6=1;
            }

        }
        actualListViewInView=1;
    }

    private void specificTypeDataInitialize(){
        if (StaticData.getTypeOfIngerention()=="Edit"){

            if (StaticData.getElementOfIngerention()=="Event"){
                textField2.setPromptText("RRRR-MM-DD");
                comboBox6.setEditable(false);
                comboBox7.setEditable(false);
                comboBox6.getItems().add("Koncert");
                comboBox6.getItems().add("Kabaret");
                comboBox6.getItems().add("Występ Teatralny");
                addPlacesToComboBox();
                idListOfElementsToComboBoxes1.clear();
                idListOfElementsToComboBoxes1= MiejsceController.getListOfIDs();
                idListOfElementsToComboBoxes2.clear();
            }
            if (StaticData.getElementOfIngerention()=="Person"){
                comboBox6.setEditable(false);
                comboBox6.getItems().add("Muzyk");
                comboBox6.getItems().add("Aktor");
                isAllNumberFieldsOk=true;
            }
            if (StaticData.getElementOfIngerention()=="Place"){
                //addButton7.setVisible(true);
                //addButton7.setDisable(true);
                comboBox6.setEditable(false);
                addTownsToComboBox();
                try {
                    idListOfElementsToComboBoxes1.clear();
                    idListOfElementsToComboBoxes1 = MiejscowoscController.getListOfIds();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                isAllNumberFieldsOk=true;
            }
            if (StaticData.getElementOfIngerention()=="Town"){
                addStatesToComboBox();
                comboBox6.setEditable(false);
                comboBox6.setPromptText("Wybierz wojewódzwtwo");
                isAllNumberFieldsOk=true;
            }
            if (StaticData.getElementOfIngerention()=="MusicDisc"){
                addButton6.setVisible(true);
                addButton6.setDisable(true);
                addButton7.setVisible(true);
                addButton7.setDisable(true);
                comboBox6.setEditable(false);
                comboBox7.setEditable(false);
                addMusiciansToComboBox6();
                addSongsToComboBox();
                idListOfElementsToComboBoxes1.clear();
                idListOfElementsToComboBoxes1 = MuzykController.getListOfIDs();
                idListOfElementsToComboBoxes2.clear();
                idListOfElementsToComboBoxes2 = UtworController.getListOfIDs();
            }
            if (StaticData.getElementOfIngerention()=="Song"){
                comboBox6.setEditable(false);
                comboBox7.setEditable(false);
                addMusiciansToComboBox6();
                addMusicDiscsToComboBox();
                idListOfElementsToComboBoxes1.clear();
                idListOfElementsToComboBoxes1 = MuzykController.getListOfIDs();
                idListOfElementsToComboBoxes2.clear();
                idListOfElementsToComboBoxes2 = PlytaController.getListOfIDs();
            }
            if (StaticData.getElementOfIngerention()=="Performance"){
                comboBox6.setEditable(false);
                comboBox6.getItems().add("Kabaret");
                comboBox6.getItems().add("Występ Teatralny");
                comboBox7.setEditable(false);
                addActorsToComboBox();
                idListOfElementsToComboBoxes1.clear();
                idListOfElementsToComboBoxes1 = AktorController.getListOfIDs();
                addButton7.setVisible(true);
                addButton7.setDisable(true);
            }

        }
    }

    private void addStatesToComboBox(){
        comboBox6.getItems().add("Dolnośląskie");
        comboBox6.getItems().add("Kujawsko-Pomorskie");
        comboBox6.getItems().add("Lubelskie");
        comboBox6.getItems().add("Lubuskie");
        comboBox6.getItems().add("Łódzkie");
        comboBox6.getItems().add("Małopolskie");
        comboBox6.getItems().add("Mazowieckie");
        comboBox6.getItems().add("Opolskie");
        comboBox6.getItems().add("Podkarpackie");
        comboBox6.getItems().add("Podlaskie");
        comboBox6.getItems().add("Pomorskie");
        comboBox6.getItems().add("Śląskie");
        comboBox6.getItems().add("Świętokrzyskie");
        comboBox6.getItems().add("Warmińsko-Mazurskie");
        comboBox6.getItems().add("Wielkopolskie");
        comboBox6.getItems().add("Zachodniopomorskie");
    }

    private void addTownsToComboBox(){
        try {
            MiejscowoscController.getAllFromMiejscowosc();
            ArrayList<String> towns = MiejscowoscController.getListOfStrings();
            comboBox6.getItems().addAll(towns);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    private void addActorsToComboBox(){
        try {
            AktorController.getAllFromAktor();
            ArrayList<String> actors = AktorController.getListOfStrings();
            comboBox7.getItems().addAll(actors);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void addMusiciansToComboBox6(){
        try {
            MuzykController.getAllFromMuzyk();
            ArrayList<String> musicians = MuzykController.getListOfStrings();
            comboBox6.getItems().addAll(musicians);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void addMusiciansToComboBox8(){
        try {
            MuzykController.getAllFromMuzyk();
            ArrayList<String> musicians = MuzykController.getListOfStrings();
            comboBox8.getItems().addAll(musicians);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void addPerformancesToComboBox(){
        try {
            PrzedstawienieController.getAllFromPrzedstawienie();
            ArrayList<String> performances = PrzedstawienieController.getListOfStrings();
            comboBox8.getItems().addAll(performances);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void addPlacesToComboBox(){
        try {
            MiejsceController.getAllFromMiejsce();
            ArrayList<String> places = MiejsceController.getListOfStrings();
            comboBox7.getItems().addAll(places);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void addMusicDiscsToComboBox(){
        try {
            PlytaController.getAllFromPlyta();
            ArrayList<String> discs = PlytaController.getListOfStrings();
            comboBox7.getItems().addAll(discs);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void addSongsToComboBox(){
        try {
            UtworController.getAllFromUtwor();
            ArrayList<String> songs = UtworController.getListOfStrings();
            comboBox7.getItems().addAll(songs);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void updateButtonMainClick(){
        isAllNeededFieldsFilled();
        if (isDateOkFormat && isAllNumberFieldsOk && isAllNeededFieldsFilled) {
            addCorrectTypeOfObject();
            AddingOrPrepareToUpdate.clearTupleParameters();
            addParametersOfTuple();
            try {
                Editing.updateInDatabase();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            backButtonClick();
        }else{
            checkWhichFieldsWrong();
        }
    }

    private boolean isAllNeededFieldsFilled() {
        if (textField1.getText().isEmpty()) {
            isAllNeededFieldsFilled = false;
            return false;
        }

        if (StaticData.getElementOfIngerention() == "Event") {
            boolean b1 = textField2.getText().isEmpty();
            boolean b2 = textField3.getText().isEmpty();
            boolean b3 = textField4.getText().isEmpty();
            int i1 = comboBox6.getSelectionModel().getSelectedIndex();
            int i2 = comboBox7.getSelectionModel().getSelectedIndex();
            if (!b1 && !b2 && !b3 && i1 != -1 && i2 != -1) {
                isAllNeededFieldsFilled = true;
                return true;
            }

        }
        if (StaticData.getElementOfIngerention() == "Person") {
            if (comboBox6.getSelectionModel().getSelectedIndex() != -1 && textField2.getText().isEmpty() == false) {
                isAllNeededFieldsFilled = true;
                return true;
            }
        }
        if (StaticData.getElementOfIngerention() == "Place") {
            if (comboBox6.getSelectionModel().getSelectedIndex() != -1) {
                isAllNeededFieldsFilled = true;
                return true;
            }
        }
        if (StaticData.getElementOfIngerention() == "Town") {
            if (comboBox6.getSelectionModel().getSelectedIndex() != -1) {
                isAllNeededFieldsFilled = true;
                return true;
            }
        }
        if (StaticData.getElementOfIngerention() == "MusicDisc") {
            if (!idInListView1.isEmpty() && !idInListView2.isEmpty()) {
                isAllNeededFieldsFilled = true;
                return true;
            }
        }
        if (StaticData.getElementOfIngerention() == "Song") {
            if (comboBox6.getSelectionModel().getSelectedIndex() != -1) {
                isAllNeededFieldsFilled = true;
                return true;
            }
        }
        if (StaticData.getElementOfIngerention() == "Performance") {
            isAllNeededFieldsFilled = true;
            return true;
        }
        return false;
    }

    private void checkWhichFieldsWrong() {
        if (!isDateOkFormat) {
            System.out.println("Date uncorrect");
            checkWrongControlElement(textField2);
        }

        if (!isAllNumberFieldsOk) {
            System.out.println("Number fields uncorrectly filled");
            if (StaticData.getElementOfIngerention() == "Event") {
                if (!textField3.getText().matches("[0-9]+(\\.[0-9][0-9]?)?")){
                    checkWrongControlElement(textField3);
                }
                if (!textField4.getText().matches("[0-9]+")){
                    checkWrongControlElement(textField4);
                }
            }
            if (StaticData.getElementOfIngerention() == "MusicDisc") {
                checkWrongControlElement(textField2);
            }
            if (StaticData.getElementOfIngerention() == "Song") {
                if (!textField2.getText().matches("[0-2][0-9][0-9][0-9]")){
                    checkWrongControlElement(textField2);
                }
                if (!textField4.getText().matches("[0-9]+")){
                    checkWrongControlElement(textField4);
                }
            }
            if (StaticData.getElementOfIngerention() == "Performance") {
                checkWrongControlElement(textField2);
            }
        }

        if (!isAllNeededFieldsFilled) {
            System.out.println("Fill all needed fields (witch * sign)");
            if (textField1.getText().isEmpty()){
                checkWrongControlElement(textField1);
            }
            if (StaticData.getElementOfIngerention() == "Event") {
                if (textField2.getText().isEmpty()){
                    checkWrongControlElement(textField2);
                }
                if (textField3.getText().isEmpty()){
                    checkWrongControlElement(textField3);
                }
                if (textField4.getText().isEmpty()){
                    checkWrongControlElement(textField4);
                }
                if (comboBox6.getSelectionModel().getSelectedIndex()==-1){
                    checkWrongControlElement(comboBox6);
                }
                if (comboBox6.getSelectionModel().getSelectedIndex()==-1){
                    checkWrongControlElement(comboBox7);
                }
            }
            if (StaticData.getElementOfIngerention() == "Person") {
                if (textField2.getText().isEmpty()){
                    checkWrongControlElement(textField2);
                }
                if (comboBox6.getSelectionModel().getSelectedIndex()==-1){
                    checkWrongControlElement(comboBox6);
                }
            }
            if (StaticData.getElementOfIngerention() == "Place") {
                if (comboBox6.getSelectionModel().getSelectedIndex()==-1){
                    checkWrongControlElement(comboBox6);
                }
            }
            if (StaticData.getElementOfIngerention() == "Town") {
                if (comboBox6.getSelectionModel().getSelectedIndex()==-1){
                    checkWrongControlElement(comboBox6);
                }
            }
            if (StaticData.getElementOfIngerention() == "MusicDisc") {
                if (comboBox6.getSelectionModel().getSelectedIndex()==-1){
                    checkWrongControlElement(comboBox6);
                    checkWrongControlElement(addButton6);
                    checkWrongControlElement(mainListView);
                }
                if (comboBox7.getSelectionModel().getSelectedIndex()==-1){
                    checkWrongControlElement(comboBox7);
                    checkWrongControlElement(addButton7);
                    checkWrongControlElement(mainListView);
                }
            }
            if (StaticData.getElementOfIngerention() == "Song") {
                if (comboBox6.getSelectionModel().getSelectedIndex()==-1){
                    checkWrongControlElement(comboBox6);
                }
            }
        }

        updateButtonMain.setDisable(true);
        new Thread(() -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            updateButtonMain.setDisable(false);
            textFields.forEach(textField -> unchenckWrongControlElement(textField));
            comboBoxes.forEach(comboBox -> unchenckWrongControlElement(comboBox));
            unchenckWrongControlElement(mainListView);
            unchenckWrongControlElement(addButton6);
            unchenckWrongControlElement(addButton7);

        }).start();



    }

    private static String defaultStyle = new TextField().getStyle();

    private void checkWrongControlElement(Control control) {
        control.setStyle("-fx-background-color: #FF0000");
    }

    private void unchenckWrongControlElement(Control control){
        control.setStyle(defaultStyle);
    }

    private void addCorrectTypeOfObject(){
        String type = StaticData.getElementOfIngerention();
        if (type=="Event"){
            if (comboBox6.getValue()=="Koncert"){
                AddingOrPrepareToUpdate.setTypeOfObject(DatabaseEnum.objectTypes.CONCERT);
            }else if (comboBox6.getValue()=="Kabaret"){
                AddingOrPrepareToUpdate.setTypeOfObject(DatabaseEnum.objectTypes.CABARET);
            }else if (comboBox6.getValue()=="Występ Teatralny"){
                AddingOrPrepareToUpdate.setTypeOfObject(DatabaseEnum.objectTypes.THEATRE_SPECTACLE);
            }
        }else if (type=="Person"){
            if (comboBox6.getValue()=="Aktor"){
                AddingOrPrepareToUpdate.setTypeOfObject(DatabaseEnum.objectTypes.ACTOR);
            }else if (comboBox6.getValue()=="Muzyk"){
                AddingOrPrepareToUpdate.setTypeOfObject(DatabaseEnum.objectTypes.MUSICIAN);
            }
        }else if (type=="Place"){
            AddingOrPrepareToUpdate.setTypeOfObject(DatabaseEnum.objectTypes.PLACE);
        }else if (type=="Town"){
            AddingOrPrepareToUpdate.setTypeOfObject(DatabaseEnum.objectTypes.TOWN);
        }else if (type=="Song"){
            AddingOrPrepareToUpdate.setTypeOfObject(DatabaseEnum.objectTypes.SONG);
        }else if (type=="MusicDisc"){
            AddingOrPrepareToUpdate.setTypeOfObject(DatabaseEnum.objectTypes.MUSIC_DISC);
        }else if (type=="Performance"){
            AddingOrPrepareToUpdate.setTypeOfObject(DatabaseEnum.objectTypes.PERFORMANCE);
        }
    }

    private void addParametersOfTuple(){
        String type = StaticData.getElementOfIngerention();
        String subtype = comboBox6.getValue();
        ArrayList<String> parameters;
        if (type=="Event"){
            parameters = new ArrayList<>();
            parameters.add(getValueOfField(textField1,false));
            AddingOrPrepareToUpdate.addToTupleParameters(DatabaseEnum.eventFields.NAME, parameters);
            parameters = new ArrayList<>();
            parameters.add(getValueOfField(textField2,false));
            AddingOrPrepareToUpdate.addToTupleParameters(DatabaseEnum.eventFields.DATE, parameters);
            parameters = new ArrayList<>();
            parameters.add(getValueOfField(textField3,false));
            AddingOrPrepareToUpdate.addToTupleParameters(DatabaseEnum.eventFields.TICKET_COST, parameters);
            parameters = new ArrayList<>();
            parameters.add(getValueOfField(textField4,false));
            AddingOrPrepareToUpdate.addToTupleParameters(DatabaseEnum.eventFields.NUMBER_OF_SEATS, parameters);
            parameters = new ArrayList<>();
            parameters.add(getValueOfComboBox(comboBox6,false));
            AddingOrPrepareToUpdate.addToTupleParameters(DatabaseEnum.eventFields.TYPE, parameters);
            parameters = new ArrayList<>();
            int numberComboBoxFiledSelected = comboBox7.getSelectionModel().getSelectedIndex();
            double id = idListOfElementsToComboBoxes1.get(numberComboBoxFiledSelected);
            parameters.add(String.valueOf(id));
            AddingOrPrepareToUpdate.addToTupleParameters(DatabaseEnum.eventFields.PLACE, parameters);

            parameters = new ArrayList<>();
            for (double i : idInListView1){
                parameters.add(String.valueOf(i));
            }
            if (comboBox6.getValue()=="Kabaret" || comboBox6.getValue()=="Występ Teatralny"){
                AddingOrPrepareToUpdate.addToTupleParameters(DatabaseEnum.eventFields.PERFORMANCES,parameters);
            }else if (comboBox6.getValue()=="Koncert"){
                AddingOrPrepareToUpdate.addToTupleParameters(DatabaseEnum.eventFields.MUSICIANS,parameters);
            }


        }else if (type=="Person"){
            if (subtype=="Aktor"){
                parameters = new ArrayList<>();
                parameters.add(getValueOfField(textField1,false));
                AddingOrPrepareToUpdate.addToTupleParameters(DatabaseEnum.actorFields.NAME, parameters);
                parameters = new ArrayList<>();
                parameters.add(getValueOfField(textField2,false));
                AddingOrPrepareToUpdate.addToTupleParameters(DatabaseEnum.actorFields.SURNAME, parameters);
                parameters = new ArrayList<>();
                parameters.add(getValueOfField(textField3,true));
                AddingOrPrepareToUpdate.addToTupleParameters(DatabaseEnum.actorFields.BAND_NAME, parameters);
                parameters = new ArrayList<>(getValuesOfListView(mainListView,true));
                AddingOrPrepareToUpdate.addToTupleParameters(DatabaseEnum.actorFields.PERFORMANCES, parameters);
            }else if (subtype=="Muzyk"){
                parameters = new ArrayList<>();
                parameters.add(getValueOfField(textField1,false));
                AddingOrPrepareToUpdate.addToTupleParameters(DatabaseEnum.musicianFields.NAME, parameters);
                parameters = new ArrayList<>();
                parameters.add(getValueOfField(textField2,false));
                AddingOrPrepareToUpdate.addToTupleParameters(DatabaseEnum.musicianFields.SURNAME, parameters);
                parameters = new ArrayList<>();
                parameters.add(getValueOfField(textField3,true));
                AddingOrPrepareToUpdate.addToTupleParameters(DatabaseEnum.musicianFields.NICKNAME, parameters);
            }
        }else if (type=="Place"){
            parameters = new ArrayList<>();
            parameters.add(getValueOfField(textField1,false));
            AddingOrPrepareToUpdate.addToTupleParameters(DatabaseEnum.placeFields.NAME, parameters);
            parameters = new ArrayList<>();
            parameters.add(getValueOfField(textField2,true));
            AddingOrPrepareToUpdate.addToTupleParameters(DatabaseEnum.placeFields.TYPE, parameters);
            parameters = new ArrayList<>();
            int numberComboBoxFiledSelected = comboBox6.getSelectionModel().getSelectedIndex();
            double id = idListOfElementsToComboBoxes1.get(numberComboBoxFiledSelected);
            parameters.add(String.valueOf(id));
            AddingOrPrepareToUpdate.addToTupleParameters(DatabaseEnum.placeFields.TOWN, parameters);
        }else if (type=="Town"){
            parameters = new ArrayList<>();
            parameters.add(getValueOfField(textField1,false));
            AddingOrPrepareToUpdate.addToTupleParameters(DatabaseEnum.townFields.NAME, parameters);
            parameters = new ArrayList<>();
            parameters.add(getValueOfField(textField2,true));
            AddingOrPrepareToUpdate.addToTupleParameters(DatabaseEnum.townFields.ZIP_CODE, parameters);
            parameters = new ArrayList<>();
            parameters.add(getValueOfComboBox(comboBox6,false));
            AddingOrPrepareToUpdate.addToTupleParameters(DatabaseEnum.townFields.STATE, parameters);
        }else if (type=="Song"){
            parameters = new ArrayList<>();
            parameters.add(getValueOfField(textField1,false));
            AddingOrPrepareToUpdate.addToTupleParameters(DatabaseEnum.songFields.TITLE, parameters);
            parameters = new ArrayList<>();
            parameters.add(getValueOfField(textField2,true));
            AddingOrPrepareToUpdate.addToTupleParameters(DatabaseEnum.songFields.RELEASE_YEAR, parameters);
            parameters = new ArrayList<>();
            parameters.add(getValueOfField(textField3,true));
            AddingOrPrepareToUpdate.addToTupleParameters(DatabaseEnum.songFields.GENRE, parameters);
            parameters = new ArrayList<>();
            parameters.add(getValueOfField(textField4,true));
            AddingOrPrepareToUpdate.addToTupleParameters(DatabaseEnum.songFields.YOUTUBE_VIEWS, parameters);
            parameters = new ArrayList<>();
            int index = comboBox6.getSelectionModel().getSelectedIndex();
            parameters.add(String.valueOf(idListOfElementsToComboBoxes1.get(index)));
            AddingOrPrepareToUpdate.addToTupleParameters(DatabaseEnum.songFields.MUSICIAN, parameters);
            parameters = new ArrayList<>();
            int index2 = comboBox7.getSelectionModel().getSelectedIndex();
            if (index2!=-1){
                parameters.add(String.valueOf(idListOfElementsToComboBoxes2.get(index2)));
            }else{
                parameters.add(null);
            }
            AddingOrPrepareToUpdate.addToTupleParameters(DatabaseEnum.songFields.MUSIC_DISC, parameters);


        }else if (type=="MusicDisc"){
            parameters = new ArrayList<>();
            parameters.add(getValueOfField(textField1,false));
            AddingOrPrepareToUpdate.addToTupleParameters(DatabaseEnum.musicDiscFields.TITLE, parameters);
            parameters = new ArrayList<>();
            parameters.add(getValueOfField(textField2, true));
            AddingOrPrepareToUpdate.addToTupleParameters(DatabaseEnum.musicDiscFields.RELEASE_YEAR, parameters);
            parameters = new ArrayList<>();
            for (double i : idInListView1){
                parameters.add(String.valueOf(i));
            }
            AddingOrPrepareToUpdate.addToTupleParameters(DatabaseEnum.musicDiscFields.MUSICIANS,parameters);
            parameters = new ArrayList<>();
            for (double i : idInListView2){
                parameters.add(String.valueOf(i));
            }
            AddingOrPrepareToUpdate.addToTupleParameters(DatabaseEnum.musicDiscFields.SONGS,parameters);

        }else if (type=="Performance"){
            parameters = new ArrayList<>();
            parameters.add(getValueOfField(textField1,false));
            AddingOrPrepareToUpdate.addToTupleParameters(DatabaseEnum.performanceFields.TITLE, parameters);
            parameters = new ArrayList<>();
            parameters.add(getValueOfField(textField2,false));
            AddingOrPrepareToUpdate.addToTupleParameters(DatabaseEnum.performanceFields.LENGTH, parameters);
            parameters = new ArrayList<>();
            parameters.add(getValueOfComboBox(comboBox6,false));
            AddingOrPrepareToUpdate.addToTupleParameters(DatabaseEnum.performanceFields.TYPE, parameters);
            parameters = new ArrayList<>();
            for (double i: idInListView1) {
                parameters.add(String.valueOf(i));
            }
            AddingOrPrepareToUpdate.addToTupleParameters(DatabaseEnum.performanceFields.ACTORS, parameters);
        }
    }

    private boolean isDateOkFormat = true;
    private boolean isAllNumberFieldsOk = true;
    private boolean isAllNeededFieldsFilled = true;

    @FXML
    public void changeTextField2() {
        if (StaticData.getElementOfIngerention() == "Event") {
            String text = textField2.getText();
            for (String s : StaticData.getCorrectDateFormats()) {
                if (text.matches(s)) {
                    isDateOkFormat = true;
                    break;
                } else {
                    isDateOkFormat = false;
                }
            }
        }
        if (StaticData.getElementOfIngerention() == "Song") {
            if (textField2.getText().matches("[0-2][0-9][0-9][0-9]")
                    && textField4.getText().matches("[0-9]+")) {
                isAllNumberFieldsOk = true;
            } else {
                isAllNumberFieldsOk = false;
            }
        }
        if (StaticData.getElementOfIngerention() == "MusicDisc") {
            if (textField2.getText().matches("[0-9]+")) {
                isAllNumberFieldsOk = true;
            } else {
                isAllNumberFieldsOk = false;
            }
        }
        if (StaticData.getElementOfIngerention() == "Performance") {
            if (textField2.getText().matches("[0-9]+(\\.[0-9]*)?")
                    || textField2.getText().matches("")) {
                isAllNumberFieldsOk = true;
            } else {
                isAllNumberFieldsOk = false;
            }
        }
    }

    @FXML
    public void changeTextField3() {
        if (StaticData.getElementOfIngerention() == "Event") {
            if (textField3.getText().matches("[0-9]+(\\.[0-9][0-9]?)?")
                    && textField4.getText().matches("[0-9]+")) {
                isAllNumberFieldsOk = true;
            } else {
                isAllNumberFieldsOk = false;
            }
        }

    }

    @FXML
    public void changeTextField4() {
        if (StaticData.getElementOfIngerention() == "Song") {
            if (textField2.getText().matches("[0-2][0-9][0-9][0-9]")
                    && textField4.getText().matches("[0-9]+")) {
                isAllNumberFieldsOk = true;
            } else {
                isAllNumberFieldsOk = false;
            }
        }
        if (StaticData.getElementOfIngerention() == "Event") {
            if (textField3.getText().matches("[0-9]+(\\.[0-9][0-9]?)?")
                    && textField4.getText().matches("[0-9]+")) {
                isAllNumberFieldsOk = true;
            } else {
                isAllNumberFieldsOk = false;
            }
        }
    }

    private int cabaretOrPerformanceInComboBox6 = -1;
    @FXML
    public void changeComboBox6(){
        if (StaticData.getElementOfIngerention()=="Event"){
            label8.setVisible(true);
            comboBox8.setVisible(true);
            addButton8.setVisible(true);
            addButton8.setDisable(true);
            comboBox8.setEditable(false);
            if (comboBox6.getValue()=="Koncert"){
                label8.setText("Muzycy");
                comboBox8.getItems().clear();
                addMusiciansToComboBox8();
                if (cabaretOrPerformanceInComboBox6 !=0){
                    mainListView.getItems().clear();
                    idInListView1.clear();
                    idListOfElementsToComboBoxes2.clear();
                    idListOfElementsToComboBoxes2=MuzykController.getListOfIDs();
                    cabaretOrPerformanceInComboBox6 =0;
                }
            }else if (comboBox6.getValue()=="Kabaret" || comboBox6.getValue()=="Występ Teatralny"){
                label8.setText("Przedstawienia");
                comboBox8.getItems().clear();
                addPerformancesToComboBox();
                if (cabaretOrPerformanceInComboBox6 !=1){
                    mainListView.getItems().clear();
                    idInListView1.clear();
                    idListOfElementsToComboBoxes2.clear();
                    idListOfElementsToComboBoxes2=PrzedstawienieController.getListOfIDs();
                    cabaretOrPerformanceInComboBox6 =1;
                }

            }
        }
        if (StaticData.getElementOfIngerention()=="Person"){
            label3.setVisible(true);
            textField3.setVisible(true);
            if (comboBox6.getValue()=="Muzyk"){
                label0.setText("ID Muzyka");
                label3.setText("Pseudonim");
            }else if (comboBox6.getValue()=="Aktor"){
                label0.setText("ID Aktora");
                label3.setText("Nazwa Grupy");
            }
        }
        if (StaticData.getElementOfIngerention()=="MusicDisc"){
            addButton6.setDisable(false);
        }

    }

    @FXML
    public void changeComboBox7() {
        if (StaticData.getElementOfIngerention() == "Performance") {
            addButton7.setDisable(false);
        }
        if (StaticData.getElementOfIngerention()=="MusicDisc"){
            addButton7.setDisable(false);
        }
    }

    @FXML
    public void changeComboBox8(){
        if (StaticData.getElementOfIngerention()=="Event"){
            addButton8.setDisable(false);
        }
    }

    @FXML
    public void changeComboBox9(){

    }


    private boolean isThisIdInListView(double id, int numberOfList) {
        if (numberOfList == 1) {
            for (double idd:idInListView1) {
                if (idd==id){
                    return true;
                }
            }
        } else if (numberOfList == 2) {
            for (Double idd:idInListView2) {
                if (idd==id){
                    return true;
                }
            }
        }
        return false;
    }

    private ArrayList<String> additionalListViewData = new ArrayList<>();
    private int actualListViewInView = 0;

    private void swapListView(int newNumberOfListView){
        if (newNumberOfListView!=actualListViewInView) {
            ArrayList<String> tmpListView = new ArrayList<>();
            for (String s : mainListView.getItems()) {
                tmpListView.add(s);
            }
            mainListView.getItems().clear();
            mainListView.getItems().addAll(additionalListViewData);
            additionalListViewData = tmpListView;
            actualListViewInView=newNumberOfListView;
        }
    }

    @FXML
    public void clickComboBox6(){
        if (StaticData.getElementOfIngerention()=="MusicDisc"){
            swapListView(1);
        }
    }

    @FXML
    public void clickComboBox7(){
        if (StaticData.getElementOfIngerention()=="MusicDisc"){
            swapListView(2);
        }
    }

    @FXML
    public void addButton6Click(){
        if (StaticData.getElementOfIngerention()=="MusicDisc"){
            int selectedField = comboBox6.getSelectionModel().getSelectedIndex();
            double selectedMusicianID = idListOfElementsToComboBoxes1.get(selectedField);
            if (isThisIdInListView(selectedMusicianID,1)==false){
                String actualFieldOfComboBox = comboBox6.getItems().get(selectedField);
                mainListView.getItems().add(actualFieldOfComboBox);
                idInListView1.add(selectedMusicianID);
            }else {
                System.out.println("This Musician exists in this Disc now.");
            }
        }
    }

    @FXML
    public void addButton7Click(){
        if (StaticData.getElementOfIngerention() == "Performance") {
            int selectedField = comboBox7.getSelectionModel().getSelectedIndex();
            double selectedActorID = idListOfElementsToComboBoxes1.get(selectedField);
            if (isThisIdInListView(selectedActorID, 1)==false){
                String actualFieldOfComboBox = comboBox7.getItems().get(selectedField);
                mainListView.getItems().add(actualFieldOfComboBox);
                idInListView1.add(selectedActorID);
            }else{
                System.out.println("This Actor exists in this Performance now.");
            }
        }
        if (StaticData.getElementOfIngerention()=="MusicDisc"){
            int selectedField = comboBox7.getSelectionModel().getSelectedIndex();
            double selectedSongID = idListOfElementsToComboBoxes2.get(selectedField);
            if (isThisIdInListView(selectedSongID,2)==false){
                String actualFieldOfComboBox = comboBox7.getItems().get(selectedField);
                mainListView.getItems().add(actualFieldOfComboBox);
                idInListView2.add(selectedSongID);
            }else {
                System.out.println("This Song exists in this Disc now.");
            }
        }
    }

    @FXML
    public void addButton8Click(){
        if (StaticData.getElementOfIngerention()=="Event"){
            int selectedField = comboBox8.getSelectionModel().getSelectedIndex();
            double selectedSongID = idListOfElementsToComboBoxes2.get(selectedField);
            if (comboBox6.getValue()=="Koncert"){
                if (isThisIdInListView(selectedSongID,1)==false){
                    String actualFieldOfComboBox = comboBox8.getItems().get(selectedField);
                    mainListView.getItems().add(actualFieldOfComboBox);
                    idInListView1.add(selectedSongID);
                }else {
                    System.out.println("This Musician exists in this Event now.");
                }

            }else if (comboBox6.getValue()=="Kabaret" || comboBox6.getValue()=="Występ Teatralny"){
                if (isThisIdInListView(selectedSongID,1)==false){
                    String actualFieldOfComboBox = comboBox8.getItems().get(selectedField);
                    mainListView.getItems().add(actualFieldOfComboBox);
                    idInListView1.add(selectedSongID);
                }else {
                    System.out.println("This Performance exists in this Event now.");
                }

            }
        }
    }

    @FXML
    public void addButton9Click(){

    }

    /**
     *
     * @return text from textField with or without blocade of return empty string
     */
    private String getValueOfField(TextField textField, boolean canBeEmpty){
        if (canBeEmpty){
            return textField.getText();
        }else{
            if (textField.getText().isEmpty()){
                throw new NullPointerException();
            }else{
                return textField.getText();
            }
        }
    }

    private String getValueOfComboBox(ComboBox<String> comboBox, boolean canBeEmpty){
        if (canBeEmpty){
            return comboBox.getValue();
        }else{
            if (comboBox.getValue().isEmpty()){
                throw new NullPointerException();
            }else{
                return comboBox.getValue();
            }
        }
    }

    private ArrayList<String> getValuesOfListView(ListView<String> listView, boolean canBeEmpty) {
        ArrayList<String> result = new ArrayList<>();
        for (String s : listView.getItems()) {
            result.add(s);
        }

        if (canBeEmpty) {
            return result;
        } else {
            if (listView.getItems().isEmpty()) {
                throw new NullPointerException();
            } else {
                return result;
            }
        }
    }


    @FXML
    public void backButtonClick(){
        openScreenFromFXMLFilesPackage("AdminMenuScreen.fxml");
    }
}
