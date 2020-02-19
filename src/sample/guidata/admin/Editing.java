package sample.guidata.admin;

import sample.database.controllers.MiejsceController;
import sample.database.controllers.MiejscowoscController;
import sample.database.controllers.PrzedstawienieController;
import sample.gui.StaticData;

import java.sql.SQLException;
import java.util.ArrayList;

public class Editing {

    private static ArrayList<String> filledFields = new ArrayList<>();


    public static void directToEditScreen(DatabaseEnum.objectTypes type, double objectID ){
        ArrayList<Integer> numberOfFileds = StaticData.getActiveFieldsNumbers();
        try {
            if (type == DatabaseEnum.objectTypes.CONCERT || type == DatabaseEnum.objectTypes.CABARET || type == DatabaseEnum.objectTypes.THEATRE_SPECTACLE) {

            } else if (type == DatabaseEnum.objectTypes.ACTOR) {

            } else if (type == DatabaseEnum.objectTypes.MUSICIAN) {

            } else if (type == DatabaseEnum.objectTypes.MUSIC_DISC) {

            } else if (type == DatabaseEnum.objectTypes.SONG) {

            } else if (type == DatabaseEnum.objectTypes.PERFORMANCE) {
                String title = PrzedstawienieController.getTitleFromPrzedstawienie(objectID);
                String dlugosc = PrzedstawienieController.getLenghtFromPrzedstawienie(objectID);
                filledFields.clear();
                filledFields.add(title);
                filledFields.add(dlugosc);
            } else if (type == DatabaseEnum.objectTypes.PLACE) {
                String name = MiejsceController.getNameFromMiejsce(objectID);
                String placeType = MiejsceController.getTypeFromMiejsce(objectID);
                double townID = MiejsceController.getTownIdFromMiejsce(objectID);
                MiejscowoscController.getOneMiejscowosc(townID);
                String townStr = MiejscowoscController.getListOfStrings().get(0);
                filledFields.clear();
                filledFields.add(name);
                filledFields.add(placeType);
                filledFields.add(townStr);

            } else if (type == DatabaseEnum.objectTypes.TOWN) {
                String name = MiejscowoscController.getNameFromMiejscowosc(objectID);
                String zipCode = MiejscowoscController.getZipCodeFromMiejscowosc(objectID);
                String state = MiejscowoscController.getStateFromMiejscowosc(objectID);
                filledFields.clear();
                filledFields.add(name);
                filledFields.add(zipCode);
                filledFields.add(state);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    public static ArrayList<String> getFilledFields() {
        return filledFields;
    }
}
