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

    public void removeDelivery(int transportID, Delivery_Document delivery_doc) {
        if (transportsData.getTransports().containsKey(transportID)) {
            Transport transport =transportsData.getTransports().get(transportID);
            transport.getDelivery_documents().remove(delivery_doc);
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


}
