package Domain;

public class Truck {
    private final int truckID;
    private final String truckType;
    private double truckWeight;
    private double MaxWeight;
    private boolean available;

    public Truck(int truckID, String truckType, double truckWeight, double MaxWeight) {
        this.truckID = truckID;
        this.truckType = truckType;
        this.truckWeight = truckWeight;
        this.MaxWeight = MaxWeight;
        this.available = true;
    }
    public int getTruckID() {return truckID;}
    public String getTruckType() { return truckType; }
    public double getTruckWeight() { return truckWeight; }
    public double getMaxWeight() { return MaxWeight; }
    public boolean isAvailable() { return available; }
    public void setMaxWeight(double maxWeight) {
        MaxWeight = maxWeight;
    }
    public void setAvailable(boolean available) { this.available = available; }

    @Override
    public String toString() {
        return "Truck{" +
                "truckID=" + truckID +
                ", truckType=" + truckType  +
                ", truckWeight=" + truckWeight +
                ", MaxWeight=" + MaxWeight +
                ", available=" + available +
                '}';
    }
}
