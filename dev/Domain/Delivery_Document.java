package Domain;
import java.util.HashMap;
import java.util.Map;
import java.sql.Time;
import java.util.Date;
import java.util.List;

public class Delivery_Document {
    private int documentID;
    private Date date;
    private Time timeOfDepurture;
    private int trackNumber;
    private Driver driver;
    private Store source;
    private Supplier destination;
    private HashMap<Item,Integer> items;

    public Delivery_Document(Store source, Date date, Time timeOfDepurture, int trackNumber, Driver driver, int documentID, Supplier destination, HashMap<Item,Integer> items) {
        this.source = source;
        this.date = date;
        this.timeOfDepurture = timeOfDepurture;
        this.trackNumber = trackNumber;
        this.driver = driver;
        this.documentID = documentID;
        this.destination = destination;
        this.items = items;
    }

    public int getDocumentID() {return documentID;}
    public Date getDate() {return date;}
    public Time getTimeOfDepurture() {return timeOfDepurture;}
    public int getTrackNumber() {return trackNumber;}
    public Driver getDriver() {return driver;}
    public Store  getSource() {return source;}
    public Supplier getDestination() {return destination;}
    public HashMap<Item,Integer> getItems() {return items;}

    public void setDate(Date date) {
        this.date = date;
    }

    public void setTimeOfDepurture(Time timeOfDepurture) {
        this.timeOfDepurture = timeOfDepurture;
    }

    public void setTrackNumber(int trackNumber) {
        this.trackNumber = trackNumber;
    }

    public void setDriver(Driver driver) {
        this.driver = driver;
    }

    public void setSource(Store source) {
        this.source = source;
    }

    public void setDestination(Supplier destination) {
        this.destination = destination;
    }

    public void setItems(HashMap<Item,Integer> items) {
        this.items = items;
    }

    @Override
    public String toString() {
        return "Delivery_Document{" +
                "documentID=" + documentID +
                ", date=" + date +
                ", timeOfDepurture=" + timeOfDepurture +
                ", trackNumber=" + trackNumber +
                ", driversName='" + driver + '\'' +
                ", source=" + source +
                ", destination=" + destination +
                ", items=" + items +
                '}';
    }
}
