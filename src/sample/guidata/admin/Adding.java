package sample.guidata.admin;

import sample.database.controllers.MiejscowoscController;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Adding {

    private static DatabaseEnum.objectTypes typeOfObject; //All options in DatabaseEnum enum class
    private static Map<DatabaseEnum.fields, ArrayList<String>> tupleParameters = new HashMap<>();

    public static void addToDatabase() throws SQLException {
        //only for tests is this code below
        /*for (DatabaseEnum.fields field : tupleParameters.keySet()) {
            System.out.println(tupleParameters.get(field));;
        }*/
        if (typeOfObject==DatabaseEnum.objectTypes.CONCERT){

        }else if (typeOfObject==DatabaseEnum.objectTypes.CABARET){

        }else if (typeOfObject==DatabaseEnum.objectTypes.THEATRE_SPECTACLE){

        }else if (typeOfObject==DatabaseEnum.objectTypes.PERFORMANCE){

        }else if (typeOfObject==DatabaseEnum.objectTypes.ACTOR){

        }else if (typeOfObject==DatabaseEnum.objectTypes.MUSICIAN){

        }else if (typeOfObject==DatabaseEnum.objectTypes.MUSIC_DISC){

        }else if (typeOfObject==DatabaseEnum.objectTypes.SONG){

        }else if (typeOfObject==DatabaseEnum.objectTypes.PLACE){

        }else if (typeOfObject==DatabaseEnum.objectTypes.TOWN){
            String name = getElementFromTupleParameters(DatabaseEnum.townFields.NAME).get(0);
            String state = getElementFromTupleParameters(DatabaseEnum.townFields.STATE).get(0);
            String zipCode = getElementFromTupleParameters(DatabaseEnum.townFields.ZIP_CODE).get(0);
            MiejscowoscController.AddMiejscowosc(name, state, zipCode);
        }
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
