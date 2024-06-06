package Domain;


import Data.TrucksData;

public class TruckController {
    private TrucksData trucksData;
    public TruckController() {
        this.trucksData = new TrucksData();
    }

    public TrucksData getTrucksData() {
        return trucksData;
    }

    public void printAllAvailableTrucks(double weight) {
        System.out.println("Available Trucks:");
        for (Truck truck : trucksData.getTrucks().values()) {
            if (truck.isAvailable() && truck.getTruckWeight()+truck.getMaxWeight() >= weight) {
                System.out.println(truck);
            }
        }
    }

    public void addNewTruck(int truckID, String truckType, double truckWeight, double MaxWeight) {
        if (trucksData.getTrucks().containsKey(truckID)) {
            System.out.println("Truck already exists");
        }
        else {
            Truck newtruck = new Truck(truckID, truckType, truckWeight, MaxWeight);
            this.trucksData.addTruck(newtruck);
        }
    }

    public Truck getTruck(int truckID) {
        if (trucksData.getTrucks().get(truckID) != null)
            return trucksData.getTrucks().get(truckID);
        else return null;
    }


}
