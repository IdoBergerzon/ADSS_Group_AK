package Domain.Transports;

import DAL.Transports_DAL.*;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

public class TestDAO {

    public static void main(String[] args) {
        TestDAO testDAO = new TestDAO();

        // Initialize DAOs
        TruckDAO truckDAO = new TruckDAO();
        DriverDAO driverDAO = new DriverDAO();
        ALocationDAO aLocationDAO = new ALocationDAO();
        ItemDAO itemDAO = new ItemDAO();
        DeliveryDocumentDAO deliveryDocumentDAO = new DeliveryDocumentDAO();
        TransportDAO transportDAO = new TransportDAO();

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

            // Test Transport operations
            testDAO.testTransportOperations(transportDAO, truckDAO, driverDAO, deliveryDocumentDAO, aLocationDAO, itemDAO);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void testTruckOperations(TruckDAO truckDAO) throws SQLException {
        // Create and add trucks
        Truck truck1 = new Truck(1, "Semi-Truck", 15000, 30000);
        Truck truck2 = new Truck(2, "Pickup Truck", 5000, 10000);

        try {
            truckDAO.add(truck1);
            truckDAO.add(truck2);

            // Test retrieving trucks
            System.out.println("All Trucks:");
            HashMap<Integer, Truck> allTrucks = truckDAO.getAll();
            for (Truck truck : allTrucks.values()) {
                System.out.println(truck);
            }


            // Test removing a truck
            truckDAO.remove(truck2.getTruckID());
            System.out.println("Removed Truck: " + truck2);
        } finally {
            // Clean up: Remove added trucks
            truckDAO.remove(truck1.getTruckID());
        }
    }

    private void testDriverOperations(DriverDAO driverDAO) throws SQLException {
        // Create and add drivers
        Driver driver1 = new Driver(1, "John Doe", 5000);
        Driver driver2 = new Driver(2, "Jane Smith", 6000);

        try {
            driverDAO.add(driver1);
            driverDAO.add(driver2);

            // Test retrieving drivers
            System.out.println("All Drivers:");
            HashMap<Integer, Driver> allDrivers = driverDAO.getAll();
            for (Driver driver : allDrivers.values()) {
                System.out.println(driver);
            }
            // Test removing a driver
            driverDAO.remove(driver2.getDriverID());
            System.out.println("Removed Driver: " + driver2);
        } finally {
            // Clean up: Remove added drivers
            driverDAO.remove(driver1.getDriverID());
        }
    }

    private void testALocationOperations(ALocationDAO aLocationDAO) throws SQLException {
        // Create and add locations
        Address address1 = new Address("123 Main St", 10001, 5);
        Address address2 = new Address("456 Elm St", 20002, 7);
        Store store = new Store(1, address1, "Store Contact", "123-4567");
        Supplier supplier = new Supplier(2, address2, "Supplier Contact", "987-6543");

        try {
            aLocationDAO.add(store);
            aLocationDAO.add(supplier);

            // Test retrieving locations
            System.out.println("All Locations:");
            HashMap<Integer, ALocation> allLocations = aLocationDAO.getAll();
            for (ALocation location : allLocations.values()) {
                System.out.println(location);
            }

            // Test updating a location
            store.setContact("Updated Store Contact");
            aLocationDAO.update(store);
            System.out.println("Updated Location: " + aLocationDAO.get(store.getLocationID()));

            // Test removing a location
            aLocationDAO.remove(supplier.getLocationID());
            System.out.println("Removed Location: " + supplier);
        } finally {
            // Clean up: Remove added locations
            aLocationDAO.remove(store.getLocationID());
        }
    }

    private void testItemOperations(ItemDAO itemDAO) throws SQLException {
        // Create and add items
        Item item1 = new Item(1, "Item1", 12.5);
        Item item2 = new Item(2, "Item2", 15.0);

        try {
            itemDAO.add(item1);
            itemDAO.add(item2);

            // Test retrieving items
            System.out.println("All Items:");
            HashMap<Integer, Item> allItems = itemDAO.getAll();
            for (Item item : allItems.values()) {
                System.out.println(item);
            }

            // Test updating an item
            item1.setItemName("Updated Item1");
            itemDAO.update(item1);
            System.out.println("Updated Item: " + itemDAO.get(item1.getItemID()));

            // Test removing an item
            itemDAO.remove(item2.getItemID());
            System.out.println("Removed Item: " + item2);
        } finally {
            // Clean up: Remove added items
            itemDAO.remove(item1.getItemID());
        }
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
            Delivery_Document deliveryDocument2 = new Delivery_Document(source, 2, destination, items);


            // Test adding a delivery document
            deliveryDocumentDAO.add(deliveryDocument);
            deliveryDocumentDAO.add(deliveryDocument2);
            System.out.println(deliveryDocumentDAO.getAll().keySet());
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
            deliveryDocumentDAO.remove(deliveryDocument.getDocumentID());
            System.out.println("Removed Delivery Document: " + deliveryDocument);

        } finally {
            // Clean up: Remove added items and locations
            itemDAO.remove(item1.getItemID());
            itemDAO.remove(item2.getItemID());
            aLocationDAO.remove(source.getLocationID());
            aLocationDAO.remove(destination.getLocationID());
        }
    }

