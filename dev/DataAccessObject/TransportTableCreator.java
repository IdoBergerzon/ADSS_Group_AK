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

    public static void createTransportTable() {
        try {
            Class.forName("org.sqlite.JDBC");

            // Establish connection to the SQLite database
            try (Connection connection = DriverManager.getConnection(URL);
                 Statement statement = connection.createStatement()) {
                // SQL statement to create the roles table
                String sql = "CREATE TABLE IF NOT EXISTS branches ("
                        + "Branch_ID INTEGER PRIMARY KEY,"
                        + "name TEXT NOT NULL,"
                        + "address TEXT NOT NULL"
                        + ");";

                // Execute the SQL statement to create the table
                statement.execute(sql);
                System.out.println("Roles table created or already exists.");

                // Print the absolute path of the database file
                System.out.println("SQLite database location: " + connection.getMetaData().getURL());
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

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

    public static void main(String[] args) {
        createDriversTable();
        createTrucksTable();
        createTransportTable();
    }
}
