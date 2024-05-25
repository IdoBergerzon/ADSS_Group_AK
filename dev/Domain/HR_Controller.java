package Domain;
import java.util.Date;
import java.util.Scanner;

import Data.InMemoryWorkerRepository;

public class HR_Controller {

    private final InMemoryWorkerRepository workers_memory=InMemoryWorkerRepository.getInstance();

    public int Add_New_Worker(String details) {
        //ID,name,hourly wage, monthly wage,start date,role,branch,dayoff=0,department
        String[] string_details = details.split(",");

        Date curr = new Date();
        int new_ID = Integer.parseInt(string_details[0]);
        if (workers_memory.getWorkerById(new_ID) == null) {
            return -1;
        }
        String new_name = string_details[1];
        int new_monthly_wage = Integer.parseInt(string_details[2]);
        int new_hourly_wage = Integer.parseInt(string_details[3]);
        int managerID=Integer.parseInt(string_details[4]);


        //check if the role is exist
        int role_ID = Integer.parseInt(string_details[5]);
        Role role;
        if (workers_memory.getRoleByID(role_ID) == null) {
            return -1;
        } else {
            role = workers_memory.getRoleByID(role_ID);
        }
        ;

        //check if branch is exist

        int branch_ID = Integer.parseInt(string_details[6]);
        Branch new_branch;
        if (workers_memory.getBranchByID(branch_ID) == null) {
            return -1;
        } else {
            new_branch = workers_memory.getBranchByID(branch_ID);

        }
        String department = string_details[7];


        Worker new_worker = new Worker(new_ID, new_name,new_monthly_wage, new_hourly_wage, curr,managerID, role, new_branch, department);
        return 1;
    };

    public String Display_Worker_Details(int id){
        String roles="";
        Worker result=workers_memory.getWorkerById(id);
        if(result==null){
            return "Worker doesn't exist\n";
        }
        String result_str="";
        result_str+="Worker ID: "+result.getId()+"\n";
        result_str+="Worker name: "+result.getName()+"\n";
        result_str+="Monthly wage: "+result.getMonthly_wage()+"\n";
        result_str+="Hourly wage: "+result.getHourly_wage()+"\n";
        result_str+="Start date: "+result.getStart_Date()+"\n";
        result_str+="Direct manager ID: "+result.getDirect_manager()+"\n";
        result_str+="Roles: ";
        for(int i=0;i<result.getRoles().length;i++){
            result_str+=result.getRoles()[i]+", ";
            if(i==result.getRoles().length-1){
                result_str+=result.getRoles()[i];
            }
        }
        result_str+=result.getWork_branch().getBranch_name()+"\n";
        result_str+=result.getDepartement()+"\n";

        return result_str;

    }
    public String Edit_Worker_Details(int id){
        Worker to_update=workers_memory.getWorkerById(id);
        if(to_update==null){
            return "Worker doesn't exist\n";
        }
        Scanner scanner = new Scanner(System.in);
        System.out.println("what you want to update for WorkerID: "+to_update.getId()+" ?\n"
        +"1.name\n"+
        "2.monthly wage\n"
        +"3.hourly wage\n"
        +"4.direct manager ID\n"
        +"5.departement\n");
        int choice = scanner.nextInt();

        switch (choice) {
            case 1:
                System.out.println("insert new name: \n");
                String new_name=scanner.nextLine();
                to_update.setName(new_name);
                return "update name success \n";

            case 2:
                System.out.println("insert new monthly wage: \n");
                int new_monthly_wage=scanner.nextInt();
                to_update.setMonthly_wage(new_monthly_wage);
                return "update wage success \n";

            case 3:
                System.out.println("insert new hourly wage: \n");
                int new_hourly_wage=scanner.nextInt();
                to_update.setHourly_wage(new_hourly_wage);
                return "update wage success \n";

            case 4:
                System.out.println("insert new manager ID: \n");
                int new_manager_ID=scanner.nextInt();
                to_update.setDirect_manager(new_manager_ID);
                return "update manager ID success \n";

            case 5:
                System.out.println("insert new department: \n");
                String new_department=scanner.nextLine();
                to_update.setDepartement(new_department);
                return "update department success \n";

            default:
                System.out.println("Invalid choice. Please enter a number between 1 and 5.");
                break;
        }
        return "";


    }


    public void addNewRoleForWorker(int workerID, int roleID){
        if (workers_memory.getWorkerById(workerID) == null) {
            throw new IllegalArgumentException("Worker with ID " + workerID + " does not exist");
        } if (workers_memory.getRoleByID(roleID) == null) {
            throw new IllegalArgumentException("Role with ID " + roleID + " does not exist");
        }

        workers_memory.getWorkerById(workerID).addNewRole(workers_memory.getRoleByID(roleID));
    }

    public void cteateNewRole(int roleId, String roleName){
        Role newRole= new Role(roleId,roleName);
        workers_memory.addRole(newRole);

    }
}
