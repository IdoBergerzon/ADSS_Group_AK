package Domain;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

public class TransportsRepository implements IRepository<Transport> {
    private HashMap<Integer, Transport> transports;


    public TransportsRepository() {
        this.transports = new HashMap<>();
    }

    public HashMap<Integer, Transport> getTransports() {
        return transports;
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
        if (!transports.containsKey(transport.getTransportID()))
            this.transports.put(transport.getTransportID(), transport);
        }

    @Override
    public void remove(int id) {
        this.transports.remove(id);
    }

    @Override
    public void update(Transport transport) {
        if (this.transports.containsKey(transport.getTransportID())) {
            this.transports.replace(transport.getTransportID(), transport);
        }
    }

    @Override
    public Transport get(int id) {
        if (this.transports.containsKey(id)) {
            return this.transports.get(id);
        }
        return null;
    }

    @Override
    public List<Transport> getAll() {
        return new ArrayList<>(this.transports.values());
    }
}


