package Data;

import Domain.Driver;
import Domain.License;

import java.util.HashMap;
import java.util.List;

public class DriversData {
    private HashMap<Driver, List<License>> drivers;

    public DriversData() {
        this.drivers = new HashMap<>();
    }

    public HashMap<Driver, List<License>> getDrivers() {
        return drivers;
    }
}
