package Domain;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

public class ItemsRepository<T> implements IRepository<T>{
    private HashMap<Integer, Item> items;

    public ItemsRepository() { this.items = new HashMap<>(); }
    public HashMap<Integer, Item> getItems() { return items; }
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
    public void add(T t) {
        if (t instanceof Item) {
            Item item = (Item) t;
            items.put(item.getItemID(),item);
        }
    }

    @Override
    public void remove(int itemID) {
        items.remove(itemID);
        }

    @Override
    public void update(T t) {
        if (t instanceof Item) {
            Item item = (Item) t;
            if (items.containsKey(item.getItemID())) {
                items.replace(item.getItemID(),item);
                items.put(item.getItemID(),item);
            }
        }
    }

    @Override
    public T get(int id) {
        if (items.containsKey(id)) {
            return (T) items.get(id);
        }
        return null;
    }

    @Override
    public List<T> getAll() {
        List<T> allItems = new ArrayList<>();
        for (Item item : items.values()) {
            allItems.add((T) item);
        }
        return allItems;
    }
}
