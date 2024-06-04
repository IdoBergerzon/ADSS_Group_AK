package Presentation;

import Domain.*;

import java.util.*;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;


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
            System.out.println("Choose one of the following options:");
            System.out.println("1. Import data to the system");
            System.out.println("2. Start a system without data");
            choice = scanner.nextInt();
            switch (choice) {

                case 1:
                    HashSet<Driver> driverHashSet = readDriversFromCSV("C:\\Users\\WIN10\\Documents\\שנה ב\\ניתו''צ\\עבודה 1 ניתוצ\\ADSS_Group_AK\\dev\\DataTable\\drivers.csv");
                    driverController.getDriversData().setDrivers(driverHashSet);

                    HashMap<Integer, Truck> truckHashMap = readTrucksFromCSV("C:\\Users\\WIN10\\Documents\\שנה ב\\ניתו''צ\\עבודה 1 ניתוצ\\ADSS_Group_AK\\dev\\DataTable\\trucks.csv");
                    truckController.getTrucksData().setTrucks(truckHashMap);

                    HashMap<Integer, Item> itemHashMap = readItemsFromCSV("C:\\Users\\WIN10\\Documents\\שנה ב\\ניתו''צ\\עבודה 1 ניתוצ\\ADSS_Group_AK\\dev\\DataTable\\items.csv");
                    deliveryController.getItemsData().setItems(itemHashMap);

                    HashMap<Integer, ALocation> locationHashMap = readLocationsFromCSV("C:\\Users\\WIN10\\Documents\\שנה ב\\ניתו''צ\\עבודה 1 ניתוצ\\ADSS_Group_AK\\dev\\DataTable\\location.csv");
                    locationController.getLocationsData().setLocations(locationHashMap);
                    System.out.println("System started with data");
                    break;

                case 2:
                    System.out.println("System data is empty.");
                    break;

                default:
                    System.out.println("Wrong choice. Please try again.");
                    break;
            }
        }while (choice != 2 && choice != 1);


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
                    System.out.println("5. Add Item");
                    System.out.println("0. Exit");

                    add = scanner.nextInt();

                    switch (add) {
                        case 0: // Back to Main Menu
                            System.out.println("Returning to the main menu.\n");
                            break;


                        case 1:
                            // Add Truck
                            System.out.println("Insert truck ID:");
                            int truckID = scanner.nextInt();
                            scanner.nextLine();
                            if (truckController.getTruck(truckID) != null){
                                System.out.print("The truck already exists in the system\n");
                            }
                            else {
                                System.out.print("Insert truck Type:\n");
                                String truckType = scanner.nextLine();
                                System.out.println("Insert truck Weight:");
                                double truckWeight = scanner.nextDouble();
                                System.out.println("Insert truck Max Weight:");
                                double MaxWeight = scanner.nextDouble();
                                truckController.addNewTruck(truckID,truckType,MaxWeight,truckWeight);
                                System.out.println("Truck added successfully");
                            }
                            break;


                        case 2:
                            // Add Driver
                            System.out.print("Enter Driver ID:\n");
                            int driverID = scanner.nextInt();
                            scanner.nextLine();
                            if (driverController.getDriver(driverID) != null) {
                                System.out.print("The Driver already exists in the system\n");
                            }
                            else {
                            System.out.print("Enter Driver Name:\n");
                            String driverName = scanner.nextLine();
                            System.out.print("Enter the driver's license number:\n");
                            int licenseNumber = scanner.nextInt();
                            driverController.addDriver(driverID, driverName, licenseNumber);
                            }
                            break;

                        //Add supplier or Store
                        case 3:
                            int locationChoice;
                            System.out.println("1. Add Supplier");
                            System.out.println("2. Add Store");
                            System.out.print("Enter your choice:\n");
                            locationChoice = scanner.nextInt();
                            scanner.nextLine(); // Consume newline

                            if (locationChoice == 1 || locationChoice == 2) {
                                System.out.print("Enter Location ID:\n");
                                int locationID = scanner.nextInt();
                                scanner.nextLine(); // Consume newline
                                if (locationController.getLocation(locationID) != null) {
                                    System.out.print("The location already exists in the system\n");
                                }
                                else {
                                    System.out.print("Enter Address details:\n");
                                    String full_address = scanner.nextLine();

                                    System.out.print("Enter address_code:\n");
                                    int address_code = scanner.nextInt();
                                    scanner.nextLine(); // Consume newline

                                    System.out.print("Enter Shipping area:\n");
                                    int shipping_area = scanner.nextInt();


                                    Address address = new Address(full_address, address_code, shipping_area);

                                    System.out.print("Enter contact:\n");
                                    String contact = scanner.nextLine();
                                    scanner.nextLine();

                                    System.out.print("Enter phone:\n");
                                    String phone = scanner.nextLine();

                                    String l_type = (locationChoice == 1) ? "Supplier" : "Store";
                                    locationController.addLocation(locationID, address, contact, phone, l_type);
                                    System.out.println(l_type + " added successfully.");
                                }
                            } else {
                                System.out.println("Invalid choice for the given location type.\n");
                            }
                            break;

                        case 4:
                            System.out.print("Enter Delivery Document ID:\n");
                            int deliveryDocumentID = scanner.nextInt();
                            scanner.nextLine();
                            if (deliveryController.getDelivery_Document(deliveryDocumentID) != null) {
                                System.out.print("The Delivery Document already exists in the system\n");
                            }
                            else {
                                System.out.println("Enter Source ID:");
                                int sourceID = scanner.nextInt();
                                scanner.nextLine();
                                if (locationController.getLocation(sourceID) == null) {
                                    System.out.println("Invalid source ID\n");
                                } else if (locationController.getLocation(sourceID).getL_type() == "Supplier") {
                                    System.out.print("The location is Supplier (destination)\n");
                                }
                                else {
                                    System.out.print("Enter Destination ID:\n");
                                    int destinationID = scanner.nextInt();
                                    scanner.nextLine();
                                    if (locationController.getLocation(destinationID) == null) {
                                        System.out.println("Invalid destination ID\n");
                                    } else if (locationController.getLocation(destinationID).getL_type() == "Store") {
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
                                            if (deliveryController.getItemsData().getItem(itemID) == null) {
                                                System.out.println("Item does not exist in the system\n");
                                                break;
                                            }
                                            else {
                                                Item item = deliveryController.getItemsData().getItem(itemID);
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
                                        deliveryController.addDelivery_Document(deliveryDocumentID, store, supplier, newItems);
                                    }
                                }
                            }
                            break;

                        case 5:
                            System.out.print("Enter Item ID:\n");
                            int itemID = scanner.nextInt();
                            scanner.nextLine();
                            if (deliveryController.getItemsData().getItem(itemID) != null) {
                                System.out.println("Item already exists in the system\n");
                            }
                            else {
                                System.out.println("Enter item name:");
                                String name = scanner.nextLine();
                                System.out.print("Enter item weight:\n");
                                double weight = scanner.nextDouble();
                                Item item = new Item(itemID, name, weight);
                                deliveryController.getItemsData().addItem(item);
                                System.out.println("Item added successfully.\n");
                            }
                            break;


                        default:
                            System.out.println("Invalid choice. Please try again.\n");
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
                            System.out.println("Returning to the main menu.\n");
                            break;

                        //Update truck availability
                        case 1:
                            System.out.println("Insert truck ID:");
                            int truckID = scanner.nextInt();
                            System.out.println("Insert status available (true/false):");
                            boolean truckAvailable = scanner.nextBoolean();
                            if (truckController.getTruck(truckID) == null)
                                System.out.println("Truck does not exist.\n");
                            else {
                                truckController.getTruck(truckID).setAvailable(truckAvailable);
                                System.out.println("Truck's availability were updated.\n" + truckController.getTruck(truckID));
                            }
                            break;

                        //Update driver details
                        case 2:
                            System.out.println("Insert driver ID:");
                            int driverID = scanner.nextInt();
                            scanner.nextLine();
                            if (driverController.getDriver(driverID) == null) {
                                System.out.println("Driver does not exist.\n");
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
                                    System.out.println("Returning to the main menu.\n");
                                    break;

                                //Update driver availability
                                case 1:
                                    System.out.println("Insert status available (true/false):");
                                    boolean driverAvailable = scanner.nextBoolean();
                                    driverController.getDriver(driverID).setAvailable(driverAvailable);
                                    System.out.println("Driver's availability were updated.\n" + driverController.getDriver(driverID) + "\n");
                                    break;

                                //Update driver license
                                case 2:
                                    System.out.println("Insert new license (int):");
                                    int license = scanner.nextInt();
                                    driverController.getDriver(driverID).setLicenseMaxWeight(license);
                                    System.out.println("Driver's license were updated.\n" + driverController.getDriver(driverID) + "\n");
                                    break;

                                default:
                                    System.out.println("Invalid choice. Please try again.\n");
                                    break;
                            }
                            break;

                        //Update location details
                        case 3:
                            System.out.println("Insert location ID:");
                            int locationID = scanner.nextInt();
                            scanner.nextLine();
                            if (locationController.getLocation(locationID) == null) {
                                System.out.println("Location does not exist.\n");
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
                                    System.out.println("Returning to the main menu.\n");
                                    break;

                                //Update shipping area
                                case 1:
                                    System.out.println("Insert number of shipping area:");
                                    int shippingArea = scanner.nextInt();
                                    locationController.setShipping_area(locationID, shippingArea);
                                    break;

                                //Update contact
                                case 2:
                                    System.out.println("Insert new contact:\n");
                                    String contact = scanner.next();
                                    locationController.updateContact(locationID, contact);
                                    break;

                                //Update phone number
                                case 3:
                                    System.out.println("Insert new phone number:\n");
                                    String phoneNumber = scanner.next();
                                    locationController.updatePhone(locationID, phoneNumber);
                                    break;


                                default:
                                    System.out.println("Invalid choice. Please try again.\n");
                                    break;
                            }
                            break;

                        //Update transport
                        case 4:
                            System.out.println("Insert transport ID:");
                            int transportID = scanner.nextInt();
                            scanner.nextLine();
                            if (transportController.getTransport(transportID) == null) {
                                System.out.println("Transport does not exist.\n");
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
                                    System.out.println("Returning to the main menu.\n");
                                    break;

                                //Change truck
                                case 1:
                                    System.out.println("Insert new truck ID:");
                                    int newTruckID = scanner.nextInt();
                                    scanner.nextLine();
                                    if (truckController.getTruck(newTruckID) == null) {
                                        break;
                                    }
                                    Truck newTruck = truckController.getTruck(newTruckID);
                                    transport.setTruck(newTruck);
                                    System.out.println("Transport's truck was changed\n" + transport + "\n");
                                    break;

                                //Change driver
                                case 2:
                                    System.out.println("Insert new driver ID:");
                                    int newDriverID = scanner.nextInt();
                                    scanner.nextLine();
                                    if (driverController.getDriver(newDriverID) == null) {
                                        break;
                                    }
                                    Driver newDriver = driverController.getDriver(newDriverID);
                                    transport.setDriver(newDriver);
                                    System.out.println("Driver's driver was changed\n" + transport + "\n");
                                    break;

                                //Add delivery
                                case 3:
                                    System.out.println("Insert new delivery ID:");
                                    int newDeliveryID = scanner.nextInt();
                                    scanner.nextLine();
                                    if (deliveryController.getDelivery_Document(newDeliveryID) == null) {
                                        break;
                                    }
                                    Delivery_Document newDelivery = deliveryController.getDelivery_Document(newDeliveryID);
                                    transport.addDeliveryDocument(newDelivery);
                                    System.out.println("Delivery's document was changed\n" + transport);
                                    break;

                                //Add comment
                                case 4:
                                    System.out.println("Insert new comment:\n");
                                    String newComment = scanner.next();
                                    transport.addComment(newComment);
                                    System.out.println("Comment added\n" + transport + "\n");
                                    break;

                                default:
                                    System.out.println("Invalid choice. Please try again.\n");
                                    break;
                            }
                            break;

                        //Update delivery document
                        case 5:
                            System.out.println("Insert delivery ID:");
                            int deliveryID = scanner.nextInt();
                            scanner.nextLine();
                            if (deliveryController.getDelivery_Document(deliveryID) == null) {
                                break;
                            }

                            Delivery_Document deliveryDocument = deliveryController.getDelivery_Document(deliveryID);
                            System.out.println("Delivery document menu:");
                            System.out.println("1. Change source");
                            System.out.println("2. Change destination");
                            System.out.println("3. Change delivery status");
                            System.out.println("4. Add item");
                            System.out.println("5. Remove item");
                            System.out.println("0. Back to Main Menu");

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
                                        break;
                                    }
                                    if (locationController.getLocation(newSourceID).getL_type() == "Supplier") {
                                        System.out.println("The ID is that of a supplier\n");
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
                                    scanner.nextLine();
                                    if (locationController.getLocation(newDestinationID) == null) {
                                        break;
                                    }
                                    if (locationController.getLocation(newDestinationID).getL_type() == "Store") {
                                        System.out.println("The ID is that of a store\n");
                                        break;
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
                                    }
                                    else if (deliveryStatus.equals("finished")) {
                                        deliveryDocument.setDelivery_status(Delivery_DocumentStatus.finished);
                                    }
                                    else if (deliveryStatus.equals("waiting")) {
                                        deliveryDocument.setDelivery_status(Delivery_DocumentStatus.waiting);
                                    }
                                    else {
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
                                    if (deliveryController.getItemsData().getItem(newitemID) == null) {
                                        System.out.println("Item " + newitemID + " does not exist\n");
                                    }
                                    else {
                                        System.out.println("Insert amount:");
                                        int itemAmount = scanner.nextInt();
                                        Item item = deliveryController.getItemsData().getItem(newitemID);
                                        if (deliveryDocument.getItems().containsKey(item)) {
                                            deliveryDocument.getItems().put(item, itemAmount + deliveryDocument.getItems().get(item));
                                        } else
                                            deliveryDocument.getItems().put(item, itemAmount);
                                        System.out.println("item" + item + "was added to the delivery document\n");
                                        System.out.println("The weight is " + deliveryDocument.getTotalWeight() + "\n");
                                        break;
                                    }

                                //Remove item
                                case 5:
                                    System.out.println("Insert item ID to remove:");
                                    int itemID = scanner.nextInt();
                                    scanner.nextLine();
                                    if (deliveryController.getItemsData().getItem(itemID) == null) {
                                        System.out.println("Item " + itemID + " does not exist\n");
                                    }
                                    else {
                                    int flag = 0;
                                    for (Item item1 : deliveryDocument.getItems().keySet()){
                                        if (itemID == item1.getItemID()) {
                                            deliveryDocument.getItems().remove(item1);
                                            flag = 1;
                                        }
                                    }
                                    if (flag == 0) {
                                        System.out.println("item does not exist in the delivery document\n");
                                    }
                                    else {
                                        System.out.println("item" + itemID + "was removed from the delivery document\n");
                                        System.out.println("The weight is " + deliveryDocument.getTotalWeight() + "\n");
                                    }
                                    break;
                                    }

                                default:
                                    System.out.println("Invalid choice. Please try again.\n");
                                    break;
                            }
                            break;

                        default:
                            System.out.println("Invalid choice. Please try again.\n");
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
                            System.out.println("Returning to the main menu.\n");
                            break;

                        case 1: // Display truck details
                            System.out.println("Insert truck ID:");
                            int truckID = scanner.nextInt();
                            scanner.nextLine();
                            if (truckController.getTruck(truckID) == null){
                                System.out.println("Truck does not exist.\n");
                            }
                            else
                                System.out.println(truckController.getTruck(truckID));
                            break;

                        //Display driver details
                        case 2:
                            System.out.println("Insert driver ID:");
                            int driverID = scanner.nextInt();
                            scanner.nextLine();
                            if (driverController.getDriver(driverID) == null){
                                System.out.println("Driver does not exist.\n");
                            }
                            else
                                System.out.println(driverController.getDriver(driverID));
                            break;

                        //Display transport
                        case 3:
                            System.out.println("Insert transport ID:");
                            int transportID = scanner.nextInt();
                            scanner.nextLine();
                            if (transportController.getTransport(transportID) == null){
                                System.out.println("Transport does not exist.\n");
                            }
                            else
                                System.out.println(transportController.getTransport(transportID));
                            break;

                        //Display delivery documents
                        case 4:
                            System.out.println("Insert delivery documents ID:");
                            int deliveryDocumentsID = scanner.nextInt();
                            scanner.nextLine();
                            if (deliveryController.getDelivery_Document(deliveryDocumentsID) == null)
                                System.out.println("Delivery Document does not exist.\n");
                            else
                                System.out.println(deliveryController.getDelivery_Document(deliveryDocumentsID) + "\n");
                            break;

                        //Display location details
                        case 5:
                            System.out.println("Insert location documents ID:");
                            int locationDocumentsID = scanner.nextInt();
                            scanner.nextLine();
                            if (locationController.getLocation(locationDocumentsID) == null)
                                System.out.println("Location Document does not exist.\n");
                            else
                                System.out.println(locationController.getLocation(locationDocumentsID) + "\n");
                            break;

                            //Display all transports
                            case 6:
                            if (transportController.getTransportsData().getTransports().isEmpty())
                                System.out.println("There are no transports in the system.\n");
                            else {
                                System.out.println(transportController.getTransportsData().toString() + "\n");
                            }
                            break;

                        //Display all trucks
                        case 7:
                            if (truckController.getTrucksData().getTrucks().isEmpty())
                                System.out.println("There are no trucks in the system.\n");
                            else
                                System.out.println(truckController.getTrucksData().toString() + "\n");
                            break;

                        //Display all drivers
                        case 8:
                            if (driverController.getDriversData().getDrivers().isEmpty())
                                System.out.println("There are no drivers in the system.\n");
                            else
                                System.out.println(driverController.getDriversData().toString() + "\n");
                            break;



                        default:
                            System.out.println("Invalid choice. Please try again.\n");
                            break;
                    }
                    break;

/************** Start Transport *******************************/

                //Start Transport
                case 4:
                    System.out.println("Insert transport ID:");
                    int transportID = scanner.nextInt();
                    scanner.nextLine();
                    if (transportController.getTransport(transportID) != null){
                        System.out.println("Transport already exists.\n");
                        break;
                    }
                    System.out.println("Insert truck ID:");
                    int truckID = scanner.nextInt();
                    scanner.nextLine();
                    if (truckController.getTruck(truckID) == null) {
                        System.out.println("Truck does not exist.\n");
                        break;
                    }
                    Truck truck = truckController.getTruck(truckID);

                    System.out.println("Insert driver ID:");
                    int driverID = scanner.nextInt();
                    scanner.nextLine();
                    if (driverController.getDriver(driverID) == null) {
                        System.out.println("Driver does not exist.\n");
                        break;
                    }
                    Driver driver = driverController.getDriver(driverID);
                    System.out.println("Insert Sources Shipping Area:");
                    int sourcesArea = scanner.nextInt();
                    deliveryController.getDeliverySourceInArea(sourcesArea);
                    int deliverySourceID = scanner.nextInt();
                    deliveryController.getDeliveryDestinationInArea(deliverySourceID);
                    List <Delivery_Document> deliveryDocs;
                    deliveryDocs = new ArrayList<Delivery_Document>();
                    System.out.println("Insert delivery ID:");
                    int deliveryID = scanner.nextInt();
                    while (deliveryID != 0){
                        System.out.println("Insert delivery documents ID (not 0):\n(Press 0 to return to the Main Menu)\n");
                        deliveryID = scanner.nextInt();
                        scanner.nextLine();
                        if (deliveryController.getDelivery_Document(deliveryID) == null) {
                            System.out.println("Delivery Document does not exist.\n");
                            continue;
                        }
                        if (deliveryController.getDelivery_Document(deliveryID).getItemsStatus() == Delivery_ItemsStatus.in_Progress) {
                            deliveryController.getDelivery_Document(deliveryID).setItemsStatus(Delivery_ItemsStatus.complete);
                        }
                        deliveryDocs.add(deliveryController.getDelivery_Document(deliveryID));
                    }
                    //Create transport and add it to TransportData
                    Transport newTransport = new Transport(transportID, truck, driver, deliveryDocs, "");
                    transportController.getTransportsData().addTransport(newTransport);
                    //Check if transport is valid
                    if (newTransport.calc_transportWeight() <= driver.getLicenseMaxWeight() && newTransport.calc_transportWeight() <= truck.getMaxWeight() + truck.getTruckWeight()) {
                        System.out.println("Proper Transport " + newTransport + "\n");
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
                            System.out.println("Returning to the main menu.\n");
                            break;

                        //Change truck
                        case 1:
                            System.out.println("Insert new truck ID:");
                            int newTruckID = scanner.nextInt();
                            scanner.nextLine();
                            if (truckController.getTruck(newTruckID) == null){
                                System.out.println("Truck does not exist.\n");
                            }
                            else {
                                Truck newTruck = truckController.getTruck(newTruckID);
                                if (!newTruck.isAvailable()) {
                                    System.out.println("Truck does not available.\n");
                                } else {
                                    double tempWeight = newTransport.getTotalWeights().get(newTransport.getTotalWeights().size() - 1) - newTransport.getTruck().getTruckWeight() + newTruck.getTruckWeight();
                                    if (newTruck.getTruckWeight() + newTruck.getMaxWeight() >= tempWeight) {
                                        if (newTransport.getDriver().getLicenseMaxWeight() >= tempWeight) {
                                            newTransport.setTruck(newTruck);
                                            System.out.println("Transport OK, truck was changed successfully.\n");
                                            break;
                                        } else {
                                            System.out.println("The driver's license is less than the weight of the transport.\nPlease insert new driverID:\n(Press 0 to return to the Main Menu)\n");
                                            int ChangeDriverID = scanner.nextInt();
                                            scanner.nextLine();
                                            if (driverController.getDriver(ChangeDriverID) == null) {
                                                System.out.println("Driver does not exist.\n");
                                            }
                                            else if (!driverController.getDriver(ChangeDriverID).isAvailable()) {
                                                System.out.println("Driver does not available.\n");
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
                            scanner.nextLine();
                            if (locationController.getLocation(sourceID) == null)
                                System.out.println("Location does not exist.\n");
                            else {
                                if (!locationController.getLocation(sourceID).getL_type().equals("Store"))
                                    System.out.println("The location is Supplier.\n");
                                else {
                                    Store source = (Store) locationController.getLocation(sourceID);
                                    if (newTransport.getSource().contains(source)) {
                                        newTransport.removeSource(source);
                                        if (newTransport.checkTransport()) {
                                            System.out.println("Transport OK, source removed successfully.\n");
                                            break;
                                        } else
                                            System.out.println("The weight is still exceeded.\n");
                                    }
                                    else
                                        System.out.println("Source" + source + "does not exist in this transport.\n");
                                }
                            }

                        //Remove destination
                        case 3:
                            System.out.println("Insert destination ID to remove:");
                            int destinationID = scanner.nextInt();
                            scanner.nextLine();
                            if (locationController.getLocation(destinationID) == null) {
                                System.out.println("Location does not exist.\n");
                            }
                            else {
                                if (!locationController.getLocation(destinationID).getL_type().equals("Supplier"))
                                    System.out.println("The location is Store.\n");
                                else {
                                    Supplier destination = (Supplier) locationController.getLocation(destinationID);
                                    if (newTransport.getDestinations().contains(destination)) {
                                        newTransport.removeDestination(destination);
                                        if (newTransport.checkTransport()) {
                                            System.out.println("Transport OK, destination removed successfully.\n");
                                            break;
                                        } else
                                            System.out.println("The weight is still exceeded.\n");
                                    }
                                    else
                                        System.out.println("Destination" + destination + "does not exist in this transport.\n");
                                }
                            }

                        //Remove item from delivery in transport
                        case 4:
                            System.out.println("Insert delivery ID to remove from:");
                            int deliveryId = scanner.nextInt();
                            scanner.nextLine();
                            if (!newTransport.getDelivery_documents().contains(deliveryId)) {
                                System.out.println("Delivery Document does not exist in this transport.\n");
                            }
                            else {
                                Delivery_Document deliveryDoc = newTransport.getDelivery_documents().get(deliveryId);
                                System.out.println("Insert item ID to remove:");
                                int itemID = scanner.nextInt();
                                scanner.nextLine();
                                if (deliveryController.getItemsData().getItems().get(itemID) == null) {
                                    System.out.println("Item does not exist.\n");
                                } else {
                                    int itemFlag = 0;
                                    for (Item itemRemove : deliveryDoc.getItems().keySet()) {
                                        if (itemID == itemRemove.getItemID()) {
                                            deliveryDoc.getItems().remove(itemRemove);
                                            if (deliveryDoc.getItems().isEmpty())
                                                newTransport.getDelivery_documents().remove(deliveryDoc);
                                            newTransport.calc_transportWeight();
                                            itemFlag = 1;
                                        }
                                    }
                                    if (itemFlag == 0) {
                                        System.out.println("item does not exist in this delivery document.\n");
                                    } else if (newTransport.checkTransport()) {
                                        System.out.println("Transport OK, item" + itemID + "was removed from the delivery document in this transport.\n");
                                        break;
                                    } else
                                        System.out.println("The weight is still exceeded.\n");
                                }
                            }


                    } while (reChoice != 0);

/************** Finish Transport *******************************/

                //Finish Transport
                case 5:
                    System.out.println("Insert transport ID:");
                    int finishTransportID = scanner.nextInt();
                    scanner.nextLine();
                    if (transportController.getTransport(finishTransportID) == null) {
                        System.out.println("Transport does not exist.\n");
                    } else if (transportController.getTransport(finishTransportID).isFinished()) {
                        System.out.println("Transport is already finished.\n");
                    }
                    else {
                        Transport transportF = transportController.getTransport(finishTransportID);
                        transportController.finishTransport(transportF);
                        System.out.println("Transport finished.\n");
                    }
                    break;

/************** Exit *******************************/

                //Exit
                case 6:
                    System.out.println("Good bye.\n");
                    break;

                default:
                    System.out.println("Invalid choice. Please try again.\n");
            }
        }
        while (choice != 6);
        scanner.close();
        }
    public static HashSet<Driver> readDriversFromCSV(String csvFilePath) {
        HashSet<Driver> driversD = new HashSet<>();
        try (BufferedReader br = new BufferedReader(new FileReader(csvFilePath))) {
            String line;
            boolean firstLine = true;
            while ((line = br.readLine()) != null) {
                if (firstLine) {
                    firstLine = false;
                    continue;
                }
                String[] data = line.split(",");
                int driverID = Integer.parseInt(data[2]);
                String name = data[1];
                int maxWeight = Integer.parseInt(data[0]);
                Driver driver = new Driver(driverID, name, maxWeight);
                driversD.add(driver);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return driversD;
    }

    public static HashMap<Integer, Truck> readTrucksFromCSV(String csvFilePath) {
        HashMap<Integer, Truck> trucksD = new HashMap<>();

        try (BufferedReader br = new BufferedReader(new FileReader(csvFilePath))) {
            String line;
            boolean firstLine = true;
            while ((line = br.readLine()) != null) {
                if (firstLine) {
                    firstLine = false;
                    continue;
                }
                String[] data = line.split(",");
                int truckID = Integer.parseInt(data[3]);
                String type = data[2];
                double truckWeight = Double.parseDouble(data[1]);
                double maxWeight = Double.parseDouble(data[0]);
                Truck truck = new Truck(truckID, type, truckWeight, maxWeight);
                trucksD.put(truckID, truck);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return trucksD;
    }


        public static HashMap<Integer, Item> readItemsFromCSV(String csvFilePath){
            HashMap<Integer, Item> itemsD = new HashMap<>();

            try (BufferedReader br = new BufferedReader(new FileReader(csvFilePath))) {
                String line;
                boolean firstLine = true;
                while ((line = br.readLine()) != null) {
                    if (firstLine) {
                        firstLine = false;
                        continue;
                    }
                    String[] data = line.split(",");
                    int itemID = Integer.parseInt(data[0]);
                    String name = data[1];
                    double weight = Double.parseDouble(data[2]);
                    Item item = new Item(itemID, name, weight);
                    itemsD.put(itemID, item);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            return itemsD;
        }

    public static HashMap<Integer, ALocation> readLocationsFromCSV(String csvFilePath){
        HashMap<Integer, ALocation> locationHashMap = new HashMap<>();

        try (BufferedReader br = new BufferedReader(new FileReader(csvFilePath))) {
            String line;
            boolean firstLine = true;
            while ((line = br.readLine()) != null) {
                if (firstLine) {
                    firstLine = false;
                    continue;
                }
                String[] data = line.split(",");
                int locationID = Integer.parseInt(data[6].trim());
                int shippingArea = Integer.parseInt(data[5].trim());
                int address_code = Integer.parseInt(data[4].trim());
                String full_address = data[3];
                String contact = data[2];
                String phone = data[1];
                String l_type = data[0];
                Address address = new Address(full_address,address_code,shippingArea);
                if (l_type.equals("Supplier")) {
                    Supplier supplier = new Supplier(locationID, address, contact, phone);
                    locationHashMap.put(locationID, supplier);
                }
                else if (l_type.equals("Store")) {
                    Store store = new Store(locationID, address, contact, phone);
                    locationHashMap.put(locationID, store);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return locationHashMap;
    }
    }

