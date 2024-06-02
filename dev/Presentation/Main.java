package Presentation;

import Domain.DriverController;
import Domain.License;

public class Main {
    public static void main(String[] args) {




        DriverController controller = new DriverController();

        // Create some licenses
        License license1 = new License("B", 3500);
        License license2 = new License("C", 7500);



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
