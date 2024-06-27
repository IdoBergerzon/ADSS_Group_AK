package Data;

import java.sql.*;

public class Database {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/";

    public static Connection connect() throws SQLException {
        return DriverManager.getConnection(DB_URL);
    }
}
