package Domain;

import Data.DriversData;
import Data.LicensesData;

import java.util.List;

public class DriverController {
    private LicensesData licensesData;

    public DriverController() {
        this.licensesData = new LicensesData();
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
}
