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
        Role Cashier = new Role(4, "cashier");
        Role delivery = new Role(5, "delivery");
        Branch br1 = new Branch(1, "branch1", "BeerSheva");
        Branch br2 = new Branch(2, "branch2", "Ashdod");
        Date dt1 = new Date(2024,1,1);
        Date dt2 = new Date(2024,2,1);
        Worker ido = new Worker(1,"Ido", 5000,0, dt1, null, hr, br1, "hr");
        Worker aviv = new Worker(2,"Aviv", 0,50, dt2, 1, shift_manager, br1, "managment");
        Worker hezi = new Worker(3,"hezi", 0,50, dt2, 1, shift_manager, br2, "managment");
        Worker lior = new Worker(4,"lior", 0,50, dt2, 3, storekeeper, br2, "managment");
        Worker asaf = new Worker(5,"asaf", 0,50, dt2, 3, Cashier, br2, "managment");
        Worker rudi = new Worker(6,"rudi", 0,50, dt2, 3, storekeeper, br2, "managment");
        Worker tamir = new Worker(7,"tamir", 0,50, dt2, 3, Cashier, br2, "managment");
        Worker daniel = new Worker(8,"daniel", 0,50, dt2, 1, delivery, br2, "managment");

        WR.addWorker(ido);
        WR.addWorker(aviv);
        WR.addWorker(hezi);
        WR.addWorker(lior);
        WR.addWorker(asaf);
        WR.addWorker(rudi);
        WR.addWorker(tamir);
        WR.addWorker(daniel);
        WR.addBranch(br1);
        WR.addBranch(br2);
        WR.addRole(hr);
        WR.addRole(shift_manager);
        WR.addRole(storekeeper);
        WR.addRole(delivery);
        WR.addRole(Cashier);
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
