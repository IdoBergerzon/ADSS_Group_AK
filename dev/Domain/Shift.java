package Domain;
import java.util.Date;

public class Shift {
    private int shift_ID;
    private int amount_of_workers;
    private Worker shift_manager;
    private Worker[] shift_workers;
    private boolean shift_type; //true=morning,false=evening
    private Date date_of_shift;

    public Shift(int shift_ID, int amount_of_workers, Worker shift_manager, Worker[] shift_workers, boolean shift_type, Date date_of_shift) {
        this.shift_ID = shift_ID;
        this.amount_of_workers = amount_of_workers;
        this.shift_manager = shift_manager;
        this.shift_workers = shift_workers;
        this.shift_type = shift_type;
        this.date_of_shift = date_of_shift;
    }

    public int getShift_ID() {
        return shift_ID;
    }

    public void setShift_ID(int shift_ID) {
        this.shift_ID = shift_ID;
    }

    public int getAmount_of_workers() {
        return amount_of_workers;
    }

    public void setAmount_of_workers(int amount_of_workers) {
        this.amount_of_workers = amount_of_workers;
    }

    public Worker getShift_manager() {
        return shift_manager;
    }

    public void setShift_manager(Worker shift_manager) {
        this.shift_manager = shift_manager;
    }

    public Worker[] getShift_workers() {
        return shift_workers;
    }

    public void setShift_workers(Worker[] shift_workers) {
        this.shift_workers = shift_workers;
    }

    public Date getDate_of_shift() {
        return date_of_shift;
    }

    public void setDate_of_shift(Date date_of_shift) {
        this.date_of_shift = date_of_shift;
    }

    public boolean isShift_type() {
        return shift_type;
    }

    public void setShift_type(boolean shift_type) {
        this.shift_type = shift_type;
    }
}