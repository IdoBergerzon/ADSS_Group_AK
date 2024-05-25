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


        hr_controller.Add_New_Worker(newWorker);


    }
    public String Display_Worker_Details(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Insert ID");
        int id=scanner.nextInt();
        return hr_controller.Display_Worker_Details(id);
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

}
