package sample.guidata.admin;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Adding {

    private static DatabaseEnum.objectTypes typeOfObject; //All options in DatabaseEnum enum class
    private static Map<DatabaseEnum.fields, ArrayList<String>> tupleParameters = new HashMap<>();

    public static void addToDatabase() {
        //only for tests is this code below
        /*for (DatabaseEnum.fields field : tupleParameters.keySet()) {
            System.out.println(tupleParameters.get(field));;
        }*/
    }

    public static DatabaseEnum.objectTypes getTypeOfObject() {
        return typeOfObject;
    }

    public static void setTypeOfObject(DatabaseEnum.objectTypes typeOfObject) {
        Adding.typeOfObject = typeOfObject;
    }

    public static void addToTupleParameters(DatabaseEnum.fields field, ArrayList<String> value){
        tupleParameters.put(field, value);
    }

    public static ArrayList<String> getElementFromTupleParameters(DatabaseEnum.fields field){
        ArrayList<String> value = tupleParameters.get(field);
        if (tupleParameters.isEmpty()){
            System.out.println("Not found any parameters in tuples map");
            return null;
        }else if (value==null){
            System.out.println("Not found element of this field name");
            return null;
        }else{
            return value;
        }
    }

    public static void clearTupleParameters(){
        tupleParameters.clear();
    }

    public static int getSizeTupleParameters(){
        return tupleParameters.size();
    }
}
