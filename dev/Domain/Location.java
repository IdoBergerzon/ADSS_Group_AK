package Domain;

public abstract class Location {
    private String Name;
    private String address;
    private String Contact;
    private String phone;
    private int shippingArea;

    public Location(String name, String address, String contact, String phone, int shippingArea) {
        Name = name;
        this.address = address;
        Contact = contact;
        this.phone = phone;
        this.shippingArea = shippingArea;
    }
    public String getName() {
        return Name;
    }
    public String getAddress() {
        return address;
    }
    public String getContact() {
        return Contact;
    }
    public String getPhone() {
        return phone;
    }
    public int getShippingArea() { return shippingArea;}

    public void setName(String name) { this.Name = name; }
    public void setAddress(String address) { this.address = address; }
    public void setContact(String contact) { this.Contact = contact; }
    public void setPhone(String phone) { this.phone = phone; }
    public void setShippingArea(int shippingArea) { this.shippingArea = shippingArea; }

    @Override
    public String toString() {
        return "name='" + Name + '\'' +
                ", address='" + address + '\'' +
                ", contact='" + Contact + '\'' +
                ", phone='" + phone
                + ", shipping area=" + shippingArea;
    }
}
