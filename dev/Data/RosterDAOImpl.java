package Data;

import Domain.*;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RosterDAOImpl implements IDao<JsonNode, Pair> {
    private ObjectMapper objectMapper = new ObjectMapper();


    @Override
    public void insert(JsonNode roster) {
        String sql = "INSERT INTO rosters (Branch_ID, week,json) VALUES (?, ?, ?)";
        try (Connection connection = Database.connect();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, roster.get("branch").get("branchID").asInt());
            statement.setInt(2, roster.get("week").asInt());
            statement.setString(3, roster.toString());

            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }



    }
    @Override
    public void remove(Pair key) {
        String sql = "DELETE FROM rosters WHERE Branch_ID = ? AND week = ? ";
        try (Connection connection = Database.connect();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, (Integer) key.getFirst());
            statement.setInt(2,(Integer) key.getSecond());
            int affectedRows = statement.executeUpdate();
            if (affectedRows == 0) {
                throw new NullPointerException("roster does not exist for this worker and that week");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
    @Override
    public JsonNode search(Pair key) {
        String sql = "SELECT * FROM rosters WHERE  Branch_ID = ? AND week = ?";
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

    public List<JsonNode> getAllRosters(){
        List<JsonNode> rosters_list = new ArrayList<>();
        String sql = "SELECT * FROM rosters";
        try (Connection connection = Database.connect();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    String jsonString = resultSet.getString("json");
                    rosters_list.add( objectMapper.readTree(jsonString));
                }
            } catch (JsonMappingException e) {
                throw new RuntimeException(e);
            } catch (JsonProcessingException e) {
                throw new RuntimeException(e);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rosters_list;

    }

    public int getMaxWeek() {
        String sql = "SELECT MAX(week) AS max_week FROM rosters";
        try (Connection connection = Database.connect();
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {
            if (resultSet.next()) {
                return resultSet.getInt("max_week");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

/*    public static void main(String[] args){
        RosterDAOImpl dao = new RosterDAOImpl();
        BranchDAOImpl bdao = new BranchDAOImpl();
        WorkerDAOImpl wdao = new WorkerDAOImpl();
        Branch br1=new Branch(1,"ashdod","duiasd");
*//*        bdao.insert(JsonNodeConverter.toJsonNode(br1));
        JsonNode jsonNode1 = bdao.search(1);
        Branch br1 = JsonNodeConverter.fromJsonNode(jsonNode1, Branch.class);
        JsonNode jsonNode2 = bdao.search(2);*//*

       //bdao.insert(JsonNodeConverter.toJsonNode(br2));
        Branch br2 = new Branch(2,"haifa","jdaskd");
        Shift[][] shifts1 = new Shift[7][2];
        Shift[][] shifts2 = new Shift[7][2];
        JsonNode json = wdao.search(2);
        ((ObjectNode) json).remove("active");
        Worker aviv = JsonNodeConverter.fromJsonNode(json, Worker.class);
        json = wdao.search(3);
        ((ObjectNode) json).remove("active");
        Worker hezi = JsonNodeConverter.fromJsonNode(json, Worker.class);
        json = wdao.search(4);
        ((ObjectNode) json).remove("active");
        Worker lior = JsonNodeConverter.fromJsonNode(json, Worker.class);
        Role shift_manager = new Role(2,"shift_manager");
        Role storekeeper = new Role(3,"storekeeper");
        for (int day = 0; day < 7; day++) {
            for(int i=0; i < 2; i++) {
                // Morning shifts
                //shifts1[day][i]=new Shift(br1.getBranchID(), day, i, new Worker[]{aviv}, List.of(shift_manager));
                shifts2[day][i]=new Shift(br2.getBranchID(), day, i, new Worker[]{hezi, lior}, List.of(shift_manager, storekeeper));

            }
        }

        Roster roster1 = new Roster(br1,shifts1);
        Roster roster2 = new Roster(br2,shifts2);
        System.out.println(roster2);
        //dao.insert(JsonNodeConverter.toJsonNode(roster1));
        dao.insert(JsonNodeConverter.toJsonNode(roster2));
        System.out.println(dao.search(new Pair<>(2,1)));
        //dao.remove(new Pair<>(2,1));
        //System.out.println(dao.search(new Pair<>(2,0)));
    }*/

}
