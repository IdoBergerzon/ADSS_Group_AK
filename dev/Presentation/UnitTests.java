package Presentation;

import Domain.*;
import org.junit.jupiter.api.*; // Using JUnit 5 annotations

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
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
        driverController.addDriver(1, "John Doe", 5000);
        Driver driver = driverController.getDriver(1);
        assertNotNull(driver);
        assertEquals("John Doe", driver.getDriverName());
        assertEquals(5000, driver.getLicenseMaxWeight());
    }

    @Test
    public void getShippingAreaForDest() {
        HashMap<Item, Integer> items = new HashMap<>();
        items.put(new Item(1, "Item1", 2.5), 5);

        Address storeAddress1 = new Address("123 Main St", 5566, 12345);
        Address storeAddress2 = new Address("456 Elm St", 7788, 67890);

        Address supplierAddress1 = new Address("789 Oak St", 1122, 20);
        Address supplierAddress2 = new Address("910 Maple St", 3344, 25);

        documentsController.addDelivery_Document(1, new Store(1, storeAddress1, "Store1", "123-456-7890"), new Supplier(2, supplierAddress1, "Supplier1", "111-222-3333"), items);
        documentsController.addDelivery_Document(2, new Store(2, storeAddress2, "Store2", "987-654-3210"), new Supplier(4, supplierAddress2, "Supplier2", "444-555-6666"), items);

        documentsController.getShippingAreaForDest(10);
        String expectedOutput = "[20, 25]";

        // Assert that the captured output (from outputStreamCaptor) matches the expected output exactly
        assertEquals(expectedOutput, outputStreamCaptor.toString().trim());
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
        Truck truck = new Truck(1, "TruckModel", 1000, 1200);
        Driver driver = new Driver(1, "DriverName", 15000);
        List<Delivery_Document> deliveryDocs = new ArrayList<>();
        Transport transport = new Transport(1, truck, driver, deliveryDocs, "");
        transportController.addTransport(transport);

        transportController.finishTransport();
        assertTrue(transport.isFinished());
        assertTrue(driver.isAvailable());
        assertTrue(truck.isAvailable());
    }

    @Test
    public void testAddTransport() {
        Truck truck = new Truck(1, "TruckModel", 100, 10000);
        Driver driver = new Driver(1, "DriverName", 15000);
        List<Delivery_Document> deliveryDocs = new ArrayList<>();
        Transport transport = new Transport(1, truck, driver, deliveryDocs, "");

        transportController.addTransport(transport);
        assertEquals(1, transportController.getTransportsData().getAll().size());
        assertEquals(transport, transportController.getTransport(1));
    }
    @Test
    public void testAddLocation() {
        Address address = new Address("123 Main St", 12345, 1);
        locationController.addLocation(1, address, "John Doe", "555-1234", "Supplier");

        ALocation location = locationController.getLocation(1);
        assertNotNull(location);
        assertTrue(location instanceof Supplier);
        assertEquals("123 Main St", location.getAddress().getFull_address());
        assertEquals("John Doe", location.getContact());
        assertEquals("555-1234", location.getPhone());
    }

    @Test
    public void testAddLocationThatAlreadyExists() {
        Address address = new Address("123 Main St", 12345, 1);
        locationController.addLocation(1, address, "John Doe", "555-1234", "Supplier");
        locationController.addLocation(1, address, "Jane Smith", "555-5678", "Store");

        ALocation location = locationController.getLocation(1);
        assertNotNull(location);
        assertTrue(location instanceof Supplier);  // Check that the location was not overridden
        assertEquals("John Doe", location.getContact());
    }
}