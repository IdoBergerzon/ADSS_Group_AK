package Domain;
import Data.InMemoryWorkerRepository;
import Data.Worker_Repository;

import java.util.Date;


public class Checking_Controller {
    private Worker_Repository WR;

    public Checking_Controller() {
        this.WR = InMemoryWorkerRepository.getInstance();

        ///Enitilize the system with some information
        Role hr = new Role(1, "hr");
        Role shift_manager = new Role(2, "shift_manager");
        Role storekeeper = new Role(3, "storekeeper");
        Branch br1 = new Branch(1, "branch1", "BeerSheva");
        Branch br2 = new Branch(2, "branch2", "Ashdod");
        Date dt1 = new Date(2024,1,1);
        Date dt2 = new Date(2024,2,1);
        Worker ido = new Worker(1,"Ido", 5000,0, dt1, null, hr, br1, "hr");
        Worker aviv = new Worker(2,"Aviv", 0,50, dt2, 1, shift_manager, br1, "managment");

        WR.addWorker(ido);
        WR.addWorker(aviv);
        WR.addBranch(br1);
        WR.addBranch(br2);
        WR.addRole(hr);
        WR.addRole(shift_manager);
        WR.addRole(storekeeper);
    }



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
