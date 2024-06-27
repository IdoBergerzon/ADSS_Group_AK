package Domain;

public interface IRequestRepository {

    void addRequest(Request request) throws Exception;
    void editRequest(Request request) throws Exception;
    Request[] getAllRequests();
    Request getRequestByWorker(int worker_id);
}
