package sample.guidata.admin;

import sample.gui.controllers.Screen;

public class Editing {

    public static void directToEditScreen(DatabaseEnum.objectTypes type, double objectID ){
        if (type==DatabaseEnum.objectTypes.CONCERT || type==DatabaseEnum.objectTypes.CABARET || type==DatabaseEnum.objectTypes.THEATRE_SPECTACLE){

        }else if (type==DatabaseEnum.objectTypes.ACTOR){

        }else if (type==DatabaseEnum.objectTypes.MUSICIAN){

        }else if (type==DatabaseEnum.objectTypes.MUSIC_DISC){

        }else if (type==DatabaseEnum.objectTypes.SONG){

        }else if (type==DatabaseEnum.objectTypes.PERFORMANCE){

        }else if (type==DatabaseEnum.objectTypes.PLACE){

        }else if (type==DatabaseEnum.objectTypes.TOWN){

        }
    }
}
