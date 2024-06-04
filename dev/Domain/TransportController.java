package Domain;

import Data.TransportsData;

import java.util.List;

public class TransportController {
    private TransportsData transportsData;
    public TransportController() {
        transportsData = new TransportsData();
    }

    public TransportsData getTransportsData() {
        return transportsData;
    }

    public Transport getTransport(int transportID) {
        if (!transportsData.getTransports().containsKey(transportID)) {
            return null;
        }
        return transportsData.getTransports().get(transportID);
    }

    /**
     * Add Delivery_document to transport
     * @param transportID
     * @param delivery_doc
     */
    public void addDelivery(int transportID, Delivery_Document delivery_doc) {
        if (transportsData.getTransports().containsKey(transportID)) {
            Transport transport =transportsData.getTransports().get(transportID);
            if (transport.getTotalWeights().get(transport.getTotalWeights().size()-1) + delivery_doc.getTotalWeight() <= transport.getTruck().getMaxWeight() + transport.getTruck().getTruckWeight()) {
                if (!transport.getDelivery_documents().contains(delivery_doc)) {
                    transport.getDelivery_documents().add(delivery_doc);
                    transport.addSource(delivery_doc.getSource());
                    transport.addDestination(delivery_doc.getDestination());
                    transport.calc_transportWeight();
                }
                else{
                    System.out.println("delivery already exists");
                }
            }
            else {
                transport.addComment("Overweight: Transport weight exceeds the permissible weight for the truck.\n" + delivery_doc + " Not add to the transport.\n");
            }
        }
        else System.out.println("transport does not exist");
    }

    /**
     * Removing the truck shipment in case of excess weight,
     * and removing the source/destination from the route if necessary
     * @param transportID
     * @param delivery_doc
     */
    public void removeDelivery(int transportID, Delivery_Document delivery_doc) {
        if (transportsData.getTransports().containsKey(transportID)) {
            Transport transport =transportsData.getTransportById(transportID);
            transport.getDelivery_documents().remove(delivery_doc);
            int flagS=0;
            int flagD=0;
            for (Delivery_Document delivery_document : transport.getDelivery_documents()){
                if (delivery_document.getSource() == delivery_doc.getSource()){
                    flagS=1;
                }
                if (delivery_document.getDestination() == delivery_doc.getDestination()){
                    flagD=1;
                }
            }
            if (flagS == 0)
                transport.removeSource(delivery_doc.getSource());
            if (flagD == 0)
                transport.removeDestination(delivery_doc.getDestination());
        }
        else System.out.println("transport does not exist");
    }

    /**
     * Removing source location from the transport, and updating the transport form
     * @param transportID
     * @param source
     */
    public void removeSource(int transportID, Store source) {
        Transport transport =transportsData.getTransportById(transportID);
        transport.removeSource(source);
        transport.addComment("Remove Source: " + source + " from the transport");
    }

    /**
     * Removing destination location from the transport, and updating the transport form
     * @param transportID
     * @param destination
     */
    public void removeDestination(int transportID, Supplier destination) {
        Transport transport =transportsData.getTransportById(transportID);
        transport.removeDestination(destination);
        transport.addComment("Remove Destination: " + destination + " from the transport");
    }

    /**
     * Removal of an item from delivery on an transport
     * @param transportID
     * @param deliveryID
     * @param item
     */
    public void removeItemFromDelivery(int transportID, int deliveryID, Item item) {
        Transport transport =transportsData.getTransportById(transportID);
        Delivery_Document delivery_doc = transport.getDelivery_documents().get(deliveryID);
        if (delivery_doc.getItems().containsValue(item)) {
            delivery_doc.removeItemWeight(item);
            transport.calc_transportWeight();
            delivery_doc.setItemsStatus(Delivery_ItemsStatus.itemMissing);
            transport.addComment("Removed Item: " + item +" removed from" + delivery_doc + "\n");
        }
        else System.out.println("item does not exist in this delivery.\n");
    }

