package Data;

import Domain.Role;
import Domain.Worker;
import Domain.Branch;
import java.util.List;


public interface Worker_Repository {
    void addRole(Role role);
    Role getRoleByID(int role_id);

    void addWorker(Worker worker);
    Worker getWorkerById(int id);
    List<Worker> getAllWorkers();
    void updateWorker(Worker worker);
    void deleteWorker(int id);

    void addBranch(Branch branch);
    Branch getBranchByID(int id);
}