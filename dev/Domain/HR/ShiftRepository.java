package Domain.HR;


import DAL.HR.RosterDAOImpl;
import com.fasterxml.jackson.databind.JsonNode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ShiftRepository implements IRepository<Roster,Pair> {
    private final Map<Pair, Roster> rosters;
    private RosterDAOImpl dao;



    private ShiftRepository() {
        rosters = new HashMap<>();
        dao = new RosterDAOImpl();

    }

    private static class InShiftHolder {
        private final static ShiftRepository INSTANCE = new ShiftRepository();
    }

    /**
     * public static method that returns the single instance of the class
     */

    public static ShiftRepository getInstance() {
        return ShiftRepository.InShiftHolder.INSTANCE;
    }

    @Override
    public Roster get(Pair key) {
        if(rosters.containsKey(key))
            return rosters.get(key);
        JsonNode jn = dao.search(key);
        if (jn != null) {
            return JsonNodeConverter.fromJsonNode(jn, Roster.class);
        }
        return null;

    }

    @Override
    public void add(Roster roster) {
        Pair<Integer,Integer> requestKey = new Pair<>(roster.getBranch_id(), roster.getWeek());
        try {
            dao.insert(JsonNodeConverter.toJsonNode(roster));
            rosters.put(requestKey, roster);
        } catch (Exception e) {
            throw new IllegalArgumentException("Roster for week:" + roster.getWeek() + " for branch " + roster.getBranch_id() + " already exists");
        }

    }

    @Override
    public void remove(Pair key) {
        if(key != null){
            try{
                dao.remove(key);
                rosters.remove(key);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void setShift(Shift shift) {
        Pair key = new Pair<>(shift.getBranch_id(), shift.getWeekNum());
        Roster roster = rosters.get(key);
        roster.addShift(shift);
        rosters.put(key,roster);
    }


    public List<Roster> getAllRosters(){
        List<JsonNode> jsonNodes = dao.getAllRosters();
        List<Roster> list_rosters = new ArrayList<>();
        for (JsonNode jsonNode : jsonNodes) {
            Roster roster = JsonNodeConverter.fromJsonNode(jsonNode, Roster.class);
            if(!rosters.containsKey(new Pair<>(roster.getBranch_id(),roster.getWeek()))){
                rosters.put(new Pair<>(roster.getBranch_id(),roster.getWeek()), roster);
            }
            list_rosters.add(roster);
        }
        return list_rosters;
    }

    public int getMaxWeek() {
        return dao.getMaxWeek();
    }





}
