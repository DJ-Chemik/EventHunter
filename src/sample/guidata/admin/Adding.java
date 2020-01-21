package sample.guidata.admin;

import java.util.HashMap;
import java.util.Map;

public class Adding {

    private static DatabaseEnum.ObjectTypes typeOfObject; //All options in DatabaseEnum enum class
    private static Map<String, String> tupleParameters = new HashMap<>();

    public static void addToDatabase(){

    }

    public static void addToTupleParameters(String field, String value){
        tupleParameters.put(field, value);
    }

    public static String getElementFromTupleParameters(String field){
        String value = tupleParameters.get(field);
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

    public static void getSizeTupleParameters(){
        tupleParameters.size();
    }

    public static DatabaseEnum.ObjectTypes getTypeOfObject() {
        return typeOfObject;
    }

    public static void setTypeOfObject(DatabaseEnum.ObjectTypes typeOfObject) {
        Adding.typeOfObject = typeOfObject;
    }
}
