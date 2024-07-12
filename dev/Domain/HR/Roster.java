package Domain.HR;


import Domain.Transport.ALocation;

public class Roster {
    private int branch_id;
    private Shift[][] shift_arrangment;
    private int week;

    public Roster(int branch_id, Shift[][] shift_arrangment) {
        this.branch_id = branch_id;
        this.shift_arrangment = shift_arrangment;
        //Getting the week number
        this.week = Week.getWeek();

    }
    public Roster(int branch_id) {
        this.branch_id = branch_id;
        //Getting the week number
        this.week = Week.getWeek();
        shift_arrangment = new Shift[7][2];

    }

    public Roster(){

    }


    public int getBranch_id() {
        return branch_id;
    }

    public void setBranch_id(int  branch_id) {
        this.branch_id = branch_id;
    }

    public Shift[][] getShift_arrangment() {
        return shift_arrangment;
    }

    public void setShift_arrangment(Shift[][] shift_arrangment) {
        this.shift_arrangment = shift_arrangment;
    }

    public int getWeek() {
        return week;
    }

    public void setWeek(int week) {
        this.week = week;
    }

    public void addShift(Shift shift) {
        if (shift_arrangment[shift.getDay_of_week()][shift.getShift_type()] != null) {
            throw new IllegalArgumentException("Shift already exists");
        }
        shift_arrangment[shift.getDay_of_week()][shift.getShift_type()]=shift;
    }

    public void setShift(Shift shift) {
        if (shift_arrangment[shift.getDay_of_week()][shift.getShift_type()] == null) {
            throw new IllegalArgumentException("Shift doesn't exists");
        }
        shift_arrangment[shift.getDay_of_week()][shift.getShift_type()]=shift;
    }

    public String toString(){
        String str = "Roster's week: " + week + "\n";
        str+="Branch: " + branch_id + "\n";
        for(int i=0; i<7; i++){
            for(int j=0; j<2; j++){
                str+=shift_arrangment[i][j]+"\n";
            }
        }
        return str;
    }


}
