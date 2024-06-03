package Presentation;

import Domain.*;

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
            System.out.println("4. Execute Transport");
            System.out.println("5. Reset Data Bases");
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
                    System.out.println("4. Add Transport");

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
                                System.out.print("Insert truck Type: ");
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
                            System.out.print("Enter Driver ID: ");
                            int driverID = scanner.nextInt();
                            if (driverController.getDriver(driverID) != null) {
                                System.out.print("The Driver already exists in the system");
                            }
                            else {
                            System.out.print("Enter Driver Name: ");
                            String driverName = scanner.nextLine();
                            System.out.print("Enter the driver's license number");
                            int licenseNumber = scanner.nextInt();
                                driverController.addDriver(driverID, driverName, licenseNumber);
                                break;
                            }
                        case 3:
                            int locationChoice;
                            System.out.println("1. Add Supplier");
                            System.out.println("2. Add Store");
                            System.out.print("Enter your choice: ");
                            locationChoice = scanner.nextInt();
                            scanner.nextLine(); // Consume newline

                            if (locationChoice == 1 || locationChoice == 2) {
                                System.out.print("Enter Location ID: ");
                                int locationID = scanner.nextInt();
                                scanner.nextLine(); // Consume newline

                                System.out.print("Enter Address details: ");
                                String full_address = scanner.nextLine();

                                System.out.print("Enter address_code: ");
                                int address_code = scanner.nextInt();
                                scanner.nextLine(); // Consume newline

                                Address address = new Address(full_address, address_code);

                                System.out.print("Enter contact: ");
                                String contact = scanner.nextLine();

                                System.out.print("Enter phone: ");
                                String phone = scanner.nextLine();

                                String l_type = (locationChoice == 1) ? "Supplier" : "Store";
                                locationController.addLocation(locationID, address, contact, phone, l_type);
                                System.out.println(l_type + " added successfully.");
                            } else {
                                System.out.println("Invalid choice for the given location type.");
                            }
                            break;
                            case 4:



                        default:
                            System.out.println("Invalid choice. Please try again.");
                            break;
                    }





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
                                    System.out.println("Insert item ID to add");
                                    int newitemID = scanner.nextInt();
                                    System.out.println("Insert item name");
                                    String itemName = scanner.next();
                                    System.out.println("Insert item weight");
                                    int itemWeight = scanner.nextInt();
                                    System.out.println("Insert amount");
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
                                    System.out.println("Insert item ID to remove");
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
                            }
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



                        default: // Invalid choice in the sub-menu
                            System.out.println("Invalid choice. Returning to the main menu.");
                            break;
                    }
                    break;

/************** Execute Transport *******************************/

                //Execute Transport
                case 4:

                    break;

/************** Reset Data Base *******************************/

                //Reset Data Bases
                case 5:

                    break;

/************** Exit *******************************/

                //Exit
                case 6:

                    break;
            }
        }
        while (choice != 6);
        scanner.close();







        DriverController controller = new DriverController();




        // Print all drivers and their licenses
        controller.printAllDrivers();

        // Print initial driver availability
        System.out.println("Initial driver availability:");
        controller.printAllDrivers();

        // Change availability of a driver
        controller.changeDriverAvailability(1, false);
        System.out.println("Driver availability changed for driver 1.");

        // Print updated driver availability
        System.out.println("Updated driver availability:");
        controller.printAllDrivers();


        }
    }

