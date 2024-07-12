package DAL;

import java.sql.*;

public class TransportTableCreator {
    private static final String URL = "jdbc:sqlite:C:\\Users\\TAMIR\\Documents\\שנה ב\\סמסטר ב\\נושאים מתקדמים בתכנות\\ADSS_Group_AK\\mydatabase.db"; // Path to the SQLite database file

    public static void createDriversTable() {
        try {
            Class.forName("org.sqlite.JDBC");
            try (Connection connection = DataBase.connect();
                 Statement statement = connection.createStatement()) {


                String sql = "CREATE TABLE IF NOT EXISTS drivers (" +
                        "driverID INTEGER PRIMARY KEY, " +
                        "driverName TEXT NOT NULL, " +
                        "available BOOLEAN NOT NULL, " +
                        "licenseMaxWeight INTEGER NOT NULL);";

                statement.execute(sql);
                //System.out.println("Drivers table created or already exists.");

                //System.out.println("SQLite database location: " + connection.getMetaData().getURL());
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    public static void createTrucksTable() {
        try {
            Class.forName("org.sqlite.JDBC");
            try (Connection connection = DataBase.connect();
                 Statement statement = connection.createStatement()) {

                String sql = "CREATE TABLE IF NOT EXISTS trucks (" +
                        "truckID INTEGER PRIMARY KEY, " +
                        "truckType TEXT NOT NULL, " +
                        "truckWeight REAL NOT NULL, " +
                        "maxWeight REAL NOT NULL, " +
                        "available BOOLEAN NOT NULL);";

                statement.execute(sql);
                //System.out.println("Trucks table created or already exists.");
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    public static void createDeliveryDocumentItemsTable() {
        try {
            Class.forName("org.sqlite.JDBC");

            try (Connection connection = DataBase.connect();
                 Statement statement = connection.createStatement()) {

                // Create the table with (documentID, itemID) as primary key
                String sql = "CREATE TABLE IF NOT EXISTS delivery_document_items (" +
                        "documentID INTEGER NOT NULL, " +
                        "itemID INTEGER NOT NULL, " +
                        "quantity INTEGER NOT NULL, " +
                        "PRIMARY KEY (documentID, itemID), " +
                        "FOREIGN KEY (documentID) REFERENCES delivery_documents(documentID), " +
                        "FOREIGN KEY (itemID) REFERENCES items(itemID));";

                statement.execute(sql);
                //System.out.println("Delivery document items table created or already exists.");
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    public static void createItemsTable() {
        try {
            Class.forName("org.sqlite.JDBC");

            try (Connection connection = DataBase.connect();
                 Statement statement = connection.createStatement()) {
                String sql = "CREATE TABLE IF NOT EXISTS items (" +
                        "itemID INTEGER PRIMARY KEY, " +
                        "itemName TEXT NOT NULL, " +
                        "weight REAL NOT NULL);";

                statement.execute(sql);
                //System.out.println("Items table created or already exists.");
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }


    public static void createDeliveryDocumentsTable() {
        try {
            Class.forName("org.sqlite.JDBC");
            try (Connection connection = DataBase.connect();
                 Statement statement = connection.createStatement()) {

                // Check if the table already exists
                DatabaseMetaData metaData = connection.getMetaData();
                ResultSet resultSet = metaData.getTables(null, null, "delivery_documents", null);
                if (resultSet.next()) {
                    //System.out.println("Delivery Documents table already exists.");
                    return; // Table already exists, no need to create it again
                }

                // Create the table
                String createSql = "CREATE TABLE delivery_documents (" +
                        "documentID INTEGER PRIMARY KEY, " +
                        "sourceID INTEGER NOT NULL, " +
                        "destinationID INTEGER NOT NULL, " +
                        "totalWeight REAL NOT NULL, " +
                        "delivery_status TEXT NOT NULL, " +
                        "itemsStatus TEXT NOT NULL, " +
                        "FOREIGN KEY (sourceID) REFERENCES locations(locationID), " +
                        "FOREIGN KEY (destinationID) REFERENCES suppliers(locationID));";

                statement.executeUpdate(createSql);
                //System.out.println("Delivery Documents table created.");
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }



    public static void createLocationTable() {
        try {
            Class.forName("org.sqlite.JDBC");
            try (Connection connection = DataBase.connect();
                 Statement statement = connection.createStatement()) {
                String createTableSql = "CREATE TABLE IF NOT EXISTS locations (" +
                        "locationID INTEGER PRIMARY KEY, " +
                        "contact TEXT NOT NULL, " +
                        "phone TEXT NOT NULL, " +
                        "lType INTEGER NOT NULL, " +
                        "full_address TEXT NOT NULL, " +
                        "address_code INTEGER NOT NULL, " +
                        "shipping_area INTEGER NOT NULL)";
                statement.execute(createTableSql);
                //System.out.println("Location Table created or already exists.");
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    public static void createTransportDeliveryDocumentsTable() {
        try {
            Class.forName("org.sqlite.JDBC");

            try (Connection connection = DataBase.connect();
                 Statement statement = connection.createStatement()) {
                // Create the table
                String sql = "CREATE TABLE IF NOT EXISTS transport_delivery_documents (" +
                        "transportID INTEGER NOT NULL, " +
                        "documentID INTEGER NOT NULL, " +
                        "PRIMARY KEY (transportID, documentID), " +
                        "FOREIGN KEY (transportID) REFERENCES transport(transportID), " +
                        "FOREIGN KEY (documentID) REFERENCES delivery_documents(documentID)" +
                        ");";

                statement.execute(sql);
                //System.out.println("Transport delivery documents table created or already exists.");
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }





    public static void createTransportTable() {
        try {
            Class.forName("org.sqlite.JDBC");
            try (Connection connection = DataBase.connect();
                 Statement statement = connection.createStatement()) {
                String sql = "CREATE TABLE IF NOT EXISTS transport (" +
                        "transportID INTEGER PRIMARY KEY, " +
                        "truckID INTEGER NOT NULL, " +
                        "driverID INTEGER NOT NULL, " +
                        "comments TEXT, " +
                        "FOREIGN KEY (truckID) REFERENCES trucks(truckID), " +
                        "FOREIGN KEY (driverID) REFERENCES drivers(driverID));";

                statement.execute(sql);

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
