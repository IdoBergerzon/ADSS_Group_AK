package DAL.Transport;

import Domain.Transport.ALocation;
import Domain.Transport.Address;
import Domain.Transport.Store;
import Domain.Transport.Supplier;

import java.sql.*;
import java.util.HashMap;

public class ALocationDAO implements IDAO<ALocation> {

    public ALocationDAO() {
        TransportTableCreator.createLocationTable();
    }

//    public static void Main{
//
//    }

    @Override
    public void add(ALocation aLocation) throws SQLException {
        if (!this.getAll().containsKey(aLocation.getLocationID())) {
            String sql = "INSERT INTO locations(locationID, contact, phone, lType, full_address, address_code, shipping_area) VALUES(?, ?, ?, ?, ?, ?, ?)";

            // Establish database connection
            try (Connection connection = DataBase.connect();
                 PreparedStatement pstmt = connection.prepareStatement(sql)) {
                // Set the parameters for the PreparedStatement
                pstmt.setInt(1, aLocation.getLocationID());
                pstmt.setString(2, aLocation.getContact());
                pstmt.setString(3, aLocation.getPhone());
                pstmt.setString(4, aLocation.getlType());
                pstmt.setString(5, aLocation.getAddress().getFull_address());
                pstmt.setInt(6, aLocation.getAddress().getAddress_code());
                pstmt.setInt(7, aLocation.getAddress().getShipping_area());

                // Execute the insert statement
                pstmt.executeUpdate();
            }
        }
    }



    @Override
    public void remove(int locationID) throws SQLException {
        if (this.getAll().containsKey(locationID)) {
            String sql = "DELETE FROM locations WHERE locationID = ?";
            try (Connection connection = DataBase.connect();
                 PreparedStatement pstmt = connection.prepareStatement(sql)) {
                pstmt.setInt(1, locationID);
                pstmt.executeUpdate();
            }
        }
    }

    @Override
    public void update(ALocation aLocation) throws SQLException {
        if (this.getAll().containsKey(aLocation.getLocationID())) {
            String sql = "UPDATE locations SET contact = ?, phone = ?, lType = ?, full_address = ?, address_code = ?, shipping_area = ? WHERE locationID = ?";

            // Establish the database connection
            try (Connection connection = DataBase.connect();
                 PreparedStatement pstmt = connection.prepareStatement(sql)) {
                // Set the parameters for the PreparedStatement
                pstmt.setString(1, aLocation.getContact());
                pstmt.setString(2, aLocation.getPhone());
                pstmt.setString(3, aLocation.getlType());
                pstmt.setString(4, aLocation.getAddress().getFull_address());
                pstmt.setInt(5, aLocation.getAddress().getAddress_code());
                pstmt.setInt(6, aLocation.getAddress().getShipping_area());
                pstmt.setInt(7, aLocation.getLocationID());

                // Execute the update statement
                pstmt.executeUpdate();
            }
        }
    }

    @Override
    public ALocation get(int id) throws SQLException {
        String sql = "SELECT * FROM locations WHERE locationID = ?";
        ALocation aLocation = null;

        try (Connection connection = DataBase.connect();
             PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                int locationID = rs.getInt("locationID");
                String contact = rs.getString("contact");
                String phone = rs.getString("phone");
                String lType = rs.getString("lType");
                String fullAddress = rs.getString("full_address");
                int addressCode = rs.getInt("address_code");
                int shippingArea = rs.getInt("shipping_area");
                Address address = new Address(fullAddress, addressCode, shippingArea);
                if ("Store".equals(lType)) {
                    Store store = new Store(locationID, address, contact, phone);
                    return store;
                } else if ("Supplier".equals(lType)) {
                    Supplier supplier = new Supplier(locationID, address, contact, phone);
                    return supplier;
                }
            }

            return null;
        }
    }


        @Override
        public HashMap<Integer, ALocation> getAll() throws SQLException {
            HashMap<Integer, ALocation> locationMap = new HashMap<>();

            String sql = "SELECT * FROM locations";

            try (Connection connection = DataBase.connect();
                 PreparedStatement pstmt = connection.prepareStatement(sql);
                 ResultSet rs = pstmt.executeQuery()) {

                while (rs.next()) {
                    int locationID = rs.getInt("locationID");
                    String contact = rs.getString("contact");
                    String phone = rs.getString("phone");
                    String lType = rs.getString("lType");
                    String fullAddress = rs.getString("full_address");
                    int addressCode = rs.getInt("address_code");
                    int shippingArea = rs.getInt("shipping_area");

                    // Create Address object
                    Address address = new Address(fullAddress, addressCode, shippingArea);

                    // Create ALocation object

                    if ("Store".equals(lType)) {
                        Store store = new Store(locationID, address, contact, phone);
                        locationMap.put(locationID, store);
                    } else if ("Supplier".equals(lType)) {
                        Supplier supplier = new Supplier(locationID, address, contact, phone);
                        locationMap.put(locationID, supplier);
                    }
                }
            }
        return locationMap;
    }
}
