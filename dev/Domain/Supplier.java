package Domain;

public class Supplier extends Location {

    public Supplier(String name, String address, String contact, String phone) {
        super(name, address, contact, phone);
    }

    @Override
    public void setName(String name) {
        super.setName(name);
    }
    @Override
    public void setContact(String contact) {
        super.setContact(contact);
    }
    @Override
    public void setPhone(String phone) {
        super.setPhone(phone);
    }
    @Override
    public void setAddress(String address) {
        super.setAddress(address);
    }
    @Override
    public String getName() {
        return super.getName();
    }
    @Override
    public String getAddress() {
        return super.getAddress();
    }
    @Override
    public String getContact() {
        return super.getContact();
    }
    @Override
    public String getPhone() {
        return super.getPhone();
    }
    @Override
    public String toString() {
        return "Supplier{" + super.toString() + "}";
    }
}
