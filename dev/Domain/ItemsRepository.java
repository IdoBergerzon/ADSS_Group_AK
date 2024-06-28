package Domain;

import java.util.HashMap;

public class ItemsRepository {
    private HashMap<Integer, Item> items;

    public ItemsRepository() { this.items = new HashMap<>(); }
    public HashMap<Integer, Item> getItems() { return items; }
    public void setItems(HashMap<Integer, Item> items) { this.items = items; }
    public void addItem(Item item) {
        items.put(item.getItemID(),item);
    }
    public void removeItem(Item item) {
        items.remove(item.getItemID());
    }
    public Item getItem(int itemID) {
        return items.get(itemID);
    }

    @Override
    public String toString() {
        StringBuilder itemsStr = new StringBuilder();
        itemsStr.append("All Items:\n");
        for (Item item : items.values()) {
            itemsStr.append(item + "\n");
        }
        return itemsStr.toString();
    }
}
