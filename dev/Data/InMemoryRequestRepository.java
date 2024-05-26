package Data;

import Domain.Request;
import com.sun.jdi.request.DuplicateRequestException;

import java.util.HashMap;
import java.util.Map;

public class InMemoryRequestRepository {
    private final Map<Pair, Request> curr_requests;
    private final Map<Integer, Request> past_requests;///need to change here

    public InMemoryRequestRepository() {
        curr_requests = new HashMap<>();
        past_requests = new HashMap<>();
    }

    public void addRequest(Request request) {
        Pair<Integer,Integer> requestKey = new Pair<>(request.getWorker().getId(), request.getWeek());
        if (curr_requests.containsKey(requestKey)) {
            throw new DuplicateRequestException("Request already exists");
        }
        curr_requests.put(requestKey, request);

    }

    public Request[] getAllRequests() {
        return curr_requests.values().toArray(new Request[0]);
    }

}
