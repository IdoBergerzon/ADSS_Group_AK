package Data;

import Domain.Driver;



import java.util.HashSet;


public class DriversData {
    private HashSet<Driver> drivers;

    public DriversData() {
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

}
