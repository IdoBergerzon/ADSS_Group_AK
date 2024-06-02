package Presentation;

import Domain.DriverController;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
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
