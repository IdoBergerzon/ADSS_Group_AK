package Data;

import java.sql.*;

public class Database {
    private static final String DB_URL = "jdbc:sqlite:mydatabase.db";

    public static Connection connect() throws SQLException {
        return DriverManager.getConnection(DB_URL);
    }
}
