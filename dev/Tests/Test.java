package Tests;

import Data.*;
import Domain.*;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class Test {
    public void testInsertWorker() {
        WorkerDAOImpl workerDAO = new WorkerDAOImpl();
        RoleDAOImpl roleDAO = new RoleDAOImpl();
        ObjectMapper objectMapper = new ObjectMapper();
        HR_Controller hr=new HR_Controller();

        //creating Worker//
        String newWorkerDetails = "";
        newWorkerDetails="20"+",ido"+",55"+",55"+",11"+",2"+",cashiers"+",2"+",leumi: 5555555";
        hr.Add_New_Worker(newWorkerDetails);

        //search the worker that insert//
        JsonNode JN=workerDAO.search(20);
        ((ObjectNode) JN).remove("active");
        Worker to_check=JsonNodeConverter.fromJsonNode(JN,Worker.class);
        System.out.println(to_check);

        //creating the expected worker//
        Role role=JsonNodeConverter.fromJsonNode(roleDAO.search(11),Role.class);
        Worker ido=new Worker(20,"ido",55,55,new Date(),2,role,2,"cashiers","leumi: 5555555");
        workerDAO.remove(20);
        System.out.println(ido);

        //call to test func//

        if(assertWorker(ido,to_check)){
            System.out.println("insert worker test passed");
        }
        else System.out.println("insert worker test doesn't passed");


    }
    public void testUpdateWorker() {
    WorkerRepository workers=WorkerRepository.getInstance();
    RoleRepository roles=RoleRepository.getInstance();
    //create new worker//
    Role role=new Role(999,"cash counter");
    Worker ido=new Worker(21,"ido",55,55,new Date(),2,role,2,"cashiers","leumi: 5555555");
    //insert worker to repository and database//
    workers.add(ido);
    //create worker that represent the updated worker//
    Worker updated=new Worker(21,"moshe",55,55,new Date(),2,role,2,"cashiers","leumi: 5555555");
    //update exist
    workers.updateWorker(updated);

    Worker to_check=workers.get(21);
    workers.remove(21);
    if(assertWorker(updated,to_check)){
        System.out.println("update worker test passed");
    }
    else System.out.println("update worker test doesn't passed");





    }

    private boolean assertWorker(Worker worker, Worker expected){
        if (worker == null) {
            throw new AssertionError("Worker is null");
        } else if (expected==null) {
            throw new AssertionError("Worker is null");
        }
        else if(worker.equals(expected)){
            return true;
            //System.out.println("insert worker test passed");
        }
        else return false;//System.out.println("insert worker test doesn't passed");
    }

    public void testInsertRole(){
        RoleRepository roles=RoleRepository.getInstance();
        Role role=new Role(998,"security guard");

        roles.add(role);
        Role to_check=roles.get(998);
        roles.remove(998);
        if(assertRole(role,to_check)){
            System.out.println("insert Role test passed");

        }else System.out.println("insert Role test doesn't passed");

    }
    public void testUpdateRole(){
        RoleRepository roles=RoleRepository.getInstance();
        Role role=new Role(998,"security guard");

        roles.add(role);
        Role updated=new Role(998,"guard");

        roles.updateRole(updated);

        Role to_check=roles.get(998);

        roles.remove(998);
        if(assertRole(updated,to_check)){
            System.out.println("update Role test passed");

        }else System.out.println("update Role test doesn't passed");

    }


    private boolean assertRole(Role role_to_check, Role expected){
        if (role_to_check == null) {
            throw new AssertionError("Worker is null");
        } else if (expected==null) {
            throw new AssertionError("Worker is null");
        }
        else if(role_to_check.equals(expected)){
            return true;
        }
        else return false;
    }

    public void testInsertBranch(){
        BranchRepository branches=BranchRepository.getInstance();
        Branch branch=new Branch(10,"haifa","haatzmaut 89");

        branches.add(branch);
        Branch to_check=branches.get(10);
        branches.remove(10);
        assertBranch(branch,to_check);

    }

    private void assertBranch(Branch branch_to_check, Branch expected){
        if (branch_to_check == null) {
            throw new AssertionError("Worker is null");
        } else if (expected==null) {
            throw new AssertionError("Worker is null");
        }
        else if(branch_to_check.equals(expected)){
            System.out.println("insert Branch test passed");
        }
        else System.out.println("insert Branch test doesn't passed");
    }


    public void testInsertRequest() {
        RequestRepository requests = RequestRepository.getInstance();
        WorkerRepository workers = WorkerRepository.getInstance();
        Role role = new Role(999, "cash counter");
        Worker worker = new Worker(30, "david", 55, 55, new Date(), 2, role, 2, "cashiers", "leumi: 1234567");

        // Add worker to the repository
        workers.add(worker);

        Boolean[][] weeklyRequest = {
                {true, false, true, false, true, false, true}, // Morning shifts
                {false, true, false, true, false, true, false} // Evening shifts
        };

        Request request = new Request(worker, weeklyRequest);
        requests.add(request);

        Pair<Integer, Integer> requestKey = new Pair<>(worker.getId(), request.getWeek());
        Request to_check = requests.get(requestKey);
        workers.remove(30);
        requests.remove(requestKey);

        if(assertRequest(request, to_check)){
            System.out.println("Insert request test passed");
        }else System.out.println("Insert request test did not pass");
    }



    public void testUpdateRequest() {
        RequestRepository requests = RequestRepository.getInstance();
        WorkerRepository workers = WorkerRepository.getInstance();
        Role role = new Role(999, "cash counter");
        Worker worker = new Worker(30, "david", 55, 55, new Date(), 2, role, 2, "cashiers", "leumi: 1234567");

        // Add worker to the repository
        workers.add(worker);

        Boolean[][] weeklyRequest = {
                {true, false, true, false, true, false, true}, // Morning shifts
                {false, true, false, true, false, true, false} // Evening shifts
        };

        Request request = new Request(worker, weeklyRequest);
        requests.add(request);

        // Update request
        Boolean[][] updatedWeeklyRequest = {
                {false, true, false, true, false, true, false}, // Morning shifts updated
                {true, false, true, false, true, false, true} // Evening shifts updated
        };

        request.setRequest(updatedWeeklyRequest);
        try {
            requests.editRequest(request);
        } catch (Exception e) {
            e.printStackTrace();
        }

        Pair<Integer, Integer> requestKey = new Pair<>(worker.getId(), request.getWeek());
        Request to_check = requests.get(requestKey);
        workers.remove(30);
        requests.remove(requestKey);

        if(assertRequest(request, to_check)){
            System.out.println("update request test passed");
        }else System.out.println("update request test did not pass");

    }
    private boolean assertRequest(Request request_to_check, Request expected) {
        if (request_to_check == null) {
            throw new AssertionError("Request is null");
        } else if (expected == null) {
            throw new AssertionError("Expected request is null");
        } else if (request_to_check.equals(expected)) {
            return true;
        } else {
            return false;
        }
    }


    public void testInsertRoster() {
        ShiftRepository shiftRepository = ShiftRepository.getInstance();
        Branch branch = new Branch(1, "Main Branch", "123 Main St");
        Role shiftManagerRole = new Role(2, "Shift Manager");

        // Creating workers with shift manager role
        Worker shiftManager1 = new Worker(1, "John Doe", 3000, 20, new Date(), null, shiftManagerRole, 1, "Sales", "Bank1: 123456");
        Worker shiftManager2 = new Worker(2, "Jane Smith", 3200, 22, new Date(), null, shiftManagerRole, 1, "Support", "Bank2: 654321");

        // Creating shift arrangements with shift managers
        Shift[][] shiftArrangements = new Shift[7][2];
        shiftArrangements[0][0] = new Shift(1, 0, 0, new Worker[]{shiftManager1}, Arrays.asList(shiftManagerRole));
        shiftArrangements[0][1] = new Shift(1, 0, 1, new Worker[]{shiftManager2}, Arrays.asList(shiftManagerRole));

        Roster roster = new Roster(branch, shiftArrangements);
        shiftRepository.add(roster);

        Roster retrievedRoster = shiftRepository.get(new Pair<>(1, Week.getWeek()));
        shiftRepository.remove(new Pair<>(1, Week.getWeek()));
        assertRoster(roster, retrievedRoster);
    }
    private void assertRoster(Roster original, Roster updated) {
        if (original == null) {
            throw new AssertionError("Original roster is null");
        } else if (updated == null) {
            throw new AssertionError("Updated roster is null");
        } else if (original.equals(updated)) {
            System.out.println("Roster insert test passed");
        } else {
            System.out.println("Roster insert test failed");
        }
    }


    public void testCreateWorkerWithRoleAndAddOneMoreRole() {
        // Create repositories
        WorkerRepository workerRepository = WorkerRepository.getInstance();
        RoleRepository roleRepository = RoleRepository.getInstance();

        // Add an initial role to RoleRepository
        Role initialRole = new Role(99, "Engineer");
        roleRepository.add(initialRole);

        // Create a worker with initial role
        Worker worker = new Worker(1001, "John Doe", 3000, 2500, new Date(), 1, initialRole, 1, "IT", "123456789");
        workerRepository.add(worker);

        // Add one more role to RoleRepository
        Role additionalRole = new Role(98, "Manager");
        roleRepository.add(additionalRole);

        // Add the additional role for the worker
        int workerID = 1001;
        int additionalRoleID = 98;
        HR_Controller hrController = new HR_Controller();
        hrController.addNewRoleForWorker(workerID, additionalRoleID);

        // Retrieve the updated worker from repository and check roles
        Worker updatedWorker = workerRepository.get(workerID);
        if (updatedWorker != null) {
            boolean initialRoleFound = false;
            boolean additionalRoleFound = false;
            for (Role workerRole : updatedWorker.getRoles()) {
                if (workerRole.getRoleID() == initialRole.getRoleID()) {
                    initialRoleFound = true;
                }
                if (workerRole.getRoleID() == additionalRole.getRoleID()) {
                    additionalRoleFound = true;
                }
            }
            assert initialRoleFound : "Initial role not found for the worker.";
            assert additionalRoleFound : "Additional role not added for the worker.";

            roleRepository.remove(99);
            roleRepository.remove(98);
            workerRepository.remove(1001);
            System.out.println("Create Worker with Role and Add One More Role test passed.");
        } else {
            System.out.println("Worker not found in repository.");
        }
    }


    public void testRetireWorkerAndGetAllWorkersIncludingInactive() {
        // Create a Worker instance
        Worker worker = new Worker(1000, "John Doe", 3000, 20, new Date(), 1, null, 1, "IT", "123456789");
        HR_Controller hr =new HR_Controller();
        // Add the worker to the repository
        WorkerRepository workerRepository = WorkerRepository.getInstance();
        workerRepository.add(worker);


        // Verify worker exists in active workers list//
        List<Worker> activeWorkers = workerRepository.getAllWorkers();
        if(activeWorkers.contains(worker)) {

            // Retire the worker//
            hr.fireWorker(1000);
            // Verify worker is no longer in active workers list
            activeWorkers = workerRepository.getAllWorkers();
            if(activeWorkers.contains(worker)){
                System.out.println("retire worker test does not passed");
            }
            else System.out.println("retire worker test passed");

        }
        else System.out.println("worker doesn't exist");

        //workerRepository.remove(1000);
    }










    public static void main(String[] args) {
        Test test = new Test();
        test.testInsertWorker();
        test.testUpdateWorker();
        test.testInsertRole();
        test.testUpdateRole();
        test.testInsertBranch();
        test.testInsertRequest();
        test.testUpdateRequest();
        test.testInsertRoster();
        test.testCreateWorkerWithRoleAndAddOneMoreRole();
        test.testRetireWorkerAndGetAllWorkersIncludingInactive();

    }
}
