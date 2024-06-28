package Domain;

import java.util.HashMap;

public class TransportsRepository {
    private HashMap<Integer, Transport> transports;


    public TransportsRepository() {
        this.transports = new HashMap<>();
    }

    public HashMap<Integer, Transport> getTransports() {
        return transports;
    }

    public void addTransport(Transport transport) {
        this.transports.put(transport.getTransportID(), transport);
    }

    public void resetTransports() {
        this.transports = new HashMap<>();
    }

    public Transport getTransportById(int transportID) {
        if (this.transports.containsKey(transportID)) {
            return this.transports.get(transportID);
        }
        else {
            System.out.println("transport not found");
            return null;
        }
    }

    public void removeTransportById(int transportID) {
        this.transports.remove(transportID);
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
}


