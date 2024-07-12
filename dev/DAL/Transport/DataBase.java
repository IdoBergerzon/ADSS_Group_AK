package DAL.Transport;

import java.sql.*;

public class DataBase {
    private static final String DB_URL = "jdbc:sqlite:C:\\Users\\WIN10\\Documents\\שנה ב\\ניתו''צ\\עבודה 1 ניתוצ\\ADSS_Group_AK\\myDataBase.db";

    public static Connection connect() throws SQLException {
        return DriverManager.getConnection(DB_URL);
    }

    public static void closeConnection(Connection con) {
        try {
            if (con != null && !con.isClosed()) {
                con.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

