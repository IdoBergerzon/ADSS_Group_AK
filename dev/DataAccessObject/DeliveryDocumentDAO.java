package DataAccessObject;

import Domain.Delivery_Document;
import Domain.Delivery_DocumentStatus;
import Domain.Delivery_ItemsStatus;
import Domain.Item;

import java.sql.*;
import java.util.HashMap;

public class DeliveryDocumentDAO implements IDAO<Delivery_Document> {
    private final String URL = "jdbc:sqlite:sample.db";
    private final ItemDAO itemDAO = new ItemDAO(); // Assuming ItemDAO is correctly implemented

    @Override
    public void add(Delivery_Document deliveryDocument) throws SQLException {
        String sql = "INSERT INTO delivery_documents(documentID, sourceID, destinationID, totalWeight, delivery_status, itemsStatus) VALUES(?, ?, ?, ?, ?, ?)";
        try (Connection connection = DriverManager.getConnection(URL);
             PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, deliveryDocument.getDocumentID());
            pstmt.setInt(2, deliveryDocument.getSource().getLocationID());
            pstmt.setInt(3, deliveryDocument.getDestination().getLocationID());
            pstmt.setDouble(4, deliveryDocument.getTotalWeight());
            pstmt.setString(5, deliveryDocument.getDelivery_Status().toString());
            pstmt.setString(6, deliveryDocument.getItemsStatus().toString());
            pstmt.executeUpdate();

            // Add items to the delivery_document_items table
            for (Item item : deliveryDocument.getItems().keySet()) {
                addItemsToDeliveryDocument(connection, deliveryDocument.getDocumentID(), item, deliveryDocument.getAmount(item));
            }
        }
    }

    private void addItemsToDeliveryDocument(Connection connection, int documentID, Item item, int quantity) throws SQLException {
        String sql = "INSERT INTO delivery_document_items(documentID, itemID, quantity) VALUES(?, ?, ?)";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, documentID);
            pstmt.setInt(2, item.getItemID());
            pstmt.setInt(3, quantity);
            pstmt.executeUpdate();
        }
    }

    @Override
    public void remove(Delivery_Document deliveryDocument) throws SQLException {
        String sql = "DELETE FROM delivery_documents WHERE documentID = ?";
        try (Connection connection = DriverManager.getConnection(URL);
             PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, deliveryDocument.getDocumentID());
            pstmt.executeUpdate();
        }
    }

    @Override
    public void update(Delivery_Document deliveryDocument) throws SQLException {
        String sql = "UPDATE delivery_documents SET sourceID = ?, destinationID = ?, totalWeight = ?, delivery_status = ?, itemsStatus = ? WHERE documentID = ?";
        try (Connection connection = DriverManager.getConnection(URL);
             PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, deliveryDocument.getSource().getLocationID());
            pstmt.setInt(2, deliveryDocument.getDestination().getLocationID());
            pstmt.setDouble(3, deliveryDocument.getTotalWeight());
            pstmt.setString(4, deliveryDocument.getDelivery_Status().toString());
            pstmt.setString(5, deliveryDocument.getItemsStatus().toString());
            pstmt.setInt(6, deliveryDocument.getDocumentID());
            pstmt.executeUpdate();

            // Remove all existing items for the document
            removeItemsFromDeliveryDocument(connection, deliveryDocument.getDocumentID());

            // Add updated items to the delivery_document_items table
            for (Item item : deliveryDocument.getItems().keySet()) {
                addItemsToDeliveryDocument(connection, deliveryDocument.getDocumentID(), item, deliveryDocument.getAmount(item));
            }
        }
    }

    private void removeItemsFromDeliveryDocument(Connection connection, int documentID) throws SQLException {
        String sql = "DELETE FROM delivery_document_items WHERE documentID = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, documentID);
            pstmt.executeUpdate();
        }
    }

    @Override
    public Delivery_Document get(int id) throws SQLException {
        String sql = "SELECT * FROM delivery_documents WHERE documentID = ?";
        Delivery_Document deliveryDocument = null;
        try (Connection connection = DriverManager.getConnection(URL);
             PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                int documentID = rs.getInt("documentID");
                int sourceID = rs.getInt("sourceID");
                int destinationID = rs.getInt("destinationID");
                double totalWeight = rs.getDouble("totalWeight");
                String deliveryStatusStr = rs.getString("delivery_status");
                String itemsStatusStr = rs.getString("itemsStatus");

                // Retrieve source and destination locations using their IDs
                LocationController locationController = new LocationController(); // Adjust this as per your implementation
                Store source = (Store) locationController.getLocation(sourceID);
                Supplier destination = (Supplier) locationController.getLocation(destinationID);

                // Parse enums from string values
                Delivery_DocumentStatus deliveryStatus = Delivery_DocumentStatus.valueOf(deliveryStatusStr);
                Delivery_ItemsStatus itemsStatus = Delivery_ItemsStatus.valueOf(itemsStatusStr);

                // Retrieve items for the delivery document
                HashMap<Item, Integer> items = getItemsForDeliveryDocument(connection, documentID);

                deliveryDocument = new Delivery_Document(source, documentID, destination, items);
                deliveryDocument.setDelivery_status(deliveryStatus);
                deliveryDocument.setItemsStatus(itemsStatus);
                deliveryDocument.setTotalWeight(totalWeight);
            }
        }
        return deliveryDocument;
    }

    private HashMap<Item, Integer> getItemsForDeliveryDocument(Connection connection, int documentID) throws SQLException {
        String sql = "SELECT * FROM delivery_document_items WHERE documentID = ?";
        HashMap<Item, Integer> items = new HashMap<>();
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, documentID);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                int itemID = rs.getInt("itemID");
                int quantity = rs.getInt("quantity");

                // Retrieve item from ItemDAO
                Item item = itemDAO.get(itemID);
                items.put(item, quantity);
            }
        }
        return items;
    }

    @Override
    public HashMap<Integer, Delivery_Document> getAll() throws SQLException {
        String sql = "SELECT * FROM delivery_documents";
        HashMap<Integer, Delivery_Document> deliveryDocuments = new HashMap<>();
        try (Connection connection = DriverManager.getConnection(URL);
             Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                int documentID = rs.getInt("documentID");

                // Use get method to retrieve each Delivery_Document
                Delivery_Document deliveryDocument = get(documentID);
                deliveryDocuments.put(documentID, deliveryDocument);
            }
        }
        return deliveryDocuments;
    }
}
