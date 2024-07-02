package DataAccessObject;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class TransportTableCreator {
    private static final String URL = "jdbc:sqlite:sample.db"; // Path to the SQLite database file

    public static void createDriversTable() {
        try {
            Class.forName("org.sqlite.JDBC");
            try (Connection connection = DriverManager.getConnection(URL);
                 Statement statement = connection.createStatement()) {
                String sql = "CREATE TABLE IF NOT EXISTS drivers (" +
                        "driverID INTEGER PRIMARY KEY, " +
                        "driverName TEXT NOT NULL, " +
                        "available BOOLEAN NOT NULL, " +
                        "licenseMaxWeight INTEGER NOT NULL);";

                statement.execute(sql);
                System.out.println("Drivers table created or already exists.");

                System.out.println("SQLite database location: " + connection.getMetaData().getURL());
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    public static void createTrucksTable() {
        try {
            Class.forName("org.sqlite.JDBC");
            try (Connection connection = DriverManager.getConnection(URL);
                 Statement statement = connection.createStatement()) {
                String sql = "CREATE TABLE IF NOT EXISTS trucks (" +
                        "truckID INTEGER PRIMARY KEY, " +
                        "truckType TEXT NOT NULL, " +
                        "truckWeight REAL NOT NULL, " +
                        "maxWeight REAL NOT NULL, " +
                        "available BOOLEAN NOT NULL);";

                statement.execute(sql);
                System.out.println("Trucks table created or already exists.");
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

//    public static void createTransportTable() {
//        try {
//            Class.forName("org.sqlite.JDBC");
//
//            // Establish connection to the SQLite database
//            try (Connection connection = DriverManager.getConnection(URL);
//                 Statement statement = connection.createStatement()) {
//                // SQL statement to create the roles table
//                String sql = "CREATE TABLE IF NOT EXISTS branches ("
//                        + "Branch_ID INTEGER PRIMARY KEY,"
//                        + "name TEXT NOT NULL,"
//                        + "address TEXT NOT NULL"
//                        + ");";
//
//                // Execute the SQL statement to create the table
//                statement.execute(sql);
//                System.out.println("Roles table created or already exists.");
//
//                // Print the absolute path of the database file
//                System.out.println("SQLite database location: " + connection.getMetaData().getURL());
//            }
//        } catch (ClassNotFoundException | SQLException e) {
//            e.printStackTrace();
//        }
//    }

    public static void createItemsTable() {
        try {
            Class.forName("org.sqlite.JDBC");

            try (Connection connection = DriverManager.getConnection(URL);
                 Statement statement = connection.createStatement()) {
                String sql = "CREATE TABLE IF NOT EXISTS items (" +
                        "itemID INTEGER PRIMARY KEY, " +
                        "itemName TEXT NOT NULL, " +
                        "weight REAL NOT NULL);";

                statement.execute(sql);
                System.out.println("Items table created or already exists.");
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }


    public static void createDeliveryDocumentsTable() {
        try {
            Class.forName("org.sqlite.JDBC");

            try (Connection connection = DriverManager.getConnection(URL);
                 Statement statement = connection.createStatement()) {
                String sql = "CREATE TABLE IF NOT EXISTS delivery_documents (" +
                        "documentID INTEGER PRIMARY KEY, " +
                        "source INTEGER NOT NULL, " +
                        "destination INTEGER NOT NULL, " +
                        "totalWeight REAL NOT NULL, " +
                        "delivery_status TEXT NOT NULL, " +
                        "itemsStatus TEXT NOT NULL, " +
                        "FOREIGN KEY (source) REFERENCES locations(locationID), " +
                        "FOREIGN KEY (destination) REFERENCES suppliers(supplierID));";

                statement.execute(sql);
                System.out.println("Delivery Documents table created or already exists.");
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    public static void createLocationTable() {
        try {
            Class.forName("org.sqlite.JDBC");
            try (Connection connection = DriverManager.getConnection(URL);
                 Statement statement = connection.createStatement()) {
                String sql = "CREATE TABLE IF NOT EXISTS locations (" +
                        "locationID INTEGER PRIMARY KEY, " +
                        "full_address TEXT NOT NULL, " +
                        "addressCode INTEGER NOT NULL, " +
                        "shippingErea INTEGER NOT NULL, " +
                        "contact TEXT NOT NULL, " +
                        "phone TEXT NOT NULL, " +
                        "lType INTEGER NOT NULL);";

                statement.execute(sql);
                System.out.println("Location Table created or already exists.");
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    public static void createTransportDeliveryDocumentsTable() {
        try {
            Class.forName("org.sqlite.JDBC");

            try (Connection connection = DriverManager.getConnection("jdbc:sqlite:sample.db");
                 Statement statement = connection.createStatement()) {
                String sql = "CREATE TABLE IF NOT EXISTS transport_delivery_documents (" +
                        "transportID INTEGER NOT NULL, " +
                        "documentID INTEGER NOT NULL, " +
                        "PRIMARY KEY (transportID, documentID), " +
                        "FOREIGN KEY (transportID) REFERENCES transport(transportID), " +
                        "FOREIGN KEY (documentID) REFERENCES Delivery_Document(documentID));";

                statement.execute(sql);
                System.out.println("Transport delivery documents table created or already exists.");
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }


    public static void createTransportTable() {
        try {
            Class.forName("org.sqlite.JDBC");

            try (Connection connection = DriverManager.getConnection("jdbc:sqlite:sample.db");
                 Statement statement = connection.createStatement()) {
                String sql = "CREATE TABLE IF NOT EXISTS transport (" +
                        "transportID INTEGER PRIMARY KEY, " +
                        "truckID INTEGER NOT NULL, " +
                        "driverID INTEGER NOT NULL, " +
                        "comments TEXT, " +
                        "FOREIGN KEY (truckID) REFERENCES trucks(truckID), " +
                        "FOREIGN KEY (driverID) REFERENCES drivers(driverID));";

                statement.execute(sql);
                System.out.println("Transport table created or already exists.");
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }


    public static void main(String[] args) {
        createDriversTable();
        createTrucksTable();
        createItemsTable();
        createLocationTable();
        createDeliveryDocumentsTable();
        createTransportTable();
    }
}
