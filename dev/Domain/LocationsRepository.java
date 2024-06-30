package Domain;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

public class LocationsRepository<T> implements IRepository<T> {
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

    @Override
    public void add(T t) {
        if (t instanceof ALocation) {
            ALocation location = (ALocation) t;
            locations.put(location.getLocationID(), location);
        }
    }

    @Override
    public void remove(int locationID) {
        locations.remove(locationID);
        }

    @Override
    public void update(T t) {
        if (t instanceof ALocation) {
            ALocation location = (ALocation) t;
            if (locations.containsKey(location.getLocationID())) {
                locations.replace(location.getLocationID(), location);
            }
        }
    }

    @Override
    public T get(int id) {
        if (locations.containsKey(id)) {
            return (T) locations.get(id);
        }
        return null;
    }

    @Override
    public List<T> getAll() {
        return new ArrayList(this.locations.values());
    }
}
