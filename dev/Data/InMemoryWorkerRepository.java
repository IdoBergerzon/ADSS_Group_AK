package Data;

import Domain.Branch;
import Domain.Role;
import Domain.Worker;
import java.util.*;


public class InMemoryWorkerRepository implements Worker_Repository {
    private final Map<Integer, Worker> workers;
    private final Map<Integer, Worker> noActiveWorkers;
    private final Map<Integer, Role> roles;
    private final Map<Integer, Branch> branches;

    // Making this class singletone so we only create one instance while runs
    //private static InMemoryWorkerRepository instance;
//    public static synchronized InMemoryWorkerRepository getInstance() {
//        if (instance == null) {
//            instance = new InMemoryWorkerRepository();
//        }
//        return instance;
//    }

    private InMemoryWorkerRepository() {
        this.workers = new HashMap<>();
        this.noActiveWorkers = new HashMap<>();
        this.roles = new HashMap<>();
        this.branches = new HashMap<>();
    }

    /**
     * SingletonHolder is loaded on the first execution of Singleton.getInstance()
     * or the first access to SingletonHolder.INSTANCE, not before.
     */
    private static class InMemoryHolder {
        private final static InMemoryWorkerRepository INSTANCE = new InMemoryWorkerRepository();
    }

    //public static method that returns the single instance of the class
    public static InMemoryWorkerRepository getInstance() {
        return InMemoryHolder.INSTANCE;
    }


//private InMemoryWorkerRepository() {
//}
//
//    /**
//     * SingletonHolder is loaded on the first execution of Singleton.getInstance()
//     * or the first access to SingletonHolder.INSTANCE, not before.
//     */
//    private static class InMemoryHolder {
//        private final static InMemoryWorkerRepository INSTANCE = new InMemoryWorkerRepository();
//    }
//
//    public static InMemoryWorkerRepository getInstance() {
//        return InMemoryHolder.INSTANCE;
//    }


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

    @Override
    public List<Role> getAllRoles() {
        return new ArrayList<>(roles.values());
    }

    @Override
    public void addBranch(Branch branch) {
        if (branch == null) {
            throw new IllegalArgumentException("Role cannot be null");
        }
        if(roles.containsKey(branch.getBranchID())){
            throw new IllegalArgumentException("Role with id " + branch.getBranchID() + " already exists");
        }
        branches.put(branch.getBranchID(), branch);
    }

    @Override
    public Branch getBranchByID(int id) {
        return branches.get(id);
    }


    @Override
    public List<Branch> getAllBranches() {
        return new ArrayList<>(branches.values());
    }
}