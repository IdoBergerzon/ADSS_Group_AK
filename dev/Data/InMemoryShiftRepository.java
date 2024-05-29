package Data;

import Domain.Roster;
import Domain.Shift;


import java.util.HashMap;
import java.util.Map;

public class InMemoryShiftRepository implements Shift_Respository{
    private final Map<Pair, Roster> rosters;



    private InMemoryShiftRepository() {
        rosters = new HashMap<>();

    }

    private static class InShiftHolder {
        private final static InMemoryShiftRepository INSTANCE = new InMemoryShiftRepository();
    }

    /**
     * public static method that returns the single instance of the class
     */

    public static InMemoryShiftRepository getInstance() {
        return InMemoryShiftRepository.InShiftHolder.INSTANCE;
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


}
