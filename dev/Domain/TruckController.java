package Domain;


import Data.TrucksData;

public class TruckController {
    private TrucksData trucksData;
    public TruckController() {
        this.trucksData = new TrucksData();
    }

    public void addNewTruck(int truckID, String truckType, double truckWeight, double MaxWeight) {
        Truck newtruck = new Truck(truckID, truckType, truckWeight, MaxWeight, false);
        this.trucksData.addTruck(newtruck);
    }

    public Truck getTruck(int truckID) {
        if (trucksData.getTrucks().get(truckID) != null)
            return trucksData.getTrucks().get(truckID);
        else return null;
    }

    public void showTrucks(int truckID) {
        if (this.getTruck(truckID) != null) {
            System.out.println(this.getTruck(truckID).toString());
        }
    }


}
