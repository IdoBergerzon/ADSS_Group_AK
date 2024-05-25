package Domain;
import Data.InMemoryWorkerRepository;
import Data.Worker_Repository;

import java.util.Optional;


public class Checking_Controller {
    private Worker_Repository WR;

    public Checking_Controller() {
        this.WR = InMemoryWorkerRepository.getInstance();
    }


    //if 1 so HR
    //if 0 so regular worker
    //if -1 doesnt exist
    public int checking_ID(int ID){
        Worker result=WR.getWorkerById(ID);
        if(result==null){
            return -1;
        }

        if(result.getRoles()[0].getRoleID()==1){
            return 1;
        }
        else return 0;

    }
}
