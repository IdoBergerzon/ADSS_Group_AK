package Domain;

import DataAccessObject.DeliveryDocumentDAO;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

public class Delivery_DocumentsRepository implements IRepository<Delivery_Document> {
    private HashMap<Integer, Delivery_Document> delivery_Documents;
    private final DeliveryDocumentDAO deliveryDocumentDAO = new DeliveryDocumentDAO();

    public Delivery_DocumentsRepository() {
        delivery_Documents = new HashMap<>();
    }

    public void setDelivery_Documents(HashMap<Integer, Delivery_Document> delivery_Documents) {
        this.delivery_Documents = delivery_Documents;
    }

    @Override
    public void add(Delivery_Document delivery_Document) {
        int deliveryID = delivery_Document.getDocumentID();
        try {
            if (deliveryDocumentDAO.get(deliveryID) != null && !delivery_Documents.containsKey(delivery_Document.getDocumentID())) {
                delivery_Documents.put(delivery_Document.getDocumentID(), delivery_Document);
            } else if (deliveryDocumentDAO.get(deliveryID) == null && !delivery_Documents.containsKey(deliveryID)) {
                delivery_Documents.put(deliveryID, delivery_Document);
                deliveryDocumentDAO.add(delivery_Document);
            }
            else
                System.out.println("Delivery Document already exist");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void remove(int documentID) {
        try {
            if (deliveryDocumentDAO.get(documentID) != null && delivery_Documents.containsKey(documentID)) {
                delivery_Documents.remove(documentID);
                deliveryDocumentDAO.remove(documentID);
            } else if (deliveryDocumentDAO.get(documentID) != null && !delivery_Documents.containsKey(documentID)) {
                deliveryDocumentDAO.remove(documentID);
            }
            else
                System.out.println("Delivery Document not found");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(Delivery_Document delivery) {
        int documentID = delivery.getDocumentID();
        try {
            if (deliveryDocumentDAO.get(documentID) != null && delivery_Documents.containsKey(documentID)) {
                delivery_Documents.replace(documentID, delivery);
                deliveryDocumentDAO.update(delivery);
            } else if (deliveryDocumentDAO.get(documentID) != null && !delivery_Documents.containsKey(documentID)) {
                deliveryDocumentDAO.update(delivery);
                delivery_Documents.put(documentID, delivery);
            }
            System.out.println("DocumentID: " + documentID + "not found");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Delivery_Document get(int id) {
        try {
            if (deliveryDocumentDAO.get(id) != null && delivery_Documents.containsKey(id)) {
                return delivery_Documents.get(id);
            } else if (deliveryDocumentDAO.get(id) != null && !delivery_Documents.containsKey(id)) {
                delivery_Documents.put(id, deliveryDocumentDAO.get(id));
                return deliveryDocumentDAO.get(id);
            }
            else
                System.out.println("delivery document not found");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    @Override
    public HashMap<Integer, Delivery_Document> getAll() {
        HashMap<Integer, Delivery_Document> alldelivery_Documents = new HashMap<>();
        try {
            if (deliveryDocumentDAO != null) {
                alldelivery_Documents = deliveryDocumentDAO.getAll();
                delivery_Documents = alldelivery_Documents;
                return delivery_Documents;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return alldelivery_Documents;
    }
}
