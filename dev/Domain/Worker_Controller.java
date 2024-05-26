package Domain;

import Data.InMemoryRequestRepository;
import Data.InMemoryWorkerRepository;

public class Worker_Controller {
    private final InMemoryWorkerRepository workers_memory=InMemoryWorkerRepository.getInstance();
    private final InMemoryRequestRepository requests_repository=InMemoryRequestRepository.getInstance();

    public void displayMyDetails(int id){
        Worker result=workers_memory.getWorkerById(id);
        System.out.println(result);
    }

    public void addRequest(int id, Boolean[][] requestArray)throws Exception{
        Request newRequest = new Request(workers_memory.getWorkerById(id),requestArray); //לשנות את השבוע ולבנות בדיקות אם קיים כבר בקשה או אם המערך לא נכון או משהו
        try {
            requests_repository.addRequest(newRequest);
        } catch (Exception e) {
            throw new Exception("Request already exists");
        }
    }
    public void EditRequest(int id, Boolean[][] requestArray) {
        requests_repository.getRequestByWorker(id).setRequest(requestArray);
    }
}
