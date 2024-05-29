package Domain;

import Data.TransportsData;

import javax.sound.midi.Track;

public class TransportController {

    private TransportsData transportsData;

    public TransportController() {
        this.transportsData = new TransportsData();
    }

    public TransportsData getTransportsData() {
        return transportsData;
    }

    public void setShipping_area(Address address, int shipping_area) {
        address.setShipping_area(shipping_area);
        System.out.println("Shipping area set successfully for address: " + address.getFull_address());
    }
//to add alarm
    public void changeDriver(int trackId, Driver newDriver) {
        // Retrieve the transport associated with the given track ID
        Transport transport = transportsData.getTransportById(trackId);

        if (transport != null) {
            transport.getDriver().setAvailable(true);
            // Update the driver object of the transport
            transport.setDriver(newDriver);
            System.out.println("Driver changed successfully for track " + trackId);
        } else {
            System.out.println("Transport not found with ID: " + trackId);
        }

    }



    }






