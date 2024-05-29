package Domain;


import java.util.List;

public class Shift {
    private int weekNum;
    private int branch_id;
    private int Day_of_week;
    private int shift_type;//true=morning,false=evening
    private Worker[] shift_workers;
    private List<Role> shift_Roles;


    public Shift(int branch_id, int day_of_week, int shift_type, Worker[] shift_workers, List<Role> shift_Roles) {
        this.weekNum = Week.getWeek();
        this.branch_id = branch_id;
        this.Day_of_week = day_of_week;
        this.shift_type = shift_type;
        this.shift_workers = shift_workers;
        this.shift_Roles= shift_Roles;
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

    public int getShift_type() {
        return shift_type;
    }

    public void setShift_type(int shift_type) {
        this.shift_type = shift_type;
    }
}
