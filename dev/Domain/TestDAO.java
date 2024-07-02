package Domain;

import DataAccessObject.DriverDAO;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class TestDAO {
    public static void main(String[] args) {
        DriverDAO driverDAO = new DriverDAO();

        Driver driver1 = new Driver(1, "John Doe", 5000);
        Driver driver2 = new Driver(2, "Jane Smith", 7000);
        Driver driver3 = new Driver(3, "Tamir Cohen", 8000);

        try {
            driverDAO.add(driver1);
            driverDAO.add(driver2);
            driverDAO.add(driver3);
            Driver driver = driverDAO.get(1);
            System.out.println(driver);

            driver.setAvailable(false);
            driverDAO.update(driver);

            Driver updatedDriver = driverDAO.get(1);
            System.out.println(updatedDriver);

            HashMap<Integer,Driver> driverHashMap = driverDAO.getAll();
            List<Driver> drivers = new ArrayList<Driver>(driverHashMap.values());
            for (Driver d : drivers) {
                System.out.println(d);
            }

            driverDAO.remove(driver2);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
