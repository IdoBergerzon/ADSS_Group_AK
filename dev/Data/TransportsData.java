package Data;

import Domain.Transport;

import java.util.HashMap;

public class TransportsData {
    private HashMap<Integer, Transport> transports;

    public Transport getTransportById(int trackId) {
        return transports.get(trackId);
    }
}

