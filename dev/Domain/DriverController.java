package Domain;

import Data.DriversData;
import Data.LicensesData;
import Domain.Driver;

import java.util.List;

public class DriverController {
    private LicensesData licensesData;
    private DriversData driversData;

    public DriverController() {
        this.licensesData = new LicensesData();
        this.driversData = new DriversData();
    }

    public void addLicense(String title, double maxWeight) {
        License newLicense = new License(title, maxWeight);
        licensesData.addLicense(newLicense);
        System.out.println("License added successfully: " + newLicense);
    }

    public void printAllLicenses() {
        System.out.println("All Licenses:");
        for (License license : licensesData.getLicenses()) {
            System.out.println(license);
        }
    }

    public void addDriver(int driverID, String driverName, boolean available, License license) {
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


    public void addLicenseToDriver(int driverID, License license) {
        for (Driver driver : driversData.getDrivers()) {
            if (driver.getDriverID() == driverID) {
                driver.setLicense(license);
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


