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
    public String toString(){
        String to_return="";
        String morning_request="";
        String evening_request="";
        for (int i=0;i<weeklyRequest[0].length;i++){
            for (int j=0;j<weeklyRequest.length;j++){
                if(j==0){
                    if(weeklyRequest[j][i]==true){
                        morning_request+="Y";
                    }
                    else morning_request+="N";
                }
                if(j==1){
                    if(weeklyRequest[j][i]==true){
                        evening_request+="Y";
                    }
                    else evening_request+="N";
                }

            }
        }
        to_return="morning requests: "+morning_request+"\n"+"evening requests: "+evening_request;
        return to_return;

    }
}
