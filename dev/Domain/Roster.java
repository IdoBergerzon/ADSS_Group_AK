package Domain;

public class Roster {
    private Branch branch;
    private Shift[][] shift_arrangment;
    private int week_number;

    public Roster(Branch branch, Shift[][] shift_arrangment, int week_number) {
        this.branch = branch;
        this.shift_arrangment = shift_arrangment;
        this.week_number = week_number;
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

    public int getWeek_number() {
        return week_number;
    }

    public void setWeek_number(int week_number) {
        this.week_number = week_number;
    }


}
