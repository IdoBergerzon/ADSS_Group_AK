package Data;

import Domain.Worker;

import java.util.List;
import java.util.Optional;

public interface Worker_Repository {
    void addWorker(Worker worker);
    Optional<Worker> getWorkerById(int id);
    List<Worker> getAllWorkers();
    void updateWorker(Worker worker);
    void deleteWorker(int id);
}