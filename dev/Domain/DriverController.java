package Domain;

import java.util.List;
import java.util.Scanner;

public class DriverController {
    private DriversRepository driversRepository;

    public DriverController() {
        this.driversRepository = new DriversRepository();
    }

    public DriversRepository getDriversData() {
        return driversRepository;
    }

    public void addDriver(int driverID, String driverName, int license) {
        Driver newDriver = new Driver(driverID, driverName, license);
        if (!driversRepository.getDrivers().containsKey(driverID)) {
            driversRepository.add(newDriver);
            System.out.println("Driver added successfully: " + newDriver);
        } else {
            System.out.println("Driver with ID " + driverID + " already exists.");
        }
    }

    public boolean createDriver() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter Driver ID:\n");
        int driverID = scanner.nextInt();
        scanner.nextLine();
        if (this.getDriver(driverID) != null) {
            System.out.print("The Driver already exists in the system\n");
            return false;
        } else {
            System.out.print("Enter Driver Name:\n");
            String driverName = scanner.nextLine();
            System.out.print("Enter max weight license:\n");
            int licenseNumber = scanner.nextInt();
            this.addDriver(driverID, driverName, licenseNumber);
        }
        return true;
    }

    public void updateDriverLicense(int driverID) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Insert new max weight license (int):");
        int license = scanner.nextInt();
        this.getDriver(driverID).setLicenseMaxWeight(license);
        System.out.println("Driver's license were updated.\n" + this.getDriver(driverID) + "\n");
    }

    public Driver getDriver(int driverID) {
        return (Driver) driversRepository.get(driverID);
    }

    public void printAllAvailableDrivers(double weight) {
        System.out.println("All Available Drivers:");
        List<Driver> availableDrivers = driversRepository.getAll();
        for (int i = 0; i < availableDrivers.size(); i++) {
            Driver driver = availableDrivers.get(i);
            if (driver.isAvailable() && driver.getLicenseMaxWeight() >= weight) {
                System.out.println(driver);
            }
        }
    }

    public void displayDriver(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Insert driver ID:");
        int driverID = scanner.nextInt();
        scanner.nextLine();
        if (this.getDriver(driverID) == null) {
            System.out.println("Driver does not exist.\n");
        } else
            System.out.println(this.getDriver(driverID));
    }

    public void displayAllDrivers() {
        Scanner scanner = new Scanner(System.in);
        if (this.getDriversData().getDrivers().isEmpty())
            System.out.println("There are no drivers in the system.\n");
        else
            System.out.println(this.getDriversData().toString() + "\n");
    }
}


