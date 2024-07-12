package Domain.Transports;

import DAL.Transports_DAL.ALocationDAO;

import java.sql.SQLException;
import java.util.HashMap;

public class LocationsRepository implements IRepository<ALocation> {
    private HashMap<Integer, ALocation> locations;
    private final ALocationDAO locationDAO = new ALocationDAO();

    public LocationsRepository() {
        this.locations = new HashMap<>();
    }

    public void setLocations(HashMap<Integer, ALocation> locations) {
        this.locations = locations;
    }

    @Override
    public void add(ALocation location) {
        int locationID = location.getLocationID();
        try {
            if (locationDAO.get(locationID)==null && !locations.containsKey(location.getLocationID())){
                locations.put(location.getLocationID(), location);
                locationDAO.add(location);
            } else if (locationDAO.get(locationID) != null && locations.containsKey(locationID)) {
                locations.put(location.getLocationID(), location);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void remove(int locationID) {
        try {
            if (locationDAO.get(locationID) != null && locations.containsKey(locationID)) {
                locationDAO.remove(locationID);
                locations.remove(locationID);
            } else if (locationDAO.get(locationID) != null && !locations.containsKey(locationID)) {
                locationDAO.remove(locationID);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(ALocation location) {
        int locationID = location.getLocationID();
        try {
            if (locationDAO.get(locationID) != null && locations.containsKey(locationID)) {
                locations.replace(location.getLocationID(), location);
                locationDAO.update(location);
            } else if (locationDAO.get(locationID) != null && !locations.containsKey(locationID)) {
                locationDAO.update(location);
                locations.put(locationID, location);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public ALocation get(int id) {
        try {
            if (locationDAO.get(id) != null && locations.containsKey(id)) {
                return locations.get(id);
            } else if (locationDAO.get(id) != null && !locations.containsKey(id)) {
                locations.put(id, locationDAO.get(id));
                return locations.get(id);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    @Override
    public HashMap<Integer, ALocation> getAll() {
        HashMap<Integer, ALocation> allLocations = new HashMap<>();
        try {
            if (locationDAO != null) {
                allLocations = locationDAO.getAll();
                locations = allLocations;
                return locations;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return locations;
    }
}
