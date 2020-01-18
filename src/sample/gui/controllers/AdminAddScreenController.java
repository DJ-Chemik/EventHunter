package sample.gui.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import sample.gui.StaticData;

import java.util.ArrayList;

public class AdminAddScreenController extends Screen {

    @FXML
    Label label0, label1, label2, label3, label4, label5, label6, label7, label8, label9;
    @FXML
    TextField textField0, textField1, textField2, textField3, textField4, textField5;
    @FXML
    ComboBox<Object> comboBox6, comboBox7, comboBox8, comboBox9;
    @FXML
    ListView<Object> listView;

    ArrayList<Label> labels = new ArrayList<>();
    ArrayList<TextField> textFields = new ArrayList<>();
    ArrayList<ComboBox> comboBoxes = new ArrayList<>();
    ArrayList<Control> fields = new ArrayList<>();

    public void initialize() {
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

        labels.forEach(label -> label.setVisible(false));
        textFields.forEach(textField -> textField.setVisible(false));
        comboBoxes.forEach(comboBox -> comboBox.setVisible(false));
        fields.addAll(textFields);
        fields.addAll(comboBoxes);


        displayOnlyNeededFieldsAndLabels();

    }

    private void displayOnlyNeededFieldsAndLabels(){
        int[] j= {0};
        StaticData.getActiveFieldsNumbers().forEach(
                (i) -> {
                    labels.get(i).setVisible(true);
                    labels.get(i).setText(StaticData.getActiveLabelsNames().get(j[0]));
                    fields.get(i).setVisible(true);
                    j[0]++;
                });



    }

    @FXML
    public void backButtonClick(){
        openScreenFromFXMLFilesPackage("AdminMenuScreen.fxml");
    }
}
