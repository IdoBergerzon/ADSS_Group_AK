package Domain;

import DataAccessObject.TruckDAO;

import java.sql.SQLException;
import java.util.*;

public class TrucksRepository implements IRepository<Truck> {
    private HashMap<Integer, Truck> trucks;
    private final TruckDAO truckDAO = new TruckDAO();

    public TrucksRepository() { this.trucks = new HashMap<>();}
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
        int truckID = truck.getTruckID();
        try {
            if (!trucks.containsKey(truckID) && truckDAO.get(truckID) == null){
                trucks.put(truckID, truck);
                truckDAO.add(truck);
            } else if (!trucks.containsKey(truckID) && truckDAO.get(truckID) != null) {
                trucks.put(truckID, truck);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void remove(int truckID) {
        try {
            if (trucks.containsKey(truckID)) {
                trucks.remove(truckID);
                truckDAO.remove(truckID);
            } else if (!trucks.containsKey(truckID) && truckDAO.get(truckID) != null) {
                truckDAO.remove(truckID);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        trucks.remove(truckID);
    }

    @Override
    public void update(Truck truck) {
        int truckID = truck.getTruckID();
        try {
            if (trucks.containsKey(truckID)) {
                trucks.replace(truck.getTruckID(), truck);
                truckDAO.update(truck);
            } else if (!trucks.containsKey(truckID) && truckDAO.get(truckID)!=null) {
                truckDAO.update(truck);
                trucks.put(truck.getTruckID(), truck);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Truck get(int id) {
        try {
            if (trucks.containsKey(id)) {
                return trucks.get(id);
            } else if (!trucks.containsKey(id) && truckDAO.get(id) != null) {
                trucks.put(id, truckDAO.get(id));
                return trucks.get(id);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    @Override
    public HashMap<Integer, Truck> getAll() {
        HashMap<Integer, Truck> allTrucks = new HashMap<>();
        try {
            if (truckDAO != null) {
                allTrucks = truckDAO.getAll();
                trucks = allTrucks;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return allTrucks;
    }
}
