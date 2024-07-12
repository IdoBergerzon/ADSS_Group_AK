package Domain.Transport;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TransportController {
    private TransportsRepository transportsRepository;

    public TransportController() {
        transportsRepository = new TransportsRepository();
    }

    public void addTransport(Transport transport) {
        transportsRepository.add(transport);
    }

    public TransportsRepository getTransportsData() {
        return transportsRepository;
    }

    public Transport getTransport(int transportID) {
        if (transportsRepository.get(transportID) == null) {
            return null;
        }
        return transportsRepository.get(transportID);
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

    public boolean calcWeight(Transport transport) {
        double tWeight = transport.calc_transportWeight();
        return tWeight <= transport.getDriver().getLicenseMaxWeight() && tWeight <= transport.getTruck().getMaxWeight() + transport.getTruck().getTruckWeight();
    }

    public void displayAllTransports() {
        Scanner scanner = new Scanner(System.in);
        if (this.getTransportsData().getAll().isEmpty())
            System.out.println("There are no transports in the system.\n");
        else {
            System.out.println(this.getTransportsData().toString() + "\n");
        }
    }

    public void finishTransport(Transport transport,DriverController driverController, Delivery_DocumentsController documentsController,TruckController truckController) {
        int finishTransportID = transport.getTransportID();
        if (this.getTransport(finishTransportID) == null) {
            System.out.println("Transport does not exist.\n");
        } else if (this.getTransport(finishTransportID).isFinished()) {
            System.out.println("Transport is already finished.\n");
        } else {
            transport.setFinished(true);
            transport.getDriver().setAvailable(true);
            transport.getTruck().setAvailable(true);
            for (Delivery_Document delivery : transport.getDelivery_documents()) {
                delivery.setDelivery_status(Delivery_DocumentStatus.finished);
                documentsController.getDocumentsRepository().update(delivery);
            }
            driverController.getDriversData().update(transport.getDriver());
            truckController.getTrucksData().update(transport.getTruck());
            transportsRepository.update(transport);
            System.out.println("Transport finished successfully");
        }
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

    public boolean executeTransport(DriverController driverController, LocationController locationController, Delivery_DocumentsController deliveryController, TruckController truckController) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Insert transport ID:");
        int transportID = scanner.nextInt();
        scanner.nextLine();
        if (this.getTransport(transportID) != null) {
            System.out.println("Transport already exists.\n");
            return false;
        }
        truckController.printAllAvailableTrucks(0);
        System.out.println("Insert truck ID:");
        int truckID = scanner.nextInt();
        scanner.nextLine();
        if (truckController.getTruck(truckID) == null) {
            System.out.println("Truck does not exist.\n");
            return false;
        }
        if (!truckController.getTruck(truckID).isAvailable()) {
            System.out.println("Truck is not available.\n");
            return false;
        }
        Truck truck = truckController.getTruck(truckID);


        driverController.printAllAvailableDrivers(0);
        System.out.println("Insert driver ID:");
        int driverID = scanner.nextInt();
        scanner.nextLine();
        if (driverController.getDriver(driverID) == null) {
            System.out.println("Driver does not exist.\n");
            return false;
        }
        if (!driverController.getDriver(driverID).isAvailable()) {
            System.out.println("Driver is not available.\n");
            return false;
        }
        Driver driver = driverController.getDriver(driverID);


        List<Delivery_Document> deliveryDocs;
        deliveryDocs = new ArrayList<Delivery_Document>();
        int searchMore = 1;
        while (searchMore != 0) {
            if (searchMore != 1) {
                break;
            }
            System.out.println("\nSource shipping area:");
            locationController.getAllSourceShippingArea();
            System.out.println("\nInsert Sources Shipping Area:");
            int sourcesArea = scanner.nextInt();
            System.out.println("\nDestination shipping area:");
            deliveryController.getShippingAreaForDest(sourcesArea);
            System.out.println("\nInsert Destinations Shipping Area:");
            int destinationArea = scanner.nextInt();
            deliveryController.getDeliveryInArea(sourcesArea, destinationArea);

            System.out.println("Insert delivery document ID:\n(You can also select a transport document that has not been displayed) \n(Press 0 to return to the Main Menu)\n");
            int deliveryID = scanner.nextInt();
            scanner.nextLine();
            if (deliveryID == 0) {
                break;
            }
            if (deliveryController.getDelivery_Document(deliveryID) == null) {
                System.out.println("Delivery Document does not exist.\n");
                continue;
            }
            if (deliveryController.getDelivery_Document(deliveryID).getDelivery_Status().equals(Delivery_DocumentStatus.finished) ||
                    deliveryController.getDelivery_Document(deliveryID).getDelivery_Status().equals(Delivery_DocumentStatus.in_Progress)) {
                System.out.println("Delivery has already been initiated.\n");
                continue;
            }
            if (deliveryController.getDelivery_Document(deliveryID).getItemsStatus() == Delivery_ItemsStatus.in_Progress) {
                deliveryController.getDelivery_Document(deliveryID).setItemsStatus(Delivery_ItemsStatus.complete);
            }
            deliveryDocs.add(deliveryController.getDelivery_Document(deliveryID));

            System.out.println("Add more delivery document ID? (No = 0, Yes = 1)");
            searchMore = scanner.nextInt();

        }
        //Create transport and add it to TransportData
        Transport newTransport = new Transport(transportID, truck, driver, deliveryDocs, "");
        this.addTransport(newTransport);
        //Check if transport is valid
        if (calcWeight(newTransport)) {
            System.out.println("Proper Transport " + newTransport + "\n");
            return true;
        }

        int reChoice;
        do {
            //Replanning transport
            System.out.println("Overweight, please choose one from the following numbers:");
            System.out.println("1. Change truck");
            System.out.println("2. Remove source");
            System.out.println("3. Remove destination");
            System.out.println("4. Remove Item from delivery");
            System.out.println("0. Exit and Delete transport");
            reChoice = scanner.nextInt();

            switch (reChoice) {
                case 0:
                    this.getTransportsData().remove(transportID);
                    System.out.println("Returning to the main menu.\n");
                    break;

                //Change truck
                case 1:
                    boolean a = changeTruck(newTransport,truckController);
                    if (!a) {
                        reChoice = 0;
                    }
                    break;

                //Remove source
                case 2:
                    boolean b = removeSource(newTransport,locationController);
                    if (!b) {
                        reChoice = 0;
                    }
                    break;

                //Remove destination
                case 3:
                    boolean c = removeDestination(newTransport,locationController);
                    if (!c) {
                        reChoice = 0;
                    }
                    break;

                //Remove item from delivery in transport
                case 4:
                    boolean d = removeItemFromDoc(newTransport,deliveryController);
                    if (!d) {
                        reChoice = 0;
                    }
                    break;
            }
        } while (reChoice != 0);
        return true;
    }

    public boolean changeTruck(Transport newTransport, TruckController truckController){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Possible Trucks:\n");
        truckController.printAllAvailableTrucks(newTransport.getWeight());
            System.out.println("Insert new truck ID:\n(Press 0 to return to the Main Menu)\n");
            int newTruckID = scanner.nextInt();
            scanner.nextLine();
            if (newTruckID == 0) {
                this.getTransportsData().remove(newTransport.getTransportID());
                return false;
            }
            if (truckController.getTruck(newTruckID) == null) {
                System.out.println("Truck does not exist.\n");
            } else {
                Truck newTruck = truckController.getTruck(newTruckID);
                if (newTransport.setTruck(newTruck)) {
                    System.out.println("Transport OK, truck was changed successfully.\n");
                    return false;
                }
            }
        return true;
    }

    public boolean removeSource(Transport newTransport,LocationController locationController){
        Scanner scanner = new Scanner(System.in);
        newTransport.printAllSources();
        System.out.println("Insert source ID to remove:");
        int sourceID = scanner.nextInt();
        scanner.nextLine();
        if (locationController.getLocation(sourceID) == null)
            System.out.println("Location does not exist.\n");
        else {
            if (!locationController.getLocation(sourceID).getL_type().equals("Store"))
                System.out.println("The location is a destination.\n");
            else {
                Store source = (Store) locationController.getLocation(sourceID);
                if (newTransport.getSource().contains(source)) {
                    newTransport.removeSource(source);
                    if (newTransport.checkTransport()) {
                        System.out.println("Transport OK, source removed successfully.\n");
                        return false;
                    } else
                        System.out.println("The weight is still exceeded.\n");
                } else
                    System.out.println("Source" + source + "does not exist in this transport.\n");
            }
        }
        return true;
    }

    public boolean removeDestination(Transport newTransport,LocationController locationController){
        Scanner scanner = new Scanner(System.in);
        newTransport.printAllDestinations();
        System.out.println("Insert destination ID to remove:");
        int destinationID = scanner.nextInt();
        scanner.nextLine();
        if (locationController.getLocation(destinationID) == null) {
            System.out.println("Location does not exist.\n");
        } else {
            if (!locationController.getLocation(destinationID).getL_type().equals("Supplier"))
                System.out.println("The location is Store.\n");
            else {
                Supplier destination = (Supplier) locationController.getLocation(destinationID);
                if (newTransport.getDestinations().contains(destination)) {
                    newTransport.removeDestination(destination);
                    if (newTransport.checkTransport()) {
                        System.out.println("Transport OK, destination removed successfully.\n");
                        return false;
                    } else
                        System.out.println("The weight is still exceeded.\n");
                } else
                    System.out.println("Destination" + destination + "does not exist in this transport.\n");
            }
        }
        return true;
    }

    public boolean removeItemFromDoc(Transport newTransport, Delivery_DocumentsController deliveryController){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Delivery documents in this transport:");
        newTransport.printAllDeliveryDocuments();
        System.out.println("Insert delivery ID to remove from:");
        int deliveryId = scanner.nextInt();
        scanner.nextLine();
        if (deliveryController.getDelivery_Document(deliveryId) == null) {
            System.out.println("Delivery does not exist.\n");
        }
        if (!newTransport.getDelivery_documents().contains(deliveryController.getDelivery_Document(deliveryId))) {
            System.out.println("Delivery Document does not exist in this transport.\n");
        } else {
            Delivery_Document deliveryDoc = null;
            for (Delivery_Document delivery : newTransport.getDelivery_documents()) {
                if (delivery.getDocumentID() == deliveryId) {
                    deliveryDoc = delivery;
                }
            }
            if (deliveryDoc != null) {
                System.out.println("Items in this delivery documents:\n");
                deliveryDoc.printAllItems();
                System.out.println("Insert item ID to remove:");
                int itemID = scanner.nextInt();
                scanner.nextLine();
                if (deliveryController.getItemsData().get(itemID) == null) {
                    System.out.println("Item does not exist.\n");
                } else {
                    Item removedItem = deliveryController.getItemsData().get(itemID);
                    if (deliveryDoc.removeItem(removedItem)) {
                        System.out.println("Item" + removedItem + "removed successfully.\n");
                        newTransport.calc_transportWeight();
                        if (newTransport.checkTransport()) {
                            System.out.println("Transport OK, item" + itemID + "was removed from the delivery document in this transport.\n");
                            return false;
                        } else
                            System.out.println("The weight is still exceeded.\n");
                    }
                }
            } else {
                System.out.println("Delivery does not exist in this transport.\n");
            }
        }
        return true;
    }
}

