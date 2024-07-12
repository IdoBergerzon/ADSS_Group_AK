package Domain.Transport;

import DAL.Transport.TransportDAO;
import Domain.HR.IRepository;

import java.sql.SQLException;
import java.util.HashMap;

public class TransportsRepository implements IRepository<Transport, Integer> {
    private HashMap<Integer, Transport> transports;
    private final TransportDAO transportDAO = new TransportDAO();

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
            tranStr.append(transport).append("\n");
        }
        return tranStr.toString();
    }

    @Override
    public void add(Transport transport) {
        int id = transport.getTransportID();
        try {
            if (transportDAO.get(id) != null && !transports.containsKey(id)) {
                this.transports.put(id, transport);
            } else if (transportDAO.get(id) == null && !transports.containsKey(id)) {
                this.transports.put(id, transport);
                this.transportDAO.add(transport);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void remove(Integer id) {
        try {
            if (transportDAO.get(id) != null && !transports.containsKey(id)) {
                transportDAO.remove(id);
            } else if (transportDAO.get(id) != null && transports.containsKey(id)) {
                transportDAO.remove(id);
                transports.remove(id);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void update(Transport transport) {
        int id = transport.getTransportID();
        try {
            if (transportDAO.get(id) != null) {
                this.transports.put(id, transport);
                this.transportDAO.update(transport);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Transport get(Integer id) {
        try {
            if (transportDAO.get(id) != null && this.transports.containsKey(id)) {
                return this.transports.get(id);
            } else if (transportDAO.get(id) != null && !transports.containsKey(id)) {
                this.transports.put(id, transportDAO.get(id));
                return this.transports.get(id);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }


    public HashMap<Integer, Transport> getAll() {
        HashMap<Integer, Transport> allTransports = new HashMap<>();
        try {
            if (transportDAO != null) {
                allTransports = transportDAO.getAll();
                transports = allTransports;
                return transports;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return transports;
    }
}
