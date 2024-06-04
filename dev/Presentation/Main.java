package Presentation;

import Domain.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Delivery_DocumentsController deliveryController = new Delivery_DocumentsController();
        DriverController driverController = new DriverController();
        LocationController locationController = new LocationController();
        TransportController transportController = new TransportController();
        TruckController truckController = new TruckController();

        Scanner scanner = new Scanner(System.in);
        int choice;

        do {
            System.out.println("Main Menu:");
            System.out.println("1. Add");
            System.out.println("2. Update");
            System.out.println("3. Display");
            System.out.println("4. Start Transport");
            System.out.println("5. Finish Transport");
            System.out.println("6. Exit");

            choice = scanner.nextInt();

            switch (choice) {
                // Add
                case 1:
                    int add;
                    System.out.println("Add Menu:");
                    System.out.println("Enter your choice: ");
                    System.out.println("1. Add Truck");
                    System.out.println("2. Add Driver");
                    System.out.println("3. Add Location");
                    System.out.println("4. Add Delivery Document");
                    System.out.println("0. Exit");

                    add = scanner.nextInt();

                    switch (add) {
                        case 0: // Back to Main Menu
                            System.out.println("Returning to the main menu.");
                            break;


                        case 1:
                            // Add Truck
                            System.out.println("Insert truck ID:");
                            int truckID = scanner.nextInt();
                            if (truckController.getTruck(truckID) != null){
                                System.out.print("The truck already exists in the system");
                            }
                            else {
                                System.out.print("Insert truck Type:");
                                String truckType = scanner.nextLine();
                                scanner.nextLine();
                                System.out.println("Insert truck Weight:");
                                double truckWeight = scanner.nextDouble();
                                scanner.nextLine();
                                System.out.println("Insert truck Max Weight:");
                                double MaxWeight = scanner.nextDouble();
                                truckController.addNewTruck(truckID,truckType,MaxWeight,truckWeight);
                                break;
                            }


                        case 2:
                            // Add Driver
                            System.out.print("Enter Driver ID:");
                            int driverID = scanner.nextInt();
                            if (driverController.getDriver(driverID) != null) {
                                System.out.print("The Driver already exists in the system");
                            }
                            else {
                            System.out.print("Enter Driver Name:");
                            String driverName = scanner.nextLine();
                            System.out.print("Enter the driver's license number");
                            int licenseNumber = scanner.nextInt();
                                driverController.addDriver(driverID, driverName, licenseNumber);
                                break;
                            }

                        //Add supplier or Store
                        case 3:
                            int locationChoice;
                            System.out.println("1. Add Supplier");
                            System.out.println("2. Add Store");
                            System.out.print("Enter your choice:");
                            locationChoice = scanner.nextInt();
                            scanner.nextLine(); // Consume newline

                            if (locationChoice == 1 || locationChoice == 2) {
                                System.out.print("Enter Location ID:");
                                int locationID = scanner.nextInt();
                                scanner.nextLine(); // Consume newline

                                System.out.print("Enter Address details:");
                                String full_address = scanner.nextLine();

                                System.out.print("Enter address_code:");
                                int address_code = scanner.nextInt();
                                scanner.nextLine(); // Consume newline

                                Address address = new Address(full_address, address_code);

                                System.out.print("Enter contact:");
                                String contact = scanner.nextLine();

                                System.out.print("Enter phone:");
                                String phone = scanner.nextLine();

                                String l_type = (locationChoice == 1) ? "Supplier" : "Store";
                                locationController.addLocation(locationID, address, contact, phone, l_type);
                                System.out.println(l_type + " added successfully.");
                            } else {
                                System.out.println("Invalid choice for the given location type.");
                            }
                            break;

                        case 4:
                            System.out.print("Enter Delivery Document ID:");
                            int deliveryDocumentID = scanner.nextInt();
                            if (deliveryController.getDelivery_Document(deliveryDocumentID) != null) {
                                System.out.print("The Delivery Document already exists in the system");
                            }
                            else {
                                System.out.println("Enter Source ID:");
                                int sourceID = scanner.nextInt();
                                if (locationController.getLocation(sourceID) == null) {
                                    System.out.println("Invalid source ID");
                                } else if (locationController.getLocation(sourceID).getL_type() == "Supplier") {
                                    System.out.print("The location is Supplier (destination)");
                                }
                                else {
                                    System.out.print("Enter Destination ID:");
                                    int destinationID = scanner.nextInt();
                                    if (locationController.getLocation(destinationID) == null) {
                                        System.out.println("Invalid destination ID");
                                    } else if (locationController.getLocation(destinationID).getL_type() == "Store") {
                                        System.out.print("The location is Store");
                                    } else {
                                        Store store = (Store) locationController.getLocation(sourceID);
                                        Supplier supplier = (Supplier) locationController.getLocation(destinationID);
                                        HashMap<Item, Integer> newItems = new HashMap<>();
                                        int moreItem = 1;
                                        while (moreItem != 0) {
                                            System.out.print("Enter Item ID:");
                                            int itemID = scanner.nextInt();
                                            System.out.print("Enter Item Name:");
                                            String itemName = scanner.nextLine();
                                            System.out.print("Enter Weight:");
                                            double weight = scanner.nextDouble();
                                            Item item = new Item(itemID, itemName, weight);
                                            System.out.print("Enter Quantity:");
                                            int quantity = scanner.nextInt();
                                            newItems.put(item, quantity);
                                            System.out.print("Add more item? (0 = No, 1 = Yes)");
                                            moreItem = scanner.nextInt();
                                        }
                                        deliveryController.addDelivery_Document(deliveryDocumentID, store, supplier, newItems);
                                    }
                                }
                            }
                            break;


                        default:
                            System.out.println("Invalid choice. Please try again.");
                            break;
                    }
                    break;





/************** Update *******************************/

                //Update
                case 2:
                    int update;
                    System.out.println("Enter number:");
                    System.out.println("1. Update truck availability");
                    System.out.println("2. Update driver details");
                    System.out.println("3. Update location details");
                    System.out.println("4. Update transport details");
                    System.out.println("5. Update delivery details");
                    System.out.println("0. Back to Main Menu");

                    update = scanner.nextInt();
                    switch (update) {
                        case 0: // Back to Main Menu
                            System.out.println("Returning to the main menu.");
                            break;

                        //Update truck availability
                        case 1:
                            System.out.println("Insert truck ID:");
                            int truckID = scanner.nextInt();
                            System.out.println("Insert status available (true/false):");
                            boolean truckAvailable = scanner.nextBoolean();
                            if (truckController.getTruck(truckID) == null)
                                System.out.println("Truck does not exist.");
                            else {
                                truckController.getTruck(truckID).setAvailable(truckAvailable);
                                System.out.println("Truck's availability were updated.\n" + truckController.getTruck(truckID));
                            }
                            break;

                        //Update driver details
                        case 2:
                            System.out.println("Insert driver ID:");
                            int driverID = scanner.nextInt();
                            if (driverController.getDriver(driverID) == null) {
                                System.out.println("Driver does not exist.");
                                break;
                            }
                            System.out.println("Driver update menu:");
                            System.out.println("1. Update driver availability");
                            System.out.println("2. Update driver license");
                            System.out.println("0. Back to Main Menu");

                            update = scanner.nextInt();
                            switch (update) {
                                //Back to main menu
                                case 0:
                                    System.out.println("Returning to the main menu.");
                                    break;

                                //Update driver availability
                                case 1:
                                    System.out.println("Insert status available (true/false):");
                                    boolean driverAvailable = scanner.nextBoolean();
                                    driverController.getDriver(driverID).setAvailable(driverAvailable);
                                    System.out.println("Driver's availability were updated.\n" + driverController.getDriver(driverID));
                                    break;

                                //Update driver license
                                case 2:
                                    System.out.println("Insert new license (int):");
                                    int license = scanner.nextInt();
                                    driverController.getDriver(driverID).setLicenseMaxWeight(license);
                                    System.out.println("Driver's license were updated.\n" + driverController.getDriver(driverID));
                                    break;

                                default:
                                    System.out.println("Invalid choice. Please try again.");
                                    break;
                            }
                            break;

                        //Update location details
                        case 3:
                            System.out.println("Insert location ID:");
                            int locationID = scanner.nextInt();
                            if (locationController.getLocation(locationID) == null) {
                                System.out.println("Location does not exist.");
                                break;
                            }
                            System.out.println("Location update menu:");
                            System.out.println("1. Update shipping area");
                            System.out.println("2. Update contact");
                            System.out.println("3. Update phone number");
                            System.out.println("0. Back to Main Menu");

                            update = scanner.nextInt();
                            switch (update) {
                                case 0:
                                    System.out.println("Returning to the main menu.");
                                    break;

                                //Update shipping area
                                case 1:
                                    System.out.println("Insert number of shipping area:");
                                    int shippingArea = scanner.nextInt();
                                    locationController.setShipping_area(locationID, shippingArea);
                                    break;

                                //Update contact
                                case 2:
                                    System.out.println("Insert new contact:");
                                    String contact = scanner.next();
                                    locationController.updateContact(locationID, contact);
                                    break;

                                //Update phone number
                                case 3:
                                    System.out.println("Insert new phone number:");
                                    String phoneNumber = scanner.next();
                                    locationController.updatePhone(locationID, phoneNumber);
                                    break;


                                default:
                                    System.out.println("Invalid choice. Please try again.");
                                    break;
                            }
                            break;

                        //Update transport
                        case 4:
                            System.out.println("Insert transport ID:");
                            int transportID = scanner.nextInt();
                            if (transportController.getTransport(transportID) == null) {
                                System.out.println("Transport does not exist.");
                                break;
                            }
                            Transport transport = transportController.getTransport(transportID);
                            System.out.println("Transport update menu:");
                            System.out.println("1. Change truck");
                            System.out.println("2. Change driver");
                            System.out.println("3. Add delivery");
                            System.out.println("4. Remove delivery");
                            System.out.println("5. Transport weighting");
                            System.out.println("6. Replanning transport");
                            System.out.println("0. Back to Main Menu");

                            update = scanner.nextInt();
                            switch (update) {
                                case 0:
                                    System.out.println("Returning to the main menu.");
                                    break;

                                //Change truck
                                case 1:
                                    System.out.println("Insert new truck ID:");
                                    int newTruckID = scanner.nextInt();
                                    if (truckController.getTruck(newTruckID) == null) {
                                        break;
                                    }
                                    Truck newTruck = truckController.getTruck(newTruckID);
                                    transport.setTruck(newTruck);
                                    System.out.println("Transport's truck was changed\n" + transport);
                                    break;

                                //Change driver
                                case 2:
                                    System.out.println("Insert new driver ID:");
                                    int newDriverID = scanner.nextInt();
                                    if (driverController.getDriver(newDriverID) == null) {
                                        break;
                                    }
                                    Driver newDriver = driverController.getDriver(newDriverID);
                                    transport.setDriver(newDriver);
                                    System.out.println("Driver's driver was changed\n" + transport);
                                    break;

                                //Add delivery
                                case 3:
                                    System.out.println("Insert new delivery ID:");
                                    int newDeliveryID = scanner.nextInt();
                                    if (deliveryController.getDelivery_Document(newDeliveryID) == null) {
                                        break;
                                    }
                                    Delivery_Document newDelivery = deliveryController.getDelivery_Document(newDeliveryID);
                                    transport.addDeliveryDocument(newDelivery);
                                    System.out.println("Delivery's document was changed\n" + transport);
                                    break;

                                //Add comment
                                case 4:
                                    System.out.println("Insert new comment:");
                                    String newComment = scanner.next();
                                    transport.addComment(newComment);
                                    System.out.println("Comment added\n" + transport);
                                    break;

                                default:
                                    System.out.println("Invalid choice. Please try again.");
                                    break;
                            }
                            break;

                        //Update delivery document
                        case 5:
                            System.out.println("Insert delivery ID:");
                            int deliveryID = scanner.nextInt();
                            if (deliveryController.getDelivery_Document(deliveryID) == null) {
                                break;
                            }

                            Delivery_Document deliveryDocument = deliveryController.getDelivery_Document(deliveryID);
                            System.out.println("Delivery document menu:");
                            System.out.println("1. Change source");
                            System.out.println("2. Change destination");
                            System.out.println("3. Change delivery status");
                            System.out.println("4. Change item status");
                            System.out.println("5. Add item");
                            System.out.println("6. Remove item");
                            System.out.println("0. Back to Main Menu");

                            update = scanner.nextInt();
                            switch (update) {
                                //Back to the main menu
                                case 0:
                                    System.out.println("Returning to the main menu.");
                                    break;

                                //Change source
                                case 1:
                                    System.out.println("Insert new source ID:");
                                    int newSourceID = scanner.nextInt();
                                    if (locationController.getLocation(newSourceID) == null) {
                                        break;
                                    }
                                    if (locationController.getLocation(newSourceID).getL_type() == "Supplier") {
                                        System.out.println("The ID is that of a supplier");
                                        break;
                                    }
                                    Store newSource = (Store) locationController.getLocation(newSourceID);
                                    deliveryDocument.setSource(newSource);
                                    System.out.println("Source's document was changed\n" + deliveryDocument);
                                    break;

                                //Change destination
                                case 2:
                                    System.out.println("Insert new destination ID:");
                                    int newDestinationID = scanner.nextInt();
                                    if (locationController.getLocation(newDestinationID) == null) {
                                        break;
                                    }
                                    if (locationController.getLocation(newDestinationID).getL_type() == "Store") {
                                        System.out.println("The ID is that of a store");
                                        break;
                                    }
                                    Supplier newDestination = (Supplier) locationController.getLocation(newDestinationID);
                                    deliveryDocument.setDestination(newDestination);
                                    System.out.println("Destination's document was changed\n" + deliveryDocument);
                                    break;

                                //Change delivery status
                                case 3:
                                    System.out.println("Insert new delivery status (in_Progress, finished, waiting):");
                                    String deliveryStatus = scanner.next();
                                    if (deliveryStatus.equals("in_Progress")) {
                                        deliveryDocument.setDelivery_status(Delivery_DocumentStatus.in_Progress);
                                    }
                                    else if (deliveryStatus.equals("finished")) {
                                        deliveryDocument.setDelivery_status(Delivery_DocumentStatus.finished);
                                    }
                                    else if (deliveryStatus.equals("waiting")) {
                                        deliveryDocument.setDelivery_status(Delivery_DocumentStatus.waiting);
                                    }
                                    else {
                                        System.out.println("Delivery document status is not in in_Progress or finished");
                                        break;
                                    }
                                    System.out.println("Delivery document" + deliveryID + ",status was changed to" + deliveryDocument.getDelivery_Status());
                                    break;

                                //Change item status
                                case 4:
                                    System.out.println("Insert new item status (complete, itemMissing, in_Progress):");
                                    String deliveryItemStatus = scanner.next();
                                    if (deliveryItemStatus.equals("complete")) {
                                        deliveryDocument.setItemsStatus(Delivery_ItemsStatus.complete);
                                    }
                                    else if (deliveryItemStatus.equals("itemMissing")) {
                                        deliveryDocument.setItemsStatus(Delivery_ItemsStatus.itemMissing);
                                    }
                                    else if (deliveryItemStatus.equals("in_Progress")) {
                                        deliveryDocument.setItemsStatus(Delivery_ItemsStatus.in_Progress);
                                    }
                                    else {
                                        System.out.println("Delivery document status is not in in_Progress or completed");
                                        break;
                                    }
                                    System.out.println("Delivery document" + deliveryID + ",item status was changed to" + deliveryDocument.getItemsStatus());
                                    break;

                                //Add item
                                case 5:
                                    System.out.println("Insert item ID to add:");
                                    int newitemID = scanner.nextInt();
                                    System.out.println("Insert item name:");
                                    String itemName = scanner.next();
                                    System.out.println("Insert item weight:");
                                    int itemWeight = scanner.nextInt();
                                    System.out.println("Insert amount:");
                                    int itemAmount = scanner.nextInt();
                                    Item item = new Item(newitemID, itemName, itemWeight);
                                    if (deliveryDocument.getItems().containsKey(item)) {
                                        deliveryDocument.getItems().put(item, itemAmount + deliveryDocument.getItems().get(item));
                                    }
                                    else
                                        deliveryDocument.getItems().put(item, itemAmount);
                                    System.out.println("item" + item + "was added to the delivery document");
                                    System.out.println("The weight is " + deliveryDocument.getTotalWeight());
                                    break;

                                //Remove item
                                case 6:
                                    System.out.println("Insert item ID to remove:");
                                    int itemID = scanner.nextInt();
                                    int flag = 0;
                                    for (Item item1 : deliveryDocument.getItems().keySet()){
                                        if (itemID == item1.getItemID()) {
                                            deliveryDocument.getItems().remove(item1);
                                            flag = 1;
                                        }
                                    }
                                    if (flag == 0) {
                                        System.out.println("item does not exist");
                                    }
                                    else {
                                        System.out.println("item" + itemID + "was removed from the delivery document");
                                        System.out.println("The weight is " + deliveryDocument.getTotalWeight());
                                    }
                                    break;

                                default:
                                    System.out.println("Invalid choice. Please try again.");
                                    break;
                            }
                            break;

                        default:
                            System.out.println("Invalid choice. Please try again.");
                            break;
                    }
                    break;

/************** Display *******************************/

                //Display
                case 3:
                    int display;
                    //Display Menu
                    System.out.println("Enter number:");
                    System.out.println("1. Display truck details");
                    System.out.println("2. Display driver details");
                    System.out.println("3. Display transport");
                    System.out.println("4. Display delivery documents");
                    System.out.println("5. Display location details");
                    System.out.println("6. Display all transports");
                    System.out.println("7. Display all trucks");
                    System.out.println("8. Display all drivers");
                    System.out.println("0. Back to Main Menu");

                    display = scanner.nextInt();
                    switch (display) {
                        case 0: // Back to Main Menu
                            System.out.println("Returning to the main menu.");
                            break;

                        case 1: // Display truck details
                            System.out.println("Insert truck ID:");
                            int truckID = scanner.nextInt();
                            if (truckController.getTruck(truckID) == null){
                                System.out.println("Truck does not exist.");
                            }
                            else
                                System.out.println(truckController.getTruck(truckID));
                            break;

                        //Display driver details
                        case 2:
                            System.out.println("Insert driver ID:");
                            int driverID = scanner.nextInt();
                            if (driverController.getDriver(driverID) == null){
                                System.out.println("Driver does not exist.");
                            }
                            else
                                System.out.println(driverController.getDriver(driverID));
                            break;

                        //Display transport
                        case 3:
                            System.out.println("Insert transport ID:");
                            int transportID = scanner.nextInt();
                            if (transportController.getTransport(transportID) == null){
                                System.out.println("Transport does not exist.");
                            }
                            else
                                System.out.println(transportController.getTransport(transportID));
                            break;

                        //Display delivery documents
                        case 4:
                            System.out.println("Insert delivery documents ID:");
                            int deliveryDocumentsID = scanner.nextInt();
                            if (deliveryController.getDelivery_Document(deliveryDocumentsID) == null)
                                System.out.println("Delivery Document does not exist.");
                            else
                                System.out.println(deliveryController.getDelivery_Document(deliveryDocumentsID));
                            break;

                        //Display location details
                        case 5:
                            System.out.println("Insert location documents ID:");
                            int locationDocumentsID = scanner.nextInt();
                            if (locationController.getLocation(locationDocumentsID) == null)
                                System.out.println("Location Document does not exist.");
                            else
                                System.out.println(locationController.getLocation(locationDocumentsID));
                            break;

                            //Display all transports
                            case 6:
                            if (transportController.getTransportsData().getTransports().isEmpty())
                                System.out.println("There are no transports in the system.");
                            else {
                                System.out.println(transportController.getTransportsData().toString());
                            }
                            break;

                        //Display all trucks
                        case 7:
                            if (truckController.getTrucksData().getTrucks().isEmpty())
                                System.out.println("There are no trucks in the system.");
                            else
                                System.out.println(truckController.getTrucksData().toString());
                            break;

                        //Display all drivers
                        case 8:
                            if (driverController.getDriversData().getDrivers().isEmpty())
                                System.out.println("There are no drivers in the system.");
                            else
                                System.out.println(driverController.getDriversData().toString());
                            break;



                        default:
                            System.out.println("Invalid choice. Please try again.");
                            break;
                    }
                    break;

/************** Start Transport *******************************/

                //Start Transport
                case 4:
                    System.out.println("Insert transport ID:");
                    int transportID = scanner.nextInt();
                    if (transportController.getTransport(transportID) != null){
                        System.out.println("Transport already exists.");
                        break;
                    }
                    System.out.println("Insert truck ID:");
                    int truckID = scanner.nextInt();
                    if (truckController.getTruck(truckID) == null) {
                        System.out.println("Truck does not exist.");
                        break;
                    }
                    Truck truck = truckController.getTruck(truckID);

                    System.out.println("Insert driver ID:");
                    int driverID = scanner.nextInt();
                    if (driverController.getDriver(driverID) == null) {
                        System.out.println("Driver does not exist.");
                        break;
                    }
                    Driver driver = driverController.getDriver(driverID);

                    List <Delivery_Document> deliveryDocs;
                    deliveryDocs = new ArrayList<Delivery_Document>();
                    System.out.println("Insert delivery ID:");
                    int deliveryID = scanner.nextInt();
                    while (deliveryID != 0){
                        System.out.println("Insert delivery documents ID:\n(Press 0 to return to the Main Menu)");
                        deliveryID = scanner.nextInt();
                        if (deliveryController.getDelivery_Document(deliveryID) == null) {
                            System.out.println("Delivery Document does not exist.");
                            continue;
                        }
                        deliveryDocs.add(deliveryController.getDelivery_Document(deliveryID));
                    }
                    //Create transport and add it to TransportData
                    Transport newTransport = new Transport(transportID, truck, driver, deliveryDocs, "");
                    transportController.getTransportsData().addTransport(newTransport);
                    //Check if transport is valid
                    if (newTransport.calc_transportWeight() <= driver.getLicenseMaxWeight() && newTransport.calc_transportWeight() <= truck.getMaxWeight() + truck.getTruckWeight()) {
                        System.out.println("Proper Transport " + newTransport);
                        break;
                    }
                    //Replanning transport
                    System.out.println("Overweight, please choose one from the following numbers:");
                    System.out.println("1. Change truck");
                    System.out.println("2. Remove source");
                    System.out.println("3. Remove destination");
                    System.out.println("4. Remove Item from delivery");
                    System.out.println("0. Exit and Delete transport");
                    int reChoice;
                    reChoice = scanner.nextInt();

                    switch (reChoice) {
                        case 0:
                            transportController.getTransportsData().removeTransportById(transportID);
                            System.out.println("Returning to the main menu.");
                            break;

                        //Change truck
                        case 1:
                            System.out.println("Insert new truck ID:");
                            int newTruckID = scanner.nextInt();
                            if (truckController.getTruck(newTruckID) == null){
                                System.out.println("Truck does not exist.");
                            }
                            else {
                                Truck newTruck = truckController.getTruck(newTruckID);
                                if (!newTruck.isAvailable()) {
                                    System.out.println("Truck does not available.");
                                } else {
                                    double tempWeight = newTransport.getTotalWeights().get(newTransport.getTotalWeights().size() - 1) - newTransport.getTruck().getTruckWeight() + newTruck.getTruckWeight();
                                    if (newTruck.getTruckWeight() + newTruck.getMaxWeight() >= tempWeight) {
                                        if (newTransport.getDriver().getLicenseMaxWeight() >= tempWeight) {
                                            newTransport.setTruck(newTruck);
                                            System.out.println("Transport OK, truck was changed successfully.");
                                            break;
                                        } else {
                                            System.out.println("The driver's license is less than the weight of the transport.\nPlease insert new driverID:\n(Press 0 to return to the Main Menu)\n");
                                            int ChangeDriverID = scanner.nextInt();
                                            if (driverController.getDriver(ChangeDriverID) == null) {
                                                System.out.println("Driver does not exist.");
                                            }
                                            else if (!driverController.getDriver(ChangeDriverID).isAvailable()) {
                                                System.out.println("Driver does not available.");
                                            } else if (tempWeight <= driverController.getDriver(ChangeDriverID).getLicenseMaxWeight()) {
                                                newTransport.setDriver(driverController.getDriver(ChangeDriverID));
                                            }
                                            else
                                                System.out.println("The driver's license is less than the weight of the transport.\n");
                                        }
                                    } else {
                                        System.out.println("The weight that truck number" + truckID + "can carry is less than the weight of the transport\n");
                                    }
                                }
                            }

                        //Remove source
                        case 2:
                            System.out.println("Insert source ID to remove:");
                            int sourceID = scanner.nextInt();
                            if (locationController.getLocation(sourceID) == null)
                                System.out.println("Location does not exist.");
                            else {
                                if (locationController.getLocation(sourceID).getL_type() != "Store")
                                    System.out.println("The location is Supplier.");
                                else {
                                    Store source = (Store) locationController.getLocation(sourceID);
                                    if (newTransport.getSource().contains(source)) {
                                        newTransport.removeSource(source);
                                        if (newTransport.checkTransport()) {
                                            System.out.println("Transport OK, source removed successfully.");
                                            break;
                                        } else
                                            System.out.println("The weight is still exceeded.");
                                    }
                                    else
                                        System.out.println("Source" + source + "does not exist in this transport.");
                                }
                            }

                        //Remove destination
                        case 3:
                            System.out.println("Insert destination ID to remove:");
                            int destinationID = scanner.nextInt();
                            if (locationController.getLocation(destinationID) == null) {
                                System.out.println("Location does not exist.");
                            }
                            else {
                                if (locationController.getLocation(destinationID).getL_type() != "Supplier")
                                    System.out.println("The location is Store.");
                                else {
                                    Supplier destination = (Supplier) locationController.getLocation(destinationID);
                                    if (newTransport.getDestinations().contains(destination)) {
                                        newTransport.removeDestination(destination);
                                        if (newTransport.checkTransport()) {
                                            System.out.println("Transport OK, destination removed successfully.");
                                            break;
                                        } else
                                            System.out.println("The weight is still exceeded.");
                                    }
                                    else
                                        System.out.println("Destination" + destination + "does not exist in this transport.");
                                }
                            }

                        //Remove item from delivery in transport
                        case 4:
                            System.out.println("Insert delivery ID to remove from:");
                            int deliveryId = scanner.nextInt();
                            if (!newTransport.getDelivery_documents().contains(deliveryId)) {
                                System.out.println("Delivery Document does not exist in this transport.");
                            }
                            else {
                                Delivery_Document deliveryDoc = newTransport.getDelivery_documents().get(deliveryId);
                                System.out.println("Insert item ID to remove:");
                                int itemID = scanner.nextInt();
                                int itemFlag = 0;
                                for (Item itemRemove : deliveryDoc.getItems().keySet()){
                                    if (itemID == itemRemove.getItemID()){
                                        deliveryDoc.getItems().remove(itemRemove);
                                        newTransport.calc_transportWeight();
                                        itemFlag = 1;
                                    }
                                }
                                if (itemFlag == 0) {
                                    System.out.println("item does not exist in this delivery document.");
                                }
                                else if (newTransport.checkTransport()) {
                                    System.out.println("Transport OK, item" + itemID + "was removed from the delivery document in this transport.");
                                    break;
                                } else
                                    System.out.println("The weight is still exceeded.");
                            }
                    } while (reChoice != 0);

/************** Finish Transport *******************************/

                //Finish Transport
                case 5:
                    System.out.println("Insert transport ID:");
                    int finishTransportID = scanner.nextInt();
                    if (transportController.getTransport(finishTransportID) == null) {
                        System.out.println("Transport does not exist.");
                    } else if (transportController.getTransport(finishTransportID).isFinished()) {
                        System.out.println("Transport is already finished.");
                    }
                    else {
                        Transport transportF = transportController.getTransport(finishTransportID);
                        transportController.finishTransport(transportF);
                        System.out.println("Transport finished.");
                    }
                    break;

/************** Exit *******************************/

                //Exit
                case 6:
                    System.out.println("Good bye.");
                    break;

                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
        while (choice != 6);
        scanner.close();
        }
    }

