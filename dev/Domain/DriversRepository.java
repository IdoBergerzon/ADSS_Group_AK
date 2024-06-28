package Domain;

import java.util.HashSet;

public class DriversRepository {
    private HashSet<Driver> drivers;

    public DriversRepository() {
        this.drivers = new HashSet<>();
    }

    public void setDrivers(HashSet<Driver> drivers) {
        this.drivers = drivers;
    }

    public HashSet<Driver> getDrivers() {
        return drivers;
    }

    public void addDriver(Driver driver) {
        this.drivers.add(driver);
    }


    public Driver getDriverById(int driverID) {
        for (Driver driver : getDrivers()) {
            if (driver.getDriverID() == driverID) {
                return driver;
            }
        }
        return null; // Return null if driver with given ID is not found
    }

    @Override
    public String toString() {
        StringBuilder driversStr = new StringBuilder();
        driversStr.append("All Drivers:\n");
        for (Driver driver : getDrivers()) {
            driversStr.append(driver + "\n");
        }
        return driversStr.toString();
    }
}
