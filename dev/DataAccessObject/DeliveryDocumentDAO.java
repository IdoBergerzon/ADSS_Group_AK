package DataAccessObject;

import Domain.Delivery_Document;

import java.sql.SQLException;
import java.util.List;

public class DeliveryDocumentDAO implements IDAO<Delivery_Document> {

    @Override
    public void add(Delivery_Document deliveryDocument) throws SQLException {

    }

    @Override
    public void remove(Delivery_Document deliveryDocument) throws SQLException {

    }

    @Override
    public void update(Delivery_Document deliveryDocument) throws SQLException {

    }

    @Override
    public Delivery_Document get(int id) throws SQLException {
        return null;
    }

    @Override
    public List<Delivery_Document> getAll() throws SQLException {
        return List.of();
    }
}
