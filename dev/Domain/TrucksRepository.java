package Domain;

import java.util.*;

public class TrucksRepository implements IRepository<Truck> {
    private HashMap<Integer, Truck> trucks;

    public TrucksRepository() { this.trucks = new HashMap<>();}

    public HashMap<Integer, Truck> getTrucks() { return trucks; }

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

    @Override
    public void add(Truck truck) {
        if (!trucks.containsKey(truck.getTruckID())){
            trucks.put(truck.getTruckID(), truck);
        }
    }

    @Override
    public void remove(int truckID) {
        trucks.remove(truckID);
    }

    @Override
    public void update(Truck truck) {
        if (trucks.containsKey(truck.getTruckID())) {
            trucks.replace(truck.getTruckID(), truck);
        }
    }

    @Override
    public Truck get(int id) {
        if (trucks.containsKey(id)) {
            return trucks.get(id);
        }
        return null;
    }

    @Override
    public List<Truck> getAll() {
        return new ArrayList(this.trucks.values());
    }
}
