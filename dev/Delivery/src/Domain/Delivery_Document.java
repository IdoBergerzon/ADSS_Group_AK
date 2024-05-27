package Domain;
import java.util.HashMap;
import java.util.Map;
import java.sql.Time;
import java.util.Date;

public class Delivery_Document {
    private int documentID;
    private Date date;
    private Time timeOfDepurture;
    private int trackNumber;
    private String driversName;
    private Supplier source;
    private Store destination;
    private Map<String,Integer> items;

    public Delivery_Document(Supplier source, Date date, Time timeOfDepurture, int trackNumber, String driversName, int documentID, Store destination, Map<String,Integer> items) {
        this.source = source;
        this.date = date;
        this.timeOfDepurture = timeOfDepurture;
        this.trackNumber = trackNumber;
        this.driversName = driversName;
        this.documentID = documentID;
        this.destination = destination;
        this.items = items;
    }
}
