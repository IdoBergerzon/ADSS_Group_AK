package Domain;

import Data.TransportsData;

import java.util.List;

public class TransportController {
    private TransportsData transportsData;

    public TransportController() {
        transportsData = new TransportsData();
    }

    public TransportsData getTransportsData() {
        return transportsData;
    }

    public Transport getTransport(int transportID) {
        if (!transportsData.getTransports().containsKey(transportID)) {
            return null;
        }
        return transportsData.getTransports().get(transportID);
    }

    /**
     * Transport closing (driver release, truck release, delivery status update)
     *
     * @param transport
     * @return
     */
    public void finishTransport(Transport transport) {
        transport.getDriver().setAvailable(true);
        transport.getTruck().setAvailable(true);
        for (Delivery_Document delivery : transport.getDelivery_documents()) {
            delivery.setDelivery_status(Delivery_DocumentStatus.finished);
        }
        transport.setFinished(true);
        System.out.println("Transport finished successfully");
    }


}

