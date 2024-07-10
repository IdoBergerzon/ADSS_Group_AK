package DataAccessObject;

import Domain.*;
import DataAccessObject.*;
import java.sql.*;
import java.util.HashMap;

public class DeliveryDocumentDAO implements IDAO<Delivery_Document> {
    private final String URL = "myDataBase.db";
    private final ItemDAO itemDAO = new ItemDAO();
    private final ALocationDAO locationDAO = new ALocationDAO();

    public DeliveryDocumentDAO() {
        TransportTableCreator.createDeliveryDocumentsTable();
        TransportTableCreator.createDeliveryDocumentItemsTable();
    }

    @Override
    public void add(Delivery_Document deliveryDocument) throws SQLException {
        if (!this.getAll().containsKey(deliveryDocument.getDocumentID())) {
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
    }

    private void addItemsToDeliveryDocument(Connection connection, int documentID, Item item, int quantity) throws SQLException {
        String checkIfExistsSql = "SELECT * FROM delivery_document_items WHERE documentID = ? AND itemID = ?";
        String updateSql = "UPDATE delivery_document_items SET quantity = quantity + ? WHERE documentID = ? AND itemID = ?";
        String insertSql = "INSERT INTO delivery_document_items(documentID, itemID, quantity) VALUES(?, ?, ?)";

        try (PreparedStatement checkIfExistsStmt = connection.prepareStatement(checkIfExistsSql)) {
            checkIfExistsStmt.setInt(1, documentID);
            checkIfExistsStmt.setInt(2, item.getItemID());

            ResultSet rs = checkIfExistsStmt.executeQuery();

            if (rs.next()) {
                // If the entry exists, update the quantity
                try (PreparedStatement updateStmt = connection.prepareStatement(updateSql)) {
                    updateStmt.setInt(1, quantity);
                    updateStmt.setInt(2, documentID);
                    updateStmt.setInt(3, item.getItemID());
                    updateStmt.executeUpdate();
                }
            } else {
                // If the entry does not exist, insert a new row
                try (PreparedStatement insertStmt = connection.prepareStatement(insertSql)) {
                    insertStmt.setInt(1, documentID);
                    insertStmt.setInt(2, item.getItemID());
                    insertStmt.setInt(3, quantity);
                    insertStmt.executeUpdate();
                }
            }
        }
    }

    @Override
    public void remove(int deliveryID) throws SQLException {
        if (this.getAll().containsKey(deliveryID)) {
            String sql = "DELETE FROM delivery_documents WHERE documentID = ?";
            try (Connection connection = DriverManager.getConnection(URL);
                 PreparedStatement pstmt = connection.prepareStatement(sql)) {
                pstmt.setInt(1, deliveryID);
                pstmt.executeUpdate();
            }
        }
    }

    @Override
    public void update(Delivery_Document deliveryDocument) throws SQLException {
        if (this.getAll().containsKey(deliveryDocument.getDocumentID())) {
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
    }

    private void removeItemsFromDeliveryDocument(Connection connection, int documentID) throws SQLException {
        if (this.getAll().containsKey(documentID)) {
            String sql = "DELETE FROM delivery_document_items WHERE documentID = ?";
            try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
                pstmt.setInt(1, documentID);
                pstmt.executeUpdate();
            }
        }
    }

    @Override
    public Delivery_Document get(int id) throws SQLException {
        if (this.getAll().containsKey(id)) {
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

                    Store source = (Store) locationDAO.get(sourceID);
                    Supplier destination = (Supplier) locationDAO.get(destinationID);
                    Delivery_DocumentStatus deliveryStatus = Delivery_DocumentStatus.valueOf(deliveryStatusStr);
                    Delivery_ItemsStatus itemsStatus = Delivery_ItemsStatus.valueOf(itemsStatusStr);

                    HashMap<Item, Integer> items = getItemsForDeliveryDocument(connection, documentID);

                    deliveryDocument = new Delivery_Document(source, documentID, destination, items);
                    deliveryDocument.setDelivery_status(deliveryStatus);
                    deliveryDocument.setItemsStatus(itemsStatus);
                    deliveryDocument.setTotalWeight(totalWeight);
                }
            }
            return deliveryDocument;
        }
        return null;
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
                int sourceID = rs.getInt("sourceID");
                int destinationID = rs.getInt("destinationID");
                double totalWeight = rs.getDouble("totalWeight");
                String deliveryStatusStr = rs.getString("delivery_status");
                String itemsStatusStr = rs.getString("itemsStatus");

                // Fetch source and destination locations using locationDAO
                Store source = (Store) locationDAO.get(sourceID);
                Supplier destination = (Supplier) locationDAO.get(destinationID);

                // Convert delivery status and items status from string to enum
                Delivery_DocumentStatus deliveryStatus = Delivery_DocumentStatus.valueOf(deliveryStatusStr);
                Delivery_ItemsStatus itemsStatus = Delivery_ItemsStatus.valueOf(itemsStatusStr);

                // Fetch items associated with the delivery document
                HashMap<Item, Integer> items = getItemsForDeliveryDocument(connection, documentID);

                // Create Delivery_Document object
                Delivery_Document deliveryDocument = new Delivery_Document(source, documentID, destination, items);
                deliveryDocument.setDelivery_status(deliveryStatus);
                deliveryDocument.setItemsStatus(itemsStatus);
                deliveryDocument.setTotalWeight(totalWeight);

                // Add deliveryDocument to deliveryDocuments map
                deliveryDocuments.put(documentID, deliveryDocument);
            }
        }
        return deliveryDocuments;
    }

}
