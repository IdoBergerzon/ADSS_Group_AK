package Domain;

import Data.Delivery_DocumentsData;

import java.util.HashMap;

public class Delivery_DocumentsController {
    Delivery_DocumentsData documentsData;
    public Delivery_DocumentsController(){
        documentsData = new Delivery_DocumentsData();
    }

    public void addDelivery_Document(int delivery_id, Store source, Supplier destination, HashMap<Item,Integer> items){
        if (documentsData.getDelivery_Documents().containsKey(delivery_id)){
            System.out.println("delivery " + delivery_id + " already exists");
        }
        else {
            Delivery_Document newDelivery = new Delivery_Document(source, delivery_id, destination, items);
            documentsData.getDelivery_Documents().put(delivery_id, newDelivery);
        }
    }

    public Delivery_Document getDelivery_Document(int delivery_id){
        if (documentsData.getDelivery_Documents().containsKey(delivery_id)){
            return documentsData.getDelivery_Documents().get(delivery_id);
        }
        else {
            System.out.println("delivery " + delivery_id + " does not exist");
            return null;
        }
    }

    public String showDelivery_Document(int delivery_id){
        if (documentsData.getDelivery_Documents().containsKey(delivery_id)){
            return this.documentsData.getDelivery_Documents().toString();
        }
        else
            return null;
    }

    public void updateStatus(int delivery_id, Delivery_DocumentStatus status){
        Delivery_Document delivery = documentsData.getDelivery_Documents().get(delivery_id);
        delivery.setDelivery_status(status);
    }
    public void updateItemStatus(Delivery_ItemsStatus status, int delivery_id){
        Delivery_Document delivery = documentsData.getDelivery_Documents().get(delivery_id);
        delivery.setItemsStatus(status);
    }

    public Delivery_DocumentStatus getDeliveryStatus (int delivery_id){
        Delivery_Document delivery = documentsData.getDelivery_Documents().get(delivery_id);
        return delivery.getDelivery_Status();
    }
    public Delivery_ItemsStatus getItemsStatus (int delivery_id){
        Delivery_Document delivery = documentsData.getDelivery_Documents().get(delivery_id);
        return delivery.getItemsStatus();
    }

    public void setDestPhone(int delivery_id, String phone){
        Delivery_Document delivery = documentsData.getDelivery_Documents().get(delivery_id);
        delivery.getDestination().setPhone(phone);
    }

    public void setDestContact(int delivery_id, String contact){
        Delivery_Document delivery = documentsData.getDelivery_Documents().get(delivery_id);
        delivery.getDestination().setContact(contact);
    }
    public void setDestAddress(int delivery_id, Address address){
        Delivery_Document delivery = documentsData.getDelivery_Documents().get(delivery_id);
        delivery.getDestination().setAddress(address);
    }
    public void setSourcePhone(int delivery_id, String phone){
        Delivery_Document delivery = documentsData.getDelivery_Documents().get(delivery_id);
        delivery.getSource().setPhone(phone);
    }
    public void setSourceContact(int delivery_id, String contact){
        Delivery_Document delivery = documentsData.getDelivery_Documents().get(delivery_id);
        delivery.getSource().setContact(contact);
    }
    public void setSourceAddress(int delivery_id, Address address){
        Delivery_Document delivery = documentsData.getDelivery_Documents().get(delivery_id);
        delivery.getSource().setAddress(address);
    }
    public double getTotalWeight(int delivery_id){
        Delivery_Document delivery = documentsData.getDelivery_Documents().get(delivery_id);
        return delivery.getTotalWeight();
    }

    public double removeItemW(int delivery_id, Item item){
        Delivery_Document delivery = documentsData.getDelivery_Documents().get(delivery_id);
        delivery.removeItemWeight(item);
        return delivery.getTotalWeight();
    }

    public double addItemW(int delivery_id, Item item){
        Delivery_Document delivery = documentsData.getDelivery_Documents().get(delivery_id);
        delivery.addItemWeight(item);
        return delivery.getTotalWeight();
    }

    public Delivery_DocumentsData getDocumentsData() {
        return documentsData;
    }
}
