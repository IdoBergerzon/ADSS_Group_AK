package Domain.Transport;

import Domain.HR.Role;
import Domain.HR.RoleRepository;

import java.time.LocalDate;
import java.util.Date;
import java.util.Scanner;

public class DriverController {
    private DriversRepository driversRepository;
    private RoleRepository roleRepository;

    public DriverController() {
        this.driversRepository = new DriversRepository();
    }

    public DriversRepository getDriversData() {
        return driversRepository;
    }

    public void addDriver(int driverID, String name, int monthly_wage, int hourly_wage, Date start_date, Integer direct_manager_ID, Role role, int branch_id, String departement, String bank_details, int licenseMaxWeight) {
        Driver newDriver = new Driver(driverID,name,monthly_wage,hourly_wage,start_date,direct_manager_ID,role,branch_id,departement,bank_details, licenseMaxWeight);
        if (!driversRepository.getAll().containsKey(driverID)) {
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
            System.out.println("Enter Monthly Wage:\n");
            int monthlyWage = scanner.nextInt();
            System.out.print("Enter Hourly Wage:\n");
            int hourlyWage = scanner.nextInt();
            Date startDate = new Date();
            System.out.print("Enter Direct Manager ID:\n");
            int direct_manager_ID = scanner.nextInt();
            System.out.print("Enter Role ID:\n");
            int roleID = scanner.nextInt();
            System.out.print("Enter Branch ID:\n");
            int branchID = scanner.nextInt();
            System.out.print("Enter Departement:\n");
            String departement = scanner.nextLine();
            System.out.print("Enter Bank Details:\n");
            String bank_details = scanner.nextLine();
            this.addDriver(driverID, driverName ,monthlyWage , hourlyWage, startDate, direct_manager_ID, roleRepository.get(roleID), branchID, departement, bank_details, licenseNumber);
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
        return driversRepository.get(driverID);
    }

    public void printAllAvailableDrivers(double weight) {
        System.out.println("All Available Drivers:");
        for (Driver driver: driversRepository.getAll().values()) {
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
        if (this.getDriversData().getAll().isEmpty())
            System.out.println("There are no drivers in the system.\n");
        else
            System.out.println(this.getDriversData().toString() + "\n");
    }
}