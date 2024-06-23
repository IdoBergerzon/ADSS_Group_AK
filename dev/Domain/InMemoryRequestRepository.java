package Domain;


import java.util.HashMap;
import java.util.Map;

public class InMemoryRequestRepository implements Request_Repository{
    private final Map<Pair, Request> curr_requests;
    private final Map<Integer, Request[]> past_requests;///need to change here

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

    public void addRequest(Request request) throws Exception {
        Pair<Integer,Integer> requestKey = new Pair<>(request.getWorker().getId(), request.getWeek());
        if (curr_requests.containsKey(requestKey)) {
            throw new Exception("Request already exists");
        }
        curr_requests.put(requestKey, request);

    }

    public void editRequest(Request request) throws Exception {
        Pair<Integer,Integer> requestKey = new Pair<>(request.getWorker().getId(), request.getWeek());
        if (!curr_requests.containsKey(requestKey)){
            throw new Exception("Request not exist, can't edit");
        }
        curr_requests.put(requestKey, request);
    }

    public Request[] getAllRequests() {
        return curr_requests.values().toArray(new Request[0]);
    }

    public Request getRequestByWorker(int worker_id) {
        Pair<Integer,Integer> requestKey = new Pair<>(worker_id, Week.getWeek());
        return curr_requests.get(requestKey);
    }



    public Request[] getPastRequests(int week) {
        return past_requests.get(week);
    }

}
