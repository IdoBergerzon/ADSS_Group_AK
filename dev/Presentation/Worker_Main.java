package Presentation;

import Domain.Worker_Controller;

import java.util.Scanner;

public class Worker_Main {
    private Worker_Controller controller;
    private int worker_id;

    public Worker_Main(int id) {
        this.controller = new Worker_Controller(id);
        this.worker_id=id;
    }
    public void displayMyDetails(){
        controller.displayMyDetails();
    }

    public void addRequest(){
        Boolean flag=true;
        String morningRequest="";
        String eveningRequest="";
        while (flag) {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Please enter your work request for next week morning shift starting from Sunday to Saturday? (Y/N)");
            morningRequest = scanner.nextLine();
            System.out.println("Please enter your work request for next week evening shift starting from Sunday to Saturday? (Y/N)");
            eveningRequest = scanner.nextLine();
            if(morningRequest.length()==7 && eveningRequest.length()==7){
                for(int i=0;i<morningRequest.length();i++){
                    if((morningRequest.charAt(i)=='Y' || morningRequest.charAt(i)=='N')&&((eveningRequest.charAt(i)=='Y' || eveningRequest.charAt(i)=='N'))){
                        flag=false;
                    }

                }
                if(flag){
                    System.out.println("you enter wrong characters, please try again\n");
                }
            }
            else {
                System.out.println("you enter less/more days than you need to,please try again \n");
            }
        }
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
        try {
            controller.addRequest(request);
            System.out.println("Great you submitted a new request");
        }catch (Exception e){
            System.out.println("request for this week already exist");
        }
    }
    public void EditRequest(){
        try {
            controller.getrequestById();
        }catch (Exception e){
            System.out.println("request for this week does not exist, please add new request");
            return;
        }
        System.out.println("this is the prior request: \n"+controller.getrequestById());
        String morningRequest="";
        String eveningRequest="";
        Boolean flag=true;
        while(flag) {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Please enter your work request for next week morning shift starting from Sunday to Saturday? (Y/N)");
            morningRequest = scanner.nextLine();
            System.out.println("Please enter your work request for next week evening shift starting from Sunday to Saturday? (Y/N)");
            eveningRequest = scanner.nextLine();
            if(morningRequest.length()==7 && eveningRequest.length()==7){
                for(int i=0;i<morningRequest.length();i++){
                    if((morningRequest.charAt(i)=='Y' || morningRequest.charAt(i)=='N')&&((eveningRequest.charAt(i)=='Y' || eveningRequest.charAt(i)=='N'))){
                        flag=false;
                    }

                }
                if(flag){
                    System.out.println("you enter wrong characters, please try again\n");
                }
            }
            else {
                System.out.println("you enter less/more days than you need to,please try again \n");
            }
        }
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

        try {
            controller.EditRequest(request);
        }catch (Exception e){
            System.out.println("request does not exist");
        }
    }
    public void ShowPastShifts(){
        System.out.println(controller.ShowPastShifts());
    }

    public void ShowCurrRoster() {
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println("Please enter week for Roster you would like to see: \n");
            try {
                System.out.println(controller.ShowPastRoster(sc.nextInt()));
                break;
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }





    public void RetireMassage(){
        Scanner sc = new Scanner(System.in);
        System.out.println("We are sorry to see you leave, are you sure you want to resign? (y/n)");
        String answer = sc.nextLine();
        if(answer.equals("y")){
            controller.RetireMassage();
            System.out.println("Bye Bye!!");
        } else{
            System.out.println("You choose to stay, Awesome!!!");
        }
    }

}
