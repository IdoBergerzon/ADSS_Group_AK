package Domain;

import java.util.List;


public interface IWorkerRepository {

    void addWorker(Worker worker);
    Worker getWorkerById(int id);
    List<Worker> getAllWorkers();
    void updateWorker(Worker worker);
    void deleteWorker(int id);


}