package Data;

import Domain.Request;

public interface Request_Repository {

    void addRequest(Request request) throws Exception;
    void editRequest(Request request) throws Exception;
    Request[] getAllRequests();
    Request getRequestByWorker(int worker_id);
}
