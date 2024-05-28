package Main;

import Data.Drivers;
import Data.Licenses;
import Domain.Driver;
import Domain.DriverController;
import Domain.License;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        DriverController driverController = new DriverController();

        // Add licenses using the addLicense method in DriverController
        driverController.addLicense("Car License", 3000);
        driverController.addLicense("Truck License", 7000);
        driverController.addLicense("Motorcycle License", 500);



        driverController.printAllLicenses();
    }
}
