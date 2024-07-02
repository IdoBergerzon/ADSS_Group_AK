package DataAccessObject;

import Domain.Transport;
import Domain.Truck;

import java.sql.SQLException;
import java.util.List;

public class TruckDAO implements IDAO<Truck>{

    @Override
    public void add(Truck truck) throws SQLException {

    }

    @Override
    public void remove(Truck truck) throws SQLException {

    }

    @Override
    public void update(Truck truck) throws SQLException {

    }

    @Override
    public Truck get(int id) throws SQLException {
        return null;
    }

    @Override
    public List<Truck> getAll() throws SQLException {
        return List.of();
    }
}
