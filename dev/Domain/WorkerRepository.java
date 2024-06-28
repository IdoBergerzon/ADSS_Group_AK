package Domain;

import Data.RoleDAOImpl;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.*;


public class WorkerRepository implements IRepository<Worker,Integer> {
    private final Map<Integer, Worker> workers;
    private final Map<Integer, Worker> noActiveWorkers;
    private RoleDAOImpl dao;



    private WorkerRepository() {
        this.workers = new HashMap<>();
        this.noActiveWorkers = new HashMap<>();
        this.dao=new RoleDAOImpl();
    }

    /**
     * SingletonHolder is loaded on the first execution of Singleton.getInstance()
     * or the first access to SingletonHolder.INSTANCE, not before.
     */
    private static class InMemoryHolder {
        private final static WorkerRepository INSTANCE = new WorkerRepository();
    }

    /**
     * public static method that returns the single instance of the class
     */

    public static WorkerRepository getInstance() {
        return InMemoryHolder.INSTANCE;
    }


    @Override
    public void add(Worker worker) {

        if (worker == null) {
            throw new IllegalArgumentException("Worker cannot be null");
        }
        if(workers.containsKey(worker.getId()) || dao.search(worker.getId())!=null) {
            throw new IllegalArgumentException("Worker with id " + worker.getId() + " already exists");
        }else{
            workers.put(worker.getId(), worker);
            dao.insert(toJsonNode(worker));
        }
    }

    @Override
    public Worker get(Integer id) {
        return workers.get(id);
    }

    public List<Worker> getAllWorkers() {
        return new ArrayList<>(workers.values());
    }


    public void updateWorker(Worker worker) {
        if (workers.containsKey(worker.getId())) {
            workers.put(worker.getId(), worker);
        }
        else{
            throw new IllegalArgumentException("Worker with id " + worker.getId() + " does not exist, can't update it");
        }
    }

    @Override
    public void remove(Integer id) {

        Worker worker = workers.remove(id);
        if (worker != null) {
            noActiveWorkers.put(id, worker);
        } else{
            throw new IllegalArgumentException("Worker with id " + id + " does not exist, can't delete it");
        }
    }

    private JsonNode toJsonNode(Worker worker){
        ObjectMapper mapper = new ObjectMapper();
        return mapper.convertValue(worker, JsonNode.class);
    }
    private Worker fromJsonNode(JsonNode worker){
        ObjectMapper mapper = new ObjectMapper();
        return mapper.convertValue(worker, Worker.class);
    }
}