package Data;

import Domain.Work_Request;
import Domain.Worker;

import java.util.HashMap;
import java.util.Map;

public class InMemoryRequestRepository {
    private final Map<Integer, Work_Request> requests; ///need to change here

    public InMemoryRequestRepository() {
        requests = new HashMap<>();
    }
}
