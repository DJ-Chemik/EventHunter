package sample.gui;

import java.util.ArrayList;

public class StaticData {

    private static ArrayList<Integer> activeFieldsNumbers = new ArrayList<>();
    private static ArrayList<String> activeLabelsNames = new ArrayList<>();
    private static String typeOfIngerention = "";
    private static String elementOfIngerention = "";
    private static ArrayList<String> correctDateFormats = new ArrayList<>();


    public static void addCorrectDateFormats(){
        String corectYear = "[0-2][0-9][0-9][0-9]";
        correctDateFormats.add(corectYear + "-" + "[0][1,3-9]" + "-" + "[0-2][0-9]");
        correctDateFormats.add(corectYear + "-" + "[0][1,3,5,7,8]" + "-" + "[3][0-1]");
        correctDateFormats.add(corectYear + "-" + "[0][4,6,9]" + "-" + "[3][0]");

        correctDateFormats.add(corectYear + "-" + "[0][2]" + "-" + "[0-1][0-9]");
        correctDateFormats.add(corectYear + "-" + "[0][2]" + "-" + "[0-2][0-8]");

        correctDateFormats.add(corectYear + "-" + "[1][0-2]" + "-" +"[0-2][0-9]");
        correctDateFormats.add(corectYear + "-" + "[1][0,2]" + "-" +"[3][0-1]");
        correctDateFormats.add(corectYear + "-" + "[1][1]" + "-" + "[3][0]");
    }

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

    public static ArrayList<String> getCorrectDateFormats() {
        return correctDateFormats;
    }
}
