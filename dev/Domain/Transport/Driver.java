package Domain.Transport;

import Domain.HR.Role;
import Domain.HR.Worker;

import java.util.Date;

public class Driver extends Worker {
    private boolean available;
    private int licenseMaxWeight;


    public Driver(int id, String name, int monthly_wage, int hourly_wage, Date start_date, Integer direct_manager_ID, Role role, int branch_id, String departement, String bank_details, int licenseMaxWeight) {
        super(id,name,monthly_wage,hourly_wage,start_date,direct_manager_ID,role,branch_id,departement,bank_details);
        this.available = true;
        this.licenseMaxWeight = licenseMaxWeight;
    }
    public boolean isAvailable() {return available;}
    public int getLicenseMaxWeight() {return licenseMaxWeight;}

    public void setAvailable(boolean available) { this.available = available; }
    public void setLicenseMaxWeight(int licenseMaxWeight) { this.licenseMaxWeight = licenseMaxWeight; }



    @Override
    public String toString() {
        return "Driver{" + super.toString() + ", available=" + available + ", licenseMaxWeight=" + licenseMaxWeight + '}';
    }


}