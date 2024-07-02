package DataAccessObject;

import Domain.Transport;

import java.sql.SQLException;
import java.util.List;

public class TransportDAO implements IDAO<Transport> {

    @Override
    public void add(Transport transport) throws SQLException {

    }

    @Override
    public void remove(Transport transport) throws SQLException {

    }

    @Override
    public void update(Transport transport) throws SQLException {

    }

    @Override
    public Transport get(int id) throws SQLException {
        return null;
    }

    @Override
    public List<Transport> getAll() throws SQLException {
        return List.of();
    }
}
