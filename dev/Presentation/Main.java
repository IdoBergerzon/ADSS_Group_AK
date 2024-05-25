package Presentation;
import Domain.Checking_Controller;
//import Domain.HR_manager;
import Domain.Worker;

import java.net.StandardSocketOptions;
import java.util.Scanner;

public class Main {


    public static void main(String[] args) {
        Checking_Controller check = new Checking_Controller();
        Scanner scanner = new Scanner(System.in);
        System.out.print("Please enter your ID: ");
        int userID = Integer.parseInt(scanner.nextLine());
        int worker=check.checking_ID(userID);//check the ID of the worker

        if(worker == 1){ //in case of HR manager
            HR_Main main1=new HR_Main();
            System.out.println("1.display worker details\n" +
                    "2.edit worker details\n" +
                    "3.add new worker\n" +
                    "4.add new role for worker\n" +
                    "5.create new role\n" +
                    "6.display worker that can work by shift\n" +
                    "7.create new roster\n" +
                    "8.create new shift\n" +
                    "9.exit\n");

            System.out.print("Please enter your choice (1-8): ");
            int choice = scanner.nextInt();


            // Use a switch statement to handle the user's choice
            switch (choice) {
                case 1:
                    main1.Display_Worker_Details();
                    break;
                case 2:
                    main1.Edit_Worker_Details();
                    break;
                case 3:
                    main1.addNewWorker();
                    break;
                case 4:
                    main1.addNewRoleForWorker();
                    break;
                case 5:
                    main1.createNewRole();
                    break;
                case 6:
                    //displayWorkersByShift();
                    break;
                case 7:
                    //createNewRoster();
                    break;
                case 8:
                    //createNewShift();
                    break;
                case 9:
                    break;
                default:
                    System.out.println("Invalid choice. Please enter a number between 1 and 9.");
                    break;
            }

            // Close the scanner
            scanner.close();
        }
        if(worker == 0){//in case of regular worker
            Worker_Main main2=new Worker_Main();

            System.out.println("1.display details\n" +
                    "2.add request\n" +
                    "3.edit exist request\n" +
                    "4.past shifts\n" +
                    "5.retire massage\n"+
                    "6.exit\n");
            System.out.print("Please enter your choice (1-5): ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    //displayWorkerDetails();
                    break;
                case 2:
                    //editWorkerDetails();
                    break;
                case 3:
                    //addNewWorker();
                    break;
                case 4:
                    //addNewRoleForWorker();
                    break;
                case 5:
                    //createNewRole();
                case 6:
                    break;
                default:
                    System.out.println("Invalid choice. Please enter a number between 1 and 6.");
                    break;
            }
        }

    }
}
