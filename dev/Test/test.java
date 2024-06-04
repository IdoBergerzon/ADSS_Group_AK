package Test;
import Domain.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;
import Domain.TransportController;

public class test {
    TruckController truckController;


    void add_Trunck() {
        truckController = new TruckController();
        truckController.addNewTruck(1, "Toyota",1500,1000);

    }
}