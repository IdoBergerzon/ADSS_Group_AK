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

    public static void createTransportTable() {
        try {
            // Load SQLite JDBC driver class
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

    public static void main(String[] args) {
        createTransportTable();
    }
}
