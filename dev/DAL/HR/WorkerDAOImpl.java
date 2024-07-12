package DAL.HR;


import Domain.HR.Role;
import Domain.HR.Worker;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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



}
//class Main {
//    public static void main(String[] args) {
//        WorkerDAOImpl workerDAO = new WorkerDAOImpl();
//        ObjectMapper objectMapper = new ObjectMapper();
//        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
//
//        try {
//            // Create four workers
//            Worker worker1 = new Worker(1, "Ido", 5000, 25, dateFormat.parse("2020-01-01"), 1, new Role(1,"hr" ), 1, "Engineering", "Bank A");
//            Worker worker2 = new Worker(2, "Aviv", 6000, 30, dateFormat.parse("2019-05-15"), 1, new Role(2,"shift_manager"), 2, "manager", "Bank B");
//            Worker worker5 = new Worker(3, "Hezi", 6000, 30, dateFormat.parse("2019-05-15"), 1, new Role(2,"shift_manager"), 2, "manager", "Bank B");
//            Worker worker3 = new Worker(4, "Lior", 4000, 20, dateFormat.parse("2021-03-10"), 3, new Role(3,"storekeeper"), 2, "Deliveries", "Bank C");
//            Worker worker4 = new Worker(5, "Asaf", 5500, 28, dateFormat.parse("2018-07-22"), 3, new Role(4,"Cashier"), 2, "Deliveries", "Bank D");
//
//            // Convert workers to JSON and insert them into the database
//            insertWorker(workerDAO, objectMapper, worker1);
//            insertWorker(workerDAO, objectMapper, worker2);
//            insertWorker(workerDAO, objectMapper, worker3);
//            insertWorker(workerDAO, objectMapper, worker4);
//
//            System.out.println("Workers inserted successfully.");
//
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }
//    }
//    private static void insertWorker(WorkerDAOImpl workerDAO, ObjectMapper objectMapper, Worker worker) {
//        JsonNode workerJson = objectMapper.convertValue(worker, ObjectNode.class);
//        workerDAO.insert(workerJson);
//    }
//}


