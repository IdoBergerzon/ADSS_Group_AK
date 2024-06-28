package Domain;

import java.util.HashMap;

public class ItemsRepository implements IRepository{
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
    public void add(Object o) {
        if (o instanceof Item) {
            Item item = (Item) o;
            items.put(item.getItemID(),item);
        }
    }

    @Override
    public void remove(Object o) {
        if (o instanceof Item) {
            Item item = (Item) o;
            items.remove(item.getItemID());
        }
    }

    @Override
    public void update(Object o) {
        if (o instanceof Item) {
            Item item = (Item) o;
            if(items.containsKey(item.getItemID())) {
                items.remove(item.getItemID());
                items.put(item.getItemID(),item);
            }
        }
    }

    @Override
    public Object get(int id) {
        if(items.containsKey(id)) {
            return items.get(id);
        }
        return null;
    }
}
