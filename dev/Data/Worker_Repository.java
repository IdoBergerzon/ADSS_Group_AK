package Data;

import Domain.Worker;
import java.util.List;


public interface Worker_Repository {
    void addRole(String role_name, int role_id);
    String getRoleByID(int role_id);

    void addWorker(String worker);
    String getWorkerById(int id);
    List<String> getAllWorkers();

    void updateWorker(String worker);
    void deleteWorker(int id);

    void addBranch(String branch_name, int branch_id);
    String getBranchByID(int id);
}