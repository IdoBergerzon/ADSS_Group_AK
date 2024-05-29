package Domain;

import java.util.Objects;

public class Main {
    public static void main(String[] args) {
        DriverController controller = new DriverController();

        // Create some licenses
        License license1 = new License("B", 3500);
        License license2 = new License("C", 7500);

        // Add licenses to the license data
        controller.addLicense(license1.getTitle(), license1.getMaxWeight());
        controller.addLicense(license2.getTitle(), license2.getMaxWeight());

        // Add drivers
        controller.addDriver(1, "John Doe", true, license1);
        controller.addDriver(2, "Jane Smith", true, null);

        // Add a new license to an existing driver
        controller.addLicenseToDriver(2, license2);

        // Print all licenses
        controller.printAllLicenses();

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
