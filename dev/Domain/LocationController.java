package Domain;

import Domain.ALocation;
import Data.LocationsData;

import javax.xml.stream.Location;

public class LocationController {
    private LocationsData locationsData;
    public LocationController() {
        locationsData = new LocationsData();
    }

    public LocationsData getLocationsData() {
        return locationsData;
    }
    public void setLocationsData(LocationsData locationsData) {
        this.locationsData = locationsData;
    }

    public void addLocation(int locationID, Address address, String contact, String phone, String l_type) {
        if (!locationsData.getLocations().containsKey(locationID)) {
            if (l_type == "Supplier"){
                Supplier supplier = new Supplier(locationID, address, contact, phone);
                locationsData.addLocation(supplier);
            } else if (l_type == "Store") {
                Store store = new Store(locationID, address, contact, phone);
                locationsData.addLocation(store);
            }
            else {
                System.out.println(l_type + "is not a l_type\n");
            }
        } else {
            System.out.println("location already exists");
        }
    }
    public ALocation getLocation(int locationID) {
            if (locationsData.getLocations().get(locationID) != null) {
                return locationsData.getLocations().get(locationID);
            }
            return null;
        }

    public void updateAddress(int locationID, Address address) {
        if (!locationsData.getLocations().containsKey(locationID)) {
            System.out.println("location does not exist");
        }
        else {
            ALocation location = locationsData.getLocations().get(locationID);
            location.setAddress(address);
        }
    }

    public void updateContact(int locationID, String contact) {
        if (!locationsData.getLocations().containsKey(locationID)) {
            System.out.println("location does not exist");
        }
        else {
            ALocation location = locationsData.getLocations().get(locationID);
            location.setContact(contact);
        }
    }

    public void updatePhone(int locationID, String phone) {
        if (!locationsData.getLocations().containsKey(locationID)) {
            System.out.println("location does not exist");
        }
        else   {
            ALocation location = locationsData.getLocations().get(locationID);
            location.setPhone(phone);
        }
    }

    /**
     * Division into shipping areas
     * @param locationID
     * @param shipping_area
     */
    public void setShipping_area(int locationID, int shipping_area) {
        if (!locationsData.getLocations().containsKey(locationID)) {
            System.out.println("location does not exist");
        }
        else {
            ALocation location = locationsData.getLocations().get(locationID);
            location.getAddress().setShipping_area(shipping_area);
            System.out.println("Shipping area set successfully for address: " + location.getAddress().getShipping_area());
        }
    }

}
