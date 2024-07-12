package Domain.Transports;

import DAL.Transports_DAL.ItemDAO;

import java.sql.SQLException;
import java.util.HashMap;

public class ItemsRepository implements IRepository<Item>{
    private HashMap<Integer, Item> items;
    private final ItemDAO itemDAO = new ItemDAO();

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
        int itemID = item.getItemID();
        try {
            if (itemDAO.get(itemID) != null && !items.containsKey(item.getItemID())) {
                items.put(itemID, item);
            } else if (itemDAO.get(itemID) == null) {
                items.put(itemID, item);
                itemDAO.add(item);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void remove(int itemID) {
        try {
            if (itemDAO.get(itemID) != null && items.containsKey(itemID)) {
                items.remove(itemID);
                itemDAO.remove(itemID);
            } else if (itemDAO.get(itemID) != null && !items.containsKey(itemID)) {
                itemDAO.remove(itemID);
            }
            items.remove(itemID);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(Item item) {
        int itemID = item.getItemID();
        try {
            if (itemDAO.get(itemID) != null && items.containsKey(itemID)) {
                items.replace(item.getItemID(),item);
                itemDAO.update(item);
            } else if (itemDAO.get(itemID) != null && !items.containsKey(itemID)) {
                itemDAO.update(item);
                items.put(item.getItemID(), item);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Item get(int id) {
        try {
            if (itemDAO.get(id) != null && items.containsKey(id)) {
                return items.get(id);
            } else if (itemDAO.get(id) != null && !items.containsKey(id)) {
                items.put(id, items.get(id));
                return items.get(id);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    @Override
    public HashMap<Integer, Item> getAll() {
        HashMap<Integer, Item> allItems = new HashMap<>();
        try {
            if (itemDAO != null) {
                allItems = itemDAO.getAll();
                items = allItems;
                return items;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return items;
    }
}
