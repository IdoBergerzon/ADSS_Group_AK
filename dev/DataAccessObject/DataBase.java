package DataAccessObject;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.sql.*;

public class DataBase {
    private static final String DB_URL = "jdbc:sqlite:jdbc:sqlite:C:\\Users\\TAMIR\\Documents\\שנה ב\\סמסטר ב\\נושאים מתקדמים בתכנות\\ADSS_Group_AK\\mydatabase.db";

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

