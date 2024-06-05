package Domain;
import java.util.HashMap;

public class Delivery_Document {
    private final int documentID;
    private Store source;
    private Supplier destination;
    private double totalWeight;
    private Delivery_DocumentStatus delivery_status;
    private Delivery_ItemsStatus itemsStatus;
    private HashMap<Item,Integer> items;

    public Delivery_Document(Store source, int documentID, Supplier destination, HashMap<Item,Integer> items) {
        this.source = source;
        this.documentID = documentID;
        this.destination = destination;
        this.itemsStatus = Delivery_ItemsStatus.in_Progress;
        this.delivery_status = Delivery_DocumentStatus.waiting;
        this.items = items;
        this.totalWeight = getTotalWeight();
    }

    public int getDocumentID() {return documentID;}
    public Store  getSource() {return source;}
    public Supplier getDestination() {return destination;}
    public HashMap<Item,Integer> getItems() {return items;}
    public Delivery_DocumentStatus getDelivery_Status() {return delivery_status;}
    public Delivery_ItemsStatus getItemsStatus() {return itemsStatus;}

    public void setSource(Store source) {
        this.source = source;
    }

    public void setDestination(Supplier destination) {
        this.destination = destination;
    }

    public void setDelivery_status(Delivery_DocumentStatus status) {
        this.delivery_status = status;
    }

    public void setItemsStatus(Delivery_ItemsStatus status) {
        this.itemsStatus = status;
    }

    public double getTotalWeight() {
        double totalWeight = 0;
        for (Item item : items.keySet()) {
            totalWeight += item.getWeight() * items.get(item);
        }
        this.totalWeight = totalWeight;
        return totalWeight;
    }

    public boolean removeItem(Item item) {
        if (items.containsKey(item)) {
            items.remove(item);
            this.getTotalWeight();
            return true;
        }
        else
            System.out.println("item does not exist in this delivery document.\n");
        return false;
    }

    public void printAllItems() {
        for (Item item : items.keySet()) {
            System.out.println(item + "," + items.get(item));
        }
    }

    @Override
    public String toString() {
        //להוסיף את הפרטים החסרים מהובלה
        return "     Delivery_Document{" + '\'' +
                "\n     documentID=" + documentID +
                "\n     source=" + source.toString() +
                "\n     destination=" + destination.toString() +
                "\n     items=" + items +
                "\n     status=" + delivery_status.toString() +
                '}';
    }
}
