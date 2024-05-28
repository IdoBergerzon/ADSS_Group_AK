package Domain;

public class Store extends Location {

    public Store(String name, String address, String contact, String phone, int shippingArea) {
        super(name, address, contact, phone, shippingArea);
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
    public String getPhone() {
        return super.getPhone();
    }
    @Override
    public String getContact() {
        return super.getContact();
    }
    @Override
    public void setName(String name) {
        super.setName(name);
    }
    @Override
    public void setAddress(String address) {
        super.setAddress(address);
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
    public String toString() {
        return "Store{" + super.toString() + "}";
    }
}
