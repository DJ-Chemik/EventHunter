package sample.gui;

import java.util.ArrayList;

public class StaticData {

    private static ArrayList<Integer> activeFieldsNumbers = new ArrayList<>();
    private static ArrayList<String> activeLabelsNames = new ArrayList<>();
    private static String typeOfIngerention = "";
    private static String elementOfIngerention = "";


    public static ArrayList<String> getActiveLabelsNames() {
        return activeLabelsNames;
    }

    public static ArrayList<Integer> getActiveFieldsNumbers() {
        return activeFieldsNumbers;
    }

    public static void setActiveLabelsNames(String[] names) {
        activeLabelsNames.clear();
        for (String name:names){
            activeLabelsNames.add(name);
        }
    }

    public static void setActiveFieldsNumbers(Integer[] numbers) {
        activeFieldsNumbers.clear();
        for (int number: numbers) {
            activeFieldsNumbers.add(number);
        }
    }

    public static void setTypeAndElementOfIngeretion(String type, String element){
        typeOfIngerention =type;
        elementOfIngerention =element;
    }

    public static String getTypeOfIngerention() {
        return typeOfIngerention;
    }

    public static String getElementOfIngerention() {
        return elementOfIngerention;
    }
}
