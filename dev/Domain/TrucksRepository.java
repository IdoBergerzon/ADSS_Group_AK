package Domain;

import java.util.HashMap;

public class TrucksRepository {
    private HashMap<Integer, Truck> trucks;

    public TrucksRepository() { this.trucks = new HashMap<>();}
    public HashMap<Integer, Truck> getTrucks() { return trucks; }
    public void addTruck(Truck truck) {
        trucks.put(truck.getTruckID(), truck);
        System.out.println("Truck added successfully: " + truck.getTruckID() + "\n");
    }
    public void removeTruck(Truck truck) {
        trucks.remove(truck.getTruckID());
    }

    public void setTrucks(HashMap<Integer, Truck> trucks) {
        this.trucks = trucks;
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
