package DAL.Transports_DAL;

import Domain.Item;

import java.sql.*;
import java.util.HashMap;

public class ItemDAO implements IDAO<Item> {
    private final String URL ="jdbc:sqlite:C:\\Users\\WIN10\\Documents\\שנה ב\\ניתו''צ\\עבודה 1 ניתוצ\\ADSS_Group_AK\\myDataBase.db";

    public ItemDAO() {
        TransportTableCreator.createItemsTable();
    }

    @Override
    public void add(Item item) throws SQLException {
        if (this.getAll().containsKey(item.getItemID())) {
            System.out.println("item already exists");
        } else {
            String sql = "INSERT INTO items(itemID, itemName, weight) VALUES(?, ?, ?)";
            try (Connection connection = DataBase.connect();
                 PreparedStatement pstmt = connection.prepareStatement(sql)) {
                pstmt.setInt(1, item.getItemID());
                pstmt.setString(2, item.getItemName());
                pstmt.setDouble(3, item.getWeight());
                pstmt.executeUpdate();
            }
        }
    }
    @Override
    public void remove(int itemID) throws SQLException {
        if (this.getAll().containsKey(itemID)) {
            String sql = "DELETE FROM items WHERE itemID = ?";
            try (Connection connection = DataBase.connect();
                 PreparedStatement pstmt = connection.prepareStatement(sql)) {
                pstmt.setInt(1, itemID);
                pstmt.executeUpdate();
            }
        }
    }

    @Override
    public void update(Item item) throws SQLException {
        if (this.getAll().containsKey(item.getItemID())) {
            String sql = "UPDATE items SET itemName = ?, weight = ? WHERE itemID = ?";
            try (Connection connection = DataBase.connect();
                 PreparedStatement pstmt = connection.prepareStatement(sql)) {
                pstmt.setString(1, item.getItemName());
                pstmt.setDouble(2, item.getWeight());
                pstmt.setInt(3, item.getItemID());
                pstmt.executeUpdate();
            }
        }
    }


    @Override
    public Item get(int id) throws SQLException {
        String sql = "SELECT * FROM items WHERE itemID = ?";
        Item item = null;
        try (Connection connection = DataBase.connect();
             PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                int itemID = rs.getInt("itemID");
                String itemName = rs.getString("itemName");
                double weight = rs.getDouble("weight");
                item = new Item(itemID, itemName, weight);
            }
        }
        return item;
    }

    @Override
    public HashMap<Integer, Item> getAll() throws SQLException {
        String sql = "SELECT * FROM items";
        HashMap<Integer, Item> itemsMap = new HashMap<>();
        try (Connection connection = DataBase.connect();
             PreparedStatement pstmt = connection.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) {
                int itemID = rs.getInt("itemID");
                String itemName = rs.getString("itemName");
                double weight = rs.getDouble("weight");
                Item item = new Item(itemID, itemName, weight);
                itemsMap.put(itemID, item);
            }
        }
        return itemsMap;
    }
}
