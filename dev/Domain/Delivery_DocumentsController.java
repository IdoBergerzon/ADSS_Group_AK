package Domain;

import java.util.*;

public class Delivery_DocumentsController {
    Delivery_DocumentsRepository documentsRepository;
    ItemsRepository itemsRepository;
    public Delivery_DocumentsController(){
        documentsRepository = new Delivery_DocumentsRepository();
        itemsRepository = new ItemsRepository();
    }

    public ItemsRepository getItemsData(){
        return itemsRepository;
    }

    public void addDelivery_Document(int delivery_id, Store source, Supplier destination, HashMap<Item,Integer> items){
        if (documentsRepository.getDelivery_Documents().containsKey(delivery_id)){
            System.out.println("delivery " + delivery_id + " already exists");
        }
        else {
            Delivery_Document newDelivery = new Delivery_Document(source, delivery_id, destination, items);
            documentsRepository.getDelivery_Documents().put(delivery_id, newDelivery);
        }
    }

    public Delivery_Document getDelivery_Document(int delivery_id){
        return (Delivery_Document) documentsRepository.get(delivery_id);
    }

    public void getShippingAreaForDest(int SourceArea){
        int flag = 0;
        Set<Integer> setArea = new HashSet<>();
        List<Delivery_Document> deliveryDocumentList = documentsRepository.getAll();
        for (int i = 0; i < deliveryDocumentList.size(); i++){
            Delivery_Document delivery = deliveryDocumentList.get(i);
            if (delivery.getSource().getShippingArea() == SourceArea && delivery.getDelivery_Status().equals(Delivery_DocumentStatus.waiting)) {
                setArea.add(delivery.getDestination().getShippingArea());
                flag = 1;
            }
        }
        if (flag == 0){
            System.out.println("There are no delivery documents whose source address is " + SourceArea);
        }
        else {
            System.out.println(setArea);
        }
    }

    public void displayDelivery_Document(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Insert delivery documents ID:");
        int deliveryDocumentsID = scanner.nextInt();
        scanner.nextLine();
        if (this.getDelivery_Document(deliveryDocumentsID) == null)
            System.out.println("Delivery Document does not exist.\n");
        else
            System.out.println(this.getDelivery_Document(deliveryDocumentsID) + "\n");
    }

