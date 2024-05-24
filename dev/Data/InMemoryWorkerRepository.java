package Data;

import Domain.Worker;

import java.util.*;


public class InMemoryWorkerRepository implements Worker_Repository {
    private final Map<Integer, String[]> workers = new HashMap<>();
    private final Map<Integer, String> roles = new HashMap<>();

    @Override
    public void addWorker(String worker) {
        String[] workerDetails = worker.split(",");
        Integer workerId = Integer.parseInt(workerDetails[0]);

        // Slicing the array from index 1 to the end
        String[] workerInfo = Arrays.copyOfRange(workerDetails, 1, workerDetails.length);

        // Creating a new array to add "1" at the end--- means active
        String[] updatedWorkerInfo = Arrays.copyOf(workerInfo, workerInfo.length + 1);
        updatedWorkerInfo[updatedWorkerInfo.length - 1] = "1";

        // Storing the updated array in the map
        workers.put(workerId, updatedWorkerInfo);
    }

    @Override
    public String getWorkerById(int id) {
        String worker= null;
        if (workers.get(id)!=null){
            String[] workerDetails = workers.get(id);
            worker+=workerDetails[0];
            for(String str:workerDetails){
                worker+=","+str;
            }
        }

        return worker;
    }

    @Override
    public List<String> getAllWorkers() {
        Set<Integer> workers_id = workers.keySet();
        List<String> active_workers = new ArrayList<>();
        for (Integer id : workers_id) {
            String[] worker_details=workers.get(id);

            /// Returning only active workers using get by Id
            if (worker_details[0].equals("1")){
                active_workers.add(getWorkerById(id));
            }
        }
        return active_workers;
    }

    ///****** only implement to change the value, the real change needs to be in controller
    @Override
    public void updateWorker(String worker) {
        String[] workerDetails = worker.split(",");
        Integer workerId = Integer.parseInt(workerDetails[0]);
        if (workers.get(workerId)!=null) {
            workers.replace(workerId,Arrays.copyOfRange(workerDetails, 1, workerDetails.length));
        } else {
            throw new IllegalArgumentException("Worker with id " + workerDetails[0] + " does not exist.");
        }
    }

    @Override
    public void deleteWorker(int id) {
        String[] worker_details=workers.get(id);
        // "0" means worker is not active
        worker_details[worker_details.length-1] = "0";
    }
    @Override
    public void addRole(String role_name, int role_id){
        roles.put(role_id, role_name);
    }

    @Override
    public String getRoleByID(int id){

        return roles.get(id);
    }
}