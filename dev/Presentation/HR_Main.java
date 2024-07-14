package Presentation;

import Domain.HR.HR_Controller;


import java.util.*;

public class HR_Main {
    private HR_Controller hr_controller;

    public HR_Main() {
        this.hr_controller = new HR_Controller();
    }

    public void addNewWorker(){
        Scanner sc = new Scanner(System.in);
        String newWorkerDetails = "";
        //enter all worker details by order id, name, ...
        System.out.println("Enter worker id:");
        newWorkerDetails += sc.nextLine()+ ",";
        System.out.println("Enter worker name:");
        newWorkerDetails += sc.nextLine()+ ",";
        System.out.println("Enter worker hourly wage:");
        newWorkerDetails += sc.nextLine()+ ",";
        System.out.println("Enter worker monthly wage:");
        newWorkerDetails += sc.nextLine()+ ",";
        System.out.println("Enter worker role ID:");
        newWorkerDetails += sc.nextLine()+ ",";
        System.out.println("Enter worker branch:");
        newWorkerDetails += sc.nextLine() + ",";
        System.out.println("Enter worker department:");
        newWorkerDetails += sc.nextLine() + ",";
        System.out.println("Enter managerID:");
        newWorkerDetails += sc.nextLine() + ",";
        System.out.println("Enter Bank details:(format: BANK_NAME:ACCOUNT_NUMBER) ");
        newWorkerDetails += sc.nextLine() + ",";


        hr_controller.Add_New_Worker(newWorkerDetails);


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
                System.out.println("Worker ID doesn't exist, please try again\n");
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
                System.out.println(e.getMessage()+"\n");
            }
        }
    }

    public void createNewRole(){
        Scanner sc = new Scanner(System.in);
        while(true) {
            System.out.println("Enter new role id:");
            int role_id = sc.nextInt();
            System.out.println("Enter new role name:");
            String role_name = sc.next();
            try{
                hr_controller.createNewRole(role_id, role_name);
                System.out.println("New role created\n");
                break;
            } catch (Exception e){
                System.out.println(e.getMessage());
            }
        }
    }

    public void Edit_Worker_Details(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Insert ID");
        int id_to_update=scanner.nextInt();

        System.out.println("what you want to update for WorkerID: "+id_to_update+" ?\n"
                +"1.name\n"+
                "2.monthly wage\n"
                +"3.hourly wage\n"
                +"4.direct manager ID\n"
                +"5.departement\n"
                +"6.bank details\n");
        int choice = scanner.nextInt();

        switch (choice){
            case 1:
                String newName;
                System.out.println("insert new name: ");
                newName = scanner.next();
                System.out.println(hr_controller.Edit_Worker_Details(id_to_update,choice,newName));
                break;

            case 2:
                String new_monthly_wage;
                System.out.println("insert new monthly wage: \n");
                new_monthly_wage = scanner.next();
                System.out.println(hr_controller.Edit_Worker_Details(id_to_update,choice,new_monthly_wage));
                break;


            case 3:
                System.out.println("insert new hourly wage: \n");
                String new_hourly_wage=scanner.next();
                System.out.println(hr_controller.Edit_Worker_Details(id_to_update,choice,new_hourly_wage));
                break;

            case 4:
                System.out.println("insert new manager ID: \n");
                String new_manager_ID=scanner.next();
                System.out.println(hr_controller.Edit_Worker_Details(id_to_update,choice,new_manager_ID));
                break;
            case 5:
                System.out.println("insert new department: \n");
                String new_department=scanner.next();
                System.out.println(hr_controller.Edit_Worker_Details(id_to_update,choice,new_department));
                break;
            case 6:
                System.out.println("insert new Bank details:(format: BANK_NAME:ACCOUNT_NUMBER) ");
                String new_bank_details=scanner.next();
                System.out.println(hr_controller.Edit_Worker_Details(id_to_update,choice,new_bank_details));
                break;
            default:
                System.out.println("Invalid choice. Please enter a number between 1 and 5.");
                break;
        }

    }

    public void displayWorkersByShift(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("insert day (1-sunday,2-monday...)\n");
        int day=scanner.nextInt();
        System.out.println("insert shift type (0-morning,1-evening...)\n");
        int shift_type=scanner.nextInt();
        hr_controller.displayWorkersByShift(day,shift_type);
    }

    private void createNewShift(int branch_id, int day, int shift_type) throws Exception {
        Scanner sc = new Scanner(System.in);

        String str_shift_type = (shift_type == 0) ? "morning" : "evening";
        String[] daysOfWeek = {"Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"};
        String str_day = daysOfWeek[day];
        List<Integer> roles_for_shift = new ArrayList<>();

        String is_open = "0";
        while(true) {
            System.out.println("Now you're creating shift at " + str_day + " and shift type: " + str_shift_type + "\n" +
                    "Is branch-" + branch_id + " will be open that day?(Y/N)\n");
            is_open = sc.nextLine();
            if(is_open.equals("Y") || is_open.equals("N")){
                break;
            } else{
                System.out.println("Please enter a valid choice- Y or N\n");
            }
        }

        if(is_open.equals("N")){
            System.out.println("Great, everyone gets a day off");
            hr_controller.createNewShift(branch_id,day,shift_type,new int[0], roles_for_shift);
            return;
        }

        else if(is_open.equals("Y")) {
            roles_for_shift.add(2); //in every shift must have a shift manager
            roles_for_shift.add(20);//in every shift must have a driver
            roles_for_shift.add(3);//in every shift must have a storekeeper
            int answer;
            while (true) {
                System.out.println("enter role id you need to this shift\n" +
                        "remember each shift contains Manager (id-2), Driver(id-20), Storekeeper(id-3)\n" +
                        "(if you finish insert 0): ");
                answer = sc.nextInt();
                if (answer == 0) {
                    break;
                }
                roles_for_shift.add(answer);

            }
            int[] shiftWorkers = new int[roles_for_shift.size()];

            for (int i = 0; i < roles_for_shift.size(); i++) {
                while (true) {
                    System.out.println("Enter the ID of an worker who can fill the roll ID: " + roles_for_shift.get(i) + " for this shift \n");
                    answer = sc.nextInt();

                    // First we will check if worker already sign for this shift, then we will check if he's available for work
                    boolean is_exist = false;
                    for (int j = 0; j < i; j++) {
                        if (shiftWorkers[j] == answer) {
                            System.out.println("Worker with ID " + answer + " already exists in shift \n" +
                                    "please choose another worker ID");
                            is_exist = true;
                            break;
                        }
                    }

                    boolean is_available = hr_controller.isAvailable(answer, day, shift_type);
                    if (!is_available) {
                        System.out.println("Worker with ID " + answer + " is not available for this shift \n" +
                                "please choose another worker ID");
                    }

                    if (!is_exist && is_available) {
                        break;
                    }
                }
                shiftWorkers[i] = answer;
            }

            hr_controller.createNewShift(branch_id, day, shift_type, shiftWorkers, roles_for_shift);
        }
    }

    public void fireWorker(){
        Scanner sc = new Scanner(System.in);
        while(true) {
            System.out.println("Enter worker id:");
            int worker_id = sc.nextInt();
            try{
                hr_controller.fireWorker(worker_id);
                System.out.println("Worker fire completed\n");
                return;
            } catch (Exception e){
                System.out.println(e.getMessage()+"\n");
            }
        }


    }


    public void createNewRoster() throws Exception {
        Scanner sc = new Scanner(System.in);
        System.out.println("insert branch id\n");
        int branch_id;
        while (true) {

            branch_id=sc.nextInt();
            if(!hr_controller.isLocation(branch_id)){
                System.out.println("branch doesn't exist\n");
                return;
            }
            try {
                hr_controller.createNewRoster(branch_id);
                break;
            } catch (Exception e) {
               System.out.println("branch already has a roster for this week\n" +
                       "Would you like to create a new roster for another branch? (y/n)");
               String choice = sc.nextLine();
               if(choice.equals("n"))
                   return;
                else
                    System.out.println("Please enter a valid branch id:");
            }
        }
        for(int day=0; day < 7; day++){
            for(int shift_type=0; shift_type < 2; shift_type++) {
                while(true){
                    try {
                        createNewShift(branch_id, day, shift_type);
                        break;
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                }
            }
        }

        System.out.println("Great, the Roster is ready");
        hr_controller.setWeek();



    }
}
