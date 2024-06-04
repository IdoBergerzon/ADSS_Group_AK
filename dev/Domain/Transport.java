package Domain;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Set;

public class Transport {
    private final int transportID;
    private LocalDate date;
    private LocalTime timeOfDepurture;
    private Truck truck;
    private Driver driver;
    private Set<Store> source;
    private Set<Supplier> destinations;
    private List<Double> totalWeights;
    private List<Delivery_Document> delivery_documents;
    private boolean finished = false;
    private String comments;

    public Transport(int transportID, Truck truck, Driver driver, List<Delivery_Document> delivery_documents, String comments) {
        this.transportID = transportID;
        this.date = LocalDate.now();
        this.timeOfDepurture = LocalTime.now();
        truck.setAvailable(false);
        this.truck = truck;
        driver.setAvailable(false);
        this.driver = driver;
        this.delivery_documents = delivery_documents;
        for (Delivery_Document delivery_document : delivery_documents) {
            this.source.add(delivery_document.getSource());
        }
        for (Delivery_Document delivery_document : delivery_documents) {
            this.destinations.add(delivery_document.getDestination());
        }
        for (Delivery_Document delivery_document : delivery_documents) {
            this.totalWeights.add(delivery_document.getTotalWeight());
        }
        this.comments = comments;
    }

    public int getTransportID() {
        return transportID;
    }

    public LocalDate getDate() {
        return date;
    }

    public LocalTime getTimeOfDepurture() {
        return timeOfDepurture;
    }

    public Truck getTruck() {
        return truck;
    }

    public Driver getDriver() {
        return driver;
    }

    public Set<Store> getSource() {
        return source;
    }

    public Set<Supplier> getDestinations() {
        return destinations;
    }

    public List<Double> getTotalWeights() {
        return totalWeights;
    }
    public List<Delivery_Document> getDelivery_documents() {
        return delivery_documents;
    }

    public boolean isFinished() {
        return finished;
    }

    public String getComments() { return comments; }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public void setTimeOfDepurture(LocalTime timeOfDepurture) {
        this.timeOfDepurture = timeOfDepurture;
    }

    public void setDriver(Driver driver) {
        this.driver.setAvailable(false);
        this.driver = driver;
        if (this.getDriver() != null)
            this.getDriver().setAvailable(true);
    }

    public void setTruck(Truck truck) {
        truck.setAvailable(false);
        this.truck = truck;
        this.calc_transportWeight();
        if (this.getTruck() != null)
            this.getTruck().setAvailable(true);
    }

    public void setSource(Set<Store> source) {
        this.source = source;
    }

    public void setDestinations(Set<Supplier> destinations) {
        this.destinations = destinations;
    }

    public void setTotalWeights(List<Double> totalWeights) {
        this.totalWeights = totalWeights;
    }

    public void setDelivery_documents(List<Delivery_Document> delivery_documents) {
        this.delivery_documents = delivery_documents;
    }

    public void setFinished(boolean finished) {
        this.finished = finished;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public void addWeight(double weight) {
        this.totalWeights.add(weight);
    }

    public void addSource(Store store) {
        this.source.add(store);
    }

    public void addDestination(Supplier supplier) {
        this.destinations.add(supplier);
    }

    public void removeDestination(Supplier supplier) {
        for (Delivery_Document delivery_document : delivery_documents) {
            if (delivery_document.getDestination().equals(supplier)) {
                delivery_documents.remove(delivery_document);
            }
        }
        this.calc_transportWeight();
        this.destinations.remove(supplier);
        this.addComment("Removed Destination:" + supplier);
    }

    public void removeSource(Store store) {
        for (Delivery_Document delivery_document : delivery_documents) {
            if (delivery_document.getSource().equals(store)) {
                delivery_documents.remove(delivery_document);
            }
        }
        this.calc_transportWeight();
        this.source.remove(store);
        this.addComment("Removed source: " + store);
    }


    public void addComment(String comment) {
        this.comments = comment;
    }

    public void addDeliveryDocument(Delivery_Document delivery_document) {
        this.delivery_documents.add(delivery_document);
    }

    /**
     * Calculates the total transport weight + the weight of the truck
     */
    public double calc_transportWeight() {
        double totalW = 0;
        for (Delivery_Document delivery_doc : this.getDelivery_documents()) {
            totalW += delivery_doc.getTotalWeight();
        }
        this.addWeight(totalW + this.getTruck().getTruckWeight());
        this.addComment(this.totalWeights.size() + ". Total Weight: " + totalW +this.getTruck().getTruckWeight() + "\n");
        return totalW + this.getTruck().getTruckWeight();
    }

    public boolean checkTransport() {
        if (this.totalWeights.get(totalWeights.size()-1) <= this.getTruck().getTruckWeight() + this.getTruck().getMaxWeight() &&
                this.totalWeights.get(totalWeights.size()-1) <= this.getDriver().getLicenseMaxWeight()) {
            return true;
        }
        return false;
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
