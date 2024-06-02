package Presentation;
import Data.TrucksData;
import Domain.*;
import Domain.TransportController;
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





                //Update
                case 2:

                    break;

                //Display
                case 3:
                    int display;
                    //Display Menu
                    System.out.println("Enter display number:");
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

                        case 4:
                            System.out.println("Insert delivery documents ID:");
                            int deliveryDocumentsID = scanner.nextInt();
                            if (deliveryController.getDelivery_Document(deliveryDocumentsID) == null)
                                System.out.println("Delivery Document does not exist.");
                            else
                                System.out.println(deliveryController.getDelivery_Document(deliveryDocumentsID));
                            break;

                        case 5:
                            System.out.println("Insert location documents ID:");
                            int locationDocumentsID = scanner.nextInt();
                            break;


                        default: // Invalid choice in the sub-menu
                            System.out.println("Invalid choice. Returning to the main menu.");
                            break;
                    }
                    break;

                //Execute Transport
                case 4:

                    break;

                //Reset Data Bases
                case 5:

                    break;

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

