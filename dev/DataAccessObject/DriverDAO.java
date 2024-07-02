package DataAccessObject;

import java.net.URL;
import java.sql.SQLException;
import Domain.Driver;
import java.sql.*;
import java.util.List;


public class DriverDAO implements IDAO<Driver>{
    private TransportTableCreator tableCreator;
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

    }

    @Override
    public Driver get(int id) throws SQLException {
        return null;
    }

    @Override
    public List<Driver> getAll() throws SQLException {
        return List.of();
    }
}
