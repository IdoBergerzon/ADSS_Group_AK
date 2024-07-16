package Presentation;

import DAL.HR.RoleDAOImpl;
import DAL.HR.WorkerDAOImpl;
import DAL.Transport.DriverDAO;
import Domain.HR.*;
import Domain.Transport.*;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.junit.jupiter.api.*; // Using JUnit 5 annotations

import java.io.ByteArrayOutputStream;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
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
    private RoleRepository roleRepository;
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
    WorkerDAOImpl workerDAO = new WorkerDAOImpl();
    RoleDAOImpl roleDAO = new RoleDAOImpl();
    ObjectMapper objectMapper = new ObjectMapper();
    HR_Controller hr=new HR_Controller();
    DriverDAO driverDAO = new DriverDAO();

    @BeforeEach
    void setUp() {
        truckController = new TruckController();
        driverController = new DriverController();
        documentsController = new Delivery_DocumentsController();
        locationController = new LocationController();
        transportController = new TransportController();
        roleRepository = RoleRepository.getInstance();
        WorkerDAOImpl workerDAO = new WorkerDAOImpl();
        RoleDAOImpl roleDAO = new RoleDAOImpl();
        ObjectMapper objectMapper = new ObjectMapper();
        HR_Controller hr=new HR_Controller();
    }

    @Test
    public void testAddDriver() throws SQLException {
        int size_workers = workerDAO.GetAllWorkers().size();
        int size_drivers = driverController.getDriversData().getAll().size();
        System.out.println(size_drivers);
        assertTrue(driverController.getDriver(88) == null);


        driverController.addDriver(88, "John Doe", 6000, 34, new Date(),1 ,
                roleRepository.get(20),1,"Drivers","112233",5000);

        //search the worker that insert//
        JsonNode JN=workerDAO.search(88);
        ((ObjectNode) JN).remove("active");
        Worker worker= JsonNodeConverter.fromJsonNode(JN,Worker.class);
        Driver driver = driverDAO.get(88);
        //System.out.println(to_check);

        //creating the expected worker//
        assertTrue(driver.getName().equals(worker.getName()));
        assertTrue(driver.getName().equals("John Doe"));
        assertTrue(size_workers+1 == workerDAO.GetAllWorkers().size());
        assertTrue(size_drivers+1 == driverController.getDriversData().getAll().size());
        System.out.println(driverController.getDriversData().getAll().size());

        driverController.getDriversData().remove(88);
        //  System.out.println(ido);
        workerDAO.remove(88);

    }










    @Test
    public void testAddTransport() {
        int size = transportController.getTransportsData().getAll().size();
        assertTrue(transportController.getTransportsData().get(1) == null);


        truckController.addNewTruck(1, "TruckModel", 1000, 1200);
        Truck truck=truckController.getTruck(1);
        driverController.addDriver(14, "Avi Cohen", 7000, 50, new Date(),1 , roleRepository.get(20),2,"Drivers","112233",5500);
        Driver driver = driverController.getDriver(14);
        documentsController.addItem(1, "Avocado", 0.3);
        documentsController.addItem(2,"Apple", 0.5);


        HashMap<Item,Integer> items = new HashMap<>();
        items.put(documentsController.getItemsData().get(1), 30);
        items.put(documentsController.getItemsData().get(2), 20);
        Address address1 = new Address("Haim Hazaz 4", 5,1);
        Address address2 = new Address("Haim Hai", 98,2);
        Store store = new Store(3, address1,"Tamir","0506549848");
        Supplier supplier = new Supplier(4, address2,"Asaf","0506555988");


        documentsController.addDelivery_Document(5, store, supplier, items);
        List<Delivery_Document> deliveryDocs = new ArrayList<>();
        deliveryDocs.add(documentsController.getDelivery_Document(5));
        Transport transport = new Transport(1, truck, driver, deliveryDocs, "");
        transportController.addTransport(transport);


        assertTrue(transportController.getTransport(transport.getTransportID()) != null);
        assertTrue(size + 1 == transportController.getTransportsData().getAll().size());
        transportController.getTransportsData().remove(transport.getTransportID());
        documentsController.getDocumentsRepository().remove(5);
        driverController.getDriversData().remove(14);
        workerDAO.remove(14);
    }








}