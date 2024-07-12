package Domain.HR;


public class Roster {
    private Branch branch;
    private Shift[][] shift_arrangment;
    private int week;

    public Roster(Branch branch, Shift[][] shift_arrangment) {
        this.branch = branch;
        this.shift_arrangment = shift_arrangment;
        //Getting the week number
        this.week = Week.getWeek();

    }
    public Roster(Branch branch) {
        this.branch = branch;
        //Getting the week number
        this.week = Week.getWeek();
        shift_arrangment = new Shift[7][2];

    }

    public Roster(){

    }


    public Branch getBranch() {
        return branch;
    }

    public void setBranch(Branch branch) {
        this.branch = branch;
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
        str+="Branch: " + branch + "\n";
        for(int i=0; i<7; i++){
            for(int j=0; j<2; j++){
                str+=shift_arrangment[i][j]+"\n";
            }
        }
        return str;
    }


}
