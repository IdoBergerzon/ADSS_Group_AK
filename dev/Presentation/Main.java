package Presentation;

import Domain.Delivery_DocumentsController;
import Domain.DriverController;
import Domain.LocationController;
import Domain.TransportController;
import Domain.TruckController;

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

                    break;

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
