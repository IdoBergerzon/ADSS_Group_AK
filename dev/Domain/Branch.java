package Domain;

import java.util.Objects;

public class Branch {
    private int branch_ID;
    private String branch_name;
    private String address;

    public Branch(int branch_ID, String branch_name, String address) {
        this.branch_ID = branch_ID;
        this.branch_name = branch_name;
        this.address = address;
    }

    public int getBranchID() {
        return branch_ID;
    }

    public void setBranchID(int branch_ID) {
        this.branch_ID = branch_ID;
    }

    public String getBranch_name() {
        return branch_name;
    }

    public void setBranchName(String branch_name) {
        this.branch_name = branch_name;
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
                Objects.equals(branch_name, branch.branch_name) &&
                Objects.equals(address, branch.address);
    }

    @Override
    public int hashCode() {
        return Objects.hash(branch_ID);
    }


}
