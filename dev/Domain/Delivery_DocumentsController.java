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
        return documentsData.getDelivery_Documents().getOrDefault(delivery_id, null);
    }

    public void getDeliverySourceInArea(int SourceArea){
        System.out.println("Delivery documents with source in Shipping Area: " + SourceArea + ":");
        int flag = 0;
        for (Delivery_Document delivery : documentsData.getDelivery_Documents().values()){
            if (delivery.getSource().getShippingArea() == SourceArea && delivery.getDelivery_Status().equals(Delivery_DocumentStatus.waiting)) {
                System.out.println(delivery);
                flag = 1;
            }
        }
        if (flag == 0){
            System.out.println("No source in this shipping area");
        }
    }

    public void getDeliveryDestinationInArea(int DestinationArea){
        System.out.println("Delivery documents with Destinations in Shipping Area: " + DestinationArea + ":");
        int flag = 0;
        for (Delivery_Document delivery : documentsData.getDelivery_Documents().values()){
            if (delivery.getDestination().getShippingArea() == DestinationArea && delivery.getDelivery_Status().equals(Delivery_DocumentStatus.waiting)) {
                System.out.println(delivery);
                flag = 1;
            }
        }
        if (flag == 0){
            System.out.println("No destination in this shipping area");
        }
    }


    public Delivery_DocumentsData getDocumentsData() {
        return documentsData;
    }
}
