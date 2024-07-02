package Domain;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

public class LocationsRepository implements IRepository<ALocation> {
    private HashMap<Integer, ALocation> locations;

    public LocationsRepository() {
        this.locations = new HashMap<>();
    }

    public void setLocations(HashMap<Integer, ALocation> locations) {
        this.locations = locations;
    }

    @Override
    public void add(ALocation location) {
        if (!locations.containsKey(location.getLocationID())){
            locations.put(location.getLocationID(), location);
        }
    }

    @Override
    public void remove(int locationID) {
        locations.remove(locationID);
        }

    @Override
    public void update(ALocation location) {
        if (locations.containsKey(location.getLocationID())) {
            locations.replace(location.getLocationID(), location);
        }
    }

    @Override
    public ALocation get(int id) {
        if (locations.containsKey(id)) {
            return locations.get(id);
        }
        return null;
    }

    @Override
    public List<ALocation> getAll() {
        return new ArrayList(this.locations.values());
    }
}
