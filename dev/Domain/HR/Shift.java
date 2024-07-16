package Domain.HR;


import java.util.List;

public class Shift {
    private int weekNum;
    private int branch_id;
    private int Day_of_week;
    private int shift_type;
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

    public Shift() {

    }

    public Worker[] getShift_workers() {
        return shift_workers;
    }

    public List<Role> getShift_Roles() {
        return shift_Roles;
    }


    public void setWeekNum(int weekNum) {
        this.weekNum = weekNum;
    }

    public void setBranch_id(int branch_id) {
        this.branch_id = branch_id;
    }

    public void setDay_of_week(int day_of_week) {
        Day_of_week = day_of_week;
    }

    public void setShift_workers(Worker[] shift_workers) {
        this.shift_workers = shift_workers;
    }

    public void setShift_Roles(List<Role> shift_Roles) {
        this.shift_Roles = shift_Roles;
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

    public String toString(){
        String workerstring="";
        for(int i=0;i<this.shift_workers.length;i++){
            workerstring+= "the worker: "+shift_workers[i].getName() +" work as a: "+shift_Roles.get(i).getName()+"\n";
        }
        String day_type="";
        if(this.shift_type==0){
            day_type="morning";
        }else day_type="evening";
        return "Shift details: \n"+"branch: "+this.branch_id+"\n"+"week number: "+this.weekNum+"\n"+"shift day: "+this.Day_of_week+"\n"+"shift type: "+day_type+"\n"+workerstring+"\n\n";
    }
}
