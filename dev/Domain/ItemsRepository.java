package Domain;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

public class ItemsRepository implements IRepository<Item>{
    private HashMap<Integer, Item> items;

    public ItemsRepository() { this.items = new HashMap<>(); }
    public void setItems(HashMap<Integer, Item> items) { this.items = items; }

    @Override
    public String toString() {
        StringBuilder itemsStr = new StringBuilder();
        itemsStr.append("All Items:\n");
        for (Item item : items.values()) {
            itemsStr.append(item + "\n");
        }
        return itemsStr.toString();
    }

    @Override
    public void add(Item item) {
        if (!items.containsKey(item.getItemID())) {
            items.put(item.getItemID(), item);
        }
    }

    @Override
    public void remove(int itemID) {
        items.remove(itemID);
        }

    @Override
    public void update(Item item) {
        if (items.containsKey(item.getItemID())) {
            items.replace(item.getItemID(),item);
            items.put(item.getItemID(),item);
        }
    }

    @Override
    public Item get(int id) {
        if (items.containsKey(id)) {
            return items.get(id);
        }
        return null;
    }

    @Override
    public List<Item> getAll() {
        List<Item> allItems = new ArrayList<>();
        for (Item item : items.values()) {
            allItems.add(item);
        }
        return allItems;
    }
}
