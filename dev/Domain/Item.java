package Domain;

public class Item {
    private int itemID;
    private String itemName;
    private double weight;

    public Item(int itemID, String itemName, double weight) {
        this.itemID = itemID;
        this.itemName = itemName;
        this.weight = weight;
    }
    public int getItemID() { return itemID; }
    public String getItemName() { return itemName; }
    public double getWeight() { return weight; }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    @Override
    public String toString() {
        return "Item{" +
                "itemID=" + itemID +
                ", itemName='" + itemName + '\'' +
                ", weight=" + weight +
                '}';
    }
}
