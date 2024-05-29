package Domain;

import Data.DriversData;

public class DriverController {
    private DriversData driversData;

    public DriverController() {
        this.driversData = new DriversData();
    }

    public void addDriver(int driverID, String driverName, boolean available, int license) {
        Driver newDriver = new Driver(driverID, driverName, available, license);
        if (!driversData.getDrivers().contains(newDriver)) {
            driversData.addDriver(newDriver);
            System.out.println("Driver added successfully: " + newDriver);
        } else {
            System.out.println("Driver with ID " + driverID + " already exists.");
        }
    }

        public void printAllDrivers() {
        System.out.println("All Drivers:");
        for (Driver driver : driversData.getDrivers()) {
            System.out.println(driver);
        }
    }


    public void addLicenseToDriver(int driverID, int license) {
        for (Driver driver : driversData.getDrivers()) {
            if (driver.getDriverID() == driverID) {
                driver.setLicenseMaxWeight(license);
                System.out.println("License added successfully to driver " + driver.getDriverName());
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


    }


