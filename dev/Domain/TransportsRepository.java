package Domain;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

public class TransportsRepository<T> implements IRepository<T> {
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
    public void add(T t) {
        if (t instanceof Transport) {
            Transport transport = (Transport) t;
            this.transports.put(transport.getTransportID(), transport);
        }
    }

    @Override
    public void remove(int id) {
        this.transports.remove(id);
    }

    @Override
    public void update(T t) {
        if (t instanceof Transport) {
            Transport transport = (Transport) t;
            if (this.transports.containsKey(transport.getTransportID())) {
                this.transports.replace(transport.getTransportID(), transport);
            }
        }
    }

    @Override
    public T get(int id) {
        if (this.transports.containsKey(id)) {
            return (T) this.transports.get(id);
        }
        return null;
    }

    @Override
    public List<T> getAll() {
        return new ArrayList(this.transports.values());
    }
}


