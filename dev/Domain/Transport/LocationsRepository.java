package Domain.Transport;

import DAL.Transport.ALocationDAO;
import Domain.HR.IRepository;
import Domain.HR.ShiftRepository;

import java.sql.SQLException;
import java.util.HashMap;

public class LocationsRepository implements IRepository<ALocation, Integer> {
    private HashMap<Integer, ALocation> locations;
    private final ALocationDAO locationDAO = new ALocationDAO();

    private LocationsRepository() {
        this.locations = new HashMap<>();
    }

    private static class InLocationHolder {
        private final static LocationsRepository INSTANCE = new LocationsRepository();
    }
    public static LocationsRepository getInstance() {
        return LocationsRepository.InLocationHolder.INSTANCE;
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
    public void remove(Integer locationID) {
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
    public ALocation get(Integer id) {
        try {
            if (locationDAO.get(id) != null && locations.containsKey(id)) {
                if (locations.get(id).getL_type().equals("Store"))
                    return new Store(id, locations.get(id).getAddress(), locations.get(id).getContact(), locations.get(id).getPhone());
                else
                    return new Supplier(id, locations.get(id).getAddress(), locations.get(id).getContact(), locations.get(id).getPhone());
            } else if (locationDAO.get(id) != null && !locations.containsKey(id)) {
                locations.put(id, locationDAO.get(id));
                if (locations.get(id).getL_type().equals("Store"))
                    return new Store(id, locations.get(id).getAddress(), locations.get(id).getContact(), locations.get(id).getPhone());
                else
                    return new Supplier(id, locations.get(id).getAddress(), locations.get(id).getContact(), locations.get(id).getPhone());
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

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
