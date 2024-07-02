package Domain;
import java.time.LocalDate;


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
