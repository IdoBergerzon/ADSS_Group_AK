package DataAccessObject;

import java.sql.SQLException;
import Domain.Driver;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class DriverDAO implements IDAO<Driver>{
    private final String URL = "jdbc:sqlite:sample.db";

    public DriverDAO() {
        TransportTableCreator.createDriversTable();
    }

    @Override
    public void add(Driver driver) throws SQLException {
        String sql = "INSERT INTO drivers(driverID, driverName, available, licenseMaxWeight) VALUES(?, ?, ?, ?)";
        try (Connection connection = DriverManager.getConnection(URL);
             PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, driver.getDriverID());
            pstmt.setString(2, driver.getDriverName());
            pstmt.setBoolean(3, driver.isAvailable());
            pstmt.setInt(4, driver.getLicenseMaxWeight());
            pstmt.executeUpdate();
        }
    }

    @Override
    public void remove(Driver driver) throws SQLException {
        String sql = "DELETE FROM drivers WHERE driverID = ?";
        try (Connection connection = DriverManager.getConnection(URL);
             PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, driver.getDriverID());
            pstmt.executeUpdate();
        }
    }

    @Override
    public void update(Driver driver) throws SQLException {
        String sql = "UPDATE drivers SET driverName = ?, available = ?, licenseMaxWeight = ? WHERE driverID = ?";
        try (Connection connection = DriverManager.getConnection(URL);
             PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, driver.getDriverName());
            pstmt.setBoolean(2, driver.isAvailable());
            pstmt.setInt(3, driver.getLicenseMaxWeight());
            pstmt.setInt(4, driver.getDriverID());
            pstmt.executeUpdate();
        }
    }

    @Override
    public Driver get(int id) throws SQLException {
        String sql = "SELECT * FROM drivers WHERE driverID = ?";
        Driver driver = null;
        try (Connection connection = DriverManager.getConnection(URL);
             PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                int driverID = rs.getInt("driverID");
                String driverName = rs.getString("driverName");
                boolean available = rs.getBoolean("available");
                int licenseMaxWeight = rs.getInt("licenseMaxWeight");
                driver = new Driver(driverID, driverName, licenseMaxWeight);
                driver.setAvailable(available);
            }
        }
        return driver;
    }

    @Override
    public HashMap<Integer, Driver> getAll() throws SQLException {
        return null;
    }
}
