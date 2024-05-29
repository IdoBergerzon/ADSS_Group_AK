package Presentation;

import Domain.HR_Controller;
import Domain.Worker;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class HR_Main {
    private HR_Controller hr_controller;

    public HR_Main() {
        this.hr_controller = new HR_Controller();
    }

    public void addNewWorker(){
        Map<String,String> myMap = new HashMap<String,String>();
        Scanner sc = new Scanner(System.in);
        String newWorker = "";
        //enter all worker details by order id, name, ...
        System.out.println("Enter worker id;");
        newWorker += sc.nextLine()+ ",";
        System.out.println("Enter worker name:");
        newWorker += sc.nextLine()+ ",";
        System.out.println("Enter worker hourly wage:");
        newWorker += sc.nextLine()+ ",";
        System.out.println("Enter worker monthly wage:");
        newWorker += sc.nextLine()+ ",";
        System.out.println("Enter worker role:");
        newWorker += sc.nextLine()+ ",";
        System.out.println("Enter worker branch:");
        newWorker += sc.nextLine() + ",";
        System.out.println("Enter worker department:");
        newWorker += sc.nextLine() + ",";
        System.out.println("Enter managerID:");
        newWorker += sc.nextLine() + ",";


        hr_controller.Add_New_Worker(newWorker);


    }
    public void Display_Worker_Details(){
        Scanner scanner = new Scanner(System.in);
        while(true) {
            System.out.println("Insert ID");
            int id = scanner.nextInt();
            try {
                hr_controller.Display_Worker_Details(id);
                break;
            } catch (Exception e) {

            }
        }
    }

    public void addNewRoleForWorker(){
        Scanner sc = new Scanner(System.in);
        while(true) {
            System.out.println("Enter worker id:");
            int worker_id = sc.nextInt();
            System.out.println("Enter new worker's role id:");
            int role_id = sc.nextInt();
            try {
                hr_controller.addNewRoleForWorker(worker_id, role_id);
                break;
            } catch (Exception e) {
            }
        }
    }

    public void createNewRole(){
        Scanner sc = new Scanner(System.in);
        while(true) {
            System.out.println("Enter new role id:");
            int role_id = sc.nextInt();
            System.out.println("Enter new role name:");
            String role_name = sc.nextLine();
            try{
                hr_controller.cteateNewRole(role_id, role_name);
                break;
            } catch (Exception e){

            }
        }
    }

    public String Edit_Worker_Details(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Insert ID");
        int id_to_update=scanner.nextInt();
        return hr_controller.Edit_Worker_Details(id_to_update);
    }
    public void displayWorkersByShift(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("insert day (1-sunday,2-monday...)\n");
        int day=scanner.nextInt();
        System.out.println("insert shift type (0-morning,1-evening...)\n");
        int shift_type=scanner.nextInt();
        hr_controller.displayWorkersByShift(day,shift_type);
    }

    public void createNewShift(int branch_id, int day, int shift_type){
        Scanner sc = new Scanner(System.in);
        System.out.println("Now you're creating shift at day " + day+1 + " and shift type: " + shift_type + "\n");

        String[] roles=hr_controller.getAllRoles().split(",");

        int[][] shiftWorkers=new int[roles.length][];
        for(int i=0;i<roles.length;i++){
            System.out.println("How many "+ roles[i] + " for this shift? ");
            int amount=sc.nextInt();
            shiftWorkers[i]=new int[amount];
            for(int j=0;j<amount;j++){
                System.out.println("Enter " + j+1 + " " + roles[i] +" worker id:\n");
                int worker_id=sc.nextInt();
                shiftWorkers[i][j]=worker_id;
            }
        }
        hr_controller.createNewShift(branch_id,day,shift_type,shiftWorkers);
    }


    public void createNewRoster(){
        Scanner sc = new Scanner(System.in);
        System.out.println("insert branch id\n");
        int branch_id=sc.nextInt();
        if(!hr_controller.isBranch(branch_id)){
            System.out.println("branch doesn't exist\n");
            return;
        }
        hr_controller.createNewRoster(branch_id);
        /// אנחנו צריכים לממש פונקציה בזיכרון שתממש את ההכנסה של שיפט חדש לזיכרון
        for(int day=0; day < 7; day++){
            for(int shift_type=0; shift_type < 2; shift_type++) {
                createNewShift(branch_id, day, shift_type);
            }
        }


    }
}
