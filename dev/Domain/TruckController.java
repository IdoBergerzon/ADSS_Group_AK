package Domain;


import Data.TrucksData;

import java.util.Scanner;

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

    public boolean createTruck() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Insert truck ID:");
        int truckID = scanner.nextInt();
        scanner.nextLine();
        if (this.getTruck(truckID) != null) {
            System.out.print("The truck already exists in the system\n");
            return false;
        } else {
            System.out.print("Insert truck Type:\n");
            String truckType = scanner.nextLine();
            System.out.println("Insert truck Weight:");
            double truckWeight = scanner.nextDouble();
            System.out.println("Insert truck Max Weight:");
            double MaxWeight = scanner.nextDouble();
            this.addNewTruck(truckID, truckType, truckWeight, MaxWeight);
            System.out.println("Truck added successfully");
        }
        return true;
    }

    public Truck getTruck(int truckID) {
        if (trucksData.getTrucks().get(truckID) != null)
            return trucksData.getTrucks().get(truckID);
        else return null;
    }

    public void displayTruck() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Insert truck ID:");
        int truckID = scanner.nextInt();
        scanner.nextLine();
        if (this.getTruck(truckID) == null) {
            System.out.println("Truck does not exist.\n");
        } else
            System.out.println(this.getTruck(truckID));
    }

    public void displayAllTrucks() {
        Scanner scanner = new Scanner(System.in);
        if (this.getTrucksData().getTrucks().isEmpty())
            System.out.println("There are no trucks in the system.\n");
        else
            System.out.println(this.getTrucksData().toString() + "\n");
    }

}
