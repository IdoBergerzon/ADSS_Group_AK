package Domain;

public class Driver {
    private final int driverID;
    private String driverName;
    private boolean available;
    private int licenseMaxWeight;


    public Driver(int driverID, String driverName, boolean available, int licenseMaxWeight) {
        this.driverID = driverID;
        this.driverName = driverName;
        this.available = available;
        this.licenseMaxWeight = licenseMaxWeight;
    }
    public int getDriverID() {return driverID;}
    public String getDriverName() {return driverName;}
    public boolean isAvailable() {return available;}
    public int getLicenseMaxWeight() {return licenseMaxWeight;}

    public void setDriverName(String driverName) {
        this.driverName = driverName;
    }
    public void setAvailable(boolean available) { this.available = available; }
    public void setLicenseMaxWeight(int licenseMaxWeight) { this.licenseMaxWeight = licenseMaxWeight; }



    @Override
    public String toString() {
        return "Driver{" +
                "driverID=" + driverID +
                ", driverName='" + driverName + '\'' +
                ", available=" + available +
               ", licenseMaxWeight=" + licenseMaxWeight +
                '}';
    }

}
