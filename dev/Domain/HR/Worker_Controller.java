package Domain.HR;

import java.util.List;

public class Worker_Controller {
    private int worker_id;
    private final WorkerRepository workers_memory= WorkerRepository.getInstance();
    private final RequestRepository requests_repository= RequestRepository.getInstance();
    private final ShiftRepository shifts_repository= ShiftRepository.getInstance();

    public Worker_Controller(int worker_id) {
        new Week(shifts_repository.getMaxWeek()+1);
        this.worker_id = worker_id;
        new Week(shifts_repository.getMaxWeek());
    }
    public void displayMyDetails(){
        Worker result=workers_memory.get(worker_id);
        System.out.println(result);
    }

    public void addRequest( Boolean[][] requestArray)throws Exception{
        Request newRequest = new Request(workers_memory.get(worker_id),requestArray); //לשנות את השבוע ולבנות בדיקות אם קיים כבר בקשה או אם המערך לא נכון או משהו
        try {
            requests_repository.add(newRequest);
        } catch (Exception e) {
            throw new Exception("Request already exists");
        }
    }
    public void EditRequest(Boolean[][] requestArray) {
        Request newRequest = new Request(workers_memory.get(worker_id),requestArray);
        try{
            requests_repository.editRequest(newRequest);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public String ShowCurrRoster(){
        Worker worker=workers_memory.get(worker_id);
        Roster roster= shifts_repository.get(new Pair(worker.getBranch_id(),Week.getWeek()));
        if(roster==null){
            //roster= shifts_repository.get(new Pair(worker.getBranch_id(),Week.getWeek()-1));
            return "There is no roster for the week: "+Week.getWeek();
        }
        return roster.toString();
    }

    public String ShowPastRoster(int week) throws Exception {
        Worker worker=workers_memory.get(worker_id);
        Roster roster= shifts_repository.get(new Pair(worker.getBranch_id(),week));
        if(roster==null) {
            throw new Exception("Your branch doesn't have roster for this week");
        }
        return roster.toString();
    }

    public void RetireMassage(){
        workers_memory.remove(worker_id);
    }

    public String ShowPastShifts(){
        List<Roster> roters_list=shifts_repository.getAllRosters();
        String toreturn="";
        for(int i=0;i<roters_list.size();i++) {
            for (int j = 0; j < 7; j++) {
                for (int k = 0; k < 2; k++) {
                    Shift shift = roters_list.get(i).getShift_arrangment()[j][k];
                    if(shift != null) {
                        if (ifworkinshift(shift.getShift_workers())){
                            toreturn += roters_list.get(i).getShift_arrangment()[j][k].toString();
                        }
                    }
                }
            }
        }
        if(toreturn==""){
            return "there is not past shift\n";
        }
        return toreturn;
    }
    public Boolean ifworkinshift(Worker[] worker_list ){
        for(int i=0;i<worker_list.length;i++){
            if(worker_list[i].getId()==worker_id){
                return true;
            }
        }
        return false;
    }
    public String getrequestById(){
        return requests_repository.get(new Pair<>(worker_id,Week.getWeek())).toString();

    }
}
