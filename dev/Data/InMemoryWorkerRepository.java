package Data;

import Domain.Worker;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class InMemoryWorkerRepository implements Worker_Repository {
    private final Map<Integer, Worker> workers = new HashMap<>();

    @Override
    public void addWorker(Worker worker) {
        workers.put(worker.getId(), worker);
    }

    @Override
    public Optional<Worker> getWorkerById(int id) {
        return Optional.ofNullable(workers.get(id));
    }

    @Override
    public List<Worker> getAllWorkers() {
        return new ArrayList<>(workers.values());
    }

    @Override
    public void updateWorker(Worker worker) {
        if (workers.containsKey(worker.getId())) {
            workers.put(worker.getId(), worker);
        } else {
            throw new IllegalArgumentException("Worker with id " + worker.getId() + " does not exist.");
        }
    }

    @Override
    public void deleteWorker(int id) {
        workers.remove(id);
    }
}