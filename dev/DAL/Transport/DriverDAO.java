package DAL.Transport;

import java.sql.SQLException;

import DAL.HR.WorkerDAOImpl;
import Domain.HR.Role;
import Domain.HR.Worker;
import Domain.Transport.Driver;
import com.fasterxml.jackson.databind.JsonNode;

import java.sql.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

public class DriverDAO implements IDAO<Driver>{
    private WorkerDAOImpl workerDao = new WorkerDAOImpl();

    public DriverDAO() {
        TransportTableCreator.createDriversTable();
    }

    @Override
    public void add(Driver driver) throws SQLException {
        if (!this.getAll().containsKey(driver.getId())) {
            String sql = "INSERT INTO drivers(driverID, driverName, available, licenseMaxWeight) VALUES(?, ?, ?, ?)";
            try (Connection connection = DataBase.connect();
                 PreparedStatement pstmt = connection.prepareStatement(sql)) {
                pstmt.setInt(1, driver.getId());
                pstmt.setString(2, driver.getName());
                pstmt.setBoolean(3, driver.isAvailable());
                pstmt.setInt(4, driver.getLicenseMaxWeight());
                pstmt.executeUpdate();
            }
        }
    }

    @Override
    public void remove(int driverID) throws SQLException {
        if (this.getAll().containsKey(driverID)) {
            String sql = "DELETE FROM drivers WHERE driverID = ?";
            try (Connection connection = DataBase.connect();
                 PreparedStatement pstmt = connection.prepareStatement(sql)) {
                pstmt.setInt(1, driverID);
                pstmt.executeUpdate();
            }
        }
    }

    @Override
    public void update(Driver driver) throws SQLException {
        if (this.getAll().containsKey(driver.getId())) {
            String sql = "UPDATE drivers SET driverName = ?, available = ?, licenseMaxWeight = ? WHERE driverID = ?";
            try (Connection connection = DataBase.connect();
                 PreparedStatement pstmt = connection.prepareStatement(sql)) {
                pstmt.setString(1, driver.getName());
                pstmt.setBoolean(2, driver.isAvailable());
                pstmt.setInt(3, driver.getLicenseMaxWeight());
                pstmt.setInt(4, driver.getId());
                pstmt.executeUpdate();
            }
        }
    }

    @Override
    public Driver get(int id) throws SQLException {
        // Check if the worker exists in workerDao first
        JsonNode worker = workerDao.search(id);
        if (worker == null) {
            return null; // Worker does not exist
        }

        // Check if the driver exists in the drivers table
        String checkSql = "SELECT COUNT(*) FROM drivers WHERE driverID = ?";
        String sql = "SELECT * FROM drivers WHERE driverID = ?";
        try (Connection connection = DataBase.connect();
             PreparedStatement checkStmt = connection.prepareStatement(checkSql);
             PreparedStatement pstmt = connection.prepareStatement(sql)) {

            // Check if the driver exists
            checkStmt.setInt(1, id);
            ResultSet checkRs = checkStmt.executeQuery();
            if (checkRs.next() && checkRs.getInt(1) == 0) {
                return null; // Driver does not exist
            }

            // Retrieve driver details
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                String driverName = rs.getString("driverName");
                boolean available = rs.getBoolean("available");
                int licenseMaxWeight = rs.getInt("licenseMaxWeight");
                int monthly_wage = worker.get("monthly_wage").asInt();
                int hourly_wage = worker.get("hourly_wage").asInt();

                // Adding the Date
                String startDateStr = worker.get("start_date").asText();
                DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                Date start_date = dateFormat.parse(startDateStr);

                JsonNode rolesNode = worker.get("roles");
                Role[] roles = new Role[rolesNode.size()];
                for (int i = 0; i < rolesNode.size(); i++) {
                    JsonNode roleNode = rolesNode.get(i);

                    // Assuming Role has a constructor or factory method to create Role objects from JsonNode
                    Role role = new Role(/* Extract values from roleNode */);

                    // Add role to the roles array
                    roles[i] = role;
                }
                int direct_manager_id = worker.get("direct_manager_ID").asInt();
                int branch_id = worker.get("branch_id").asInt();
                String description = worker.get("departement").asText();
                String bank_details = worker.get("bank_details").asText();

                Driver driver = new Driver(id, driverName, monthly_wage, hourly_wage, start_date, direct_manager_id, roles[0], branch_id, description, bank_details, licenseMaxWeight);
                driver.setAvailable(available);
                return driver;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        return null;
    }


    @Override
    public HashMap<Integer, Driver> getAll() throws SQLException {
        String sql = "SELECT driverID FROM drivers";
        HashMap<Integer, Driver> driversMap = new HashMap<>();

        try (Connection connection = DataBase.connect();
             Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                int driverID = rs.getInt("driverID");
                Driver driver = get(driverID);
                driversMap.put(driverID, driver);
            }
        }
        return driversMap;
    }
}