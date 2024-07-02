package DataAccessObject;

import Domain.Driver;
import Domain.Truck;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TruckDAO implements IDAO<Truck>{
    private final String URL = "jdbc:sqlite:sample.db";

    public TruckDAO() {
        TransportTableCreator.createTrucksTable();
    }

    @Override
    public void add(Truck truck) throws SQLException {
        String sql = "INSERT INTO trucks(truckID, truckType, truckWeight, maxWeight, available) VALUES(?, ?, ?, ?,true)";
        try (Connection connection = DriverManager.getConnection(URL);
             PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, truck.getTruckID());
            pstmt.setString(2, truck.getTruckType());
            pstmt.setDouble(3, truck.getTruckWeight());
            pstmt.setDouble(4, truck.getMaxWeight());
            pstmt.setBoolean(5, truck.isAvailable());
            pstmt.executeUpdate();
        }
    }

    @Override
    public void remove(Truck truck) throws SQLException {
        String sql = "DELETE FROM drivers WHERE driverID = ?";
        try (Connection connection = DriverManager.getConnection(URL);
             PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, truck.getTruckID());
            pstmt.executeUpdate();
        }
    }

    @Override
    public void update(Truck truck) throws SQLException {
        String sql = "UPDATE drivers SET driverName = ?, available = ?, licenseMaxWeight = ? WHERE driverID = ?";
        try (Connection connection = DriverManager.getConnection(URL);
             PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, truck.getTruckID());
            pstmt.setString(2, truck.getTruckType());
            pstmt.setDouble(3, truck.getTruckWeight());
            pstmt.setDouble(4, truck.getMaxWeight());
            pstmt.setBoolean(5, truck.isAvailable());
            pstmt.executeUpdate();
        }
    }

    @Override
    public Truck get(int id) throws SQLException {
        String sql = "SELECT * FROM drivers WHERE driverID = ?";
        Truck truck = null;
        try (Connection connection = DriverManager.getConnection(URL);
             PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                int truckID = rs.getInt("truckID");
                String truckType = rs.getString("truckType");
                double truckWeight = rs.getDouble("truckWeight");
                double maxWeight = rs.getDouble("maxWeight");
                boolean available = rs.getBoolean("available");
                truck = new Truck(truckID, truckType, truckWeight, maxWeight);
                truck.setAvailable(available);
            }
        }
        return truck;
    }

    @Override
    public List<Truck> getAll() throws SQLException {
        String sql = "SELECT * FROM drivers";
        List<Truck> trucks = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection(URL);
             Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                int truckID = rs.getInt("truckID");
                String truckType = rs.getString("truckType");
                double truckWeight = rs.getDouble("truckWeight");
                double maxWeight = rs.getDouble("maxWeight");
                boolean available = rs.getBoolean("available");
                Truck truck = new Truck(truckID, truckType, truckWeight, maxWeight);
                truck.setAvailable(available);
                trucks.add(truck);
            }
        }
        return trucks;
    }
}
