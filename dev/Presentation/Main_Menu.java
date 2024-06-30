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
                    HashMap<Integer,Driver> driverHashMap = readDriversFromCSV("dev/DataTable/drivers.csv");
                    driverController.getDriversData().setDrivers(driverHashMap);

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
                for (Object trans : transportController.getTransportsData().getTransports().values()) {
                    Transport trans1 = (Transport) trans;
                    if (!trans1.isFinished()) {
                        trans1.setFinished(true);
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

                        // Add Truck
                        case 1:
                            truckController.createTruck();
                            break;

                        // Add Driver
                        case 2:
                            driverController.createDriver();
                            break;

                        //Add supplier or Store
                        case 3:
                            locationController.createLocation();
                            break;

                        //Add Delivery document
                        case 4:
                            deliveryController.createDelivery_Document(locationController);
                            break;

                        //Add item
                        case 5:
                            deliveryController.createNewItem();
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
                                    driverController.updateDriverLicense(driverID);
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
                                    locationController.updateShipping_area(locationID);
                                    break;

                                //Update contact
                                case 2:
                                    locationController.updateContact(locationID);
                                    break;

                                //Update phone number
                                case 3:
                                    locationController.updatePhone(locationID);
                                    break;


                                default:
                                    System.out.println("Invalid choice. Please try again.\n");
                                    break;
                            }
                            break;

                        //Update transport
                        case 3:
                            transportController.updateTransport(truckController,driverController,deliveryController);
                            break;

                        //Update delivery document
                        case 4:
                            deliveryController.updateDeliveryDocument(deliveryController,locationController);
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
                            truckController.displayTruck();
                            break;

                        //Display driver details
                        case 2:
                            driverController.displayDriver();
                            break;

                        //Display transport
                        case 3:
                            transportController.displayTransport();
                            break;

                        //Display delivery documents
                        case 4:
                            deliveryController.displayDelivery_Document();
                            break;

                        //Display location details
                        case 5:
                            locationController.displayLocation();
                            break;

                        //Display all transports
                        case 6:
                            transportController.displayAllTransports();
                            break;

                        //Display all trucks
                        case 7:
                            truckController.displayAllTrucks();
                            break;

                        //Display all drivers
                        case 8:
                            driverController.displayAllDrivers();
                            break;


                        default:
                            System.out.println("Invalid choice. Please try again.\n");
                            break;
                    }
                    break;

/************** Start Transport *******************************/

                //Start Transport
                case 4:
                    transportController.executeTransport(driverController,locationController,deliveryController,truckController);
            break;

