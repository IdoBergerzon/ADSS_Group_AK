
package Data;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class RosterTableCreator {
    private static final String URL = "jdbc:sqlite:mydatabase.db"; // Path to the SQLite database file

    public static void createBranchesTable() {
        try {
            // Load SQLite JDBC driver class
            Class.forName("org.sqlite.JDBC");

            // Establish connection to the SQLite database
            try (Connection connection = DriverManager.getConnection(URL);
                 Statement statement = connection.createStatement()) {
                // SQL statement to create the roles table
                String sql = "CREATE TABLE IF NOT EXISTS rosters ("
                        + "Branch_ID INTEGER NOT NULL,"
                        + "week INTEGER NOT NULL,"
                        + "json TEXT NOT NULL,"
                        + "PRIMARY KEY (Branch_ID, week)"
                        + ");";

                // Execute the SQL statement to create the table
                statement.execute(sql);
                System.out.println("Rosters table created or already exists.");

                // Print the absolute path of the database file
                System.out.println("SQLite database location: " + connection.getMetaData().getURL());
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        createBranchesTable();

    }

}