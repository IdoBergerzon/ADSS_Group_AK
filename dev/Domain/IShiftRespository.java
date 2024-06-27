package Domain;

import java.util.List;

public interface IShiftRespository {

    Roster getRoster(int branch_id, int week);
    void addRoster(Roster roster);
    void setShift(Shift shift);
    List<Roster> getAllRosters();
}
