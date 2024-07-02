package DAL;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

import java.sql.*;
import java.util.ArrayList;

import java.util.List;

public class WorkerDAOImpl implements IDao<JsonNode, Integer>{
    private ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public JsonNode search(Integer id) {
        String sql = "SELECT * FROM workers WHERE ID = ?";
        try (Connection connection = Database.connect();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    int activeStatus = resultSet.getInt("active");
                    String jsonString = resultSet.getString("json");
                    JsonNode jsonNode = objectMapper.readTree(jsonString);

                    // Add the active status to the JsonNode
                    ((ObjectNode) jsonNode).put("active", activeStatus);

                    return jsonNode;
                }
            } catch (JsonMappingException e) {
                throw new RuntimeException(e);
            } catch (JsonProcessingException e) {
                throw new RuntimeException(e);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void insert(JsonNode worker) {
        int id = worker.get("id").asInt();
        if(this.search(id) == null) {
            String sql = "INSERT INTO workers (ID, json, active) " +
                    "VALUES (?, ?,?)";
            try (Connection connection = Database.connect();
                 PreparedStatement statement = connection.prepareStatement(sql)) {

                statement.setInt(1, id);
                statement.setString(2, worker.toString());
                statement.setInt(3, 1);

                statement.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            String sql = "UPDATE workers SET json = ?,active = 1 WHERE ID = ?";
            try (Connection connection = Database.connect();
                 PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setString(1, worker.toString());
                statement.setInt(2, id);
                statement.executeUpdate();
            }  catch (SQLException e) {
                e.printStackTrace();
            }

        }
    }

    @Override
    public void remove(Integer id) {
        String sql = "DELETE FROM workers WHERE ID = ?";
        try (Connection connection = Database.connect();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            int affectedRows = statement.executeUpdate();
            if (affectedRows == 0) {
                throw new NullPointerException("Id does not exist");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public List<JsonNode> GetAllWorkers() {
        List<JsonNode> workers_list = new ArrayList<>();
        String sql = "SELECT * FROM workers WHERE active = 1";
        try (Connection connection = Database.connect();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    int id = resultSet.getInt("ID");
                    String jsonString = resultSet.getString("json");
                    workers_list.add( objectMapper.readTree(jsonString));
                }
            } catch (JsonMappingException e) {
                throw new RuntimeException(e);
            } catch (JsonProcessingException e) {
                throw new RuntimeException(e);
            }
        } catch (SQLException e) {
        e.printStackTrace();
    }
        return workers_list;
    }


    public void updateWorker(JsonNode worker) {
        String sql = "UPDATE workers SET json = ? WHERE ID = ?";
        try (Connection connection = Database.connect();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            int id = worker.get("id").asInt();
            String jsonString = worker.toString();
            statement.setString(1, jsonString);
            statement.setInt(2, id);

            int rowsUpdated = statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void retireWorker(int id) {
        String sql = "UPDATE workers SET active = 0 WHERE ID = ?";
        try (Connection connection = Database.connect();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);

            int rowsUpdated = statement.executeUpdate();
            if (rowsUpdated == 0) {
                throw new NullPointerException("Id does not exist");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }





//    public static void main(String[] args){
//        WorkerDAOImpl dao = new WorkerDAOImpl();
//        Role cashier=new Role(3,"cashiers");
//        Role store=new Role(4,"store");
//        Worker ido=new Worker(20,"ido",5000,0,new Date(),1,cashier,2,"cashiers","leumi 5555555");
//        Worker aviv=new Worker(22,"aviv",5000,0,new Date(),1,cashier,2,"cashiers","leumi 5555555");
//        ObjectMapper objectMapper = new ObjectMapper();
//        // Convert Role object to JsonNode
//        JsonNode jsonNode1 = objectMapper.valueToTree(ido);
//        JsonNode jsonNode2 = objectMapper.valueToTree(aviv);
//        //dao.remove(20);
//        //ido.addNewRole(store);
//        dao.insert(jsonNode1);
//        dao.insert(jsonNode2);
//        dao.retireWorker(ido.getId());
//        System.out.println(dao.GetAllWorkers());
//    }

}
