package Domain;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class HR_Controller {

    private final WorkerRepository workers_memory= WorkerRepository.getInstance();
    private final RequestRepository requests_repository= RequestRepository.getInstance();
    private final ShiftRepository shifts_repository= ShiftRepository.getInstance();
    private final RoleRepository roles_repository= RoleRepository.getInstance();
    private final BranchRepository branchs_repository= BranchRepository.getInstance();

    public HR_Controller(){
        new Week();
    }
    public int Add_New_Worker(String details) {
        //ID,name,hourly wage, monthly wage,start date,role,branch,dayoff=0,department
        String[] string_details = details.split(",");

        Date curr = new Date();
        int new_ID = Integer.parseInt(string_details[0]);
        if (workers_memory.get(new_ID) != null) {
            System.out.println("worker ID already exist\n");
            return -1;
        }
        String new_name = string_details[1];
        int new_hourly_wage = Integer.parseInt(string_details[2]);
        int new_monthly_wage = Integer.parseInt(string_details[3]);
        int role_ID = Integer.parseInt(string_details[4]);



        //check if the role is exist

        Role role;
        if (roles_repository.get(role_ID) == null) {
            System.out.println("role doesn't exist\n");
            List<Role> list=roles_repository.getAllRoles();
            System.out.println("The possible roles are: ");
            for (Role role1 : list) {
                System.out.print(role1+"\n");
            }
            System.out.println("\n");
            return -1;
        } else {
            role = roles_repository.get(role_ID);
        }
        ;

        //check if branch is exist

        int branch_ID = Integer.parseInt(string_details[5]);
/*        Branch new_branch;
        if (branchs_repository.getBranchByID(branch_ID) == null) {
            System.out.println("branch doesn't exist\n");
            return -1;
        } else {
            new_branch = branchs_repository.getBranchByID(branch_ID);

        }*/
        String department = string_details[6];
        int managerID=Integer.parseInt(string_details[7]);
        if (workers_memory.get(managerID) == null) {
            System.out.println("manager ID doesn't exist\n");
            return -1;
        }
        String bank_details=string_details[8];


        Worker new_worker = new Worker(new_ID, new_name,new_monthly_wage, new_hourly_wage, curr,managerID, role, branch_ID, department,bank_details);
        workers_memory.add(new_worker);
        return 1;
    };

    public void Display_Worker_Details(int id){

        Worker result=workers_memory.get(id);
        if(result==null){
            throw new IllegalArgumentException("Worker with ID " + id + " does not exist");
        }

        System.out.println(result.toString());

    }

    public String Edit_Worker_Details(int id){
        Worker to_update=workers_memory.get(id);
        if(to_update==null){
            return "Worker doesn't exist\n";
        }
        Scanner scanner = new Scanner(System.in);
        System.out.println("what you want to update for WorkerID: "+to_update.getId()+" ?\n"
        +"1.name\n"+
        "2.monthly wage\n"
        +"3.hourly wage\n"
        +"4.direct manager ID\n"
        +"5.departement\n"
        +"6.bank details\n");
        int choice = scanner.nextInt();

        switch (choice) {
            case 1:
                String new_name;
                while (true) {
                    System.out.println("insert new name: ");
                    try {
                        new_name = scanner.next();
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
                to_update.setDirect_manager_ID(new_manager_ID);
                return "update manager ID success \n";

            case 5:
                System.out.println("insert new department: \n");
                String new_department=scanner.next();
                to_update.setDepartement(new_department);
                return "update department success \n";
            case 6:
                System.out.println("insert new Bank details:(format: BANK_NAME:ACCOUNT_NUMBER) ");
                String new_bank_details=scanner.next();
                to_update.setBank_details(new_bank_details);
                return "update bank details success \n";
            default:
                System.out.println("Invalid choice. Please enter a number between 1 and 5.");
                break;
        }
        return "";


    }


    public void addNewRoleForWorker(int workerID, int roleID){
        if (workers_memory.get(workerID) == null) {
            throw new IllegalArgumentException("Worker with ID " + workerID + " does not exist");
        } if (roles_repository.get(roleID) == null) {
            throw new IllegalArgumentException("Role with ID " + roleID + " does not exist");
        }

        workers_memory.get(workerID).addNewRole(roles_repository.get(roleID));
    }

    public void createNewRole(int roleId, String roleName){
        Role newRole= new Role(roleId,roleName);
        roles_repository.add(newRole);

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
                result_workers+="Worker ID: "+request_list[i].getWorker().getId()+"\n"+"Worker name: "+request_list[i].getWorker().getName()+"\n"+roles+request_list[i].getWorker().getBranch_id()+"\n\n";
            }
        }
        System.out.println(result_workers);
    }

/*    public String getAllRoles(){
        List<Role> roles= workers_memory.getAllRoles();
        String roles_str=roles.get(0).getName();
        for(int i=0;i<roles.size();i++){
            roles_str+="," + roles.get(i).getName();
        }
        return roles_str;
    }*/
    public void createNewShift(int branch_id,int day,int shift_type,int[] shiftWorkers,List<Integer> roles_for_shift) throws Exception {
        /// Declaration part
        Worker new_worker;
        Role new_role;
        Worker[] arrangment=new Worker[shiftWorkers.length];
        List<Role> roleList = new ArrayList<>();

        for (int i=0;i<shiftWorkers.length;i++){
            new_role = roles_repository.get(roles_for_shift.get(i));

            new_worker = workers_memory.get(shiftWorkers[i]);
            //Test to make sure adding this worker is not against the rules
            if (new_worker==null){
                throw new Exception("Worker with ID-" + shiftWorkers[i]+ " does not exist");
            } else if (new_worker.getBranch_id()!=branch_id) {
                throw new Exception("Worker  with ID-" + shiftWorkers[i]+ "does not works in this branch");
            } else{
                /// Making sure the worker can work in this role
                boolean roleExists = false;
                for (Role role : new_worker.getRoles()) {
                    if (role == new_role) { // Assuming Role class has a getID() method
                        roleExists = true;
                        break;
                    }
                }
                if (!roleExists) {
                    throw new Exception("Worker " + new_worker.getId() + " does not have the required role for the shift");
                }
                roleList.add(new_role);
                arrangment[i] = new_worker;
            }
            
        }

        //Creating the shift and add to memory
        Shift new_shift=new Shift(branch_id,day,shift_type,arrangment,roleList);
        shifts_repository.setShift(new_shift);

    }

    public Boolean isBranch(int branch_id){
        if(branchs_repository.get(branch_id) == null) {
            return false;
        }
        return true;
    }

    public void createNewRoster(int branch_id){
        Branch branch=branchs_repository.get(branch_id);
        Roster new_roster =new Roster(branch);

        shifts_repository.addRoster(new_roster);
        List<Worker> list=workers_memory.getAllWorkers();
        for (int i=0;i<list.size();i++){
            list.get(i).setDays_off(list.get(i).getDays_off()+0.25);
        }

    }

    public void setWeek(){
        Week.setWeek();
    }

    public void fireWorker(int worker_id){
       try {
           workers_memory.remove(worker_id);
       } catch (Exception e){
           throw e;
       }
    }

    public boolean isAvailable(int worker_id, int day, int shift_type){
        Request request = requests_repository.get(new Pair(worker_id, Week.getWeek()));
        if(request==null){
            return true;
        }

        Boolean[][] request_array = request.getRequest();
        if(request_array[shift_type][day]){

            return true;
        } else {
            return false;
        }
    }
}
