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
    public void removeTruck(int truckID) {
        if (trucksData.getTrucks().get(truckID) != null)
            trucksData.getTrucks().remove(truckID);
        else System.out.println("truck not found");
    }

    public String showTrucks(int truckID) {
        if (this.getTruck(truckID) != null) {
            return this.getTruck(truckID).toString();
        }
        else return "Truck not found";
    }
    public void setAvailable(int truckID, boolean available) {
        if (this.getTruck(truckID) != null) {
            this.getTruck(truckID).setAvailable(available);
        }
        else System.out.println("truck not found");
    }

    public void allTrucksAvailable() {
        for (Truck truck : trucksData.getTrucks().values()) {
            truck.setAvailable(true);
        }
    }

}
