package Domain;

import java.util.HashMap;

public class Delivery_DocumentsRepository {
    private HashMap<Integer, Delivery_Document> delivery_Documents;

    public Delivery_DocumentsRepository() {
        delivery_Documents = new HashMap<>();
    }

    public HashMap<Integer, Delivery_Document> getDelivery_Documents() {
        return delivery_Documents;
    }

    public void addDelivery_Document(Delivery_Document delivery) {
        delivery_Documents.put(delivery.getDocumentID(), delivery);
    }
}
