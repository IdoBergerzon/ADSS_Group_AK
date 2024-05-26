package Presentation;

import Domain.Worker_Controller;

import java.util.Scanner;

public class Worker_Main {
    private Worker_Controller controller;

    public Worker_Main() {
        this.controller = new Worker_Controller();
    }
    public void displayMyDetails(int worker_id){
        controller.displayMyDetails(worker_id);
    }

    public void addRequest(int id){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please enter your work request for next week morning shift starting from Sunday to Saturday? (Y/N)");
        String morningRequest = scanner.nextLine();
        System.out.println("Please enter your work request for next week evening shift starting from Sunday to Saturday? (Y/N)");
        String eveningRequest = scanner.nextLine();
        Boolean[][] request= new Boolean[2][7];
        for(int i=0;i<7;i++){
            if(morningRequest.charAt(i)=='Y'){
                request[0][i]=true;
            } else{
                request[0][i]=false;
            }
            if(eveningRequest.charAt(i)=='Y'){
                request[1][i]=true;
            } else{
                request[1][i]=false;
            }
        }
        controller.addRequest(id, request);
    }

}
