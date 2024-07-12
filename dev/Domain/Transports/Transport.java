package Domain.Transports;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


public class Transport {
    private final int transportID;
    private LocalDate date;
    private String timeOfDepurture;
    private Truck truck;
    private Driver driver;
    private Set<Store> source;
    private Set<Supplier> destinations;
    private List<Double> totalWeights;
    private List<Delivery_Document> delivery_documents;
    private boolean finished = false;
    private String comments = "";

    public Transport(int transportID, Truck truck, Driver driver, List<Delivery_Document> delivery_documents, String comments) {
        this.transportID = transportID;
        this.date = LocalDate.now();
        this.timeOfDepurture = getCurrentTime();
        this.truck = truck;
        this.driver = driver;
        this.source = new HashSet<>();
        this.destinations = new HashSet<>();
        this.totalWeights = new ArrayList<>();
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

    private String getCurrentTime() {
        LocalTime now = LocalTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
        return now.format(formatter);
    }

    public String getTimeOfDepurture() {
        return timeOfDepurture;
    }

    public Truck getTruck() {
        return truck;
    }

    public double getWeight() {
        return this.totalWeights.get(this.totalWeights.size() - 1);
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

    public List<Delivery_Document> getDelivery_documents() {
        return delivery_documents;
    }

    public String getComments() {
        return comments;
    }

    public boolean isFinished() {
        return finished;
    }


    public void setDriver(Driver driver) {
        if (driver.isAvailable()) {
            if (driver.getLicenseMaxWeight() >= totalWeights.get(totalWeights.size() - 1)) {
                if (this.getDriver() != null)
                    this.getDriver().setAvailable(true);
                this.driver = driver;
                this.driver.setAvailable(false);
            } else {
                System.out.println("The driver's license is less than the weight of the transport.\n");
            }
        }
        else {
            System.out.println("driver is not available\n");
        }
    }

    public boolean setTruck(Truck truck) {
        double newWeight = totalWeights.get(totalWeights.size() - 1) - this.truck.getTruckWeight() + truck.getTruckWeight();
        if (truck.getMaxWeight() + truck.getTruckWeight() >= newWeight) {
            if (this.getDriver().getLicenseMaxWeight() >= newWeight) {
                if (truck.isAvailable()) {
                    if (this.getTruck() != null)
                        this.getTruck().setAvailable(true);
                    truck.setAvailable(false);
                    this.truck = truck;
                    this.calc_transportWeight();
                    return true;
                }
                else {
                    System.out.println("Truck does not available.\n");
                }
            }
            else System.out.println("The driver's license is less than the weight of the transport.\n");
        }
        else {
            System.out.println("The weight that truck number" + truck.getTruckID() + "can carry is less than the weight of the transport\n");
        }
        return false;
    }


    public void setFinished(boolean finished) {
        for (Delivery_Document delivery_document : delivery_documents) {
            delivery_document.setDelivery_status(Delivery_DocumentStatus.finished);
        }
        this.getDriver().setAvailable(true);
        this.getTruck().setAvailable(true);
        this.finished = finished;
    }


    public void addWeight(double weight) {
        this.totalWeights.add(weight);
    }

    public void removeDestination(Supplier supplier) {
        this.destinations.remove(supplier);
        for (int i=0; i<this.delivery_documents.size(); i++) {
            Delivery_Document delivery_document = this.delivery_documents.get(i);
            if (delivery_document.getDestination().equals(supplier)) {
                delivery_document.setDelivery_status(Delivery_DocumentStatus.waiting);
                this.removeDeliveryDocument(delivery_document);
            }
        }
        if (destinations.isEmpty()){
            source.clear();
        }
        this.calc_transportWeight();
        this.addComment("Removed Destination:" + supplier);
    }

    public void removeSource(Store store) {
        this.source.remove(store);
        for (int i=0; i<this.delivery_documents.size(); i++) {
            Delivery_Document delivery_document = this.delivery_documents.get(i);
            if (delivery_document.getSource().equals(store)) {
                delivery_document.setDelivery_status(Delivery_DocumentStatus.waiting);
                this.removeDeliveryDocument(delivery_document);
            }
        }
        if (source.isEmpty()){
            destinations.clear();
        }
        this.calc_transportWeight();
        this.addComment("Removed source: " + store);
    }

    public void printAllSources() {
        System.out.println("Sources:");
        for (Store store : source) {
            System.out.println(store);
        }
    }

    public void printAllDestinations() {
        System.out.println("Destinations:");
        for (Supplier supplier : destinations) {
            System.out.println(supplier);
        }
    }


    public void addComment(String comment) {
        this.comments = comment;
    }

    public void addDeliveryDocument(Delivery_Document delivery_document) {
        this.delivery_documents.add(delivery_document);
        this.source.add(delivery_document.getSource());
        this.destinations.add(delivery_document.getDestination());
        delivery_document.setDelivery_status(Delivery_DocumentStatus.in_Progress);
        this.calc_transportWeight();
    }

    public void removeDeliveryDocument(Delivery_Document delivery_document) {
        if (this.delivery_documents.contains(delivery_document)) {
            delivery_document.setDelivery_status(Delivery_DocumentStatus.waiting);
            this.delivery_documents.remove(delivery_document);
            Store store = delivery_document.getSource();
            Supplier supplier = delivery_document.getDestination();
            int flagS = 0;
            int flagD = 0;
            for (Delivery_Document document : delivery_documents) {
                if (document.getSource().equals(store)) {
                    flagS = 1;
                }
                if (document.getDestination().equals(supplier)) {
                    flagD = 1;
                }
            }
            if (flagS == 0) {
                source.remove(store);
                System.out.println("Removed Source:" + store);
            }
            if (flagD == 0) {
                destinations.remove(supplier);
                System.out.println("Removed Destination:" + supplier);
            }
        }else {
            System.out.println("The delivery Document does not exist in this transport\n.");
        }
    }

    /**
     * Calculates the total transport weight + the weight of the truck
     */
    public double calc_transportWeight() {
        double totalW = 0;
        for (Delivery_Document delivery_doc : this.getDelivery_documents()) {
            totalW += delivery_doc.getTotalWeight();
        }
        totalW += this.getTruck().getTruckWeight();
        this.addWeight(totalW);
        this.addComment(this.totalWeights.size() + ". Total Weight:" + totalW + "\n");
        return totalW;
    }

    public boolean checkTransport() {
        if (this.totalWeights.get(totalWeights.size()-1) <= this.getTruck().getTruckWeight() + this.getTruck().getMaxWeight() &&
                this.totalWeights.get(totalWeights.size()-1) <= this.getDriver().getLicenseMaxWeight()) {
            return true;
        }
        return false;
    }

    public void printAllDeliveryDocuments() {
        for (Delivery_Document delivery_document : this.getDelivery_documents()) {
            System.out.println(delivery_document);
        }
    }

    public String strAllSources() {
        StringBuilder s = new StringBuilder();
        for (Store store : source) {
            s.append("\n  ").append(store);
        }
        return s.toString();
    }

    public String strAllDestinations() {
        StringBuilder s = new StringBuilder();
        for (Supplier supplier : destinations) {
            s.append("\n  ").append(supplier);
        }
        return s.toString();
    }

    @Override
    public String toString() {
        String transportString = "Transport{" +
                "\ntransportID=" + transportID +
                "\n date=" + date +
                "\n timeOfDepurture=" + timeOfDepurture +
                "\n truck=" + truck +
                "\n driver=" + driver +
                "\n source=" + strAllSources() +
                "\n destinations=" + strAllDestinations();
        if (!totalWeights.isEmpty()) {
            transportString += "\n totalWeight=" + totalWeights.get(totalWeights.size()-1);
        }
        transportString += "\n comments='" + comments + '\'' + '}';
        return transportString;
    }
}
