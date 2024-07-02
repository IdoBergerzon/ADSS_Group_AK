package Domain;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class DriversRepository implements IRepository<Driver> {
    private HashMap<Integer,Driver> drivers;

    public DriversRepository() {
        this.drivers = new HashMap<>();
    }

    public void setDrivers(HashMap<Integer,Driver> drivers) {
        this.drivers = drivers;
    }

    public HashMap<Integer,Driver> getDrivers() {
        return drivers;
    }

    public List getAll() {
        return new ArrayList(drivers.values());
    }

    @Override
    public String toString() {
        StringBuilder driversStr = new StringBuilder();
        driversStr.append("All Drivers:\n");
        for (Driver driver : getDrivers().values()) {
            driversStr.append(driver + "\n");
        }
        return driversStr.toString();
    }

    @Override
    public void remove(int id) {
        if (drivers.containsKey(id)) {
            drivers.remove(id);
        }
    }

    @Override
    public void update(Driver driver) {
        if (drivers.containsKey(driver.getDriverID())) {
            drivers.replace(driver.getDriverID(), driver);
        }
    }

    @Override
    public void add(Driver driver) {
        if (!drivers.containsKey(driver.getDriverID())){
            drivers.put(driver.getDriverID(),driver);
        }
    }

    @Override
    public Driver get(int id) {
        if (drivers.containsKey(id)) {
            return drivers.get(id);
        }
        return null;
    }
}
