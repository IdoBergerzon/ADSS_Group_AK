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

        public void printAllDrivers() {
        System.out.println("All Drivers:");
        for (Driver driver : driversData.getDrivers()) {
            System.out.println(driver);
        }
    }


    public void updateLicenseToDriver(int driverID, int license) {
        for (Driver driver : driversData.getDrivers()) {
            if (driver.getDriverID() == driverID) {
                driver.setLicenseMaxWeight(license);
                System.out.println("License updated successfully to driver " + driver.getDriverName());
                return;
            }
        }
        System.out.println("Driver not found with ID: " + driverID);
    }

    public void changeDriverAvailability(int driverID, boolean available) {
        // Retrieve the driver from the DriversData
        Driver driver = driversData.getDriverById(driverID);

        if (driver != null) {
            // Update the availability of the driver
            driver.setAvailable(available);
            System.out.println("Driver availability changed successfully.");
        } else {
            System.out.println("Driver not found with ID: " + driverID);
        }
    }

    public void allDriversAvailable(){
        for (Driver driver : driversData.getDrivers()) {
            driver.setAvailable(true);
        }
    }


}

