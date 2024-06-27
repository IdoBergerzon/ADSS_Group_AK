package Data;

import Domain.Role;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RoleDAOImpl implements IRoleDao {
    private static final String URL = "jdbc:sqlite:mydatabase.db"; // Path to the SQLite database file

    @Override
    public void addRole(Role role) {
        String sql = "INSERT INTO roles (Role_ID, name) VALUES (?, ?)";
        try (Connection connection = DriverManager.getConnection(URL);
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, role.getRoleID());
            statement.setString(2, role.getName());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Role getRoleById(int id) {
        return null;
    }

    @Override
    public List<Role> getAllRoles() {
        return List.of();
    }

    @Override
    public void updateRole(Role role) {

    }

    @Override
    public void deleteRole(int id) {

    }


}
