package Domain;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

public class Delivery_DocumentsRepository implements IRepository<Delivery_Document> {
    private HashMap<Integer, Delivery_Document> delivery_Documents;

    public Delivery_DocumentsRepository() {
        delivery_Documents = new HashMap<>();
    }

    public HashMap<Integer, Delivery_Document> getDelivery_Documents() {
        return delivery_Documents;
    }

    @Override
    public void add(Delivery_Document delivery_Document) {
        if (!delivery_Documents.containsKey(delivery_Document.getDocumentID())) {
            delivery_Documents.put(delivery_Document.getDocumentID(), delivery_Document);
        }
    }

    @Override
    public void remove(int documentID) {
        if (delivery_Documents.containsKey(documentID)) {
            delivery_Documents.remove(documentID);
        }
    }

    @Override
    public void update(Delivery_Document delivery) {
        if (delivery_Documents.containsKey(delivery.getDocumentID())) {
            delivery_Documents.remove(delivery.getDocumentID());
            delivery_Documents.put(delivery.getDocumentID(), delivery);
        }
    }

    @Override
    public Delivery_Document get(int id) {
        if (delivery_Documents.containsKey(id)) {
            return delivery_Documents.get(id);
        }
        return null;
    }

    @Override
    public List<Delivery_Document> getAll() {
        return new ArrayList(delivery_Documents.values());
    }
}
