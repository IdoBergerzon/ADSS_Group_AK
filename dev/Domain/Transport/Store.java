package Domain.Transport;

public class Store extends ALocation {

    public Store(int locationID, Address address, String contact, String phone) {
        super(locationID, address, contact, phone, "Store");
    }

    @Override
    public String toString() {
        return "Store{" + super.toString() + "}";
    }
}
