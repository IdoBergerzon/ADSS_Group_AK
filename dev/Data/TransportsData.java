package Data;

import Domain.Transport;

import java.util.HashMap;

public class TransportsData {
    private HashMap<Integer, Transport> transports;


    public TransportsData() {
        this.transports = new HashMap<>();

    }
    public HashMap<Integer, Transport> getTransports() {
        return transports;
    }
    public void addTransport(Transport transport) {
        this.transports.put(transport.getTransportID(), transport);
    }

}