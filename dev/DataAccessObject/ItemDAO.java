package DataAccessObject;

import Domain.Item;

import java.sql.SQLException;
import java.util.List;

public class ItemDAO implements IDAO<Item> {

    @Override
    public void add(Item item) throws SQLException {

    }

    @Override
    public void remove(Item item) throws SQLException {

    }

    @Override
    public void update(Item item) throws SQLException {

    }

    @Override
    public Item get(int id) throws SQLException {
        return null;
    }

    @Override
    public List<Item> getAll() throws SQLException {
        return List.of();
    }
}
