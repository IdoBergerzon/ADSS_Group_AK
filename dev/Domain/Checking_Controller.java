package Domain;
import Data.InMemoryWorkerRepository;
import Data.Worker_Repository;

import java.util.Optional;


public class Checking_Controller {
    private Worker_Repository WR;

    public Checking_Controller() {
        this.WR = new InMemoryWorkerRepository();
    }

    public int checking_ID(int ID){
        String result=WR.getWorkerById(ID);
        if(result==null){
            return -1;
        }
        String[] string_result = result.split(",");

        if(Integer.parseInt(string_result[5])==1){
            return 1;
        }
        else return 0;

    }
}
