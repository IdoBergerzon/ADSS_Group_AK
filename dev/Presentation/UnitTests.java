package Presentation;

import Domain.Transports.*;
import org.junit.jupiter.api.*; // Using JUnit 5 annotations

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS) // To use non-static @BeforeAll and @AfterAll
public class UnitTests {

    private TruckController truckController;
    private DriverController driverController;
    private Delivery_DocumentsController documentsController;
    private LocationController locationController;
    private TransportController transportController;
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

    @BeforeEach
    void setUp() {
        truckController = new TruckController();
        driverController = new DriverController();
        documentsController = new Delivery_DocumentsController();
        locationController = new LocationController();
        transportController = new TransportController();
    }

    @AfterEach
    void tearDown() {
        // Cleanup code if necessary
        // This might include resetting any state modified by tests
    }

    @Test
    public void addNewTruck() {
        int truckID = 1234567;
        String truckType = "Heavy";
        double truckWeight = 1500.0;
        double maxWeight = 2000.0;

        // Add a new truck
        truckController.addNewTruck(truckID, truckType, truckWeight, maxWeight);

        // Retrieve the added truck and verify its details
        Truck retrievedTruck = truckController.getTruck(truckID);
        assertNotNull(retrievedTruck, "Failed to add truck");
        assertEquals(truckID, retrievedTruck.getTruckID(), "Incorrect truck ID");
        assertEquals(truckType, retrievedTruck.getTruckType(), "Incorrect truck type");
        assertEquals(truckWeight, retrievedTruck.getTruckWeight(), "Incorrect truck weight");
        assertEquals(maxWeight, retrievedTruck.getMaxWeight(), "Incorrect max weight");

        // Clean up by removing the truck if needed (depending on how your repository handles removals)
        // This is just illustrative; your TrucksRepository should have a remove method.
        truckController.getTrucksData().remove(truckID); // Assuming remove method exists in TrucksRepository
    }

    @Test
    public void addNewTruck_DuplicateID() {
        int truckID = 1234567;
        String truckType1 = "Heavy";
        double truckWeight1 = 1500.0;
        double maxWeight1 = 2000.0;

        String truckType2 = "Light";
        double truckWeight2 = 1000.0;
        double maxWeight2 = 1500.0;

        // Add the first truck
        truckController.addNewTruck(truckID, truckType1, truckWeight1, maxWeight1);

        // Attempt to add a second truck with the same ID
        truckController.addNewTruck(truckID, truckType2, truckWeight2, maxWeight2);

        // Retrieve the truck and verify that its details have not changed
        Truck retrievedTruck = truckController.getTruck(truckID);
        assertNotNull(retrievedTruck, "Failed to retrieve truck after adding duplicate ID");
        assertEquals(truckType1, retrievedTruck.getTruckType(), "Truck type should not change");
        assertEquals(truckWeight1, retrievedTruck.getTruckWeight(), "Truck weight should not change");
        assertEquals(maxWeight1, retrievedTruck.getMaxWeight(), "Max weight should not change");

        // Clean up by removing the truck if needed
        truckController.getTrucksData().remove(truckID); // Assuming remove method exists in TrucksRepository
    }


    @Test
    public void printAllAvailableTrucks() {
        // This test would involve adding trucks and verifying the output, which typically requires
        // capturing the console output or modifying the method to return a value instead of printing directly.
        // For simplicity, we'll outline the steps here.

        // Add multiple trucks with varying availability and weights
        truckController.addNewTruck(1234567, "Heavy", 1500.0, 2000.0);
        truckController.addNewTruck(2345678, "Light", 1000.0,1500.0);
    }

    @Test
    public void testAddDriver() {
        driverController.addDriver(11, "John Doe", 5000);
        Driver driver = driverController.getDriver(11);
        assertNotNull(driver);
        assertEquals("John Doe", driver.getDriverName());
        assertEquals(5000, driver.getLicenseMaxWeight());
    }

    @Test
    public void testCalcWeight_Valid() {
        Truck truck = new Truck(1, "TruckModel", 5000, 10000);
        Driver driver = new Driver(1, "DriverName", 15000);
        List<Delivery_Document> deliveryDocs = new ArrayList<>();
        Transport transport = new Transport(1, truck, driver, deliveryDocs, "");

        assertTrue(transportController.calcWeight(transport));
    }

