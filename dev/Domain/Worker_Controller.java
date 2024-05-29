package Domain;

import Data.InMemoryRequestRepository;
import Data.InMemoryShiftRepository;
import Data.InMemoryWorkerRepository;
import Data.InMemoryShiftRepository;

import java.util.List;

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

    public String ShowPastShifts(int worker_id){
        List<Roster> roters_list=shifts_repository.getAllRosters();
        String toreturn="";
        for(int i=0;i<roters_list.size();i++) {
            for (int j = 0; j < 7; j++) {
                for (int k = 0; k < 2; k++) {

                    if(ifworkinshift(worker_id,roters_list.get(i).getShift_arrangment()[j][k].getShift_workers())){
                        toreturn+= roters_list.get(i).getShift_arrangment()[j][k].toString();
                    }
                }
            }
        }
        return toreturn;
    }
    public Boolean ifworkinshift(int workerID,Worker[] worker_list ){
        for(int i=0;i<worker_list.length;i++){
            if(worker_list[i].getId()==workerID){
                return true;
            }
        }
        return false;
    }
    public String getrequestById(int id){
        return requests_repository.getRequestByWorker(id).toString();
    }
}
