package Data;

import Domain.Driver;
import Domain.License;
import Domain.Location;

import java.util.HashMap;
import java.util.List;

public class Drivers {
    private HashMap<Driver, List<License>> drivers;

    public Drivers() {
        this.drivers = new HashMap<>();
    }

    public HashMap<Driver, List<License>> getDrivers() {
        return drivers;
    }
}
