package Domain;

import Data.InMemoryWorkerRepository;

public class Worker_Controller {
    private final InMemoryWorkerRepository workers_memory=InMemoryWorkerRepository.getInstance();

    public void displayMyDetails(int id){
        Worker result=workers_memory.getWorkerById(id);
        System.out.println(result);
    }

    public void addRequest(int id, Boolean[][] requestArray){
        Work_Request newRequest = new Work_Request(id,requestArray,0) //לשנות את השבוע ולבנות בדיקות אם קיים כבר בקשה או אם המערך לא נכון או משהו
    }
}
