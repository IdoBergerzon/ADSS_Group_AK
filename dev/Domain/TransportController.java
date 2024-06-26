package Domain;

import Data.TransportsData;

import java.util.List;
import java.util.Scanner;

public class TransportController {
    private TransportsData transportsData;

    public TransportController() {
        transportsData = new TransportsData();
    }

    public TransportsData getTransportsData() {
        return transportsData;
    }

    public Transport getTransport(int transportID) {
        if (!transportsData.getTransports().containsKey(transportID)) {
            return null;
        }
        return transportsData.getTransports().get(transportID);
    }

    public void displayTransport() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Insert transport ID:");
        int transportID = scanner.nextInt();
        scanner.nextLine();
        if (this.getTransport(transportID) == null) {
            System.out.println("Transport does not exist.\n");
        } else
            System.out.println(this.getTransport(transportID));
    }

    public void displayAllTransports() {
        Scanner scanner = new Scanner(System.in);
        if (this.getTransportsData().getTransports().isEmpty())
            System.out.println("There are no transports in the system.\n");
        else {
            System.out.println(this.getTransportsData().toString() + "\n");
        }
    }

    /**
     * Transport closing (driver release, truck release, delivery status update)
     *
     * @param transport
     * @return
     */
    public void finishTransport(Transport transport) {
        transport.getDriver().setAvailable(true);
        transport.getTruck().setAvailable(true);
        for (Delivery_Document delivery : transport.getDelivery_documents()) {
            delivery.setDelivery_status(Delivery_DocumentStatus.finished);
        }
        transport.setFinished(true);
        System.out.println("Transport finished successfully");
    }

    public boolean updateTransport(TruckController truckController, DriverController driverController, Delivery_DocumentsController deliveryController) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Insert transport ID:");
        int transportID = scanner.nextInt();
        scanner.nextLine();
        if (this.getTransport(transportID) == null) {
            System.out.println("Transport does not exist.\n");
            return false;
        }
        Transport transport = this.getTransport(transportID);
        System.out.println("Transport update menu:");
        System.out.println("1. Change truck");
        System.out.println("2. Change driver");
        System.out.println("3. Add delivery document to transport");
        System.out.println("4. Remove delivery document from transport");
        System.out.println("5. Transport weighting");
        System.out.println("0. Back to Main Menu");

        int update;
        update = scanner.nextInt();
        switch (update) {
            case 0:
                System.out.println("Returning to the main menu.\n");
                break;

            //Change truck
            case 1:
                truckController.printAllAvailableTrucks(transport.calc_transportWeight());
                System.out.println("Insert new truck ID:");
                int newTruckID = scanner.nextInt();
                scanner.nextLine();
                if (truckController.getTruck(newTruckID) != null) {
                    Truck newTruck = truckController.getTruck(newTruckID);
                    transport.setTruck(newTruck);
                    System.out.println("Transport's truck was changed\n" + transport + "\n");
                } else
                    System.out.println("Truck does not exist.\n");
                break;

            //Change driver
            case 2:
                driverController.printAllAvailableDrivers(transport.calc_transportWeight());
                System.out.println("Insert new driver ID:");
                int newDriverID = scanner.nextInt();
                scanner.nextLine();
                if (driverController.getDriver(newDriverID) != null) {
                    Driver newDriver = driverController.getDriver(newDriverID);
                    transport.setDriver(newDriver);
                    System.out.println("Driver's driver was changed\n" + transport + "\n");
                } else
                    System.out.println("Driver does not exist.\n");
                break;

            //Add delivery
            case 3:
                System.out.println("Insert new delivery ID:");
                int newDeliveryID = scanner.nextInt();
                scanner.nextLine();
                if (deliveryController.getDelivery_Document(newDeliveryID) != null) {
                    Delivery_Document newDelivery = deliveryController.getDelivery_Document(newDeliveryID);
                    if (newDelivery.getDelivery_Status().equals(Delivery_DocumentStatus.in_Progress)
                            || newDelivery.getDelivery_Status().equals(Delivery_DocumentStatus.finished)) {
                        System.out.println("Delivery has already been initiated.\n");
                    } else {
                        transport.addDeliveryDocument(newDelivery);
                        if (!transport.checkTransport()) {
                            transport.removeDeliveryDocument(newDelivery);
                            System.out.println("Overweight.\n");
                        } else
                            System.out.println("Delivery's document was changed\n" + transport);
                    }
                } else
                    System.out.println("Delivery does not exist.\n");
                break;

            //Remove delivery
            case 4:
                transport.printAllDeliveryDocuments();
                System.out.println("Insert delivery ID to remove:\n");
                int removeID = scanner.nextInt();
                scanner.nextLine();
                if (deliveryController.getDelivery_Document(removeID) == null) {
                    System.out.println("Delivery Document does not exist.\n");
                } else {
                    Delivery_Document removeDeliveryDocument = deliveryController.getDelivery_Document(removeID);
                    transport.removeDeliveryDocument(removeDeliveryDocument);
                    System.out.println("Delivery's document was changed\n" + transport);
                }
                break;

            case 5:
                transport.calc_transportWeight();
                break;

            default:
                System.out.println("Invalid choice. Please try again.\n");
                break;
        }
        return true;
    }
}

