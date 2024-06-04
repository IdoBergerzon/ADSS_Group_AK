package Data;

import Domain.Roster;
import Domain.Shift;

import java.util.List;

public interface Shift_Respository {

    Roster getRoster(int branch_id, int week);
    void addRoster(Roster roster);
    void setShift(Shift shift);
    List<Roster> getAllRosters();
}
