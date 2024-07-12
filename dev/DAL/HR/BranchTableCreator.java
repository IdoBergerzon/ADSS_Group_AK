//
//package DAL;
//import java.sql.Connection;
//import java.sql.SQLException;
//import java.sql.Statement;
//
//public class BranchTableCreator {
//    private static final String URL = "jdbc:sqlite:mydatabase.db"; // Path to the SQLite database file
//
//    public static void createBranchesTable() {
//        try {
//            // Load SQLite JDBC driver class
//            Class.forName("org.sqlite.JDBC");
//
//            // Establish connection to the SQLite database
//            try (Connection connection = Database.connect();
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
//
//    public static void main(String[] args) {
//        createBranchesTable();
//
//    }
//
//}