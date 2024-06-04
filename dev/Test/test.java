package Test;
import Domain.*;

import Domain.TransportController;

public class test {
    TruckController truckController;
    TransportController transportController;
    DriverController driverController;
    Delivery_DocumentsController deliveryController;



    void add_Trunck() {
        truckController = new TruckController();
        truckController.addNewTruck(1, "Toyota",1500,1000);

    }

    void add_Transport() {

    }
}