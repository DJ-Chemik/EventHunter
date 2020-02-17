package sample.guidata.admin;

import sample.database.controllers.*;

import java.sql.SQLException;

public class Deleting {

    public static void deleteFromDatabase(DatabaseEnum.objectTypes type, double objectID ) throws SQLException {
        if (type==DatabaseEnum.objectTypes.CONCERT || type==DatabaseEnum.objectTypes.CABARET || type==DatabaseEnum.objectTypes.THEATRE_SPECTACLE){
            WydarzenieController.deleteWydarzenie(objectID);
        }else if (type==DatabaseEnum.objectTypes.ACTOR){
            AktorController.deleteAktor(objectID);
        }else if (type==DatabaseEnum.objectTypes.MUSICIAN){
            MuzykController.deleteMuzyk(objectID);
        }else if (type==DatabaseEnum.objectTypes.MUSIC_DISC){
            PlytaController.deletePlyta(objectID);
        }else if (type==DatabaseEnum.objectTypes.SONG){
            UtworController.deleteUtwor(objectID);
        }else if (type==DatabaseEnum.objectTypes.PERFORMANCE){
            PrzedstawienieController.deletePrzedstawienie(objectID);
        }else if (type==DatabaseEnum.objectTypes.PLACE){
            MiejsceController.deleteMiejsce(objectID);
        }else if (type==DatabaseEnum.objectTypes.TOWN){
            MiejscowoscController.deleteMiejscowosc(objectID);
        }
    }
}
