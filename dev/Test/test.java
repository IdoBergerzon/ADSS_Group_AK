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
        truckController.addNewTruck(2, "M",700,800);
        truckController.addNewTruck(1, "Toyota",1500,1000);
        truckController.addNewTruck(2, "M",700,800);

    }

    void add_Driver() {
        driverController = new DriverController();
    }

    void add_DeliveryDocuments() {
        deliveryController = new Delivery_DocumentsController();
    }
    void add_Transport() {

    }
}