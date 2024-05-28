package Domain;

import Data.Drivers;
import Data.Licenses;

import java.util.ArrayList;
import java.util.List;

public class DriverController {
    private Licenses licensesData;

    public DriverController() {
        this.licensesData = new Licenses();
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

//    public void addLicenseToDriver(Driver driver, License license, Drivers drivers) {
//        List<License> driverLicenses = driver.getLicenses();
//        if (driverLicenses == null) {
//            driverLicenses = new ArrayList<>();
//            driver.setLicenses(driverLicenses);
//        }
//        driverLicenses.add(license);
//        drivers.getDrivers().put(driver, driverLicenses);
//        System.out.println("License added successfully to driver " + driver.getDriverName());
//    }

    public void printAllDriverLicenses(Drivers drivers) {
        for (Driver driver : drivers.getDrivers().keySet()) {
            System.out.println("Driver: " + driver.getDriverName());
            List<License> licenses = drivers.getDrivers().get(driver);
            for (License license : licenses) {
                System.out.println(license);
            }
        }
    }
}
