package sample.guidata.admin;

import org.omg.PortableInterceptor.INACTIVE;
import sample.database.controllers.MiejscowoscController;
import sample.gui.StaticData;
import sample.gui.controllers.Screen;

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

            } else if (type == DatabaseEnum.objectTypes.PLACE) {

            } else if (type == DatabaseEnum.objectTypes.TOWN) {
                String nazwa = MiejscowoscController.getNazwaFromMiejscowosc(objectID);
                String zipCode = MiejscowoscController.getZipCodeFromMiejscowosc(objectID);
                String state = MiejscowoscController.getStateFromMiejscowosc(objectID);
                filledFields.clear();
                filledFields.add(nazwa);
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
