package Domain;

import java.sql.Time;
import java.util.Date;
import java.util.List;

public class Transport {
    private int transportID;
    private Date date;
    private Time timeOfDepurture;
    private Truck truck;
    private Driver driver;
    private Supplier source;
    private List<Store> destinations;
    private double totalWeight;
    private String comments;

    public Transport(int transportID, Date date, Time timeOfDepurture, Truck truck, Driver driver, Supplier source, List<Store> destinations, String comments) {
        this.transportID = transportID;
        this.date = date;
        this.timeOfDepurture = timeOfDepurture;
        this.truck = truck;
        this.driver = driver;
        this.source = source;
        this.destinations = destinations;
        this.comments = comments;
    }

    public int getTransportID() {
        return transportID;
    }

    public Date getDate() {
        return date;
    }

    public Time getTimeOfDepurture() {
        return timeOfDepurture;
    }

    public Truck getTruck() {
        return truck;
    }

    public Driver getDriver() {
        return driver;
    }

    public Supplier getSource() {
        return source;
    }

    public List<Store> getDestinations() {
        return destinations;
    }

    public double getTotalWeight() {
        return this.totalWeight;
    }

    public String getComments() { return comments; }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setTimeOfDepurture(Time timeOfDepurture) {
        this.timeOfDepurture = timeOfDepurture;
    }

    public void setDriver(Driver driver) {
        this.driver = driver;
    }

    public void setTruck(Truck truck) {
        this.truck = truck;
    }

    public void setSource(Supplier source) {
        this.source = source;
    }

    public void setDestinations(List<Store> destinations) {
        this.destinations = destinations;
    }

    public void setTotalWeight(double totalWeight) {
        this.totalWeight = totalWeight;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    @Override
    public String toString() {
        return "Transport{" +
                "transportID=" + transportID +
                ", date=" + date +
                ", timeOfDepurture=" + timeOfDepurture +
                ", truck=" + truck +
                ", driver=" + driver +
                ", source=" + source +
                ", destinations=" + destinations +
                ", comments='" + comments + '\'' +
                '}';
    }
}
