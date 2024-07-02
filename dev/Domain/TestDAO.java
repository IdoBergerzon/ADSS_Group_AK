package Domain;

import DataAccessObject.ALocationDAO;
import DataAccessObject.DriverDAO;
import DataAccessObject.TruckDAO;
import DataAccessObject.ItemDAO;
import DataAccessObject.DeliveryDocumentDAO;
import Domain.*;

import java.sql.SQLException;
import java.util.HashMap;

public class TestDAO {

    public static void main(String[] args) {
        TestDAO testDAO = new TestDAO();

        // Initialize DAOs
        TruckDAO truckDAO = new TruckDAO();
        DriverDAO driverDAO = new DriverDAO();
        ALocationDAO aLocationDAO = new ALocationDAO();
        ItemDAO itemDAO = new ItemDAO();
        DeliveryDocumentDAO deliveryDocumentDAO = new DeliveryDocumentDAO();

        try {
            // Test Trucks operations
            testDAO.testTruckOperations(truckDAO);

            // Test Drivers operations
            testDAO.testDriverOperations(driverDAO);

            // Test ALocation operations
            testDAO.testALocationOperations(aLocationDAO);

            // Test Items operations
            testDAO.testItemOperations(itemDAO);

            // Test Delivery Documents operations
            testDAO.testDeliveryDocumentOperations(deliveryDocumentDAO, itemDAO, aLocationDAO);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void testTruckOperations(TruckDAO truckDAO) throws SQLException {
        // Truck operations as previously defined
    }

    private void testDriverOperations(DriverDAO driverDAO) throws SQLException {
        // Driver operations as previously defined
    }

    private void testALocationOperations(ALocationDAO aLocationDAO) throws SQLException {
        // ALocation operations as previously defined
    }

    private void testItemOperations(ItemDAO itemDAO) throws SQLException {
        // Item operations as previously defined
    }

    private void testDeliveryDocumentOperations(DeliveryDocumentDAO deliveryDocumentDAO, ItemDAO itemDAO, ALocationDAO aLocationDAO) throws SQLException {
        // Create items and locations needed for delivery documents tests
        Item item1 = new Item(1, "Item1", 12.5);
        Item item2 = new Item(2, "Item2", 15.0);
        itemDAO.add(item1);
        itemDAO.add(item2);

        Address address1 = new Address("123 Main St", 10001, 5);
        Address address2 = new Address("456 Elm St", 20002, 7);
        Store source = new Store(1, address1, "Source Contact", "123-4567");
        Supplier destination = new Supplier(2, address2, "Destination Contact", "987-6543");
        aLocationDAO.add(source);
        aLocationDAO.add(destination);

        try {
            // Create a delivery document
            HashMap<Item, Integer> items = new HashMap<>();
            items.put(item1, 10);
            items.put(item2, 5);
            Delivery_Document deliveryDocument = new Delivery_Document(source, 1, destination, items);

            // Test adding a delivery document
            deliveryDocumentDAO.add(deliveryDocument);
            System.out.println("Added Delivery Document: " + deliveryDocument);

            // Test updating a delivery document
            deliveryDocument.setItemsStatus(Delivery_ItemsStatus.complete);
            deliveryDocumentDAO.update(deliveryDocument);
            Delivery_Document updatedDocument = deliveryDocumentDAO.get(deliveryDocument.getDocumentID());
            System.out.println("Updated Delivery Document: " + updatedDocument);

            // Test getting a delivery document by ID
            Delivery_Document retrievedDocument = deliveryDocumentDAO.get(deliveryDocument.getDocumentID());
            System.out.println("Retrieved Delivery Document by ID: " + retrievedDocument);

            // Test removing a delivery document
            deliveryDocumentDAO.remove(deliveryDocument);
            System.out.println("Removed Delivery Document: " + deliveryDocument);

        } finally {
            // Clean up: Remove added items and locations
            itemDAO.remove(item1);
            itemDAO.remove(item2);
            aLocationDAO.remove(source);
            aLocationDAO.remove(destination);
        }
    }
}
