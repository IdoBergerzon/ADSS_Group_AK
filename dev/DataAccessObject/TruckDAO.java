package DataAccessObject;

import Domain.Truck;

import java.sql.*;
import java.util.HashMap;

public class TruckDAO implements IDAO<Truck> {
    private final String URL = "jdbc:sqlite:sample.db";

    public TruckDAO() {
        TransportTableCreator.createTrucksTable();
    }

    @Override
    public void add(Truck truck) throws SQLException {
        if (!this.getAll().containsKey(truck.getTruckID())) {
            String sql = "INSERT INTO trucks(truckID, truckType, truckWeight, maxWeight, available) VALUES(?, ?, ?, ?, true)";
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
        else
            System.out.println("Truck Already Exists");
    }

    @Override
    public void remove(Truck truck) throws SQLException {
        if (this.getAll().containsKey(truck.getTruckID())) {
            String sql = "DELETE FROM trucks WHERE truckID = ?";
            try (Connection connection = DriverManager.getConnection(URL);
                 PreparedStatement pstmt = connection.prepareStatement(sql)) {
                pstmt.setInt(1, truck.getTruckID());
                pstmt.executeUpdate();
            }
        }
        else
            System.out.println("Truck Not Exists");
    }

    @Override
    public void update(Truck truck) throws SQLException {
        if (this.getAll().containsKey(truck.getTruckID())) {
            String sql = "UPDATE trucks SET truckType = ?, truckWeight = ?, maxWeight = ?, available = ? WHERE truckID = ?";
            try (Connection connection = DriverManager.getConnection(URL);
                 PreparedStatement pstmt = connection.prepareStatement(sql)) {
                pstmt.setString(1, truck.getTruckType());
                pstmt.setDouble(2, truck.getTruckWeight());
                pstmt.setDouble(3, truck.getMaxWeight());
                pstmt.setBoolean(4, truck.isAvailable());
                pstmt.setInt(5, truck.getTruckID());
                pstmt.executeUpdate();
            }
        }
        else
            System.out.println("Truck Not Exists");
    }

    @Override
    public Truck get(int id) throws SQLException {
        if (this.getAll().containsKey(id)) {
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
        else {
            System.out.println("Truck Not Exists");
            return null;
        }
    }

    @Override
    public HashMap<Integer, Truck> getAll() throws SQLException {
        String sql = "SELECT * FROM trucks";
        HashMap<Integer, Truck> trucksMap = new HashMap<>();
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
                trucksMap.put(truckID, truck);
            }
        }
        return trucksMap;
    }
}
