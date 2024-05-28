package Main;

import Data.Drivers;
import Data.Licenses;
import Data.Trucks;
import Domain.TruckController;
import Domain.Driver;
import Domain.DriverController;
import Domain.License;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
//Trucks
        TruckController truckController = new TruckController();
        DriverController driverController = new DriverController();

        // Add licenses using the addLicense method in DriverController
        driverController.addLicense("Car License", 3000);
        driverController.addLicense("Truck License", 7000);
        driverController.addLicense("Motorcycle License", 500);



        truckController.addNewTruck(1, "Toyota", 5000.0, 10000.0);
        truckController.addNewTruck(2, "BMW", 6000.0, 12000.0);

        truckController.showTrucks(1);
        truckController.showTrucks(2);
        driverController.printAllLicenses();
    }
}
