package Domain;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ShiftRepository implements IShiftRespository {
    private final Map<Pair, Roster> rosters;



    private ShiftRepository() {
        rosters = new HashMap<>();

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

    public Roster getRoster(int branch_id, int week) {
        return rosters.get(new Pair<>(branch_id,week));
    }

    public void addRoster(Roster roster) {
        Pair key = new Pair<>(roster.getBranch().getBranchID(), roster.getWeek());
        if (rosters.get(key) != null) {
            throw new IllegalArgumentException("Roster already exists");
        }
        rosters.put(key, roster);

    }

    public void setShift(Shift shift) {
        Pair key = new Pair<>(shift.getBranch_id(), shift.getWeekNum());
        Roster roster = rosters.get(key);
        roster.addShift(shift);
        rosters.put(key,roster);
    }
    public List<Roster> getAllRosters(){
        return new ArrayList<Roster>(rosters.values());
    }





}
