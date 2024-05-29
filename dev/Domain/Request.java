package Domain;
import java.time.LocalDate;


public class Request {
    private Worker worker;
    private Boolean[][] weeklyRequest;
    private int week;
    private LocalDate date;

    public Request(Worker worker, Boolean[][] weeklyRequest) {
        this.worker = worker;
        this.weeklyRequest = weeklyRequest;
        this.week = Week.getWeek();

        //Getting the week start date
        LocalDate today = LocalDate.now();
        // Determine the day of the week for the current date
        java.time.DayOfWeek currentDayOfWeek = today.getDayOfWeek();

        // Calculate the number of days to add to get to the next Sunday
        int daysToAdd = java.time.DayOfWeek.SUNDAY.getValue() - currentDayOfWeek.getValue();
        if (daysToAdd <= 0) {
            daysToAdd += 7;
        }
        LocalDate dateWeekStart = today.plusDays(daysToAdd);

    }

    public Worker getWorker() {
        return worker;
    }

    public Boolean[][] getRequest() {
        return weeklyRequest;
    }

    public int getWeek() {
        return week;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setWorker(Worker worker) {
        this.worker = worker;
    }

    public void setRequest(Boolean[][] newRequest) {
        this.weeklyRequest = newRequest;
    }

    public void setWeek(int week) {
        this.week = week;
    }
}
