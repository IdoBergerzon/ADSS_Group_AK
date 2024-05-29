package Data;

import Domain.Roster;
import Domain.Shift;


import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

public class InMemoryShiftRepository {
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

    public Shift[][] getCurrentShifts(int branch_id, int week) {
        return rosters.get(new Pair<>(branch_id,week)).getShift_arrangment();
    }

    public void addRoster(Roster roster) {
        if (rosters.get(new Pair(roster.getBranch(), roster.getWeek()))!=null){
            throw new IllegalArgumentException("Roster already exists");
        }
        rosters.put(new Pair(roster.getBranch(), roster.getWeek()),roster);
    }




    /////// need to create the whole function to add new Shift and one more function to add roster
//    public void addShift(Shift shift) {
//        Shift[][] arr = currentShifts.containsKey(new Pair<>(shift.getBranch_id(),shift.getWeekNum()));
//        if(arr == null) {
//            arr=new Shift[7][2];
//        }
//        int shift_type = shift.getShift_type() ? 1 : 0;
//        arr[shift.getDay_of_week()][shift_type] = shift;
//    }


}
