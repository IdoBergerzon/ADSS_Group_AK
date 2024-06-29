package Data;

import Domain.Pair;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RequestDAOImpl implements IDao<JsonNode, Pair> {
    private ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public JsonNode search(Pair key){
        String sql = "SELECT * FROM requests WHERE  worker_id = ?, week = ?";
        try (Connection connection = Database.connect();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, (Integer) key.getFirst());
            statement.setInt(2, (Integer) key.getSecond());
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    String jsonString = resultSet.getString("json");
                    return objectMapper.readTree(jsonString);
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
    public void insert(JsonNode request){

    }

    @Override
    public void remove(Pair key) {
        String sql = "DELETE FROM requests WHERE worker_id = ?, week = ? ";
        try (Connection connection = Database.connect();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, (Integer) key.getFirst());
            statement.setInt(2,(Integer) key.getSecond());
            int affectedRows = statement.executeUpdate();
            if (affectedRows == 0) {
                throw new NullPointerException("request does not exist for this worker and that week");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public List<JsonNode> getAllRequests(){
        List<JsonNode> requests_list = new ArrayList<>();
        String sql = "SELECT * FROM requests";
        try (Connection connection = Database.connect();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    String jsonString = resultSet.getString("json");
                    requests_list.add( objectMapper.readTree(jsonString));
                }
            } catch (JsonMappingException e) {
                throw new RuntimeException(e);
            } catch (JsonProcessingException e) {
                throw new RuntimeException(e);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return requests_list;
    }




    public void update(JsonNode request){
        String sql = "UPDATE workers SET json = ? WHERE worker_id = ?, week = ?";
        try (Connection connection = Database.connect();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            int id = request.get("worker_id").asInt();
            String jsonString = worker.toString();
            statement.setString(1, jsonString);
            statement.setInt(2, id);

            int rowsUpdated = statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
