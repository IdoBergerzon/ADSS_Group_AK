package Domain;


import Data.Trucks;

public class TruckController {
    private Trucks trucks;
    public TruckController() {
        this.trucks = new Trucks();
    }

    public void addNewTruck(int truckID, String truckType, double truckWeight, double MaxWeight) {
        Truck newtruck = new Truck(truckID, truckType, truckWeight, MaxWeight, false);
        this.trucks.addTruck(newtruck);
    }

    public Truck getTruck(int truckID) {
        if (trucks.getTrucks().get(truckID) != null)
            return trucks.getTrucks().get(truckID);
        else return null;
    }

    public void showTrucks(int truckID) {
        if (this.getTruck(truckID) != null) {
            System.out.println(this.getTruck(truckID).toString());
        }
    }


}
