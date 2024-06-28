package Domain;

import java.util.HashMap;

public class DriversRepository implements IRepository {
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
    public void add(Object o) {
        if (o instanceof Driver) {
            Driver driver = (Driver) o;
            drivers.put(((Driver)o).getDriverID(),driver);
        }
    }

    @Override
    public void remove(Object o) {
        if (o instanceof Driver) {
            Driver driver = (Driver) o;
            drivers.remove(driver.getDriverID());
        }
    }

    @Override
    public void update(Object o) {
        if (o instanceof Driver) {
            Driver driver = (Driver) o;
            if (drivers.containsKey(((Driver)o).getDriverID())) {
                drivers.remove(((Driver)o).getDriverID());
                drivers.put(((Driver)o).getDriverID(),driver);
            }
        }
    }

    @Override
    public Object get(int id) {
        if (drivers.containsKey(id)) {
            return drivers.get(id).getDriverID();
        }
        return null;
    }
}