    /**
     * Removing an item from the transport (from all shipments in which the item is included)
     * in case of excess weight, and updating the weight for each shipment
     * @param transportID
     * @param item
     */
    public void removeAllItems(int transportID, Item item) {
        Transport transport=transportsData.getTransports().get(transportID);
        int flag = 0;
        for (Delivery_Document delivery_doc : transport.getDelivery_documents()) {
            if (delivery_doc.getItems().containsKey(item)){
                delivery_doc.removeItemWeight(item);
                delivery_doc.setItemsStatus(Delivery_ItemsStatus.itemMissing);
                flag = 1;
            }
        }
        if (flag == 0)
            System.out.println("item does not exist in transport");
        else {
            transport.calc_transportWeight();
            transport.addComment("Remove Item: " + item +" removed from transport\n");
        }
    }

    /**
     * Truck replacement in case of overweight
     * @param transportID
     * @param truck
     */
    public void replaceTruck(int transportID, Truck truck) {
        Transport transport=transportsData.getTransports().get(transportID);
        if (truck != null) {
            if (truck.isAvailable()) {
                if (truck.getMaxWeight() + truck.getTruckWeight() >= transport.getTotalWeights().get(transport.getTotalWeights().size() - 1)) {
                    if (transport.getTruck() != null)
                    {
                        transport.getTruck().setAvailable(true);
                    }
                    transport.setTruck(truck);
                    transport.calc_transportWeight();
                    transport.addComment("Replace Truck: " + truck + "has been successfully paired\n");
                } else System.out.println("The truck is too small");
            } else System.out.println("truck is not available");
        }
        else System.out.println("truck is null");
    }

    /**
     * Replacing a driver in case he does not have the appropriate license for the truck
     * @param transportID
     * @param driver
     */
    public void replaceDriver(int transportID, Driver driver) {
        Transport transport=transportsData.getTransports().get(transportID);
        if (driver != null) {
            if (driver.isAvailable()) {
                if (driver.getLicenseMaxWeight() >= transport.getTotalWeights().get(transport.getTotalWeights().size()-1)) {
                    if (transport.getDriver() != null) {
                        transport.getDriver().setAvailable(true);
                    }
                    driver.setAvailable(false);
                    transport.setDriver(driver);
                    transport.addComment("Replace Driver: " + driver + "has been successfully paired\n");
                    System.out.println("Driver added successfully for track " + transportID);
                } else {
                    System.out.println("Invalid match: Driver without proper license");
                }
            }
            else System.out.println("driver is not available");
        }
        else System.out.println("Driver does not exist");
    }

    /**
     * Transport closing (driver release, truck release, delivery status update)
     * @param transport
     * @return
     */
    public void finishTransport(Transport transport) {
        transport.getDriver().setAvailable(true);
        transport.getTruck().setAvailable(true);
        for(Delivery_Document delivery : transport.getDelivery_documents()){
            delivery.setDelivery_status(Delivery_DocumentStatus.finished);
        }
        transport.setFinished(true);
        System.out.println("Transport finished successfully");
    }


    /**
     * Planning a trip for the first time, and adding it to the database
     * @param transportID
     * @param truck
     * @param driver
     * @param delivery_documents
     * @param comments
     */
    public void planTransport(int transportID, Truck truck, Driver driver, List<Delivery_Document> delivery_documents, String comments) {
        if (transportsData.getTransports().containsKey(transportID)) {
            System.out.println("TransportID already exists");
        }
        else if (!driver.isAvailable()){
            System.out.println("driver is not available");
        } else if (!truck.isAvailable()) {
            System.out.println("truck is not available");
        }
        else {
            Transport transport = new Transport(transportID, truck, driver, delivery_documents, comments);
            transportsData.addTransport(transport);
            System.out.println("Transport added successfully");
        }
    }
    //התראה על חריגה במשקל
    //הוספת הערות במקרה של חריגה במשקל
    //הורדה לתכנון מחדש של נסיעה?
    //שינוי יעדים במקרה של חריגה במשקל
}






