package Domain;
import java.util.List;

public class Driver {
    private final int driverID;
    private String driverName;
    private boolean available;
    private License license;

    public Driver(int driverID, String driverName, boolean available, License license) {
        this.driverID = driverID;
        this.driverName = driverName;
        this.available = available;
        this.license = license;
    }
    public int getDriverID() {return driverID;}
    public String getDriverName() {return driverName;}
    public boolean isAvailable() {return available;}
    public License getLicense() {return license;}

    public void setDriverName(String driverName) {
        this.driverName = driverName;
    }
    public void setAvailable(boolean available) { this.available = available; }
    public void setLicense(License license) { this.license = license; }



    @Override
    public String toString() {
        return "Driver{" +
                "driverID=" + driverID +
                ", driverName='" + driverName + '\'' +
                ", available=" + available +
               ", license=" + (license != null ? license.toString() : "No license") +
                '}';
    }
}
