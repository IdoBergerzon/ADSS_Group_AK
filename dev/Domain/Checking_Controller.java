package Domain;

import java.util.Date;
import java.util.List;


public class Checking_Controller {
    private IRepository WR;
    private ShiftRepository SR;
    private RoleRepository RR;
    private BranchRepository BR;

    /**
     * This function is Our initialize for the System
     */
    public Checking_Controller() {
        this.WR = WorkerRepository.getInstance();
        this.SR = ShiftRepository.getInstance();
        this.RR=RoleRepository.getInstance();
        this.BR=BranchRepository.getInstance();
//        Role hr = new Role(1,"hr");
        Branch br1 = new Branch(1, "branch1", "BeerSheva");
//        Date dt1 = new Date(2024,1,1);
//        Worker ido = new Worker(1,"Ido", 5000,0, dt1, null, hr, br1.getBranchID(), "hr","Leumi:5555555");

//        WR.add(ido);
//        BR.add(br1);
  //      RR.add(hr);

    }

    public void startWithObject(){
        ///Enitilize the system with some information

//        Role shift_manager = new Role(2,"shift_manager");;
//        Role storekeeper = new Role(3,"storekeeper");;
////        Role Cashier = RR.get(4);
////        Role delivery = RR.get(5);
//
//        Branch br2 = new Branch(2, "branch2", "Ashdod");
//
//        Date dt2 = new Date(2024,2,1);
//        Branch br1=BR.get(1);
//        Worker aviv = new Worker(2,"Aviv", 0,50, dt2, 1, shift_manager, br1.getBranchID(), "managment","Hapoalim:1234567");
//        Worker hezi = new Worker(3,"hezi", 0,50, dt2, 1, shift_manager, br2.getBranchID(), "managment","Leumi:6392772");
//         Worker lior = new Worker(4,"lior", 0,50, dt2, 3, storekeeper, br2.getBranchID(), "managment","Beinleumi:1455659");
////        Worker asaf = new Worker(5,"asaf", 0,50, dt2, 3, Cashier, br2.getBranchID(), "managment","Discont:6453745");
//        Worker rudi = new Worker(6,"rudi", 0,50, dt2, 3, storekeeper, br2.getBranchID(), "managment","Yahav:1525358");
//        Worker tamir = new Worker(7,"tamir", 0,50, dt2, 3, Cashier, br2.getBranchID(), "managment","Hapoalim:9753146");
//        Worker daniel = new Worker(8,"daniel", 0,50, dt2, 1, delivery, br2.getBranchID(), "managment","Leumi:6612389");
//        Worker noa = new Worker(9,"noa", 0,50, dt2, 1, shift_manager, br2.getBranchID(), "managment","Hapoalim:1234767");



//        WR.add(aviv);
//        WR.add(noa);
//        WR.add(hezi);
//        WR.add(lior);
//        WR.add(asaf);
//        WR.add(rudi);
//        WR.add(tamir);
//        WR.add(daniel);

//        BR.add(br2);

//        RR.add(shift_manager);
//        RR.add(storekeeper);
//        RR.addRole(delivery);
//        RR.addRole(Cashier);

//        Shift[][] shifts1 = new Shift[7][2];
//        Shift[][] shifts2 = new Shift[7][2];
//        for (int day = 0; day < 7; day++) {
//            for(int i=0; i < 2; i++) {
//                // Morning shifts
//                shifts1[day][i]=new Shift(br1.getBranchID(), day, i, new Worker[]{aviv}, List.of(shift_manager));
//                shifts2[day][i]=new Shift(br2.getBranchID(), day, i, new Worker[]{hezi, lior}, List.of(shift_manager, storekeeper));
//
//            }
//        }
//
//        Roster roster1 = new Roster(br1,shifts1);
//        Roster roster2 = new Roster(br2,shifts2);
//        SR.addRoster(roster1);
//        SR.addRoster(roster2);
//        Week.setWeek();
    }


    public int checking_ID(int ID){
        Worker result= (Worker) WR.get(ID);
        if(result==null){
            return -1;
        }

        if(result.getRoles()[0].getRoleID()==1){
            return 1;
        }
        else return 0;

    }
}
