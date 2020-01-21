package sample.guidata.admin;

public class DatabaseEnum {

    public enum objectTypes {
        CABARET,
        THEATRE_SPECTACLE,
        CONCERT,
        PLACE,
        TOWN,
        PERFORMANCE,
        ACTOR,
        MUSICIAN,
        MUSIC_DISC,
        SONG
    }

    public interface fields{

    }

    /**
     * Event == Cabaret || Theatre Spectacle || Concert
     */
    public enum eventFields implements fields{
        NAME,
        DATE,
        TICKET_COST,
        NUMBER_OF_SEATS,
        PLACE,                            //difficult to implement
        MUSICIANS,              //difficult to implement
        PERFORMANCES                      //difficult to implement

    }

    public enum placeFields implements fields{
        NAME,
        TYPE,
        TOWN //dufficult to implement
    }

    public enum townFields implements fields{
        NAME,
        STATE,
        ZIP_CODE
    }

    public enum performanceFields implements fields{
        TITLE,
        LENGTH,
        ACTORS,
        EVENTS

    }

    public enum actorFields implements fields{
        NAME,
        SURNAME,
        BAND_NAME,
        PERFORMANCES, //a lot of is possible
    }

    public enum musicianFields implements fields{
        NAME,
        SURNAME,
        NICKNAME,
        MUSIC_DISCS, //a lot of is possible
        EVENTS
    }

    public enum musicDiscFields implements fields{
        TITLE,
        RELEASE_YEAR,
        MUSICIANS //a lot of musicians
    }

    public enum songFields implements fields{
        MUSICIAN,
        TITLE,
        RELEASE_YEAR,
        GENRE,
        YOUTUBE_VIEWS,
        MUSIC_DISC
    }

}
