package sample.gui.controllers;

import com.sun.xml.internal.bind.v2.TODO;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import sample.gui.StaticData;
import sample.guidata.admin.Adding;
import sample.guidata.admin.DatabaseObjectTypes;

import java.util.ArrayList;

public class AdminAddScreenController extends Screen {

    @FXML
    Label label0, label1, label2, label3, label4, label5, label6, label7, label8, label9;
    @FXML
    TextField textField0, textField1, textField2, textField3, textField4, textField5;
    @FXML
    ComboBox<String> comboBox6, comboBox7, comboBox8, comboBox9;
    @FXML
    Button addButton6, addButton7, addButton8, addButton9, addButtonMain;
    @FXML
    ListView<String> listView;

    ArrayList<Label> labels = new ArrayList<>();
    ArrayList<TextField> textFields = new ArrayList<>();
    ArrayList<ComboBox> comboBoxes = new ArrayList<>();
    ArrayList<Button> addButtons = new ArrayList<>();
    ArrayList<Control> fields = new ArrayList<>();


    public void initialize() {
        addElementsofViewToArrays();
        displayOnlyNeededFieldsAndLabels();
        specificTypeDataInitialize();
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
                    j[0]++;
                    fields.get(i).setVisible(true);
                });
    }

    private void specificTypeDataInitialize(){
        if (StaticData.getTypeOfIngerention()=="Add"){

            if (StaticData.getElementOfIngerention()=="Event"){
                comboBox6.getItems().add("Koncert");
                comboBox6.getItems().add("Kabaret");
                comboBox6.getItems().add("Występ Teatralny");
            }
            if (StaticData.getElementOfIngerention()=="Person"){
                comboBox6.getItems().add("Muzyk");
                comboBox6.getItems().add("Aktor");
            }
            if (StaticData.getElementOfIngerention()=="Place"){
                addButton7.setVisible(true);
                addButton7.setDisable(true);
            }
            if (StaticData.getElementOfIngerention()=="Town"){
                addButton7.setVisible(true);
                addButton7.setDisable(true);
            }
            if (StaticData.getElementOfIngerention()=="MusicDisc"){
                addButton7.setVisible(true);
                addButton7.setDisable(true);
            }
            if (StaticData.getElementOfIngerention()=="Performance"){
                comboBox6.getItems().add("Kabaret");
                comboBox6.getItems().add("Występ Teatralny");
                addButton7.setDisable(true);
                addButton7.setDisable(true);
                addButton8.setVisible(true);
                addButton8.setDisable(true);
            }

        }else if (StaticData.getTypeOfIngerention()=="Edit"){

        }else if (StaticData.getTypeOfIngerention()=="Delete"){

        }
    }

    @FXML
    public void changeComboBox6(){
        if (StaticData.getElementOfIngerention()=="Event"){
            label8.setVisible(true);
            comboBox8.setVisible(true);
            addButton8.setVisible(true);
            addButton8.setDisable(true);
            if (comboBox6.getValue()=="Koncert"){
                label8.setText("Muzycy");
            }else if (comboBox6.getValue()=="Kabaret" || comboBox6.getValue()=="Występ Teatralny"){
                label8.setText("Przedstawienia");
            }
        }
        if (StaticData.getElementOfIngerention()=="Person"){
            label3.setVisible(true);
            textField3.setVisible(true);
            label7.setVisible(true);
            comboBox7.setVisible(true);
            addButton7.setVisible(true);
            addButton7.setDisable(true);
            if (comboBox6.getValue()=="Muzyk"){
                label0.setText("ID Muzyka");
                label3.setText("Pseudonim");
                label7.setText("Płyty");
                label8.setVisible(true);
                label8.setText("Utwory");
                comboBox8.setVisible(true);
                addButton8.setVisible(true);
                addButton8.setDisable(true);
            }else if (comboBox6.getValue()=="Aktor"){
                label0.setText("ID Aktora");
                label3.setText("Nazwa Grupy");
                label7.setText("Przedstawienia");
                label8.setVisible(false);
                comboBox8.setVisible(false);
                addButton8.setVisible(false);
            }
        }

    }

    @FXML
    public void changeComboBox7(){

    }
    @FXML
    public void changeComboBox8(){

    }
    @FXML
    public void changeComboBox9(){

    }

    @FXML
    public void addButton6Click(){

    }
    @FXML
    public void addButton7Click(){

    }
    @FXML
    public void addButton8Click(){

    }
    @FXML
    public void addButton9Click(){

    }

    @FXML
    public void addButtonMainClick(){
        addCorrectTypeOfObject();
        addParametersOfTuple();
        Adding.addToDatabase();
    }

    private void addCorrectTypeOfObject(){
        String type = StaticData.getElementOfIngerention();
        if (type=="Event"){
            if (comboBox6.getValue()=="Koncert"){
                Adding.setTypeOfObject(DatabaseObjectTypes.CONCERT);
            }else if (comboBox6.getValue()=="Kabaret"){
                Adding.setTypeOfObject(DatabaseObjectTypes.CABARET);
            }else if (comboBox6.getValue()=="Występ Teatralny"){
                Adding.setTypeOfObject(DatabaseObjectTypes.THEATRE_SPECTACLE);
            }
        }else if (type=="Person"){
            if (comboBox6.getValue()=="Aktor"){
                Adding.setTypeOfObject(DatabaseObjectTypes.ACTOR);
            }else if (comboBox6.getValue()=="Muzyk"){
                Adding.setTypeOfObject(DatabaseObjectTypes.MUSICIAN);
            }
        }else if (type=="Place"){
            Adding.setTypeOfObject(DatabaseObjectTypes.PLACE);
        }else if (type=="Town"){
            Adding.setTypeOfObject(DatabaseObjectTypes.TOWN);
        }else if (type=="Song"){
            Adding.setTypeOfObject(DatabaseObjectTypes.SONG);
        }else if (type=="MusicDisc"){
            Adding.setTypeOfObject(DatabaseObjectTypes.MUSIC_DISC);
        }else if (type=="Performance"){
            Adding.setTypeOfObject(DatabaseObjectTypes.PERFORMANCE);
        }
    }

    private void addParametersOfTuple(){
        Adding.clearTupleParameters();
        //TODO
        //dodawanie par pole-wartość
    }

    @FXML
    public void backButtonClick(){
        openScreenFromFXMLFilesPackage("AdminMenuScreen.fxml");
    }
}
