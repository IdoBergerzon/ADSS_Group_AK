package Domain;

import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

public class LocationController {
    private LocationsRepository locationsRepository;
    public LocationController() {
        locationsRepository = new LocationsRepository();
    }

    public LocationsRepository getLocationsData() {
        return locationsRepository;
    }

    public void addLocation(int locationID, Address address, String contact, String phone, String l_type) {
        if (!locationsRepository.getAll().contains(locationID)) {
            if (l_type == "Supplier"){
                Supplier supplier = new Supplier(locationID, address, contact, phone);
                locationsRepository.add(supplier);
            } else if (l_type == "Store") {
                Store store = new Store(locationID, address, contact, phone);
                locationsRepository.add(store);
            }
            else {
                System.out.println(l_type + "is not a l_type\n");
            }
        } else {
            System.out.println("location already exists");
        }
    }
    public ALocation getLocation(int locationID) {
            if (locationsRepository.getAll().get(locationID) != null) {
                return (ALocation) locationsRepository.get(locationID);
            }
            return null;
    }

    public void displayLocation() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Insert location documents ID:");
        int locationDocumentsID = scanner.nextInt();
        scanner.nextLine();
        if (this.getLocation(locationDocumentsID) == null)
            System.out.println("Location Document does not exist.\n");
        else
            System.out.println(this.getLocation(locationDocumentsID) + "\n");
    }

    public void getAllSourceShippingArea() {
        Set <Integer> ssa = new HashSet();
        List<ALocation> locationsNew = locationsRepository.getAll();
        for (int i = 0; i < locationsNew.size(); i++) {
            ALocation location = locationsNew.get(i);
            if (location.getL_type().equals("Store")) {
                ssa.add(location.getShippingArea());
            }
        }
        System.out.println(ssa);
    }

    public void createLocation() {
        Scanner scanner = new Scanner(System.in);
        int locationChoice;
        System.out.println("1. Add Supplier");
        System.out.println("2. Add Store");
        System.out.print("Enter your choice:\n");
        locationChoice = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        if (locationChoice == 1 || locationChoice == 2) {
            System.out.print("Enter Location ID:\n");
            int locationID = scanner.nextInt();
            scanner.nextLine(); // Consume newline
            if (this.getLocation(locationID) != null) {
                System.out.print("The location already exists in the system\n");
            } else {
                System.out.print("Enter Address details:\n");
                String full_address = scanner.nextLine();

                System.out.print("Enter address_code:\n");
                int address_code = scanner.nextInt();
                scanner.nextLine(); // Consume newline

                System.out.print("Enter Shipping area:\n");
                int shipping_area = scanner.nextInt();


                Address address = new Address(full_address, address_code, shipping_area);

                System.out.print("Enter contact:\n");
                String contact = scanner.nextLine();
                scanner.nextLine();

                System.out.print("Enter phone:\n");
                String phone = scanner.nextLine();

                String l_type = (locationChoice == 1) ? "Supplier" : "Store";
                this.addLocation(locationID, address, contact, phone, l_type);
                System.out.println(l_type + " added successfully.");
            }
        } else {
            System.out.println("Invalid choice for the given location type.\n");
        }
    }

    public void updateShipping_area(int locationID) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Insert number of shipping area:");
        int shippingArea = scanner.nextInt();
        ALocation location = (ALocation) locationsRepository.get(locationID);
        location.getAddress().setShipping_area(shippingArea);
        System.out.println("Shipping area set successfully: " + location+"\n");
    }

    public void updateContact(int locationID) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Insert new contact:");
        String contact = scanner.next();
        ALocation location = (ALocation) locationsRepository.get(locationID);
        location.setContact(contact);
        System.out.println("Contact updated successfully.\n");
    }

    public void updatePhone(int locationID) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Insert new phone number:");
        String phoneNumber = scanner.next();
        ALocation location = (ALocation) locationsRepository.get(locationID);
        location.setPhone(phoneNumber);
        System.out.println("Phone number updated successfully.\n");
    }
}
