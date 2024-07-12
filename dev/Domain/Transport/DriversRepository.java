package Domain.Transport;

import DAL.Transport.DriverDAO;

import java.sql.SQLException;
import java.util.HashMap;

public class DriversRepository implements IRepository<Driver> {
    private HashMap<Integer,Driver> drivers;
    private final DriverDAO driverDAO = new DriverDAO();

    public DriversRepository() {
        this.drivers = new HashMap<>();
    }

    public void setDrivers(HashMap<Integer,Driver> drivers) {
        this.drivers = drivers;
    }

    public HashMap<Integer,Driver> getAll() {
        HashMap<Integer,Driver> allDrivers = new HashMap<>(this.drivers);
        try {
            if (driverDAO != null) {
                allDrivers = driverDAO.getAll();
                drivers = allDrivers;
                return drivers;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return drivers;
    }

    @Override
    public String toString() {
        StringBuilder driversStr = new StringBuilder();
        driversStr.append("All Drivers:\n");
        for (int i = 0; i < drivers.size(); i++) {
            driversStr.append(drivers.get(i) + "\n");
        }
        return driversStr.toString();
    }

    @Override
    public void remove(int id) {
        try {
            if (!drivers.containsKey(id) && driverDAO.get(id) != null) {
                driverDAO.remove(id);
            } else if (drivers.containsKey(id) && driverDAO.get(id) != null) {
                drivers.remove(id);
                driverDAO.remove(id);
            }
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(Driver driver) {
        int driverID = driver.getDriverID();
        try {
            if (drivers.containsKey(driverID)) {
                drivers.replace(driverID, driver);
                driverDAO.update(driver);
            } else if (!drivers.containsKey(driverID) && driverDAO.get(driverID) != null) {
                driverDAO.update(driver);
                drivers.put(driverID, driver);
            }
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void add(Driver driver) {
        int driverID = driver.getDriverID();
        try {
            if (!drivers.containsKey(driverID) && driverDAO.get(driverID) != null) {
                drivers.put(driver.getDriverID(), driver);
            } else if (!drivers.containsKey(driverID) && driverDAO.get(driverID) == null) {
                drivers.put(driverID, driver);
                driverDAO.add(driver);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Driver get(int id) {
        if (drivers.containsKey(id)) {
            return drivers.get(id);
        }
        else {
            try {
                if (!drivers.containsKey(id) && driverDAO.get(id) != null) {
                    drivers.put(id,driverDAO.get(id));
                    return drivers.get(id);
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        return null;
    }
}
