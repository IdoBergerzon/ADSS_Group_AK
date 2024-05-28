package Domain;

import Data.Licenses;

public class Main {
    public static void main(String[] args) {
        Licenses licenses = new Licenses();
// Add licenses to the list
        DriverController.addLicense(new License("License 1", 100.0), licenses);
        DriverController.addLicense(new License("License 2", 200.0), licenses);
// Print all licenses
        DriverController.printAllLicenses(licenses);

    }
}