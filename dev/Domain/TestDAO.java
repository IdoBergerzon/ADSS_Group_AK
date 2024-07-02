package Domain;

import DataAccessObject.ALocationDAO;
import DataAccessObject.DriverDAO;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class TestDAO {
    public static void main(String[] args) {
        DriverDAO driverDAO = new DriverDAO();
        ALocationDAO aLocationDAO = new ALocationDAO();

        // Create sample Driver objects
        Driver driver1 = new Driver(1, "John Doe", 5000);
        Driver driver2 = new Driver(2, "Jane Smith", 7000);
        Driver driver3 = new Driver(3, "Tamir Cohen", 8000);

        // Create sample Address objects
        Address address1 = new Address("123 Main St", 10001, 50);
        Address address2 = new Address("456 Elm St", 20002, 75);

        // Create sample Store and Supplier objects
        Store store1 = new Store(1, address1, "Store Contact 1", "111-1111");
        Supplier supplier1 = new Supplier(2, address2, "Supplier Contact 1", "222-2222");

        try {
            // Test DriverDAO operations
            driverDAO.add(driver1);
            driverDAO.add(driver2);
            driverDAO.add(driver3);
            Driver driver = driverDAO.get(1);
            System.out.println("Driver with ID 1: " + driver);

            driver.setAvailable(false);
            driverDAO.update(driver);

            Driver updatedDriver = driverDAO.get(1);
            System.out.println("Updated Driver with ID 1: " + updatedDriver);

            HashMap<Integer, Driver> driverHashMap = driverDAO.getAll();
            List<Driver> drivers = new ArrayList<>(driverHashMap.values());
            System.out.println("All Drivers:");
            for (Driver d : drivers) {
                System.out.println(d);
            }

            driverDAO.remove(driver2);

            // Test ALocationDAO operations
            aLocationDAO.add(store1);
            aLocationDAO.add(supplier1);

            ALocation retrievedStore = aLocationDAO.get(1);
            System.out.println("Store with ID 1: " + retrievedStore);

            supplier1.setContact("Updated Supplier Contact");
            supplier1.setPhone("333-3333");
            aLocationDAO.update(supplier1);

            ALocation updatedSupplier = aLocationDAO.get(2);
            System.out.println("Updated Supplier with ID 2: " + updatedSupplier);

            HashMap<Integer, ALocation> locationMap = aLocationDAO.getAll();
            System.out.println("All Locations:");
            for (ALocation location : locationMap.values()) {
                System.out.println(location);
            }

            aLocationDAO.remove(store1);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
