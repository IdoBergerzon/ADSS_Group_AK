package Domain;

import java.util.*;


public class Repository implements IRepository {
    private final Map<Integer, Worker> workers;
    private final Map<Integer, Worker> noActiveWorkers;



    private Repository() {
        this.workers = new HashMap<>();
        this.noActiveWorkers = new HashMap<>();
    }

    /**
     * SingletonHolder is loaded on the first execution of Singleton.getInstance()
     * or the first access to SingletonHolder.INSTANCE, not before.
     */
    private static class InMemoryHolder {
        private final static Repository INSTANCE = new Repository();
    }

    /**
     * public static method that returns the single instance of the class
     */

    public static Repository getInstance() {
        return InMemoryHolder.INSTANCE;
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
}