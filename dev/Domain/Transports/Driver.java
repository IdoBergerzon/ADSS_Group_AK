package Domain.Transports;

public class Driver {
    private final int driverID;
    private String driverName;
    private boolean available;
    private int licenseMaxWeight;


    public Driver(int driverID, String driverName, int licenseMaxWeight) {
        this.driverID = driverID;
        this.driverName = driverName;
        this.available = true;
        this.licenseMaxWeight = licenseMaxWeight;
    }
    public int getDriverID() {return driverID;}
    public String getDriverName() {return driverName;}
    public boolean isAvailable() {return available;}
    public int getLicenseMaxWeight() {return licenseMaxWeight;}

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
