package Domain;

import DataAccessObject.TransportDAO;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

public class TransportsRepository implements IRepository<Transport> {
    private HashMap<Integer, Transport> transports;
    private TransportDAO transportDAO = new TransportDAO();


    public TransportsRepository() {
        this.transports = new HashMap<>();
    }

    public void setTransports(HashMap<Integer, Transport> transports) {
        this.transports = transports;
    }

    @Override
    public String toString() {
        StringBuilder tranStr = new StringBuilder();
        tranStr.append("All Transports:\n");
        for (Transport transport : transports.values()) {
            tranStr.append(transport + "\n");
        }
        return tranStr.toString();
    }

    @Override
    public void add(Transport transport) {
        int id = transport.getTransportID();
        try {
            if (transportDAO.get(id) != null && !transports.containsKey(transport.getTransportID()))
                this.transports.put(transport.getTransportID(), transport);
            else if (transportDAO.get(id) == null && !transports.containsKey(id)) {
                this.transports.put(id, transport);
                this.transportDAO.add(transport);
            }
            else
                System.out.println("Transport already exists");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void remove(int id) {
        try {
            if (transportDAO.get(id) != null && !transports.containsKey(id))
                transportDAO.remove(id);
            else if (transportDAO.get(id) != null && transports.containsKey(id)) {
                transportDAO.remove(id);
                transports.remove(id);
            }
            else
                System.out.println("Transport with ID " + id + " not found");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(Transport transport) {
        int id = transport.getTransportID();
        try {
            if (transportDAO.get(id) != null && this.transports.containsKey(transport.getTransportID())) {
                this.transports.replace(transport.getTransportID(), transport);
                this.transportDAO.update(transport);
            } else if (transportDAO.get(id) != null && !transports.containsKey(id)) {
                transportDAO.update(transport);
                this.transports.put(id, transport);
            }
            else
                System.out.println("Transport " + transport.getTransportID() + " not found");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Transport get(int id) {
        try {
            if (transportDAO.get(id) != null && this.transports.containsKey(id)) {
                return this.transports.get(id);
            } else if (transportDAO.get(id) != null && !transports.containsKey(id)) {
                this.transports.put(id, transportDAO.get(id));
                return this.transports.get(id);
            }
            else
                System.out.println("No Transport with ID " + id + " found");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    @Override
    public HashMap<Integer, Transport> getAll() {
        HashMap<Integer, Transport> allTransports = new HashMap<>();
        try {
            if (transportDAO != null) {
                allTransports= transportDAO.getAll();
                transports = allTransports;
                return transports;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return transports;
    }
}


