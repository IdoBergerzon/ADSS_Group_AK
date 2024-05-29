package Domain;

public abstract class ALocation {
    private final int locationID;
    private Address address;
    private String Contact;
    private String phone;
    private String l_type;

    public ALocation(int locationID, Address address, String contact, String phone, String l_type) {
        this.locationID = locationID;
        this.address = address;
        this.Contact = contact;
        this.phone = phone;
        this.l_type = l_type;
    }

    public int getLocationID() {
        return locationID;
    }

    public Address getAddress() {
        return address;
    }
    public String getContact() {
        return Contact;
    }
    public String getPhone() {
        return phone;
    }
    public String getL_type() {
        return l_type;
    }
    public void setAddress(Address address) { this.address = address; }
    public void setContact(String contact) { this.Contact = contact; }
    public void setPhone(String phone) { this.phone = phone; }
    public void setL_type(String l_type) { this.l_type = l_type; }

    @Override
    public String toString() {
        return "locationID=" + locationID + ", address=" + address + ", Contact=" + Contact + ", Phone=" + phone + ", L_type=" + l_type;
    }
}
