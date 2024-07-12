package DAL;

import Domain.Transports.Delivery_Document;
import Domain.Transports.Driver;
import Domain.Transports.Transport;
import Domain.Transports.Truck;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class TransportDAO implements IDAO<Transport> {
    private final String URL = "jdbc:sqlite:C:\\Users\\WIN10\\Documents\\שנה ב\\ניתו''צ\\עבודה 1 ניתוצ\\ADSS_Group_AK\\myDataBase.db";
    private final DriverDAO driverDAO = new DriverDAO();
    private final TruckDAO truckDAO = new TruckDAO();
    private final ALocationDAO locationDAO = new ALocationDAO();
    private final DeliveryDocumentDAO deliveryDocumentDAO = new DeliveryDocumentDAO();

    public TransportDAO() {
        TransportTableCreator.createTransportTable();
        TransportTableCreator.createTransportDeliveryDocumentsTable();
    }

    @Override
    public void add(Transport transport) throws SQLException {
        if (!this.getAll().containsKey(transport.getTransportID())) {
            String sqlTransport = "INSERT INTO transport (transportID, truckID, driverID, comments) VALUES (?, ?, ?, ?)";
            String sqlDeliveryDocument = "INSERT INTO transport_delivery_documents (transportID, documentID) VALUES (?, ?)";

            try (Connection connection = DataBase.connect();
                 PreparedStatement statementTransport = connection.prepareStatement(sqlTransport);
                 PreparedStatement statementDeliveryDocument = connection.prepareStatement(sqlDeliveryDocument)) {
                connection.setAutoCommit(false);

                try {
                    // Insert into transport table
                    statementTransport.setInt(1, transport.getTransportID());
                    statementTransport.setInt(2, transport.getTruck().getTruckID());
                    truckDAO.get(transport.getTruck().getTruckID()).setAvailable(false);
                    statementTransport.setInt(3, transport.getDriver().getDriverID());
                    driverDAO.get(transport.getDriver().getDriverID()).setAvailable(false);
                    statementTransport.setString(4, transport.getComments());
                    statementTransport.executeUpdate();

                    // Insert into transport_delivery_documents table
                    for (Delivery_Document doc : transport.getDelivery_documents()) {
                        statementDeliveryDocument.setInt(1, transport.getTransportID());
                        statementDeliveryDocument.setInt(2, doc.getDocumentID());
                        statementDeliveryDocument.addBatch();
                    }
                    statementDeliveryDocument.executeBatch();

                    connection.commit();
                } catch (SQLException e) {
                    connection.rollback();
                    throw e;
                } finally {
                    connection.setAutoCommit(true);
                }
            }
        }
    }

    @Override
    public void remove(int transportID) throws SQLException {
        String sql = "DELETE FROM transport WHERE transportID = ?";
        try (Connection connection = DataBase.connect();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, transportID);
            statement.executeUpdate();
        }
    }

    @Override
    public void update(Transport transport) throws SQLException {
        String sql = "UPDATE transport SET truckID = ?, driverID = ?, comments = ? WHERE transportID = ?";
        try (Connection connection = DataBase.connect();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, transport.getTruck().getTruckID());
            statement.setInt(2, transport.getDriver().getDriverID());
            statement.setString(3, transport.getComments());
            statement.setInt(4, transport.getTransportID());
            statement.executeUpdate();
        }
    }

    @Override
    public Transport get(int id) throws SQLException {
        if (!this.getAll().containsKey(id))
            return null;
        String sql = "SELECT * FROM transport WHERE transportID = ?";
        try (Connection connection = DataBase.connect();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                int transportID = resultSet.getInt("transportID");
                int truckID = resultSet.getInt("truckID");
                Truck truck = truckDAO.get(truckID);
                int driverID = resultSet.getInt("driverID");
                Driver driver = driverDAO.get(driverID);
                String comments = resultSet.getString("comments");
                List<Delivery_Document> deliveryDocumentList = getDeliveryDocumentsForTransport(connection, transportID);
                return new Transport(transportID, truck, driver, deliveryDocumentList, comments);
            }
            return null;
        }
    }

    private List<Delivery_Document> getDeliveryDocumentsForTransport(Connection connection, int transportID) throws SQLException {
        String sql = "SELECT documentID FROM transport_delivery_documents WHERE transportID = ?";
        List<Delivery_Document> deliveryDocuments = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, transportID);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    int documentID = resultSet.getInt("documentID");
                    Delivery_Document deliveryDocument = deliveryDocumentDAO.get(documentID);
                    if (deliveryDocument != null) {
                        deliveryDocuments.add(deliveryDocument);
                    }
                }
            }
        }
        return deliveryDocuments;
    }


    @Override
    public HashMap<Integer, Transport> getAll() throws SQLException {
        HashMap<Integer, Transport> transports = new HashMap<>();
        String sql = "SELECT * FROM transport";
        try (Connection connection = DataBase.connect();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {
            while (resultSet.next()) {
                int transportID = resultSet.getInt("transportID");
                int truckID = resultSet.getInt("truckID");
                Truck truck = truckDAO.get(truckID);
                int driverID = resultSet.getInt("driverID");
                Driver driver = driverDAO.get(driverID);
                String comments = resultSet.getString("comments");
                List<Delivery_Document> deliveryDocumentList = getDeliveryDocumentsForTransport(connection, transportID);
                Transport transport = new Transport(transportID, truck, driver, deliveryDocumentList, comments);
                transports.put(transportID, transport);
            }
        }
        return transports;
    }
}
