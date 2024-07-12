package Domain.HR;

import java.util.Objects;

public class Branch {
    private int branch_ID;
    private String branchName;
    private String address;

    public Branch(int branchID, String branchName, String address) {
        this.branch_ID = branchID;
        this.branchName = branchName;
        this.address = address;
    }

    public Branch(){

    }

    public int getBranchID() {
        return branch_ID;
    }

    public void setBranchID(int branch_ID) {
        this.branch_ID = branch_ID;
    }

    public String getBranchName() {
        return branchName;
    }

    public void setBranchName(String branch_name) {
        this.branchName = branch_name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    // Override equals and hashCode
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Branch branch = (Branch) o;
        return branch_ID == branch.branch_ID &&
                Objects.equals(branchName, branch.branchName) &&
                Objects.equals(address, branch.address);
    }

    @Override
    public int hashCode() {
        return Objects.hash(branch_ID);
    }

    public String toString(){
        String result_str="";
        result_str+="Branch ID: "+this.getBranchID()+", ";
        result_str+="Branch name: "+this.getBranchName()+", ";
        result_str+="Address: "+this.getAddress();
        return result_str;
    }

}