    private void testTransportOperations(TransportDAO transportDAO, TruckDAO truckDAO, DriverDAO driverDAO, DeliveryDocumentDAO deliveryDocumentDAO, ALocationDAO aLocationDAO, ItemDAO itemDAO) throws SQLException {
        // Create necessary entities
        Truck truck = new Truck(1, "Semi-Truck", 15000, 30000);
        Driver driver = new Driver(1, "John Doe", 5000);
        Driver driver2 = new Driver(2, "John JKLJL", 8800);

        // Add truck and driver
        truckDAO.add(truck);
        driverDAO.add(driver);
        driverDAO.add(driver2);

        // Create a delivery document for transport
        Address sourceAddress = new Address("123 Main St", 10001, 5);
        Address destinationAddress = new Address("456 Elm St", 20002, 7);
        Store sourceLocation = new Store(1, sourceAddress, "Source Contact", "123-4567");
        Supplier destinationLocation = new Supplier(2, destinationAddress, "Destination Contact", "987-6543");
        aLocationDAO.add(sourceLocation);
        aLocationDAO.add(destinationLocation);

        // Create delivery document
        Item item1 = new Item(1, "Item1", 12.5);
        Item item2 = new Item(2, "Item2", 15.0);
        itemDAO.add(item1);
        itemDAO.add(item2);
        HashMap<Item, Integer> items = new HashMap<>();
        items.put(item1, 10);
        items.put(item2, 5);
        Delivery_Document deliveryDocument = new Delivery_Document(sourceLocation, 1, destinationLocation, items);
        for (Integer i: deliveryDocumentDAO.getAll().keySet())
            System.out.println(i+"0");
        deliveryDocumentDAO.add(deliveryDocument);

        // Create transport
        Transport transport = new Transport(1, truck, driver, List.of(deliveryDocument), "Test comments");

        try {
            // Test adding a transport
            transportDAO.add(transport);
            System.out.println("Added Transport: " + transport);

            // Test updating a transport
            transport.addComment("Updated comments");
            transportDAO.update(transport);
            Transport updatedTransport = transportDAO.get(transport.getTransportID());
            System.out.println("Updated Transport: " + updatedTransport);

            // Test removing a transport
            transportDAO.remove(transport.getTransportID());
            Transport removedTransport = transportDAO.get(transport.getTransportID());
            System.out.println("Removed Transport: " + removedTransport);
        } finally {
            // Clean up: Remove added items and locations
            itemDAO.remove(item1.getItemID());
            itemDAO.remove(item2.getItemID());
            aLocationDAO.remove(sourceLocation.getLocationID());
            aLocationDAO.remove(destinationLocation.getLocationID());
            truckDAO.remove(truck.getTruckID());
            driverDAO.remove(driver.getDriverID());
            driverDAO.remove(driver2.getDriverID()); // Ensure to remove driver2 as well
        }
    }
}
