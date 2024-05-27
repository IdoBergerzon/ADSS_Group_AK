package Domain;

public class Supplier extends Location {

    public Supplier(String name, String address, String contact, String phone) {
        super(name, address, contact, phone);
    }

    @Override
    public String toString() {
        return "Supplier{" + super.toString() + "}";
    }
}
