package Domain;

public class Truck {
    private int truckID;
    private String truckType;
    private double truckWeight;
    private double MaxWeight;

    public Truck(int truckID, String truckType, double truckWeight, double MaxWeight) {
        this.truckID = truckID;
        this.truckType = truckType;
        this.truckWeight = truckWeight;
        this.MaxWeight = MaxWeight;
    }
    public int getTruckID() {return truckID;}
    public String getTruckType() { return truckType; }
    public double getTruckWeight() { return truckWeight; }
    public double getMaxWeight() { return MaxWeight; }

    @Override
    public String toString() {
        return "Truck{" +
                "truckID=" + truckID +
                ", truckType='" + truckType + '\'' +
                ", truckWeight=" + truckWeight +
                ", MaxWeight=" + MaxWeight +
                '}';
    }
}
