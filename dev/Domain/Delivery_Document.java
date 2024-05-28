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
    private Truck truck;
    private String driversName;
    private Store source;
    private Supplier destination;
    private HashMap<Item,Integer> items;

    public Delivery_Document(Store source, Date date, Time timeOfDepurture, Truck truck, String driversName, int documentID, Supplier destination, HashMap<Item,Integer> items) {
        this.source = source;
        this.date = date;
        this.timeOfDepurture = timeOfDepurture;
        this.truck = truck;
        this.driversName = driversName;
        this.documentID = documentID;
        this.destination = destination;
        this.items = items;
    }

    public int getDocumentID() {return documentID;}
    public Date getDate() {return date;}
    public Time getTimeOfDepurture() {return timeOfDepurture;}
    public Truck getTruck() {return truck;}
    public String getDriversName() {return driversName;}
    public Store  getSource() {return source;}
    public Supplier getDestination() {return destination;}
    public HashMap<Item,Integer> getItems() {return items;}

    public void setDate(Date date) {
        this.date = date;
    }

    public void setTimeOfDepurture(Time timeOfDepurture) {
        this.timeOfDepurture = timeOfDepurture;
    }

    public void setTruck(Truck truck) {
        this.truck = truck;
    }

    public void setDriversName(String driversName) {
        this.driversName = driversName;
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
                ", trackNumber=" + truck +
                ", driversName='" + driversName + '\'' +
                ", source=" + source +
                ", destination=" + destination +
                ", items=" + items +
                '}';
    }
}
