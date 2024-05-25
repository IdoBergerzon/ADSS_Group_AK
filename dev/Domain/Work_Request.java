package Domain;

import java.util.Date;

public class Work_Request {
    private Worker worker;
    private Boolean[][] request;
    private int week;

    public Work_Request(Worker worker, Boolean[][] request, int week) {
        this.worker = worker;
        this.request = request;
        this.week = week;
    }

    public Worker getWorker() {
        return worker;
    }

    public Boolean[][] getRequest() {
        return request;
    }

    public int getWeek() {
        return week;
    }

    public void setWorker(Worker worker) {
        this.worker = worker;
    }

    public void setRequest(Boolean[][] request) {
        this.request = request;
    }

    public void setWeek(int week) {
        this.week = week;
    }
}
