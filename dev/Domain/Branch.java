package Domain;

public class Branch {
    private int branch_ID;
    private String branch_name;
    private String address;

    public int getBranch_ID() {
        return branch_ID;
    }

    public void setBranch_ID(int branch_ID) {
        this.branch_ID = branch_ID;
    }

    public String getBranch_name() {
        return branch_name;
    }

    public void setBranch_name(String branch_name) {
        this.branch_name = branch_name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Branch(int branch_ID, String branch_name, String address) {
        this.branch_ID = branch_ID;
        this.branch_name = branch_name;
        this.address = address;
    }
}
