package Data;

import Domain.Driver;
import Domain.License;

import java.util.HashMap;
import java.util.List;

public class DriversData {
    private HashMap<Driver, License> drivers;

    public DriversData() {
        this.drivers = new HashMap<>();
    }

    public HashMap<Driver, License> getDrivers() {
        return drivers;
    }
}
