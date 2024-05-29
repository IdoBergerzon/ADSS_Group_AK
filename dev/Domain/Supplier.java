package Domain;

public class Supplier extends ALocation {

    public Supplier(int locationID, Address address, String contact, String phone) {
        super(locationID, address, contact, phone, "Supplier");
    }

    @Override
    public String toString() {
        return "Supplier{" + super.toString() + "}";
    }
}
