package Domain;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class DriversRepository<T> implements IRepository<T> {
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
    public void update(T t) {
        if (t instanceof Driver) {
            Driver driver = (Driver) t;
            if (drivers.containsKey(driver.getDriverID())) {
                drivers.replace(driver.getDriverID(), driver);
            }
        }
    }

    @Override
    public void add(T t) {
        if (t instanceof Driver){
            Driver driver = (Driver) t;
            drivers.put(driver.getDriverID(),driver);
        }
    }


    @Override
    public T get(int id) {
        if (drivers.containsKey(id)) {
            return (T) drivers.get(id);
        }
        return null;
    }
}
