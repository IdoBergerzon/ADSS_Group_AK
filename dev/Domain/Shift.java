package Domain;
import java.util.Date;

public class Shift {
    private int weekNum;
    private int branch_id;
    private int Day_of_week;
    private boolean shift_type;//true=morning,false=evening
    private Worker[][] shift_workers;


    public Shift(int weekNum, int branch_id, int day_of_week, boolean shift_type, Worker[][] shift_workers) {
        this.weekNum = weekNum;
        this.branch_id = branch_id;
        Day_of_week = day_of_week;
        this.shift_type = shift_type;
        this.shift_workers = shift_workers;
    }


    public int getWeekNum() {
        return weekNum;
    }

    public int getBranch_id() {
        return branch_id;
    }

    public int getDay_of_week() {
        return Day_of_week;
    }







    public boolean isShift_type() {
        return shift_type;
    }

    public void setShift_type(boolean shift_type) {
        this.shift_type = shift_type;
    }
}
