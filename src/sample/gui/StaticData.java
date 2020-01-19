package sample.gui;

import java.util.ArrayList;

public class StaticData {

    private static ArrayList<Integer> activeFieldsNumbers = new ArrayList<>();
    private static ArrayList<String> activeLabelsNames = new ArrayList<>();
    private static String typeOfIngeretion = "";
    private static String elementOfIngeretion = "";


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
        typeOfIngeretion=type;
        elementOfIngeretion=element;
    }

    public static String getTypeOfIngeretion() {
        return typeOfIngeretion;
    }

    public static String getElementOfIngeretion() {
        return elementOfIngeretion;
    }
}
