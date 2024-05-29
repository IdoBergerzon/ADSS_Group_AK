package Domain;
import java.sql.Time;
import java.util.Date;
import java.util.HashMap;

public class Delivery_Document {
    private final int documentID;
    private Store source;
    private Supplier destination;
    private HashMap<Item,Integer> items;

    public Delivery_Document(Store source, int documentID, Supplier destination, HashMap<Item,Integer> items) {
        this.source = source;
        this.documentID = documentID;
        this.destination = destination;
        this.items = items;
    }

    public int getDocumentID() {return documentID;}
    public Store  getSource() {return source;}
    public Supplier getDestination() {return destination;}
    public HashMap<Item,Integer> getItems() {return items;}

    public void setSource(Store source) {
        this.source = source;
    }

    public void setDestination(Supplier destination) {
        this.destination = destination;
    }

    public void setItems(HashMap<Item,Integer> items) {
        this.items = items;
    }

    public double getTotalWeight() {
        double totalWeight = 0;
        for (Item item : items.keySet()) {
            totalWeight += item.getWeight() * items.get(item);
        }
        return totalWeight;
    }

    @Override
    public String toString() {
        return "Delivery_Document{" + '\'' +
                ", source=" + source.toString() +
                ", destination=" + destination.toString() +
                ", items=" + items +
                '}';
    }
}
