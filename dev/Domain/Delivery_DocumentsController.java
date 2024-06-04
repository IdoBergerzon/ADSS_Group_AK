package Domain;

import Data.Delivery_DocumentsData;
import Data.ItemsData;

import java.util.HashMap;
import java.util.List;

public class Delivery_DocumentsController {
    Delivery_DocumentsData documentsData;
    ItemsData itemsData;
    public Delivery_DocumentsController(){
        documentsData = new Delivery_DocumentsData();
        itemsData = new ItemsData();
    }

    public ItemsData getItemsData(){
        return itemsData;
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

    public void getDeliverySourceInArea(int SourceArea){
        System.out.println("Delivery documents with source in Shipping Area: " + SourceArea + ":");
        for (Delivery_Document delivery : documentsData.getDelivery_Documents().values()){
            if (delivery.getSource().getShippingArea() == SourceArea) {
                System.out.println(delivery.getSource());
            }
        }
    }

    public void getDeliveryDestinationInArea(int DestinationArea){
        System.out.println("Delivery documents with Destinations in Shipping Area: " + DestinationArea + ":");
        for (Delivery_Document delivery : documentsData.getDelivery_Documents().values()){
            if (delivery.getDestination().getShippingArea() == DestinationArea) {
                System.out.println(delivery.getDestination());
            }
        }
    }

    public void removeItemFromDelivery(int delivery_id, int item_id){
        Delivery_Document delivery = documentsData.getDelivery_Documents().get(delivery_id);
        Item item = itemsData.getItem(item_id);
        delivery.getItems().remove(item);
        delivery.setItemsStatus(Delivery_ItemsStatus.itemMissing);
        System.out.println("item " + item_id + " removed from delivery " + delivery_id);
    }

    public void addItemToDelivery(int delivery_id, int item_id, int quantity){
        Delivery_Document delivery = getDelivery_Document(delivery_id);
        if (!itemsData.getItems().containsKey(item_id)){
            System.out.println("item " + item_id + " does not exist");
        }
        else {
            Item item = itemsData.getItems().get(item_id);
            delivery.getItems().put(item, quantity);
            System.out.println("item " + item_id + " added to delivery " + delivery_id);
        }
    }

    public Delivery_DocumentsData getDocumentsData() {
        return documentsData;
    }
}
