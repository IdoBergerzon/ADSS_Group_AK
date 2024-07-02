package DataAccessObject;

import Domain.Driver;
import Domain.Transport;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

public class TransportDAO implements IDAO<Transport> {
    private final String URL = "jdbc:sqlite:sample.db";
    private final DriverDAO driverDAO = new DriverDAO();
    private final TruckDAO truckDAO = new TruckDAO();
    private final ALocationDAO locationDAO = new ALocationDAO();


    @Override
    public void add(Transport transport) throws SQLException {
        if (!this.getAll().containsKey(transport.getTransportID())){

        }
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
    public HashMap<Integer, Transport> getAll() throws SQLException {
        return null;
    }
}
