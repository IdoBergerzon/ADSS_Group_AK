package Domain;

import java.util.HashMap;

public class LocationsRepository {
    private HashMap<Integer, ALocation> locations;

    public LocationsRepository() {
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
