package Presentation;

import Domain.HR_Controller;
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
        hr_controller.createWorker(newWorker);

;
    }
}
