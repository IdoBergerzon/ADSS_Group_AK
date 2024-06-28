package Data;

import Domain.Role;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RoleDAOImpl implements IDao<JsonNode,Integer> {
    private ObjectMapper objectMapper = new ObjectMapper();

    public List<JsonNode> getAllRoles() {
        List<JsonNode> rolesList = new ArrayList<>();
        String sql = "SELECT Role_ID, name FROM roles";
        try (Connection connection = Database.connect();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    int roleId = resultSet.getInt("Role_ID");
                    String roleName = resultSet.getString("name");

                    // Construct JsonNode for each role
                    JsonNode jsonNode = objectMapper.createObjectNode()
                            .put("roleID", roleId)
                            .put("name", roleName);

                    rolesList.add(jsonNode);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rolesList;
    }

    public void updateRole(JsonNode role) {
        try {
            int roleId = role.get("roleID").asInt();
            String roleName = role.get("name").asText();

            String sql = "UPDATE roles SET name = ? WHERE Role_ID = ?";
            try (Connection connection = Database.connect();
                 PreparedStatement statement = connection.prepareStatement(sql)) {

                statement.setString(1, roleName);
                statement.setInt(2, roleId);
                int rowsUpdated = statement.executeUpdate();

                if (rowsUpdated > 0) {
                    System.out.println("Updated Role with ID: " + roleId);
                } else {
                    throw new IllegalArgumentException("Role with ID " + roleId + " does not exist, update failed.");
                }

            } catch (SQLException e) {
                e.printStackTrace();
            }
        } catch (NullPointerException e) {
            throw new IllegalArgumentException("Invalid JSON structure", e);
        }
    }

    @Override
    public JsonNode search(Integer roleId) {
        String sql = "SELECT Role_ID, name FROM roles WHERE Role_ID = ?";
        try (Connection connection = Database.connect();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, roleId);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    int foundRoleId = resultSet.getInt("Role_ID");
                    String foundRoleName = resultSet.getString("name");

                    // Construct JsonNode
                    JsonNode jsonNode = objectMapper.createObjectNode()
                            .put("roleID", foundRoleId)
                            .put("name", foundRoleName);

                    return jsonNode;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        // Return null if no data found
        return null;
    }

    @Override
    public void insert(JsonNode role) {
        try {
            int roleId = role.get("roleID").asInt();
            String roleName = role.get("name").asText();

            String sql = "INSERT INTO roles (Role_ID, name) VALUES (?, ?)";
            try (Connection connection = Database.connect();
                 PreparedStatement statement = connection.prepareStatement(sql)) {

                statement.setInt(1, roleId);
                statement.setString(2, roleName);
                statement.executeUpdate();

                System.out.println("Inserted Role with ID: " + roleId);

            } catch (SQLException e) {
                e.printStackTrace();
            }
        } catch (NullPointerException e) {
            throw new IllegalArgumentException("Invalid JSON structure", e);
        }
    }


    @Override
    public void remove(Integer id) {
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




    public static void main(String[] args) {
        RoleDAOImpl roleDAO = new RoleDAOImpl();

        // Create a new role
        Role newRole = new Role(17, "aviv");
        // Add the new role to the database
        //System.out.println(roleDAO.search(14));
        ObjectMapper objectMapper = new ObjectMapper();

        // Convert Role object to JsonNode
        JsonNode jsonNode = objectMapper.valueToTree(newRole);
        roleDAO.insert(jsonNode);

        // Retrieve and print the role from the database
        //System.out.println(roleDAO.getAllRoles());
        //roleDAO.remove(13);
        //System.out.println(roleDAO.getAllRoles());


        // Print all roles
        //roleDAO.getAllRoles().forEach(role -> System.out.println("Role ID: " + role.getRoleID() + ", Role Name: " + role.getName()));

    }


}
