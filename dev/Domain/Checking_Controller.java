package Domain;
import Data.InMemoryShiftRepository;
import Data.InMemoryWorkerRepository;

import Data.Worker_Repository;

import java.util.Date;
import java.util.List;


public class Checking_Controller {
    private Worker_Repository WR;
    private InMemoryShiftRepository SR;

    /**
     * This function is Our initialize for the System
     */
    public Checking_Controller() {
        this.WR = InMemoryWorkerRepository.getInstance();
        this.SR = InMemoryShiftRepository.getInstance();
        Role hr = new Role(1, "hr");
        Branch br1 = new Branch(1, "branch1", "BeerSheva");
        Date dt1 = new Date(2024,1,1);
        Worker ido = new Worker(1,"Ido", 5000,0, dt1, null, hr, br1, "hr","Leumi:5555555");

        WR.addWorker(ido);
        WR.addBranch(br1);
        WR.addRole(hr);

    }

    public void startWithObject(){
        ///Enitilize the system with some information

        Role shift_manager = new Role(2, "shift_manager");
        Role storekeeper = new Role(3, "storekeeper");
        Role Cashier = new Role(4, "cashier");
        Role delivery = new Role(5, "delivery");

        Branch br2 = new Branch(2, "branch2", "Ashdod");

        Date dt2 = new Date(2024,2,1);
        Branch br1=WR.getBranchByID(1);
        Worker aviv = new Worker(2,"Aviv", 0,50, dt2, 1, shift_manager, br1, "managment","Hapoalim:1234567");
        Worker hezi = new Worker(3,"hezi", 0,50, dt2, 1, shift_manager, br2, "managment","Leumi:6392772");
        Worker lior = new Worker(4,"lior", 0,50, dt2, 3, storekeeper, br2, "managment","Beinleumi:1455659");
        Worker asaf = new Worker(5,"asaf", 0,50, dt2, 3, Cashier, br2, "managment","Discont:6453745");
        Worker rudi = new Worker(6,"rudi", 0,50, dt2, 3, storekeeper, br2, "managment","Yahav:1525358");
        Worker tamir = new Worker(7,"tamir", 0,50, dt2, 3, Cashier, br2, "managment","Hapoalim:9753146");
        Worker daniel = new Worker(8,"daniel", 0,50, dt2, 1, delivery, br2, "managment","Leumi:6612389");
        Worker noa = new Worker(9,"noa", 0,50, dt2, 1, shift_manager, br2, "managment","Hapoalim:1234767");



        WR.addWorker(aviv);
        WR.addWorker(noa);
        WR.addWorker(hezi);
        WR.addWorker(lior);
        WR.addWorker(asaf);
        WR.addWorker(rudi);
        WR.addWorker(tamir);
        WR.addWorker(daniel);

        WR.addBranch(br2);

        WR.addRole(shift_manager);
        WR.addRole(storekeeper);
        WR.addRole(delivery);
        WR.addRole(Cashier);

        Shift[][] shifts1 = new Shift[7][2];
        Shift[][] shifts2 = new Shift[7][2];
        for (int day = 0; day < 7; day++) {
            for(int i=0; i < 2; i++) {
                // Morning shifts
                shifts1[day][i]=new Shift(br1.getBranchID(), day, i, new Worker[]{aviv}, List.of(shift_manager));
                shifts2[day][i]=new Shift(br2.getBranchID(), day, i, new Worker[]{hezi, lior}, List.of(shift_manager, storekeeper));

            }
        }

        Roster roster1 = new Roster(br1,shifts1);
        Roster roster2 = new Roster(br2,shifts2);
        SR.addRoster(roster1);
        SR.addRoster(roster2);
        Week.setWeek();
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
