package Domain;

import java.util.HashMap;

public class LocationsRepository implements IRepository {
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

    @Override
    public void add(Object o) {
        if (o instanceof ALocation) {
            ALocation location = (ALocation) o;
            locations.put(location.getLocationID(), location);
        }
    }

    @Override
    public void remove(Object o) {
        if (o instanceof ALocation) {
            ALocation location = (ALocation) o;
            locations.remove(location.getLocationID());
        }
    }

    @Override
    public void update(Object o) {
        if (o instanceof ALocation) {
            ALocation location = (ALocation) o;
            if (locations.containsKey(location.getLocationID())) {
                locations.remove(location.getLocationID());
                locations.put(location.getLocationID(), location);
            }
        }
    }

    @Override
    public Object get(int id) {
        if (locations.containsKey(id)) {
            return locations.get(id);
        }
        return null;
    }
}
