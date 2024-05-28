package Domain;

import Data.Licenses;
import Data.Trucks;
import Domain.TruckController;

public class Main {
    public static void main(String[] args) {
//Trucks
        TruckController truckController = new TruckController();

        truckController.addNewTruck(1, "Toyota", 5000.0, 10000.0);
        truckController.addNewTruck(2, "BMW", 6000.0, 12000.0);

        truckController.showTrucks(1);
        truckController.showTrucks(2);
    }
}