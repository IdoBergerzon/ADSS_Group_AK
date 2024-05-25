package Domain;

import Data.InMemoryWorkerRepository;

public class Worker_Controller {
    private final InMemoryWorkerRepository workers_memory=InMemoryWorkerRepository.getInstance();

    public void displayMyDetails(int id){
        Worker result=workers_memory.getWorkerById(id);
        System.out.println(result);
    }
}
