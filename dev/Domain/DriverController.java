package Domain;

import Data.Licenses;

public class DriverController {

    public static void addLicense(License license, Licenses licenses) {
        licenses.getLicenses().add(license);
        System.out.println("License added successfully.");

    }

    public static void printAllLicenses(Licenses licenses) {
        System.out.println("All Licenses:");
        for (License license : licenses.getLicenses()) {
            System.out.println(license); // This line prints the details of each license
        }
    }
}