    @Test
    public void testFinishTransport() {
        truckController.addNewTruck(1, "TruckModel", 1000, 1200);
        Truck truck=truckController.getTruck(1);
        driverController.addDriver(1, "DriverName", 15000);
        Driver driver = driverController.getDriver(1);
        documentsController.addItem(1, "Avocado", 0.3);
        documentsController.addItem(2,"Apple", 0.5);
        HashMap<Item,Integer> items = new HashMap<>();
        items.put(documentsController.getItemsData().get(1), 30);
        items.put(documentsController.getItemsData().get(2), 20);
        Address address1 = new Address("Haim Hazaz 4", 5,1);
        Address address2 = new Address("Haim Hai", 98,2);
        locationController.addLocation(1, address1,"Tamir","0506549848","Store");
        locationController.addLocation(2, address2,"Asaf","0506555988","Supplier");
        documentsController.addDelivery_Document(5, (Store) locationController.getLocation(1), (Supplier) locationController.getLocation(2), items);
        List<Delivery_Document> deliveryDocs = new ArrayList<>();
        Transport transport = new Transport(1, truck, driver, deliveryDocs, "");
        transportController.addTransport(transport);
        transportController.finishTransport(transport, driverController, documentsController, truckController);
        assertTrue(transport.isFinished());
        assertTrue(driver.isAvailable());
        assertTrue(truck.isAvailable());
    }

    @Test
    public void testAddTransport() {
        truckController.addNewTruck(1, "TruckModel", 1000, 1200);
        Truck truck=truckController.getTruck(1);
        driverController.addDriver(1, "DriverName", 15000);
        Driver driver = driverController.getDriver(1);
        documentsController.addItem(1, "Avocado", 0.3);
        documentsController.addItem(2,"Apple", 0.5);
        HashMap<Item,Integer> items = new HashMap<>();
        items.put(documentsController.getItemsData().get(1), 30);
        items.put(documentsController.getItemsData().get(2), 20);
        Address address1 = new Address("Haim Hazaz 4", 5,1);
        Address address2 = new Address("Haim Hai", 98,2);
        locationController.addLocation(1, address1,"Tamir","0506549848","Store");
        locationController.addLocation(2, address2,"Asaf","0506555988","Supplier");
        documentsController.addDelivery_Document(5, (Store) locationController.getLocation(1), (Supplier) locationController.getLocation(2), items);
        List<Delivery_Document> deliveryDocs = new ArrayList<>();
        Transport transport = new Transport(1, truck, driver, deliveryDocs, "");
        transportController.addTransport(transport);
        assertTrue(transportController.getTransport(transport.getTransportID()) != null);
    }

    @Test
    public void testAddLocation() {
        Address address = new Address("123 Main St", 12345, 1);
        locationController.addLocation(8, address, "John Doe", "555-1234", "Supplier");

        ALocation location = locationController.getLocation(8);
        assertNotNull(location);
        assertTrue(location instanceof Supplier);
        assertEquals("123 Main St", location.getAddress().getFull_address());
        assertEquals("John Doe", location.getContact());
        assertEquals("555-1234", location.getPhone());
    }

    @Test
    public void testAddLocationThatAlreadyExists() {
        Address address = new Address("123 Main St", 12345, 1);
        locationController.addLocation(10, address, "John Doe", "555-1234", "Supplier");
        locationController.addLocation(11, address, "Jane Smith", "555-5678", "Store");

        ALocation location = locationController.getLocation(10);
        assertNotNull(location);
        assertTrue(location instanceof Supplier);  // Check that the location was not overridden
        assertEquals("John Doe", location.getContact());
    }

    @Test
    public void testCalculateTotalWeight() {
        truckController.addNewTruck(1, "TruckModel", 1000, 1200);
        Truck truck=truckController.getTruck(1);
        driverController.addDriver(1, "DriverName", 15000);
        Driver driver = driverController.getDriver(1);
        Address address1 = new Address("123 Main St", 10001,5);
        Address address2 = new Address("456 Elm St", 20002,4);
        Address address3 = new Address("13 Main St", 7001,3);
        Address address4 = new Address("56 Elm St", 200,4);
        ALocation store1 = new Store(1, address1, "John Doe", "555-1234");
        ALocation store2 = new Store(2, address2, "Jane Smith", "555-5678");
        ALocation supplier1 = new Supplier(3, address3, "John Doe", "555-1234");
        ALocation supplier2 = new Supplier(4, address4, "Jane Smith", "555-5678");
        HashMap<Item, Integer> items1 = new HashMap<>();
        HashMap<Item, Integer> items2 = new HashMap<>();
        Item item1 = new Item(1, "Avocado", 0.3);
        Item item2 = new Item(2, "Apple", 0.5);
        Item item3 = new Item(3, "Banana", 0.7);
        items1.put(item1, 100);
        items2.put(item2, 200);
        items2.put(item3, 300);
        items1.put(item3, 400);
        List<Delivery_Document> deliveryDocs = new ArrayList<>();
        Delivery_Document document1 = new Delivery_Document((Store) store1, 1, (Supplier) supplier1, items1);
        Delivery_Document document2 = new Delivery_Document((Store) store2, 2, (Supplier) supplier2, items2);
        deliveryDocs.add(document1);
        deliveryDocs.add(document2);
        Transport transport = new Transport(9, truck, driver,deliveryDocs, "");
        transportController.addTransport(transport);
        assertEquals(620+1000, transportController.getTransport(9).calc_transportWeight());
    }
}