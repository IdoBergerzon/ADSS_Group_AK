package Domain;

import java.util.*;

public class TrucksRepository implements IRepository {
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
    public void add(Object o) {
        if (o instanceof Truck) {
            Truck truck = (Truck) o;
            trucks.put(truck.getTruckID(), truck);
        }

    }

    @Override
    public void remove(Object o) {
        if (o instanceof Truck) {
            Truck truck = (Truck) o;
            trucks.remove(truck.getTruckID());
        }
    }

    @Override
    public void update(Object o) {
        if (o instanceof Truck) {
            Truck truck = (Truck) o;
            if (trucks.containsKey(((Truck) o).getTruckID())) {
                trucks.remove(truck.getTruckID());
                trucks.put(((Truck) o).getTruckID(), (Truck) o);
            }
        }
    }

    @Override
    public Object get(int id) {
        if (trucks.containsKey(id)) {
            return trucks.get(id);
        }
        return null;
    }
}
