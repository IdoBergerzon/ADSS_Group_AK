package Data;

import Domain.ALocation;

import java.util.HashMap;

public class LocationsData {
    private HashMap<Integer, ALocation> locations;

    public LocationsData() {
        this.locations = new HashMap<>();
    }

    public void setLocations(HashMap<Integer, ALocation> locations) {
        this.locations = locations;
    }

    public HashMap<Integer, ALocation> getLocations() {
        return locations;
    }

    public void addLocation(ALocation location) {
        locations.put(location.getLocationID(), location);
    }

    public void removeLocation(ALocation location) {
        locations.remove(location.getLocationID());
    }
}
