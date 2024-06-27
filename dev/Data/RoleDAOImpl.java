package Data;

import Domain.Role;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RoleDAOImpl implements IRoleDao {
    //private static final String URL = "jdbc:sqlite:C:\\Users\\97252\\Desktop\\סמסטר ד\\נושאים מתקדמים בתכנות\\ADSS_Group_AK\\mydatabase.db"; // Path to the SQLite database file

    @Override
    public void addRole(Role role) {
        String sql = "INSERT INTO roles (Role_ID, name) VALUES (?, ?)";
        try (Connection connection = Database.connect();
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
        String sql = "SELECT Role_ID, name FROM roles WHERE Role_ID = ?";
        try (Connection connection = Database.connect();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return new Role(resultSet.getInt("Role_ID"), resultSet.getString("name"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Role> getAllRoles() {
        List<Role> roles = new ArrayList<>();
        String sql = "SELECT Role_ID, name FROM roles";
        try (Connection connection = Database.connect();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {
            while (resultSet.next()) {
                roles.add(new Role(resultSet.getInt("Role_ID"), resultSet.getString("name")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return roles;
    }

    @Override
    public void updateRole(Role role) {
        String sql = "UPDATE roles SET name = ? WHERE Role_ID = ?";
        try (Connection connection = Database.connect();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, role.getName());
            statement.setInt(2, role.getRoleID());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteRole(int id) {
        String sql = "DELETE FROM roles WHERE Role_ID = ?";
        try (Connection connection = Database.connect();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            statement.executeUpdate();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }




/*    public static void main(String[] args) {
        RoleDAOImpl roleDAO = new RoleDAOImpl();

        // Create a new role
        //Role newRole = new Role(13, "inbar");
        // Add the new role to the database
        //roleDAO.updateRole(newRole);

        // Retrieve and print the role from the database
        //System.out.println(roleDAO.getAllRoles());
        //roleDAO.deleteRole(13);
        System.out.println(roleDAO.getAllRoles());


        // Print all roles
        roleDAO.getAllRoles().forEach(role -> System.out.println("Role ID: " + role.getRoleID() + ", Role Name: " + role.getName()));

    }*/


}
