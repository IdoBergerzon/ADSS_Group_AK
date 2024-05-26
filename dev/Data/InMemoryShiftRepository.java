package Data;

import Domain.Roster;
import Domain.Shift;


import java.util.HashMap;
import java.util.Map;

public class InMemoryShiftRepository {
    private final Map<Pair, Roster> rosters;

    private Map<Pair,Shift[][]> currentShifts;

    private InMemoryShiftRepository() {
        rosters = new HashMap<>();
        currentShifts = new HashMap<>();
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

    public Shift[][] getCurrentShifts(int branch_id, int week) {
        return currentShifts.get(new Pair<>(branch_id,week));
    }
    /////// need to create the whole function to add new Shift and one more function to add roster
    public void addShift(Shift shift) {
        Shift[][] arr = currentShifts.containsKey(new Pair<>(shift.getBranch_id(),shift.getWeekNum()))
        if(arr) {
            arr=new Shift[7][2];

            arr[shift.getDay_of_week()][shift.setShift_type()] =shift;

        }
    }


}
