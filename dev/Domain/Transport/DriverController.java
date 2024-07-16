package Domain.Transport;

import Domain.HR.*;

import java.time.LocalDate;
import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.*;
import java.time.LocalTime;
import java.util.*;

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
        Worker worker = new Worker(driverID,name,monthly_wage,hourly_wage,start_date,direct_manager_ID,role,branch_id,departement,bank_details);

        Driver newDriver = new Driver(driverID,name,monthly_wage,hourly_wage,start_date,direct_manager_ID,role,branch_id,departement,bank_details, licenseMaxWeight);

        if (!driversRepository.getAll().containsKey(driverID)) {
            driversRepository.add(newDriver);
            workerRepository.add(worker);
            System.out.println("Driver added successfully: " + newDriver);
        } else {
            System.out.println("Driver with ID " + driverID + " already exists.");
        }
    }

    public boolean createDriver() {
        Scanner scanner_int = new Scanner(System.in);
        Scanner scanner_str = new Scanner(System.in);
        System.out.print("Enter Driver ID:\n");
        int driverID = scanner_int.nextInt();
        //scanner.nextLine();
        if (this.workerRepository.get(driverID) != null) {
            System.out.print("The Driver already exists in the system\n");
            return false;
        } else {
            System.out.print("Enter Driver Name:\n");
            String driverName = scanner_str.nextLine();
            System.out.print("Enter max weight license:\n");
            int licenseNumber = scanner_int.nextInt();
            System.out.println("Enter Monthly Wage:\n");
            int monthlyWage = scanner_int.nextInt();
            System.out.print("Enter Hourly Wage:\n");
            int hourlyWage = scanner_int.nextInt();
            Date startDate = new Date();
            System.out.print("Enter Direct Manager ID:\n");
            int direct_manager_ID = scanner_int.nextInt();
            System.out.print("Enter Branch ID:\n");
            int branchID = scanner_int.nextInt();
            System.out.print("Enter Departement:\n");
            String departement = scanner_str.nextLine();
            System.out.print("Enter Bank Details:\n");
            String bank_details = scanner_str.nextLine();

            Role role=roleRepository.get(20);
            //Worker newone=new Worker(driverID,driverName,monthlyWage,hourlyWage,new Date(),direct_manager_ID,role,branchID,departement,bank_details);
            this.addDriver(driverID, driverName ,monthlyWage , hourlyWage, startDate, direct_manager_ID, role, branchID, departement, bank_details, licenseNumber);
            //workerRepository.add(newone);

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

    public List<Driver> getShiftedDrivers() {
        Map<String, Integer> dayNumbers = new HashMap<>();

        // Populate the map
        dayNumbers.put("SUNDAY", 0);
        dayNumbers.put("MONDAY", 1);
        dayNumbers.put("TUESDAY", 2);
        dayNumbers.put("WEDNESDAY", 3);
        dayNumbers.put("THURSDAY", 4);
        dayNumbers.put("FRIDAY", 5);
        dayNumbers.put("SATURDAY", 6);
         int week = shiftRepository.getMaxWeek();
        // Get the current date
        LocalDateTime currentDateTime = LocalDateTime.now();
        // Extract the time part from the current date and time
        LocalTime currentTime = currentDateTime.toLocalTime();
        String dayOfWeek = currentDateTime.getDayOfWeek().toString();
        int dayOfWeekValue = dayNumbers.get(dayOfWeek);

        LocalTime threePM = LocalTime.of(15, 0);
        int shift_type = currentTime.isAfter(threePM) ? 1 : 0;
        Shift shift = shiftRepository.getShift(2, dayOfWeekValue, shift_type, week);

        System.out.println("All Available Drivers:");
        List<Driver> in_shift = new ArrayList<Driver>();
        for (Worker worker : shift.getShift_workers()) {
            if (worker.getRoles()[0].getRoleID()==20) {
                in_shift.add((driversRepository.get(worker.getId())));
            }
        }
        return in_shift;
    }


}