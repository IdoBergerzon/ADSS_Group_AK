package Domain;

import java.sql.Time;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

public class Transport {
    private final int transportID;
    private Date date;
    private Time timeOfDepurture;
    private Truck truck;
    private Driver driver;
    private List<Store> source;
    private List<Supplier> destinations;
    private List<Double> totalWeights;
    private List<Delivery_Document> delivery_documents;
    private String comments;

    public Transport(int transportID, Date date, Time timeOfDepurture, Truck truck, Driver driver, List<Store> source, List<Supplier> destinations, List<Double> totalWeights, List<Delivery_Document> delivery_documents, String comments) {
        this.transportID = transportID;
        this.date = date;
        this.timeOfDepurture = timeOfDepurture;
        this.truck = truck;
        this.driver = driver;
        this.source = source;
        this.destinations = destinations;
        this.totalWeights = totalWeights;
        this.delivery_documents = delivery_documents;
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

    public List<Store> getSource() {
        return source;
    }

    public List<Supplier> getDestinations() {
        return destinations;
    }

    public List<Double> getTotalWeights() {
        return totalWeights;
    }
    public List<Delivery_Document> getDelivery_documents() {
        return delivery_documents;
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
        truck.setAvailable(false);
        this.truck = truck;
    }

    public void setSource(List<Store> source) {
        this.source = source;
    }

    public void setDestinations(List<Supplier> destinations) {
        this.destinations = destinations;
    }

    public void setTotalWeights(List<Double> totalWeights) {
        this.totalWeights = totalWeights;
    }

    public void setDelivery_documents(List<Delivery_Document> delivery_documents) {
        this.delivery_documents = delivery_documents;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public void addWeight(double weight) {
        this.totalWeights.add(weight);
    }

    public void addStore(Store store) {
        this.source.add(store);
    }

    public void addSupplier(Supplier supplier) {
        this.destinations.add(supplier);
    }

    public void removeSupplier(Supplier supplier) {
        this.destinations.remove(supplier);
    }

    public void addComment(String comment) {
        this.comments = comment;
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
                ", totalWeights=" + totalWeights +
                ", comments='" + comments + '\'' +
                '}';
    }
}
