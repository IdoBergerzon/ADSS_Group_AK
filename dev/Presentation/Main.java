package Presentation;
import Domain.Checking_Controller;


import java.util.Scanner;

public class Main {

    private static int counter=0;
    public static void main(String[] args) throws Exception {
        int choice;
        Scanner scanner = new Scanner(System.in);
        Checking_Controller check = new Checking_Controller();
        while (true) {
            if(counter==0){
                System.out.print("Please choose one of the following\n" +
                        "1.Start system without any details \n" +
                        "2.Start system with details  \n" +
                        "3.exit \n");
                choice = scanner.nextInt();

                switch (choice) {
                    case 1:
                        counter++;
                        break;
                    case 2:
                        check.startWithObject();
                        counter++;
                        break;
                    case 3:
                        return;
                    default:
                        System.out.println("Please enter valid input\n");
                        break;

                }
            }
            else {
                counter+=1;
                System.out.print("1.Enter id to make action \n" +
                        "2.exit \n");
                choice = scanner.nextInt();
                switch (choice) {
                    case 1:
                        System.out.print("Please enter your ID: \n");
                        int userID = scanner.nextInt();
                        int is_manager = check.checking_ID(userID);//check the ID of the worker

                        if (is_manager == 1) { //in case of HR manager
                            hr_menu(userID);
                        } else if (is_manager == 0) {//in case of regular worker
                            worker_menu(userID);
                        } else{
                            System.out.println("The ID you enter doesn't belong to an existing worker\n");
                        }
                        break;
                    case 2:
                        return;
                    default:
                        System.out.println("Please enter valid input\n");
                        break;

                }

            }
        }
    }



    public static void hr_menu(int worker_id) throws Exception {
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
                    "8.to fire an worker\n" +
                    "9.exit\n");

            System.out.print("Please enter your choice (1-9): \n");
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
                    main1.createNewRoster();
                    break;
                case 8:
                    main1.fireWorker();
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
        Worker_Main main2=new Worker_Main(worker_id);
        while (true) {
            System.out.println("1.display details\n" +
                    "2.add request\n" +
                    "3.edit exist request\n" +
                    "4.Show past shifts\n" +
                    "5.Show current/past roster\n" +
                    "6.retire massage\n" +
                    "7.exit\n");
            System.out.print("Please enter your choice (1-7): \n");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    main2.displayMyDetails();//done
                    break;
                case 2:
                    main2.addRequest();//done
                    break;
                case 3:
                    main2.EditRequest();//done
                    break;
                case 4:
                    main2.ShowPastShifts();//done
                    break;
                case 5:
                    main2.ShowCurrRoster();//done
                    break;
                case 6:
                    main2.RetireMassage();
                    return;
                case 7:
                    return;
                default:
                    System.out.println("Invalid choice. Please enter a number 1 to 7.");
                    break;
            }
        }
    }
}



