package Domain;
import java.util.List;

public class Driver {
    private int driverID;
    private String driverName;
    private List<License> licenses;

    public Driver(int driverID, String driverName, List<License> licenses) {
        this.driverID = driverID;
        this.driverName = driverName;
        this.licenses = licenses;
    }
    public int getDriverID() {return driverID;}
    public String getDriverName() {return driverName;}
    public List<License> getLicenses() {return licenses;}

    public void addLicense(License license) {
        licenses.add(license);
    }

    @Override
    public String toString() {
        return "Driver{" +
                "driverID=" + driverID +
                ", driverName='" + driverName + '\'' +
                ", licenses=" + licenses.toString() +
                '}';
    }
}
