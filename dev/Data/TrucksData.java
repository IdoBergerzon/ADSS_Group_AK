package Data;

import Domain.Truck;

import java.util.HashMap;

public class TrucksData {
    private HashMap<Integer, Truck> trucks;

    public TrucksData() { this.trucks = new HashMap<>();}
    public HashMap<Integer, Truck> getTrucks() { return trucks; }
    public void addTruck(Truck truck) {
        trucks.put(truck.getTruckID(), truck);
    }

}
