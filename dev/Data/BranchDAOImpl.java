package Data;


import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BranchDAOImpl implements IDao<JsonNode,Integer> {
    private ObjectMapper objectMapper = new ObjectMapper();

    public List<JsonNode> getAllBranches() {
        List<JsonNode> branch_list = new ArrayList<>();
        String sql = "SELECT Branch_ID, name, address FROM branches";
        try (Connection connection = Database.connect();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    int branchID = resultSet.getInt("branchID");
                    String branchName = resultSet.getString("name");
                    String address = resultSet.getString("address");

                    // Construct JsonNode for each branch
                    JsonNode jsonNode = objectMapper.createObjectNode()
                            .put("branchID", branchID)
                            .put("branch_name", branchName)
                            .put("address", address);

                    branch_list.add(jsonNode);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return branch_list;
    }

    public void updateBranch(JsonNode branch) {
        try {
            int branchId = branch.get("branchID").asInt();
            String branchName = branch.get("branch_name").asText();
            String address = branch.get("address").asText();

            String sql = "UPDATE branches SET name = ?, address = ? WHERE Branch_ID = ?";
            try (Connection connection = Database.connect();
                 PreparedStatement statement = connection.prepareStatement(sql)) {

                statement.setString(1, branchName);
                statement.setString(2, address);
                statement.setInt(3, branchId);
                int rowsUpdated = statement.executeUpdate();

                if (rowsUpdated > 0) {
                    System.out.println("Updated Branch with ID: " + branchId);
                } else {
                    throw new IllegalArgumentException("Branch with ID " + branchId + " does not exist, update failed.");
                }

            } catch (SQLException e) {
                e.printStackTrace();
            }
        } catch (NullPointerException e) {
            throw new IllegalArgumentException("Invalid JSON structure", e);
        }
    }

    @Override
    public JsonNode search(Integer branchId) {
        String sql = "SELECT Branch_ID, name, address FROM branches WHERE Branch_ID = ?";
        try (Connection connection = Database.connect();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, branchId);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    int foundBranchId = resultSet.getInt("Branch_ID");
                    String foundBranchName = resultSet.getString("name");
                    String foundAddress = resultSet.getString("address");

                    // Construct JsonNode
                    JsonNode jsonNode = objectMapper.createObjectNode()
                            .put("branchID", foundBranchId)
                            .put("branchName", foundBranchName)
                            .put("address", foundAddress);

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
    public void insert(JsonNode branch) {
        try {
            int branchId = branch.get("branchID").asInt();
            String branchName = branch.get("branchName").asText();
            String branchAddress = branch.get("address").asText();

            String sql = "INSERT INTO branches (Branch_ID, name, address) VALUES (?, ?, ?)";
            try (Connection connection = Database.connect();
                 PreparedStatement statement = connection.prepareStatement(sql)) {

                statement.setInt(1, branchId);
                statement.setString(2, branchName);
                statement.setString(3, branchAddress);
                statement.executeUpdate();

                System.out.println("Inserted Branch with ID: " + branchId);

            } catch (SQLException e) {
                e.printStackTrace();
            }
        } catch (NullPointerException e) {
            throw new IllegalArgumentException("Invalid JSON structure", e);
        }
    }


    @Override
    public void remove(Integer id) {
        String sql = "DELETE FROM branches WHERE Branch_ID = ?";
        try (Connection connection = Database.connect();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }




  /*  public static void main(String[] args) {
        BranchDAOImpl branchDAO = new BranchDAOImpl();

        // Create a new role
        Branch newBranch = new Branch(3, "Dimona", "Haniviam");
        // Add the new role to the database
        //System.out.println(roleDAO.search(14));
        ObjectMapper objectMapper = new ObjectMapper();

        // Convert Role object to JsonNode
        JsonNode jsonNode = objectMapper.valueToTree(newBranch);
        System.out.println(jsonNode);
        branchDAO.remove(3);
//        System.out.println(branchDAO.search(3).toString());

        // Retrieve and print the role from the database
        //System.out.println(roleDAO.getAllRoles());
        //roleDAO.remove(13);
        //System.out.println(roleDAO.getAllRoles());


        // Print all roles
        //roleDAO.getAllRoles().forEach(role -> System.out.println("Role ID: " + role.getRoleID() + ", Role Name: " + role.getName()));

    }

   */


}
