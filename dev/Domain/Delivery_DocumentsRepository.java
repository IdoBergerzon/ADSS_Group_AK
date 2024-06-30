package Domain;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

public class Delivery_DocumentsRepository<T> implements IRepository<T> {
    private HashMap<Integer, Delivery_Document> delivery_Documents;

    public Delivery_DocumentsRepository() {
        delivery_Documents = new HashMap<>();
    }

    public HashMap<Integer, Delivery_Document> getDelivery_Documents() {
        return delivery_Documents;
    }

    @Override
    public void add(T t) {
        if (t instanceof Delivery_Document) {
            Delivery_Document delivery = (Delivery_Document) t;
            delivery_Documents.put(delivery.getDocumentID(), delivery);
        }
    }

    @Override
    public void remove(int documentID) {
        if (delivery_Documents.containsKey(documentID)) {
            delivery_Documents.remove(documentID);
        }
    }

    @Override
    public void update(T t) {
        if (t instanceof Delivery_Document) {
            Delivery_Document delivery = (Delivery_Document) t;
            if (delivery_Documents.containsKey(delivery.getDocumentID())) {
                delivery_Documents.remove(delivery.getDocumentID());
                delivery_Documents.put(delivery.getDocumentID(), delivery);
            }
        }
    }

    @Override
    public T get(int id) {
        if (delivery_Documents.containsKey(id)) {
            return (T) delivery_Documents.get(id);
        }
        return null;
    }

    @Override
    public List<T> getAll() {
        return new ArrayList(delivery_Documents.values());
    }
}
