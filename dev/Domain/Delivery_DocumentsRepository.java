package Domain;

import java.util.HashMap;

public class Delivery_DocumentsRepository implements IRepository {
    private HashMap<Integer, Delivery_Document> delivery_Documents;

    public Delivery_DocumentsRepository() {
        delivery_Documents = new HashMap<>();
    }

    public HashMap<Integer, Delivery_Document> getDelivery_Documents() {
        return delivery_Documents;
    }

    @Override
    public void add(Object o) {
        if (o instanceof Delivery_Document) {
            Delivery_Document delivery = (Delivery_Document) o;
            delivery_Documents.put(delivery.getDocumentID(), delivery);
        }
    }

    @Override
    public void remove(Object o) {
        if (o instanceof Delivery_Document) {
            Delivery_Document delivery = (Delivery_Document) o;
            delivery_Documents.remove(delivery.getDocumentID());
        }
    }

    @Override
    public void update(Object o) {
        if (o instanceof Delivery_Document) {
            Delivery_Document delivery = (Delivery_Document) o;
            if (delivery_Documents.containsKey(delivery.getDocumentID())) {
                delivery_Documents.remove(delivery.getDocumentID());
                delivery_Documents.put(delivery.getDocumentID(), delivery);
            }
        }
    }

    @Override
    public Object get(int id) {
        if (delivery_Documents.containsKey(id)) {
            return delivery_Documents.get(id);
        }
        return null;
    }
}
