package Domain;

import Data.DriversData;

public class DriverController {
    private DriversData driversData;

    public DriverController() {
        this.driversData = new DriversData();
    }

    public DriversData getDriversData() {
        return driversData;
    }

    public void addDriver(int driverID, String driverName, int license) {
        Driver newDriver = new Driver(driverID, driverName, license);
        if (!driversData.getDrivers().contains(newDriver)) {
            driversData.addDriver(newDriver);
            System.out.println("Driver added successfully: " + newDriver);
        } else {
            System.out.println("Driver with ID " + driverID + " already exists.");
        }
    }

    public Driver getDriver(int driverID) {
        for (Driver driver : driversData.getDrivers()) {
            if (driver.getDriverID() == driverID) {
                return driver;
            }
        }
        return null;
    }

        public void printAllAvailableDrivers(double weight) {
        System.out.println("All Available Drivers:");
        for (Driver driver : driversData.getDrivers()) {
            if (driver.isAvailable() && driver.getLicenseMaxWeight() >= weight) {
                System.out.println(driver);
            }
        }
    }
}


