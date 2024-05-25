package Data;

import Domain.Branch;
import Domain.Role;
import Domain.Worker;
import java.util.*;


public class InMemoryWorkerRepository implements Worker_Repository {
    private final Map<Integer, Worker> workers = new HashMap<>();
    private final Map<Integer, Worker> noActiveWorkers = new HashMap<>();
    private final Map<Integer, Role> roles = new HashMap<>();
    private final Map<Integer, Branch> branches = new HashMap<>();

    // Making this class singletone so we only create one instance while runs
    private static InMemoryWorkerRepository instance;
    private InMemoryWorkerRepository() {
    }

    //public static method that returns the single instance of the class
    public static synchronized InMemoryWorkerRepository getInstance() {
        if (instance == null) {
            instance = new InMemoryWorkerRepository();
        }
        return instance;
    }



    @Override
    public void addWorker(Worker worker) {
        if (worker == null) {
            throw new IllegalArgumentException("Worker cannot be null");
        }
        if(workers.containsKey(worker.getId())) {
            throw new IllegalArgumentException("Worker with id " + worker.getId() + " already exists");
        }
        workers.put(worker.getId(), worker);
    }

    @Override
    public Worker getWorkerById(int id) {
        return workers.get(id);
    }

    @Override
    public List<Worker> getAllWorkers() {
        return new ArrayList<>(workers.values());
    }

    @Override
    public void updateWorker(Worker worker) {
        if (workers.containsKey(worker.getId())) {
            workers.put(worker.getId(), worker);
        }
        else{
            throw new IllegalArgumentException("Worker with id " + worker.getId() + " does not exist, can't update it");
        }
    }

    @Override
    public void deleteWorker(int id) {

        Worker worker = workers.remove(id);
        if (worker != null) {
            noActiveWorkers.put(id, worker);
        } else{
            throw new IllegalArgumentException("Worker with id " + id + " does not exist, can't delete it");
        }
    }

    @Override
    public void addRole(Role role) {
        if (role == null) {
            throw new IllegalArgumentException("Role cannot be null");
        }
        if(roles.containsKey(role.getRoleID())){
            throw new IllegalArgumentException("Role with id " + role.getRoleID() + " already exists");
        }
        roles.put(role.getRoleID(), role);
    }

    @Override
    public Role getRoleByID(int id) {
        return roles.get(id);
    }

    public void addBranch(Branch branch) {
        if (branch == null) {
            throw new IllegalArgumentException("Role cannot be null");
        }
        if(roles.containsKey(branch.getBranchID())){
            throw new IllegalArgumentException("Role with id " + branch.getBranchID() + " already exists");
        }
        branches.put(branch.getBranchID(), branch);
    }

    public Branch getBranchByID(int id) {
        return branches.get(id);
    }
}