    public boolean createNewItem() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter Item ID:\n");
        int itemID = scanner.nextInt();
        scanner.nextLine();
        if (this.getItemsData().get(itemID) != null) {
            System.out.println("Item already exists in the system\n");
            return false;
        } else {
            System.out.println("Enter item name:");
            String name = scanner.nextLine();
            System.out.print("Enter item weight:\n");
            double weight = scanner.nextDouble();
            Item item = new Item(itemID, name, weight);
            this.getItemsData().add(item);
            System.out.println("Item added successfully.\n");
        }
        return true;
    }

    public boolean createDelivery_Document(LocationController locationController){
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter Delivery Document ID:\n");
        int deliveryDocumentID = scanner.nextInt();
        scanner.nextLine();
        if (this.getDelivery_Document(deliveryDocumentID) != null) {
            System.out.print("The Delivery Document already exists in the system\n");
            return false;
        } else {
            System.out.println("Enter Source ID:");
            int sourceID = scanner.nextInt();
            scanner.nextLine();
            if (locationController.getLocation(sourceID) == null) {
                System.out.println("Invalid source ID\n");
            } else if (locationController.getLocation(sourceID).getL_type().equals("Supplier")) {
                System.out.print("The location is Supplier (destination)\n");
            } else {
                System.out.print("Enter Destination ID:\n");
                int destinationID = scanner.nextInt();
                scanner.nextLine();
                if (locationController.getLocation(destinationID) == null) {
                    System.out.println("Invalid destination ID\n");
                } else if (locationController.getLocation(destinationID).getL_type().equals("Store")) {
                    System.out.print("The location is Store\n");
                } else {
                    Store store = (Store) locationController.getLocation(sourceID);
                    Supplier supplier = (Supplier) locationController.getLocation(destinationID);
                    HashMap<Item, Integer> newItems = new HashMap<>();
                    int moreItem = 1;
                    while (moreItem != 0) {
                        System.out.print("Enter Item ID:");
                        int itemID = scanner.nextInt();
                        scanner.nextLine();
                        if (this.getItemsData().get(itemID) == null) {
                            System.out.println("Item does not exist in the system\n");
                            break;
                        } else {
                            Item item = (Item) this.getItemsData().get(itemID);
                            System.out.print("Enter Quantity:");
                            int quantity = scanner.nextInt();
                            if (newItems.containsKey(item)) {
                                quantity = newItems.get(item) + quantity;
                            }
                            newItems.put(item, quantity);
                            System.out.print("Add more item? (0 = No, 1 = Yes)");
                            moreItem = scanner.nextInt();
                        }
                    }
                    this.addDelivery_Document(deliveryDocumentID, store, supplier, newItems);
                }
            }
        }
        return true;
    }

    public void getDeliveryInArea(int sourceArea ,int destinationArea){
        System.out.println("Delivery in Shipping Area: Source= " + sourceArea + ", Destination= " + destinationArea + ":");
        int flag = 0;
        List<Delivery_Document> deliveryDocumentList = documentsRepository.getAll();
        for (int i = 0; i < deliveryDocumentList.size(); i++){
            Delivery_Document delivery = deliveryDocumentList.get(i);
            if (delivery.getSource().getShippingArea()==sourceArea && delivery.getDestination().getShippingArea()==destinationArea
                    && delivery.getDelivery_Status().equals(Delivery_DocumentStatus.waiting)){
                System.out.println(delivery);
                flag = 1;
            }
        }
        if (flag == 0){
            System.out.println("No delivery in this shipping area");
        }
    }

    public boolean updateDeliveryDocument(Delivery_DocumentsController deliveryController,LocationController locationController){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Insert delivery ID:");
        int deliveryID = scanner.nextInt();
        scanner.nextLine();
        if (deliveryController.getDelivery_Document(deliveryID) == null) {
            return false;
        } else if (!deliveryController.getDelivery_Document(deliveryID).getDelivery_Status().equals(Delivery_DocumentStatus.waiting)) {
            System.out.println("Delivery in progress or already finished .\n");
            return false;
        }

        Delivery_Document deliveryDocument = deliveryController.getDelivery_Document(deliveryID);
        System.out.println("Delivery document menu:");
        System.out.println("1. Change source");
        System.out.println("2. Change destination");
        System.out.println("3. Change delivery status");
        System.out.println("4. Add item");
        System.out.println("5. Remove item");
        System.out.println("0. Back to Main Menu");

        int update;
        update = scanner.nextInt();
        switch (update) {
            //Back to the main menu
            case 0:
                System.out.println("Returning to the main menu.\n");
                break;

            //Change source
            case 1:
                System.out.println("Insert new source ID:");
                int newSourceID = scanner.nextInt();
                scanner.nextLine();
                if (locationController.getLocation(newSourceID) == null) {
                    System.out.println("Location does not exist.\n");
                    return false;
                }
                if (locationController.getLocation(newSourceID).getL_type() == "Supplier") {
                    System.out.println("The ID is that of a supplier\n");
                    return false;
                }
                Store newSource = (Store) locationController.getLocation(newSourceID);
                deliveryDocument.setSource(newSource);
                System.out.println("Source's document was changed\n" + deliveryDocument);
                break;

            //Change destination
            case 2:
                System.out.println("Insert new destination ID:");
                int newDestinationID = scanner.nextInt();
                scanner.nextLine();
                if (locationController.getLocation(newDestinationID) == null) {
                    return false;
                }
                if (locationController.getLocation(newDestinationID).getL_type() == "Store") {
                    System.out.println("The ID is that of a store\n");
                    return false;
                }
                Supplier newDestination = (Supplier) locationController.getLocation(newDestinationID);
                deliveryDocument.setDestination(newDestination);
                System.out.println("Destination's document was changed\n" + deliveryDocument + "\n");
                break;

            //Change delivery status
            case 3:
                System.out.println("Insert new delivery status (in_Progress, finished, waiting):\n");
                String deliveryStatus = scanner.next();
                scanner.nextLine();
                if (deliveryStatus.equals("in_Progress")) {
                    deliveryDocument.setDelivery_status(Delivery_DocumentStatus.in_Progress);
                } else if (deliveryStatus.equals("finished")) {
                    deliveryDocument.setDelivery_status(Delivery_DocumentStatus.finished);
                } else if (deliveryStatus.equals("waiting")) {
                    deliveryDocument.setDelivery_status(Delivery_DocumentStatus.waiting);
                } else {
                    System.out.println("Delivery document status is not in in_Progress or finished\n");
                    break;
                }
                System.out.println("Delivery document" + deliveryID + ",status was changed to" + deliveryDocument.getDelivery_Status() + "\n");
                break;

            //Add item
            case 4:
                System.out.println("Insert item ID to add:");
                int newitemID = scanner.nextInt();
                scanner.nextLine();
                if (deliveryController.getItemsData().get(newitemID) == null) {
                    System.out.println("Item " + newitemID + " does not exist\n");
                } else {
                    System.out.println("Insert amount:");
                    int itemAmount = scanner.nextInt();
                    Item item = (Item) deliveryController.getItemsData().get(newitemID);
                    if (deliveryDocument.getItems().containsKey(item)) {
                        deliveryDocument.getItems().put(item, itemAmount + deliveryDocument.getItems().get(item));
                    } else
                        deliveryDocument.getItems().put(item, itemAmount);
                    System.out.println("item" + item + "was added to the delivery document\n");
                    System.out.println("The weight is " + deliveryDocument.getTotalWeight() + "\n");
                }
                break;

            //Remove item
            case 5:
                System.out.println("All items in this delivery document:\n");
                deliveryDocument.printAllItems();
                System.out.println("Insert item ID to remove:");
                int itemID = scanner.nextInt();
                scanner.nextLine();
                if (deliveryController.getItemsData().get(itemID) == null) {
                    System.out.println("Item " + itemID + " does not exist\n");
                } else {
                    Item item = (Item) deliveryController.getItemsData().get(itemID);
                    Set<Item> itemSet = deliveryDocument.getItems().keySet();
                    if (itemSet.contains(item)) {
                        deliveryDocument.getItems().remove(item);
                        System.out.println("item " + itemID + " was removed from the delivery document\n");
                        System.out.println("The weight is " + deliveryDocument.getTotalWeight() + "\n");
                    } else {
                        System.out.println("item does not exist in the delivery document\n");
                    }
                }
                break;

            default:
                System.out.println("Invalid choice. Please try again.\n");
                break;
        }
        return true;
    }


    public Delivery_DocumentsRepository getDocumentsRepository() {
        return documentsRepository;
    }
}
