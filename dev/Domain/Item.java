package Domain;

public class Item {
    private final int itemID;
    private String itemName;
    private double weight;

    public Item(int itemID, String itemName, double weight) {
        this.itemID = itemID;
        this.itemName = itemName;
        this.weight = weight;
    }
    public int getItemID() { return itemID; }
    public double getWeight() { return weight; }

    @Override
    public String toString() {
        return "Item{" +
                "itemID=" + itemID +
                ", itemName='" + itemName + '\'' +
                ", weight=" + weight +
                '}';
    }
}
