package DataAccessObject;

import Domain.Truck;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
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
        String sql = "DELETE FROM trucks WHERE truckID = ?";
        try (Connection connection = DriverManager.getConnection(URL);
             PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, truck.getTruckID());
            pstmt.executeUpdate();
        }
    }

    @Override
    public void update(Truck truck) throws SQLException {
        String sql = "UPDATE trucks SET truckType = ?, truckWeight = ?, maxWeight = ?, available = ? WHERE truckID = ?";
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
        String sql = "SELECT * FROM trucks WHERE truckID = ?";
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
    public HashMap<Integer, Truck> getAll() throws SQLException {
        return null;
    }
}
