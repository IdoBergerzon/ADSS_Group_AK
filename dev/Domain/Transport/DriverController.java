package Domain.Transport;

import Domain.HR.*;

import java.time.LocalDate;
import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.*;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class DriverController {
    private DriversRepository driversRepository;
    private RoleRepository roleRepository;
    private ShiftRepository shiftRepository;
    private WorkerRepository workerRepository;

    public DriverController() {
        this.driversRepository = new DriversRepository();
        this.roleRepository = RoleRepository.getInstance();
        this.shiftRepository = ShiftRepository.getInstance();
        this.workerRepository=WorkerRepository.getInstance();
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

            Role role=roleRepository.get(roleID);
            Worker newone=new Worker(driverID,driverName,monthlyWage,hourlyWage,new Date(),direct_manager_ID,role,branchID,departement,bank_details);
            workerRepository.add(newone);
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

    public void printAllAvailableDrivers(double weight, int branch_id) {
        List<Driver> in_shift = getShiftedDrivers();
        for (Driver driver: driversRepository.getAll().values()) {
            if (driver.isAvailable() && driver.getLicenseMaxWeight() >= weight && in_shift.contains(driver)) {
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

    public List<Driver>  getShiftedDrivers(){
        int week = Week.getWeek();
        // Get the current date
        LocalDateTime currentDateTime = LocalDateTime.now();
        // Extract the time part from the current date and time
        LocalTime currentTime = currentDateTime.toLocalTime();
        DayOfWeek dayOfWeek = currentDateTime.getDayOfWeek();
        int dayOfWeekValue = dayOfWeek.getValue();
        LocalTime threePM = LocalTime.of(15, 0);
        int shift_type = currentTime.isAfter(threePM) ? 1 : 0;
        Shift shift = shiftRepository.getShift(2,dayOfWeekValue,shift_type,week);

        System.out.println("All Available Drivers:");
        List<Driver> in_shift = new ArrayList<Driver>();
        for(Worker worker: shift.getShift_workers()){
            if(worker instanceof Driver){
                in_shift.add((Driver) worker);
            }
        }
        return in_shift;
    }
}