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
        try {
            controller.addRequest(id, request);
        }catch (Exception e){
            System.out.println("request for this week already exist");
        }
    }
    public void EditRequest(int id){
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

        try {
            controller.EditRequest(id,request);
        }catch (Exception e){
            System.out.println("request does not exist");
        }
    }

    public void ShowCurrRoster(int worker_id){
        Scanner sc = new Scanner(System.in);
        System.out.println("Please enter your choice: \n" +
                "1.Show current week Roster\n" +
                "2.Show past Roster\n");
        int choice = sc.nextInt();
        if(choice == 1){
            controller.ShowCurrRoster(worker_id);
        } else if(choice == 2){
            System.out.println("Please enter week for past Roster: \n");
            try {
                controller.ShowPastRoster(worker_id, sc.nextInt());
            } catch (Exception e){
                System.out.println(e.getMessage());
            }
        }

    }

    public void RetireMassage(int worker_id){
        Scanner sc = new Scanner(System.in);
        System.out.println("We are sorry to see you leave, are you sure you want to resign? (y/n)");
        String answer = sc.nextLine();
        if(answer.equals("y")){
            controller.RetireMassage(worker_id);
            System.out.println("Bye Bye!!");
        } else{
            System.out.println("You choose to stay, Awesome!!!");
        }
    }

}
