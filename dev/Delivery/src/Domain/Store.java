package Domain;

public class Store extends Location {

    public Store(String name, String address, String contact, String phone) {
        super(name, address, contact, phone);
    }

    @Override
    public String toString() {
        return "Store{" + super.toString() + "}";
    }
}
