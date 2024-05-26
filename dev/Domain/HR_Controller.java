package Domain;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import Data.InMemoryWorkerRepository;
import Data.InMemoryRequestRepository;
import Data.InMemoryShiftRepository;

public class HR_Controller {

    private final InMemoryWorkerRepository workers_memory=InMemoryWorkerRepository.getInstance();
    private final InMemoryRequestRepository requests_repository=InMemoryRequestRepository.getInstance();
    private final InMemoryShiftRepository shifts_repository=InMemoryShiftRepository.getInstance();

    public int Add_New_Worker(String details) {
        //ID,name,hourly wage, monthly wage,start date,role,branch,dayoff=0,department
        String[] string_details = details.split(",");

        Date curr = new Date();
        int new_ID = Integer.parseInt(string_details[0]);
        if (workers_memory.getWorkerById(new_ID) != null) {
            return -1;
        }
        String new_name = string_details[1];
        int new_hourly_wage = Integer.parseInt(string_details[2]);
        int new_monthly_wage = Integer.parseInt(string_details[3]);
        int role_ID = Integer.parseInt(string_details[4]);



        //check if the role is exist

        Role role;
        if (workers_memory.getRoleByID(role_ID) == null) {
            return -1;
        } else {
            role = workers_memory.getRoleByID(role_ID);
        }
        ;

        //check if branch is exist

        int branch_ID = Integer.parseInt(string_details[5]);
        Branch new_branch;
        if (workers_memory.getBranchByID(branch_ID) == null) {
            return -1;
        } else {
            new_branch = workers_memory.getBranchByID(branch_ID);

        }
        String department = string_details[6];
        int managerID=Integer.parseInt(string_details[7]);


        Worker new_worker = new Worker(new_ID, new_name,new_monthly_wage, new_hourly_wage, curr,managerID, role, new_branch, department);
        workers_memory.addWorker(new_worker);
        return 1;
    };

    public void Display_Worker_Details(int id){

        Worker result=workers_memory.getWorkerById(id);
        if(result==null){
            throw new IllegalArgumentException("Worker with ID " + id + " does not exist");
        }

        System.out.println(result.toString());

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
                String new_name;
                while (true) {
                    System.out.println("insert new name: \n");
                    try {
                        new_name = scanner.nextLine();
                        break;
                    } catch (Exception e) {
                        System.out.println("insert valid monthly name \n");
                    }
                }

                to_update.setName(new_name);
                return "update name success \n";



            case 2:
                int new_monthly_wage = 0;
                while(true) {
                    System.out.println("insert new monthly wage: \n");
                    try {
                        new_monthly_wage = scanner.nextInt();
                        break;
                    } catch (Exception e) {
                        System.out.println("insert valid monthly wage \n");
                    }
                }

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
    public void displayWorkersByShift(int day,int shift_type){
        String result_workers="";
        String roles="";
        Request[] request_list=requests_repository.getAllRequests();
        for(int i=0;i<request_list.length;i++){
            if(request_list[i].getRequest()[shift_type][day-1]){
                for(int j=0;j<request_list[i].getWorker().getRoles().length;j++){
                    roles+="Role ID: "+request_list[i].getWorker().getRoles()[j].getRoleID()+","+"Role name: "+request_list[i].getWorker().getRoles()[j].getName()+"   ,";
                    if (j==request_list[i].getWorker().getRoles().length-1){
                        roles+="Role ID: "+request_list[i].getWorker().getRoles()[j].getRoleID()+","+"Role name: "+request_list[i].getWorker().getRoles()[j].getName()+"\n";
                    }
                }
                result_workers+=request_list[i].getWorker().getId()+"\n"+request_list[i].getWorker().getName()+"\n"+roles+request_list[i].getWorker().getWork_branch()+"\n\n";
            }
        }
        System.out.println(result_workers);
    }

    public String getAllRoles(){
        List<Role> roles= workers_memory.getAllRoles();
        String roles_str=roles.get(0).getName();
        for(int i=0;i<roles.size();i++){
            roles_str+="," + roles.get(i).getName();
        }
        return roles_str;
    }
    public void createNewShift(int branch_id,int day,int shift_type,int[][] shiftWorkers){

        //if()
    }

    public Boolean isBranch(int branch_id){
        if(workers_memory.getBranchByID(branch_id) == null) {
            return false;
        }
        return true;
    }

    public void createNewRoster(int branch_id){
        Week.setWeek();
        int week=Week.getWeek();
        Shift[][] new_roster=Shift[2][7]
        for (int i=0;i<14;i++){

        }




    }
}
