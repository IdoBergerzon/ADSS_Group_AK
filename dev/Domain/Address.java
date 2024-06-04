package Domain;

public class Address {
    private final String full_address;
    private final int address_code;
    private int shipping_area;

    public Address(String full_address, int address_code) {
        this.full_address = full_address;
        this.address_code = address_code;
        this.shipping_area = 0;
    }
    public int getShipping_area() {
        return shipping_area;
    }

    public void setShipping_area(int shipping_area) {
        this.shipping_area = shipping_area;
    }

    public String toString() {
        return full_address + " " + address_code + " " + shipping_area;
    }
}
