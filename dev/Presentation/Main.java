package Presentation;
import Domain.Checking_Controller;
import Domain.Worker;


import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        int choice;
        Scanner scanner = new Scanner(System.in);
        Checking_Controller check = new Checking_Controller();
        while (true) {
            System.out.print("1.Enter id to make action \n" +
                    "2.exit \n");
            choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    System.out.print("Please enter your ID: ");
                    int userID = scanner.nextInt();
                    int is_manager = check.checking_ID(userID);//check the ID of the worker

                    if (is_manager == 1) { //in case of HR manager
                        hr_menu(userID);
                    }
                    if (is_manager == 0) {//in case of regular worker
                        worker_menu(userID);
                    }
                    break;
                case 2:
                    return;
                default:
                    System.out.println("Invalid choice. Please enter a number 1 to 2.");
                    break;

            }
            //scanner.close();

        }
    }



    public static void hr_menu(int worker_id){
        Scanner scanner = new Scanner(System.in);

        HR_Main main1=new HR_Main();
        while (true) {
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
                    main1.displayWorkersByShift();
                    break;
                case 7:
                    //createNewRoster();
                    break;
                case 8:
                    //createNewShift();
                    break;
                case 9:
                    return;

                default:
                    System.out.println("Invalid choice. Please enter a number 1 to 9.");
                    break;
            }
        }


    }

    public static void worker_menu(int worker_id){
        Scanner scanner = new Scanner(System.in);
        Worker_Main main2=new Worker_Main();
        while (true) {
            System.out.println("1.display details\n" +
                    "2.add request\n" +
                    "3.edit exist request\n" +
                    "4.past shifts\n" +
                    "5.retire massage\n" +
                    "6.exit\n");
            System.out.print("Please enter your choice (1-5): ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    main2.displayMyDetails(worker_id);
                    break;
                case 2:
                    //addRequest(worker_id);
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
                    return;
                default:
                    System.out.println("Invalid choice. Please enter a number 1 to 6.");
                    break;
            }
        }
    }
}



