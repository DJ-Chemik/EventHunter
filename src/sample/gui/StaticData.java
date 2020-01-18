package sample.gui;

import java.util.ArrayList;

public class StaticData {

    private static ArrayList<Integer> activeFieldsNumbers = new ArrayList<>();

    public static ArrayList<Integer> getActiveFieldsNumbers() {
        return activeFieldsNumbers;
    }

    public static void setActiveFieldsNumbers(Integer[] numbers) {
        activeFieldsNumbers.clear();
        for (int number: numbers) {
            activeFieldsNumbers.add(number);
        }
    }
}
