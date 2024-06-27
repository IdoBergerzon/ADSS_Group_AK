package Data;

import java.sql.*;

public class Database {
    private static final String DB_URL = "jdbc:sqlite:C:\\Users\\97252\\Desktop\\סמסטר ד\\נושאים מתקדמים בתכנות\\ADSS_Group_AK\\mydatabase.db";

    public static Connection connect() throws SQLException {
        return DriverManager.getConnection(DB_URL);
    }

/*    public static void closeConnection(Connection connection) {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }*/
}