/************** Finish Transport *******************************/

            //Finish Transport
            case 5:
                transportController.finishTransport();
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
    public static HashMap<Integer,Driver> readDriversFromCSV(String csvFilePath) {
        HashMap<Integer,Driver> driversD = new HashMap<>();
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
                driversD.put(driverID,driver);
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
        itemIntegerHashMap1.put((Item) deliveryController.getItemsData().get(1), 10);
        itemIntegerHashMap1.put((Item) deliveryController.getItemsData().get(2), 20);
        itemIntegerHashMap1.put((Item) deliveryController.getItemsData().get(3), 30);
        Delivery_Document document1 = new Delivery_Document((Store) locationController.getLocation(2), 1, (Supplier) locationController.getLocation(1), itemIntegerHashMap1);
        deliveryController.getDocumentsRepository().add(document1);

        HashMap<Item, Integer> itemIntegerHashMap2 = new HashMap<>();
        itemIntegerHashMap2.put((Item) deliveryController.getItemsData().get(28), 10);
        itemIntegerHashMap2.put((Item) deliveryController.getItemsData().get(5), 25);
        itemIntegerHashMap2.put((Item) deliveryController.getItemsData().get(15), 15);
        Delivery_Document document2 = new Delivery_Document((Store) locationController.getLocation(4), 2, (Supplier) locationController.getLocation(1), itemIntegerHashMap2);
        deliveryController.getDocumentsRepository().add(document2);

        HashMap<Item, Integer> itemIntegerHashMap3 = new HashMap<>();
        itemIntegerHashMap3.put((Item) deliveryController.getItemsData().get(20), 50);
        itemIntegerHashMap3.put((Item) deliveryController.getItemsData().get(17), 25);
        itemIntegerHashMap3.put((Item) deliveryController.getItemsData().get(12), 10);
        Delivery_Document document3 = new Delivery_Document((Store) locationController.getLocation(2), 3, (Supplier) locationController.getLocation(3), itemIntegerHashMap3);
        deliveryController.getDocumentsRepository().add(document3);

        HashMap<Item, Integer> itemIntegerHashMap4 = new HashMap<>();
        itemIntegerHashMap4.put((Item) deliveryController.getItemsData().get(23), 20);
        itemIntegerHashMap4.put((Item) deliveryController.getItemsData().get(7), 5);
        itemIntegerHashMap4.put((Item) deliveryController.getItemsData().get(2), 10);
        itemIntegerHashMap4.put((Item) deliveryController.getItemsData().get(10), 16);
        Delivery_Document document4 = new Delivery_Document((Store) locationController.getLocation(4), 4, (Supplier) locationController.getLocation(7), itemIntegerHashMap4);
        deliveryController.getDocumentsRepository().add(document4);

        HashMap<Item, Integer> itemIntegerHashMap5 = new HashMap<>();
        itemIntegerHashMap5.put((Item) deliveryController.getItemsData().get(17), 10);
        itemIntegerHashMap5.put((Item) deliveryController.getItemsData().get(18), 50);
        itemIntegerHashMap5.put((Item) deliveryController.getItemsData().get(19), 20);
        itemIntegerHashMap5.put((Item) deliveryController.getItemsData().get(20), 35);
        Delivery_Document document5 = new Delivery_Document((Store) locationController.getLocation(2), 5, (Supplier) locationController.getLocation(9), itemIntegerHashMap5);
        deliveryController.getDocumentsRepository().add(document5);

        HashMap<Item, Integer> itemIntegerHashMap6 = new HashMap<>();
        itemIntegerHashMap6.put((Item) deliveryController.getItemsData().get(21), 15);
        itemIntegerHashMap6.put((Item) deliveryController.getItemsData().get(22), 25);
        itemIntegerHashMap6.put((Item) deliveryController.getItemsData().get(23), 35);
        itemIntegerHashMap6.put((Item) deliveryController.getItemsData().get(24), 45);
        Delivery_Document document6 = new Delivery_Document((Store) locationController.getLocation(6), 6, (Supplier) locationController.getLocation(5), itemIntegerHashMap6);
        deliveryController.getDocumentsRepository().add(document6);

        HashMap<Item, Integer> itemIntegerHashMap7 = new HashMap<>();
        itemIntegerHashMap7.put((Item) deliveryController.getItemsData().get(25), 50);
        itemIntegerHashMap7.put((Item) deliveryController.getItemsData().get(26), 40);
        itemIntegerHashMap7.put((Item) deliveryController.getItemsData().get(27), 30);
        itemIntegerHashMap7.put((Item) deliveryController.getItemsData().get(28), 20);
        Delivery_Document document7 = new Delivery_Document((Store) locationController.getLocation(8), 7, (Supplier) locationController.getLocation(1), itemIntegerHashMap7);
        deliveryController.getDocumentsRepository().add(document7);

        HashMap<Item, Integer> itemIntegerHashMap8 = new HashMap<>();
        itemIntegerHashMap8.put((Item) deliveryController.getItemsData().get(29), 10);
        itemIntegerHashMap8.put((Item) deliveryController.getItemsData().get(30), 50);
        itemIntegerHashMap8.put((Item) deliveryController.getItemsData().get(1), 20);
        itemIntegerHashMap8.put((Item) deliveryController.getItemsData().get(2), 35);
        Delivery_Document document8 = new Delivery_Document((Store) locationController.getLocation(8), 8, (Supplier) locationController.getLocation(3), itemIntegerHashMap8);
        deliveryController.getDocumentsRepository().add(document8);

        HashMap<Item, Integer> itemIntegerHashMap9 = new HashMap<>();
        itemIntegerHashMap9.put((Item) deliveryController.getItemsData().get(3), 15);
        itemIntegerHashMap9.put((Item) deliveryController.getItemsData().get(4), 25);
        itemIntegerHashMap9.put((Item) deliveryController.getItemsData().get(5), 35);
        itemIntegerHashMap9.put((Item) deliveryController.getItemsData().get(6), 45);
        Delivery_Document document9 = new Delivery_Document((Store) locationController.getLocation(2), 9, (Supplier) locationController.getLocation(7), itemIntegerHashMap9);
        deliveryController.getDocumentsRepository().add(document9);

        HashMap<Item, Integer> itemIntegerHashMap10 = new HashMap<>();
        itemIntegerHashMap10.put((Item) deliveryController.getItemsData().get(1), 30);
        itemIntegerHashMap10.put((Item) deliveryController.getItemsData().get(2), 25);
        itemIntegerHashMap10.put((Item) deliveryController.getItemsData().get(3), 20);
        itemIntegerHashMap10.put((Item) deliveryController.getItemsData().get(4), 15);
        Delivery_Document document10 = new Delivery_Document((Store) locationController.getLocation(4), 10, (Supplier) locationController.getLocation(5), itemIntegerHashMap10);
        deliveryController.getDocumentsRepository().add(document10);

        HashMap<Item, Integer> itemIntegerHashMap11 = new HashMap<>();
        itemIntegerHashMap11.put((Item) deliveryController.getItemsData().get(13), 20);
        itemIntegerHashMap11.put((Item) deliveryController.getItemsData().get(14), 5);
        itemIntegerHashMap11.put((Item) deliveryController.getItemsData().get(15), 40);
        itemIntegerHashMap11.put((Item) deliveryController.getItemsData().get(16), 16);
        Delivery_Document document11 = new Delivery_Document((Store) locationController.getLocation(4), 11, (Supplier) locationController.getLocation(7), itemIntegerHashMap11);
        deliveryController.getDocumentsRepository().add(document11);

        HashMap<Item, Integer> itemIntegerHashMap12 = new HashMap<>();
        itemIntegerHashMap12.put((Item) deliveryController.getItemsData().get(5), 35);
        itemIntegerHashMap12.put((Item) deliveryController.getItemsData().get(6), 10);
        itemIntegerHashMap12.put((Item) deliveryController.getItemsData().get(7), 50);
        itemIntegerHashMap12.put((Item) deliveryController.getItemsData().get(8), 20);
        Delivery_Document document12 = new Delivery_Document((Store) locationController.getLocation(2), 12, (Supplier) locationController.getLocation(9), itemIntegerHashMap12);
        deliveryController.getDocumentsRepository().add(document12);

        HashMap<Item, Integer> itemIntegerHashMap13 = new HashMap<>();
        itemIntegerHashMap13.put((Item) deliveryController.getItemsData().get(9), 15);
        itemIntegerHashMap13.put((Item) deliveryController.getItemsData().get(10), 30);
        itemIntegerHashMap13.put((Item) deliveryController.getItemsData().get(11), 25);
        itemIntegerHashMap13.put((Item) deliveryController.getItemsData().get(12), 10);
        Delivery_Document document13 = new Delivery_Document((Store) locationController.getLocation(6), 13, (Supplier) locationController.getLocation(9), itemIntegerHashMap13);
        deliveryController.getDocumentsRepository().add(document13);

        HashMap<Item, Integer> itemIntegerHashMap14 = new HashMap<>();
        itemIntegerHashMap14.put((Item) deliveryController.getItemsData().get(13), 20);
        itemIntegerHashMap14.put((Item) deliveryController.getItemsData().get(14), 5);
        itemIntegerHashMap14.put((Item) deliveryController.getItemsData().get(15), 40);
        itemIntegerHashMap14.put((Item) deliveryController.getItemsData().get(16), 16);
        Delivery_Document document14 = new Delivery_Document((Store) locationController.getLocation(4), 14, (Supplier) locationController.getLocation(7), itemIntegerHashMap14);
        deliveryController.getDocumentsRepository().add(document14);
        return deliveryController;
    }

    public static TransportController createTransportsData
            (TransportController transportController, Delivery_DocumentsController deliveryController, TruckController truckController, DriverController driverController) {

        List <Delivery_Document> deliveryDocuments1 = new ArrayList<>();
        Transport transport11 = new Transport(1,truckController.getTruck(1),driverController.getDriver(4),deliveryDocuments1,"");
        transport11.addDeliveryDocument(deliveryController.getDelivery_Document(11));
        transport11.addDeliveryDocument(deliveryController.getDelivery_Document(12));
        transportController.getTransportsData().add(transport11);

        List <Delivery_Document> deliveryDocuments2 = new ArrayList<>();
        Transport transport12 = new Transport(2,truckController.getTruck(7),driverController.getDriver(8),deliveryDocuments2,"");
        transport12.addDeliveryDocument(deliveryController.getDelivery_Document(1));
        transport12.addDeliveryDocument(deliveryController.getDelivery_Document(2));
        transportController.getTransportsData().add(transport12);
        return transportController;
    }

    }

