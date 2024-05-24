package Domain;
import Data.InMemoryWorkerRepository;
import Data.Worker_Repository;

import java.util.Optional;


public class Checking_Controller {
    private Worker_Repository WR;

    public Checking_Controller() {
        this.WR = new InMemoryWorkerRepository();
    }

    public Worker checking_ID(int ID){
        return WR.getWorkerById(ID);
    }
}
