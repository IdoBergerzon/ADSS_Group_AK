package Domain;

import Data.TransportsData;

public class TransportController {
    private TransportsData transportsData;
    public TransportController() {
        transportsData = new TransportsData();
    }
    public TransportsData getTransportsData() {
        return transportsData;
    }


    /**
     * Add Delivery_document to transport
     * @param transportID
     * @param delivery_doc
     */
    public void addDelivery(int transportID, Delivery_Document delivery_doc) {
        if (transportsData.getTransports().containsKey(transportID)) {
            Transport transport =transportsData.getTransports().get(transportID);
            if (!transport.getDelivery_documents().contains(delivery_doc)) {
                transport.getDelivery_documents().add(delivery_doc);
            }
            else System.out.println("delivery already exists");
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
                transport.getSource().remove(delivery_doc.getSource());
            if (flagD == 0)
                transport.getDestinations().remove(delivery_doc.getDestination());
            //להוסיף הורדה של המיקום מהמסלול
        }
        else System.out.println("transport does not exist");
    }

    public void calc_transportWeight(int transportID) {
        Transport transport=transportsData.getTransports().get(transportID);
        double totalW = 0;
        for (Delivery_Document delivery_doc : transport.getDelivery_documents()) {
            totalW += delivery_doc.getTotalWeight();
        }
        transport.addWeight(totalW + transport.getTruck().getTruckWeight());
    }

    public void removeItem(int transportID, Item item) {
        Transport transport=transportsData.getTransports().get(transportID);
        int flag = 0;
        for (Delivery_Document delivery_doc : transport.getDelivery_documents()) {
            if (delivery_doc.getItems().containsKey(item)){
                delivery_doc.removeItemWeight(item);
                flag = 1;
            }
        }
        if (flag == 0) System.out.println("item does not exist in transport");
    }

    public void replaceTruck(int transportID, Truck truck) {
        Transport transport=transportsData.getTransports().get(transportID);
        transport.getTruck().setAvailable(true);
        transport.setTruck(truck);
    }




    public void setShipping_area(Address address, int shipping_area) {
        address.setShipping_area(shipping_area);
        System.out.println("Shipping area set successfully for address: " + address.getFull_address());
    }
//to add alarm
    public void changeDriver(int Id, Driver newDriver) {
        // Retrieve the transport associated with the given track ID
        Transport transport = transportsData.getTransportById(trackId);

        if (transport != null) {
            transport.getDriver().setAvailable(true);
            // Update the driver object of the transport
            transport.setDriver(newDriver);
            System.out.println("Driver changed successfully for track " + trackId);
        } else {
            System.out.println("Transport not found with ID: " + trackId);
        }

    }



    }






