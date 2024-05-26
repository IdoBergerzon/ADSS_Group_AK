package Domain;

public class Request {
    private Worker worker;
    private Boolean[][] weeklyRequest;
    private int week;

    public Request(Worker worker, Boolean[][] weeklyRequest) {
        this.worker = worker;
        this.weeklyRequest = weeklyRequest;
        this.week = Week.getWeek();
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
