package Data;

import Domain.Request;
import com.sun.jdi.request.DuplicateRequestException;

import java.util.HashMap;
import java.util.Map;

public class InMemoryRequestRepository {
    private final Map<Pair, Request> curr_requests;
    private final Map<Integer, Request> past_requests;///need to change here

    private InMemoryRequestRepository() {
        curr_requests = new HashMap<>();
        past_requests = new HashMap<>();
    }
    private static class InRequestHolder {
        private final static InMemoryRequestRepository INSTANCE = new InMemoryRequestRepository();
    }

    /**
     * public static method that returns the single instance of the class
     */

    public static InMemoryRequestRepository getInstance() {
        return InMemoryRequestRepository.InRequestHolder.INSTANCE;
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

    public void newWeek(){

    }

}
