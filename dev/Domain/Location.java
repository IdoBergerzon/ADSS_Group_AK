package Domain;

public abstract class Location {
    private String Name;
    private String address;
    private String Contact;
    private String phone;

    public Location(String name, String address, String contact, String phone) {
        Name = name;
        this.address = address;
        Contact = contact;
        this.phone = phone;
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

    @Override
    public String toString() {
        return "Name='" + Name + '\'' +
                ", address='" + address + '\'' +
                ", Contact='" + Contact + '\'' +
                ", phone='" + phone;
    }
}
