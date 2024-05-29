package Domain;

import Data.InMemoryRequestRepository;
import Data.InMemoryWorkerRepository;
import Data.InMemoryShiftRepository;

public class Worker_Controller {
    private final InMemoryWorkerRepository workers_memory=InMemoryWorkerRepository.getInstance();
    private final InMemoryRequestRepository requests_repository=InMemoryRequestRepository.getInstance();
    private final InMemoryShiftRepository shifts_repository=InMemoryShiftRepository.getInstance();

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


    public String ShowCurrRoster(int worker_id){
        Worker worker=workers_memory.getWorkerById(worker_id);
        Roster roster= shifts_repository.getRoster(worker.getWork_branch().getBranchID(),Week.getWeek());
        if(roster==null){
            roster= shifts_repository.getRoster(worker.getWork_branch().getBranchID(),Week.getWeek()-1);
        }
        return roster.toString();
    }

    public String ShowPastRoster(int worker_id, int week) throws Exception {
        Worker worker=workers_memory.getWorkerById(worker_id);
        Roster roster= shifts_repository.getRoster(worker.getWork_branch().getBranchID(),week);
        if(roster==null) {
            throw new Exception("Your branch doesn't have roster for this week");
        }
        return roster.toString();
    }
}
