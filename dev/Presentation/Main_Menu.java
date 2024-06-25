package Presentation;
import Domain.*;
import java.time.LocalTime;
import java.util.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;


public class Main_Menu {
    public static void main(String[] args) {
        Delivery_DocumentsController deliveryController = new Delivery_DocumentsController();
        DriverController driverController = new DriverController();
        LocationController locationController = new LocationController();
        TransportController transportController = new TransportController();
        TruckController truckController = new TruckController();
        LocalTime localTime = LocalTime.now();

        Scanner scanner = new Scanner(System.in);
        int choice;
        do {
            System.out.println("Welcome to SUPER-LI transportation system.");
            System.out.println("Choose one of the following options:");
            System.out.println("1. Import data to the system");
            System.out.println("2. Start a system without data");
            choice = scanner.nextInt();
            switch (choice) {

                case 1:
                    HashSet<Driver> driverHashSet = readDriversFromCSV("dev/DataTable/drivers.csv");
                    driverController.getDriversData().setDrivers(driverHashSet);

                    HashMap<Integer, Truck> truckHashMap = readTrucksFromCSV("dev/DataTable/trucks.csv");
                    truckController.getTrucksData().setTrucks(truckHashMap);

                    HashMap<Integer, Item> itemHashMap = readItemsFromCSV("dev/DataTable/items.csv");
                    deliveryController.getItemsData().setItems(itemHashMap);

                    HashMap<Integer, ALocation> locationHashMap = readLocationsFromCSV("dev/DataTable/location.csv");
                    locationController.getLocationsData().setLocations(locationHashMap);

                    createDeliveryDocsData(deliveryController, locationController);
                    createTransportsData(transportController, deliveryController, truckController, driverController);



                    System.out.println("System started with data");
                    break;

                case 2:
                    System.out.println("System data is empty.");
                    break;

                default:
                    System.out.println("Wrong choice. Please try again.");
                    break;
            }
        } while (choice != 2 && choice != 1);


        do {
            System.out.println("Main Menu:");
            System.out.println("1. Add");
            System.out.println("2. Update");
            System.out.println("3. Display");
            System.out.println("4. Create new Transport");
            System.out.println("5. Finish Transport");
            System.out.println("6. Exit");

            if (localTime.equals(LocalTime.MIDNIGHT)) {
                for (Transport trans : transportController.getTransportsData().getTransports().values()) {
                    if (!trans.isFinished()) {
                        trans.setFinished(true);
                    }
                }
            }

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
                            if (truckController.getTruck(truckID) != null) {
                                System.out.print("The truck already exists in the system\n");
                            } else {
                                System.out.print("Insert truck Type:\n");
                                String truckType = scanner.nextLine();
                                System.out.println("Insert truck Weight:");
                                double truckWeight = scanner.nextDouble();
                                System.out.println("Insert truck Max Weight:");
                                double MaxWeight = scanner.nextDouble();
                                truckController.addNewTruck(truckID, truckType, truckWeight, MaxWeight);
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
                            } else {
                                System.out.print("Enter Driver Name:\n");
                                String driverName = scanner.nextLine();
                                System.out.print("Enter max weight license:\n");
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
                                } else {
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

                        //Add Delivery document
                        case 4:
                            System.out.print("Enter Delivery Document ID:\n");
                            int deliveryDocumentID = scanner.nextInt();
                            scanner.nextLine();
                            if (deliveryController.getDelivery_Document(deliveryDocumentID) != null) {
                                System.out.print("The Delivery Document already exists in the system\n");
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
                                            if (deliveryController.getItemsData().getItem(itemID) == null) {
                                                System.out.println("Item does not exist in the system\n");
                                                break;
                                            } else {
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

                        //Add item
                        case 5:
                            System.out.print("Enter Item ID:\n");
                            int itemID = scanner.nextInt();
                            scanner.nextLine();
                            if (deliveryController.getItemsData().getItem(itemID) != null) {
                                System.out.println("Item already exists in the system\n");
                            } else {
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
                    System.out.println("1. Update driver details");
                    System.out.println("2. Update location details");
                    System.out.println("3. Update transport details");
                    System.out.println("4. Update delivery details");
                    System.out.println("0. Back to Main Menu");

                    update = scanner.nextInt();
                    switch (update) {
                        case 0: // Back to Main Menu
                            System.out.println("Returning to the main menu.\n");
                            break;

                        //Update driver details
                        case 1:
                            System.out.println("Insert driver ID:");
                            int driverID = scanner.nextInt();
                            scanner.nextLine();
                            if (driverController.getDriver(driverID) == null) {
                                System.out.println("Driver does not exist.\n");
                                break;
                            }
                            System.out.println("Driver update menu:");
                            System.out.println("1. Update driver license");
                            System.out.println("0. Back to Main Menu");

                            update = scanner.nextInt();
                            switch (update) {
                                //Back to main menu
                                case 0:
                                    System.out.println("Returning to the main menu.\n");
                                    break;


                                //Update driver license
                                case 1:
                                    System.out.println("Insert new max weight license (int):");
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
                        case 2:
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
                                    System.out.println("Shipping area updated successfully.\n");
                                    break;

                                //Update contact
                                case 2:
                                    System.out.println("Insert new contact:");
                                    String contact = scanner.next();
                                    locationController.updateContact(locationID, contact);
                                    System.out.println("Contact updated successfully.\n");
                                    break;

                                //Update phone number
                                case 3:
                                    System.out.println("Insert new phone number:");
                                    String phoneNumber = scanner.next();
                                    locationController.updatePhone(locationID, phoneNumber);
                                    System.out.println("Phone number updated successfully.\n");
                                    break;


                                default:
                                    System.out.println("Invalid choice. Please try again.\n");
                                    break;
                            }
                            break;

                        //Update transport
                        case 3:
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
                            System.out.println("3. Add delivery document to transport");
                            System.out.println("4. Remove delivery document from transport");
                            System.out.println("5. Transport weighting");
                            System.out.println("0. Back to Main Menu");

                            update = scanner.nextInt();
                            switch (update) {
                                case 0:
                                    System.out.println("Returning to the main menu.\n");
                                    break;

                                //Change truck
                                case 1:
                                    truckController.printAllAvailableTrucks(transport.calc_transportWeight());
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
                                    driverController.printAllAvailableDrivers(transport.calc_transportWeight());
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
                                    if (newDelivery.getDelivery_Status().equals(Delivery_DocumentStatus.in_Progress)
                                            || newDelivery.getDelivery_Status().equals(Delivery_DocumentStatus.finished)) {
                                        System.out.println("Delivery has already been initiated.\n");
                                        break;
                                    }
                                    transport.addDeliveryDocument(newDelivery);
                                    if (!transport.checkTransport()) {
                                        transport.removeDeliveryDocument(newDelivery);
                                        System.out.println("Overweight.\n");
                                        break;
                                    }
                                    System.out.println("Delivery's document was changed\n" + transport);
                                    break;

                                //Remove delivery
                                case 4:
                                    transport.printAllDeliveryDocuments();
                                        System.out.println("Insert delivery ID to remove:\n");
                                        int removeID = scanner.nextInt();
                                        scanner.nextLine();
                                        if (deliveryController.getDelivery_Document(removeID) == null) {
                                            System.out.println("Delivery Document does not exist.\n");
                                        }
                                        else {
                                            Delivery_Document removeDeliveryDocument = deliveryController.getDelivery_Document(removeID);
                                            transport.removeDeliveryDocument(removeDeliveryDocument);
                                            System.out.println("Delivery's document was changed\n" + transport);
                                        }
                                    break;

                                case 5:
                                    transport.calc_transportWeight();
                                    break;

                                default:
                                    System.out.println("Invalid choice. Please try again.\n");
                                    break;
                            }
                            break;

                        //Update delivery document
                        case 4:
                            System.out.println("Insert delivery ID:");
                            int deliveryID = scanner.nextInt();
                            scanner.nextLine();
                            if (deliveryController.getDelivery_Document(deliveryID) == null) {
                                break;
                            } else if (!deliveryController.getDelivery_Document(deliveryID).getDelivery_Status().equals(Delivery_DocumentStatus.waiting)) {
                                System.out.println("Delivery in progress or already finished .\n");
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
                                        System.out.println("Location does not exist.\n");
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
                                    if (deliveryController.getItemsData().getItem(newitemID) == null) {
                                        System.out.println("Item " + newitemID + " does not exist\n");
                                    } else {
                                        System.out.println("Insert amount:");
                                        int itemAmount = scanner.nextInt();
                                        Item item = deliveryController.getItemsData().getItem(newitemID);
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
                                    if (deliveryController.getItemsData().getItem(itemID) == null) {
                                        System.out.println("Item " + itemID + " does not exist\n");
                                    } else {
                                        Item item = deliveryController.getItemsData().getItem(itemID);
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
                            if (truckController.getTruck(truckID) == null) {
                                System.out.println("Truck does not exist.\n");
                            } else
                                System.out.println(truckController.getTruck(truckID));
                            break;

                        //Display driver details
                        case 2:
                            System.out.println("Insert driver ID:");
                            int driverID = scanner.nextInt();
                            scanner.nextLine();
                            if (driverController.getDriver(driverID) == null) {
                                System.out.println("Driver does not exist.\n");
                            } else
                                System.out.println(driverController.getDriver(driverID));
                            break;

                        //Display transport
                        case 3:
                            System.out.println("Insert transport ID:");
                            int transportID = scanner.nextInt();
                            scanner.nextLine();
                            if (transportController.getTransport(transportID) == null) {
                                System.out.println("Transport does not exist.\n");
                            } else
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
                    if (transportController.getTransport(transportID) != null) {
                        System.out.println("Transport already exists.\n");
                        break;
                    }
                    truckController.printAllAvailableTrucks(0);
                    System.out.println("Insert truck ID:");
                    int truckID = scanner.nextInt();
                    scanner.nextLine();
                    if (truckController.getTruck(truckID) == null) {
                        System.out.println("Truck does not exist.\n");
                        break;
                    }
                    if (!truckController.getTruck(truckID).isAvailable()) {
                        System.out.println("Truck is not available.\n");
                        break;
                    }
                    Truck truck = truckController.getTruck(truckID);


                    driverController.printAllAvailableDrivers(0);
                    System.out.println("Insert driver ID:");
                    int driverID = scanner.nextInt();
                    scanner.nextLine();
                    if (driverController.getDriver(driverID) == null) {
                        System.out.println("Driver does not exist.\n");
                        break;
                    }
                    if (!driverController.getDriver(driverID).isAvailable()) {
                        System.out.println("Driver is not available.\n");
                        break;
                    }
                    Driver driver = driverController.getDriver(driverID);


                    List<Delivery_Document> deliveryDocs;
                    deliveryDocs = new ArrayList<Delivery_Document>();
                    int searchMore = 1;
                    while (searchMore != 0) {
                        if (searchMore != 1) {
                            break;
                        }
                        System.out.println("\nSource shipping area:");
                        locationController.getAllSourceShippingArea();
                        System.out.println("\nInsert Sources Shipping Area:");
                        int sourcesArea = scanner.nextInt();
                        System.out.println("\nDestination shipping area:");
                        deliveryController.getShippingAreaForDest(sourcesArea);
                        System.out.println("\nInsert Destinations Shipping Area:");
                        int destinationArea = scanner.nextInt();
                        deliveryController.getDeliveryInArea(sourcesArea, destinationArea);

                            System.out.println("Insert delivery document ID:\n(You can also select a transport document that has not been displayed) \n(Press 0 to return to the Main Menu)\n");
                            int deliveryID = scanner.nextInt();
                            scanner.nextLine();
                            if (deliveryID == 0) {
                                break;
                            }
                            if (deliveryController.getDelivery_Document(deliveryID) == null) {
                                System.out.println("Delivery Document does not exist.\n");
                                continue;
                            }
                            if (deliveryController.getDelivery_Document(deliveryID).getDelivery_Status().equals(Delivery_DocumentStatus.finished) ||
                                    deliveryController.getDelivery_Document(deliveryID).getDelivery_Status().equals(Delivery_DocumentStatus.in_Progress)) {
                                System.out.println("Delivery has already been initiated.\n");
                                continue;
                            }
                            if (deliveryController.getDelivery_Document(deliveryID).getItemsStatus() == Delivery_ItemsStatus.in_Progress) {
                                deliveryController.getDelivery_Document(deliveryID).setItemsStatus(Delivery_ItemsStatus.complete);
                            }
                            deliveryDocs.add(deliveryController.getDelivery_Document(deliveryID));

                            System.out.println("Add more delivery document ID? (No = 0, Yes = 1)");
                            searchMore = scanner.nextInt();

                    }
                    //Create transport and add it to TransportData
                    Transport newTransport = new Transport(transportID, truck, driver, deliveryDocs, "");
                    transportController.getTransportsData().addTransport(newTransport);
                    //Check if transport is valid
                    if (newTransport.calc_transportWeight() <= driver.getLicenseMaxWeight() && newTransport.calc_transportWeight() <= truck.getMaxWeight() + truck.getTruckWeight()) {
                        System.out.println("Proper Transport " + newTransport + "\n");
                        break;
                    }

                    int reChoice;
                    do {
                        //Replanning transport
                        System.out.println("Overweight, please choose one from the following numbers:");
                        System.out.println("1. Change truck");
                        System.out.println("2. Remove source");
                        System.out.println("3. Remove destination");
                        System.out.println("4. Remove Item from delivery");
                        System.out.println("0. Exit and Delete transport");
                        reChoice = scanner.nextInt();

                        switch (reChoice) {
                            case 0:
                                transportController.getTransportsData().removeTransportById(transportID);
                                System.out.println("Returning to the main menu.\n");
                                break;

                            //Change truck
                            case 1:
                                System.out.println("Possible Trucks:\n");
                                int count = 0;
                                for (Truck truck1: truckController.getTrucksData().getTrucks().values()){
                                    if (truck1.getMaxWeight()>=newTransport.getWeight() && truck1.isAvailable()){
                                        System.out.println(truck1);
                                        count++;
                                    }
                                }
                                if (count != 0) {
                                    System.out.println("Insert new truck ID:\n(Press 0 to return to the Main Menu)\n");
                                    int newTruckID = scanner.nextInt();
                                    scanner.nextLine();
                                    if (newTruckID == 0) {
                                        transportController.getTransportsData().removeTransportById(transportID);
                                        reChoice = 0;
                                        break;
                                    }
                                    if (truckController.getTruck(newTruckID) == null) {
                                        System.out.println("Truck does not exist.\n");
                                    } else {
                                        Truck newTruck = truckController.getTruck(newTruckID);
                                        if (newTransport.setTruck(newTruck)) {
                                            System.out.println("Transport OK, truck was changed successfully.\n");
                                            reChoice = 0;
                                            break;
                                        }
                                    }
                                }
                                else
                                    System.out.println("No Trucks Available.\n");
                                break;

                            //Remove source
                            case 2:
                                newTransport.printAllSources();
                                System.out.println("Insert source ID to remove:");
                                int sourceID = scanner.nextInt();
                                scanner.nextLine();
                                if (locationController.getLocation(sourceID) == null)
                                    System.out.println("Location does not exist.\n");
                                else {
                                    if (!locationController.getLocation(sourceID).getL_type().equals("Store"))
                                        System.out.println("The location is a destination.\n");
                                    else {
                                        Store source = (Store) locationController.getLocation(sourceID);
                                        if (newTransport.getSource().contains(source)) {
                                            newTransport.removeSource(source);
                                            if (newTransport.checkTransport()) {
                                                System.out.println("Transport OK, source removed successfully.\n");
                                                reChoice = 0;
                                                break;
                                            } else
                                                System.out.println("The weight is still exceeded.\n");
                                        } else
                                            System.out.println("Source" + source + "does not exist in this transport.\n");
                                    }
                                }
                                break;

                            //Remove destination
                            case 3:
                                newTransport.printAllDestinations();
                                System.out.println("Insert destination ID to remove:");
                                int destinationID = scanner.nextInt();
                                scanner.nextLine();
                                if (locationController.getLocation(destinationID) == null) {
                                    System.out.println("Location does not exist.\n");
                                } else {
                                    if (!locationController.getLocation(destinationID).getL_type().equals("Supplier"))
                                        System.out.println("The location is Store.\n");
                                    else {
                                        Supplier destination = (Supplier) locationController.getLocation(destinationID);
                                        if (newTransport.getDestinations().contains(destination)) {
                                            newTransport.removeDestination(destination);
                                            if (newTransport.checkTransport()) {
                                                System.out.println("Transport OK, destination removed successfully.\n");
                                                reChoice = 0;
                                                break;
                                            } else
                                                System.out.println("The weight is still exceeded.\n");
                                        } else
                                            System.out.println("Destination" + destination + "does not exist in this transport.\n");
                                    }
                                }
                                break;

                            //Remove item from delivery in transport
                            case 4:
                                System.out.println("Delivery documents in this transport:");
                                newTransport.printAllDeliveryDocuments();
                                System.out.println("Insert delivery ID to remove from:");
                                int deliveryId = scanner.nextInt();
                                scanner.nextLine();
                                if (deliveryController.getDelivery_Document(deliveryId) == null) {
                                    System.out.println("Delivery does not exist.\n");
                                }
                                if (!newTransport.getDelivery_documents().contains(deliveryController.getDelivery_Document(deliveryId))) {
                                    System.out.println("Delivery Document does not exist in this transport.\n");
                                } else {
                                    Delivery_Document deliveryDoc = null;
                                    for (Delivery_Document delivery : newTransport.getDelivery_documents()) {
                                        if (delivery.getDocumentID() == deliveryId) {
                                            deliveryDoc = delivery;
                                        }
                                    }
                                    if (deliveryDoc != null) {
                                        System.out.println("Items in this delivery documents:\n");
                                        deliveryDoc.printAllItems();
                                        System.out.println("Insert item ID to remove:");
                                        int itemID = scanner.nextInt();
                                        scanner.nextLine();
                                        if (deliveryController.getItemsData().getItems().get(itemID) == null) {
                                            System.out.println("Item does not exist.\n");
                                        } else {
                                            Item removedItem = deliveryController.getItemsData().getItems().get(itemID);
                                            if (deliveryDoc.removeItem(removedItem)) {
                                                System.out.println("Item" + removedItem + "removed successfully.\n");
                                                newTransport.calc_transportWeight();
                                                if (newTransport.checkTransport()) {
                                                    System.out.println("Transport OK, item" + itemID + "was removed from the delivery document in this transport.\n");
                                                    reChoice = 0;
                                                    break;
                                                } else
                                                    System.out.println("The weight is still exceeded.\n");
                                            }
                                        }
                                    }
                                    else {
                                        System.out.println("Delivery does not exist in this transport.\n");
                                    }
                                }
                                break;
                        }
                    }while (reChoice != 0) ;
            break;

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
                } else {
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
        while(choice !=6);
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

    public static Delivery_DocumentsController createDeliveryDocsData(Delivery_DocumentsController deliveryController, LocationController locationController) {
        HashMap<Item, Integer> itemIntegerHashMap1 = new HashMap<>();
        itemIntegerHashMap1.put(deliveryController.getItemsData().getItem(1), 10);
        itemIntegerHashMap1.put(deliveryController.getItemsData().getItem(2), 20);
        itemIntegerHashMap1.put(deliveryController.getItemsData().getItem(3), 30);
        Delivery_Document document1 = new Delivery_Document((Store) locationController.getLocation(2), 1, (Supplier) locationController.getLocation(1), itemIntegerHashMap1);
        deliveryController.getDocumentsData().addDelivery_Document(document1);

        HashMap<Item, Integer> itemIntegerHashMap2 = new HashMap<>();
        itemIntegerHashMap2.put(deliveryController.getItemsData().getItem(28), 10);
        itemIntegerHashMap2.put(deliveryController.getItemsData().getItem(5), 25);
        itemIntegerHashMap2.put(deliveryController.getItemsData().getItem(15), 15);
        Delivery_Document document2 = new Delivery_Document((Store) locationController.getLocation(4), 2, (Supplier) locationController.getLocation(1), itemIntegerHashMap2);
        deliveryController.getDocumentsData().addDelivery_Document(document2);

        HashMap<Item, Integer> itemIntegerHashMap3 = new HashMap<>();
        itemIntegerHashMap3.put(deliveryController.getItemsData().getItem(20), 50);
        itemIntegerHashMap3.put(deliveryController.getItemsData().getItem(17), 25);
        itemIntegerHashMap3.put(deliveryController.getItemsData().getItem(12), 10);
        Delivery_Document document3 = new Delivery_Document((Store) locationController.getLocation(2), 3, (Supplier) locationController.getLocation(3), itemIntegerHashMap3);
        deliveryController.getDocumentsData().addDelivery_Document(document3);

        HashMap<Item, Integer> itemIntegerHashMap4 = new HashMap<>();
        itemIntegerHashMap4.put(deliveryController.getItemsData().getItem(23), 20);
        itemIntegerHashMap4.put(deliveryController.getItemsData().getItem(7), 5);
        itemIntegerHashMap4.put(deliveryController.getItemsData().getItem(2), 10);
        itemIntegerHashMap4.put(deliveryController.getItemsData().getItem(10), 16);
        Delivery_Document document4 = new Delivery_Document((Store) locationController.getLocation(4), 4, (Supplier) locationController.getLocation(7), itemIntegerHashMap4);
        deliveryController.getDocumentsData().addDelivery_Document(document4);

        HashMap<Item, Integer> itemIntegerHashMap5 = new HashMap<>();
        itemIntegerHashMap5.put(deliveryController.getItemsData().getItem(17), 10);
        itemIntegerHashMap5.put(deliveryController.getItemsData().getItem(18), 50);
        itemIntegerHashMap5.put(deliveryController.getItemsData().getItem(19), 20);
        itemIntegerHashMap5.put(deliveryController.getItemsData().getItem(20), 35);
        Delivery_Document document5 = new Delivery_Document((Store) locationController.getLocation(2), 5, (Supplier) locationController.getLocation(9), itemIntegerHashMap5);
        deliveryController.getDocumentsData().addDelivery_Document(document5);

        HashMap<Item, Integer> itemIntegerHashMap6 = new HashMap<>();
        itemIntegerHashMap6.put(deliveryController.getItemsData().getItem(21), 15);
        itemIntegerHashMap6.put(deliveryController.getItemsData().getItem(22), 25);
        itemIntegerHashMap6.put(deliveryController.getItemsData().getItem(23), 35);
        itemIntegerHashMap6.put(deliveryController.getItemsData().getItem(24), 45);
        Delivery_Document document6 = new Delivery_Document((Store) locationController.getLocation(6), 6, (Supplier) locationController.getLocation(5), itemIntegerHashMap6);
        deliveryController.getDocumentsData().addDelivery_Document(document6);

        HashMap<Item, Integer> itemIntegerHashMap7 = new HashMap<>();
        itemIntegerHashMap7.put(deliveryController.getItemsData().getItem(25), 50);
        itemIntegerHashMap7.put(deliveryController.getItemsData().getItem(26), 40);
        itemIntegerHashMap7.put(deliveryController.getItemsData().getItem(27), 30);
        itemIntegerHashMap7.put(deliveryController.getItemsData().getItem(28), 20);
        Delivery_Document document7 = new Delivery_Document((Store) locationController.getLocation(8), 7, (Supplier) locationController.getLocation(1), itemIntegerHashMap7);
        deliveryController.getDocumentsData().addDelivery_Document(document7);

        HashMap<Item, Integer> itemIntegerHashMap8 = new HashMap<>();
        itemIntegerHashMap8.put(deliveryController.getItemsData().getItem(29), 10);
        itemIntegerHashMap8.put(deliveryController.getItemsData().getItem(30), 50);
        itemIntegerHashMap8.put(deliveryController.getItemsData().getItem(1), 20);
        itemIntegerHashMap8.put(deliveryController.getItemsData().getItem(2), 35);
        Delivery_Document document8 = new Delivery_Document((Store) locationController.getLocation(8), 8, (Supplier) locationController.getLocation(3), itemIntegerHashMap8);
        deliveryController.getDocumentsData().addDelivery_Document(document8);

        HashMap<Item, Integer> itemIntegerHashMap9 = new HashMap<>();
        itemIntegerHashMap9.put(deliveryController.getItemsData().getItem(3), 15);
        itemIntegerHashMap9.put(deliveryController.getItemsData().getItem(4), 25);
        itemIntegerHashMap9.put(deliveryController.getItemsData().getItem(5), 35);
        itemIntegerHashMap9.put(deliveryController.getItemsData().getItem(6), 45);
        Delivery_Document document9 = new Delivery_Document((Store) locationController.getLocation(2), 9, (Supplier) locationController.getLocation(7), itemIntegerHashMap9);
        deliveryController.getDocumentsData().addDelivery_Document(document9);

        HashMap<Item, Integer> itemIntegerHashMap10 = new HashMap<>();
        itemIntegerHashMap10.put(deliveryController.getItemsData().getItem(1), 30);
        itemIntegerHashMap10.put(deliveryController.getItemsData().getItem(2), 25);
        itemIntegerHashMap10.put(deliveryController.getItemsData().getItem(3), 20);
        itemIntegerHashMap10.put(deliveryController.getItemsData().getItem(4), 15);
        Delivery_Document document10 = new Delivery_Document((Store) locationController.getLocation(4), 10, (Supplier) locationController.getLocation(5), itemIntegerHashMap10);
        deliveryController.getDocumentsData().addDelivery_Document(document10);

        HashMap<Item, Integer> itemIntegerHashMap11 = new HashMap<>();
        itemIntegerHashMap11.put(deliveryController.getItemsData().getItem(13), 20);
        itemIntegerHashMap11.put(deliveryController.getItemsData().getItem(14), 5);
        itemIntegerHashMap11.put(deliveryController.getItemsData().getItem(15), 40);
        itemIntegerHashMap11.put(deliveryController.getItemsData().getItem(16), 16);
        Delivery_Document document11 = new Delivery_Document((Store) locationController.getLocation(4), 11, (Supplier) locationController.getLocation(7), itemIntegerHashMap11);
        deliveryController.getDocumentsData().addDelivery_Document(document11);

        HashMap<Item, Integer> itemIntegerHashMap12 = new HashMap<>();
        itemIntegerHashMap12.put(deliveryController.getItemsData().getItem(5), 35);
        itemIntegerHashMap12.put(deliveryController.getItemsData().getItem(6), 10);
        itemIntegerHashMap12.put(deliveryController.getItemsData().getItem(7), 50);
        itemIntegerHashMap12.put(deliveryController.getItemsData().getItem(8), 20);
        Delivery_Document document12 = new Delivery_Document((Store) locationController.getLocation(2), 12, (Supplier) locationController.getLocation(9), itemIntegerHashMap12);
        deliveryController.getDocumentsData().addDelivery_Document(document12);

        HashMap<Item, Integer> itemIntegerHashMap13 = new HashMap<>();
        itemIntegerHashMap13.put(deliveryController.getItemsData().getItem(9), 15);
        itemIntegerHashMap13.put(deliveryController.getItemsData().getItem(10), 30);
        itemIntegerHashMap13.put(deliveryController.getItemsData().getItem(11), 25);
        itemIntegerHashMap13.put(deliveryController.getItemsData().getItem(12), 10);
        Delivery_Document document13 = new Delivery_Document((Store) locationController.getLocation(6), 13, (Supplier) locationController.getLocation(9), itemIntegerHashMap13);
        deliveryController.getDocumentsData().addDelivery_Document(document13);

        HashMap<Item, Integer> itemIntegerHashMap14 = new HashMap<>();
        itemIntegerHashMap14.put(deliveryController.getItemsData().getItem(13), 20);
        itemIntegerHashMap14.put(deliveryController.getItemsData().getItem(14), 5);
        itemIntegerHashMap14.put(deliveryController.getItemsData().getItem(15), 40);
        itemIntegerHashMap14.put(deliveryController.getItemsData().getItem(16), 16);
        Delivery_Document document14 = new Delivery_Document((Store) locationController.getLocation(4), 14, (Supplier) locationController.getLocation(7), itemIntegerHashMap14);
        deliveryController.getDocumentsData().addDelivery_Document(document14);
        return deliveryController;
    }

    public static TransportController createTransportsData
            (TransportController transportController, Delivery_DocumentsController deliveryController, TruckController truckController, DriverController driverController) {

        List <Delivery_Document> deliveryDocuments1 = new ArrayList<>();
        Transport transport11 = new Transport(1,truckController.getTruck(1),driverController.getDriver(4),deliveryDocuments1,"");
        transport11.addDeliveryDocument(deliveryController.getDelivery_Document(11));
        transport11.addDeliveryDocument(deliveryController.getDelivery_Document(12));
        transportController.getTransportsData().addTransport(transport11);

        List <Delivery_Document> deliveryDocuments2 = new ArrayList<>();
        Transport transport12 = new Transport(2,truckController.getTruck(7),driverController.getDriver(8),deliveryDocuments2,"");
        transport12.addDeliveryDocument(deliveryController.getDelivery_Document(1));
        transport12.addDeliveryDocument(deliveryController.getDelivery_Document(2));
        transportController.getTransportsData().addTransport(transport12);
        return transportController;
    }

    }

