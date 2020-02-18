package sample.guidata.admin;

import sample.database.controllers.MiejscowoscController;
import sample.gui.controllers.Screen;

import java.sql.SQLException;

public class Editing {

    public static void directToEditScreen(DatabaseEnum.objectTypes type, double objectID ){
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
                System.out.println(nazwa);
                System.out.println(zipCode);
                System.out.println(state);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
}
