package Domain;

import java.util.HashMap;

public class TransportsRepository implements IRepository {
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
    public void add(Object o) {
        if (o instanceof Transport) {
            Transport transport = (Transport) o;
            this.transports.put(transport.getTransportID(), transport);
        }
    }

    @Override
    public void remove(Object o) {
        if (o instanceof Transport) {
            Transport transport = (Transport) o;
            this.transports.remove(transport.getTransportID());
        }
    }

    @Override
    public void update(Object o) {
        if (o instanceof Transport) {
            Transport transport = (Transport) o;
            if (this.transports.containsKey(transport.getTransportID())) {
                this.transports.remove(transport.getTransportID());
                this.transports.put(transport.getTransportID(), transport);
            }
        }
    }

    @Override
    public Object get(int id) {
        if (this.transports.containsKey(id)) {
            return this.transports.get(id);
        }
        return null;
    }
}


