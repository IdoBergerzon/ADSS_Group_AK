package Domain;
import java.util.List;

public class Driver {
    private int driverID;
    private String driverName;
    private boolean available;
    //private List<License> licenses;

    public Driver(int driverID, String driverName, boolean available, List<License> licenses) {
        this.driverID = driverID;
        this.driverName = driverName;
        this.available = available;
    //    this.licenses = licenses;
    }
    public int getDriverID() {return driverID;}
    public String getDriverName() {return driverName;}
    public boolean isAvailable() {return available;}
    //public List<License> getLicenses() {return licenses;}

    public void setDriverID(int driverID) {
        this.driverID = driverID;
    }

    public void setDriverName(String driverName) {
        this.driverName = driverName;
    }
    public void setAvailable(boolean available) { this.available = available; }

    //public void setLicenses(List<License> licenses) {
    //    this.licenses = licenses;
    //}

    @Override
    public String toString() {
        return "Driver{" +
                "driverID=" + driverID +
                ", driverName='" + driverName + '\'' +
                ", available=" + available +
    //            ", licenses=" + licenses.toString() +
                '}';
    }
}
