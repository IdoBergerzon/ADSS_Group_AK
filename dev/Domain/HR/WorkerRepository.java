package Domain.HR;

import DAL.HR.WorkerDAOImpl;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

import java.util.*;


public class WorkerRepository implements IRepository<Worker,Integer> {
    private final Map<Integer, Worker> workers;
    private final Map<Integer, Worker> noActiveWorkers;
    private WorkerDAOImpl dao;



    private WorkerRepository() {
        this.workers = new HashMap<>();
        this.noActiveWorkers = new HashMap<>();
        this.dao = new WorkerDAOImpl();
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
        if(workers.containsKey(worker.getId())) {
            throw new IllegalArgumentException("Worker with id " + worker.getId() + " already exists");
        }
        JsonNode worker1 = dao.search(worker.getId());
        if(worker1 != null) {
            if(worker1.get("active").asInt() == 1) {
                throw new IllegalArgumentException("Worker with id " + worker.getId() + " already exists");
            }
        }
        workers.put(worker.getId(), worker);
        dao.insert(JsonNodeConverter.toJsonNode(worker));
    }

    @Override
    public Worker get(Integer id) {
        if(workers.get(id) != null)
            return workers.get(id);
        JsonNode w = dao.search(id);
        if (w != null) {
            ((ObjectNode) w).remove("active");
            Worker worker = JsonNodeConverter.fromJsonNode(w,Worker.class) ;
            workers.put(id,worker);
            return worker ;
        }
        return null;
    }

    public List<Worker> getAllWorkers() {
        List<JsonNode> workers_json = dao.GetAllWorkers();
        List<Worker> workers = new ArrayList<>();
        for(JsonNode worker : workers_json) {
            workers.add(JsonNodeConverter.fromJsonNode(worker,Worker.class));
        }
        return workers;
    }


    public void updateWorker(Worker worker) {
        if (workers.containsKey(worker.getId())) {
            workers.put(worker.getId(), worker);
//            dao.updateWorker(JsonNodeConverter.toJsonNode(worker));
        }
        if (dao.search(worker.getId()) != null) {
            dao.updateWorker(JsonNodeConverter.toJsonNode(worker));
        } else{
            throw new IllegalArgumentException("Worker with id " + worker.getId() + " does not exist, can't update it");
        }
    }

    @Override
    public void remove(Integer id) {
        try {
            workers.remove(id);
            dao.remove(id);
        } catch (NullPointerException e) {
            throw new IllegalArgumentException("Worker with id " + id + " does not exist, can't delete it");
        }

    }
    public void retireWorker(int id) {
        Worker worker = workers.remove(id);
        if (worker != null) {
            noActiveWorkers.put(id, worker);
        }
        try{
            dao.retireWorker(id);
        } catch (NullPointerException e) {
            throw new IllegalArgumentException("Worker with id " + id + " does not exist, can't delete it");
        }
    }


}