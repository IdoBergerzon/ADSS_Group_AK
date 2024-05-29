package Main;

import Domain.TruckController;
import Domain.DriverController;

public class Main {
    public static void main(String[] args) {

        TruckController truckController = new TruckController();
        DriverController driverController = new DriverController();

        // Add licenses using the addLicense method in DriverController
        driverController.addLicense("Car License", 3000);
        driverController.addLicense("Truck License", 7000);
        driverController.addLicense("Motorcycle License", 500);
        driverController.printAllLicenses();



//Trucks
        truckController.addNewTruck(1, "Toyota", 5000.0, 10000.0);
        truckController.addNewTruck(2, "BMW", 6000.0, 12000.0);

        truckController.showTrucks(1);
        truckController.showTrucks(2);
    }
}
