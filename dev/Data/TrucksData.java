package Data;

import Domain.Transport;
import Domain.Truck;

import java.util.HashMap;

public class TrucksData {
    private HashMap<Integer, Truck> trucks;

    public TrucksData() { this.trucks = new HashMap<>();}
    public HashMap<Integer, Truck> getTrucks() { return trucks; }
    public void addTruck(Truck truck) {
        trucks.put(truck.getTruckID(), truck);
    }
    public void removeTruck(Truck truck) {
        trucks.remove(truck.getTruckID());
    }

    @Override
    public String toString() {
        StringBuilder trucksStr = new StringBuilder();
        trucksStr.append("All Trucks:\n");
        for (Truck truck : trucks.values()) {
            trucksStr.append(truck + "\n");
        }
        return trucksStr.toString();
    }
}