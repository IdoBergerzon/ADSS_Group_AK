package Data;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class WorkerTableCreator {
    private static final String URL = "jdbc:sqlite:mydatabase.db"; // Path to the SQLite database file

    public static void createWorkersTable() {
        try {
            // Load SQLite JDBC driver class
            Class.forName("org.sqlite.JDBC");

            // Establish connection to the SQLite database
            try (Connection connection = DriverManager.getConnection(URL);
                 Statement statement = connection.createStatement()) {
                // SQL statement to create the roles table
                String sql = "CREATE TABLE IF NOT EXISTS workers ("
                        + "ID INTEGER PRIMARY KEY,"
                        + "json TEXT NOT NULL"
                        + ");";

                // Execute the SQL statement to create the table
                statement.execute(sql);
                System.out.println("Workers table created or already exists.");

                // Print the absolute path of the database file
                System.out.println("SQLite database location: " + connection.getMetaData().getURL());
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }
    public static void addColumnToWorkersTable() throws ClassNotFoundException {
        try {
            // Load SQLite JDBC driver class
            Class.forName("org.sqlite.JDBC");

            // Establish connection to the SQLite database
            try (Connection connection = DriverManager.getConnection(URL);
                 Statement statement = connection.createStatement()) {
                // SQL statement to add a new column
                String sql = "ALTER TABLE workers ADD COLUMN active INTEGER NOT NULL DEFAULT 1";

                // Execute the SQL statement to add the column
                statement.execute(sql);
                System.out.println("Column active added to workers table.");

                // Print the absolute path of the database file
                System.out.println("SQLite database location: " + connection.getMetaData().getURL());
            }
        } catch (ClassNotFoundException | SQLException e) {
            // Ignore "duplicate column name" errors
            if (!e.getMessage().contains("duplicate column name")) {
                e.printStackTrace();
            } else {
                System.out.println("Column active already exists in workers table.");
            }
        }
    }
    public static void main(String[] args) {
        //createWorkersTable();
        try {
            addColumnToWorkersTable();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
