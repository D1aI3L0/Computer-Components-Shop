package org.example;

import org.example.tables.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;


public class Main {

    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {

        while (true) {
            System.out.println("1. Client");
            System.out.println("2. Order");
            System.out.println("3. Product");
            System.out.println("4. Product_Order");
            System.out.println("5. Processor");
            System.out.println("6. Graphic_Card");
            System.out.println("7. Motherboard");
            System.out.println("8. Review");
            System.out.println("9. Exit");
            System.out.println("Choose table for work: ");
            int tableChoice = scanner.nextInt();

            switch (tableChoice) {
                case 1:
                    workWithClient();
                    break;
                case 2:
                    workWithOrder();
                    break;
                case 3:
                    workWithProduct();
                    break;
                case 4:
                    workWithProductOrder();
                    break;
                case 5:
                    workWithProcessor();
                    break;
                case 6:
                    workWithGraphicCard();
                    break;
                case 7:
                    workWithMotherboard();
                    break;
                case 8:
                    workWithReview();
                    break;
                case 9:
                    System.out.println("Exiting...");
                    return;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }

    static int getOperation()
    {
        System.out.println("1. Create");
        System.out.println("2. Read");
        System.out.println("3. Update");
        System.out.println("4. Delete");
        System.out.println("5. Back to main menu");
        System.out.print("Choose operation: ");
        int choice = scanner.nextInt();
        scanner.nextLine();
        return choice;
    }


    static void workWithClient()
    {
        Client client = new Client();

        while (true) {
            switch (getOperation()) {
                case 1:
                    System.out.print("Enter phone number: ");
                    String phoneNumber = scanner.nextLine();
                    System.out.print("Enter name: ");
                    String name = scanner.nextLine();
                    System.out.print("Enter surname: ");
                    String surname = scanner.nextLine();
                    System.out.print("Enter patronymic: ");
                    String patronymic = scanner.nextLine();
                    System.out.print("Enter email: ");
                    String email = scanner.nextLine();
                    client.create(phoneNumber, name, surname, patronymic, email);
                    break;
                case 2:
                    System.out.print("Enter ID: ");
                    int readId = scanner.nextInt();
                    client.read(readId);
                    break;
                case 3:
                    System.out.print("Enter ID: ");
                    int updateId = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Enter new phone number: ");
                    String newPhoneNumber = scanner.nextLine();
                    System.out.print("Enter new name: ");
                    String newName = scanner.nextLine();
                    System.out.print("Enter new surname: ");
                    String newSurname = scanner.nextLine();
                    System.out.print("Enter new patronymic: ");
                    String newPatronymic = scanner.nextLine();
                    System.out.print("Enter new email: ");
                    String newEmail = scanner.nextLine();
                    client.update(updateId, newPhoneNumber, newName, newSurname, newPatronymic, newEmail);
                    break;
                case 4:
                    System.out.print("Enter ID: ");
                    int deleteId = scanner.nextInt();
                    client.delete(deleteId);
                    break;
                case 5:
                    System.out.println("Exiting...");
                    return;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }

    static void workWithOrder()
    {
        Order order = new Order();
        while (true) {
            switch (getOperation()) {
                case 1:
                    System.out.print("Enter total price: ");
                    BigDecimal totalPrice = scanner.nextBigDecimal();
                    scanner.nextLine();
                    System.out.print("Enter order date (yyyy-MM-dd): ");
                    String dateString = scanner.nextLine();
                    LocalDate orderDate = LocalDate.parse(dateString, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
                    System.out.println("Choose order status:");
                    for (OrderStatus status : OrderStatus.values()) {
                        System.out.println(status.ordinal() + ". " + status);
                    }
                    System.out.print("Enter order status (number): ");
                    int statusIndex = scanner.nextInt();
                    scanner.nextLine();
                    OrderStatus status = OrderStatus.values()[statusIndex];
                    System.out.print("Enter payment method: ");
                    String paymentMethod = scanner.nextLine();
                    System.out.print("Enter client ID: ");
                    int clientId = scanner.nextInt();
                    scanner.nextLine();
                    order.create(totalPrice, orderDate, status, paymentMethod, clientId);
                    break;
                case 2:
                    System.out.print("Enter order ID: ");
                    int readId = scanner.nextInt();
                    scanner.nextLine();
                    order.read(readId);
                    break;
                case 3:
                    System.out.print("Enter order ID: ");
                    int updateId = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Enter new total price: ");
                    BigDecimal newTotalPrice = scanner.nextBigDecimal();
                    scanner.nextLine();
                    System.out.print("Enter new order date (yyyy-MM-dd): ");
                    String newDateString = scanner.nextLine();
                    LocalDate newOrderDate = LocalDate.parse(newDateString, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
                    System.out.println("Choose new order status:");
                    for (OrderStatus s : OrderStatus.values()) {
                        System.out.println(s.ordinal() + ". " + s);
                    }
                    System.out.print("Enter new order status (number): ");
                    int newStatusIndex = scanner.nextInt();
                    scanner.nextLine();
                    OrderStatus newStatus = OrderStatus.values()[newStatusIndex];
                    System.out.print("Enter new payment method: ");
                    String newPaymentMethod = scanner.nextLine();
                    System.out.print("Enter new client ID: ");
                    int newClientId = scanner.nextInt();
                    scanner.nextLine();
                    order.update(updateId, newTotalPrice, newOrderDate, newStatus, newPaymentMethod, newClientId);
                    break;
                case 4:
                    System.out.print("Enter order ID: ");
                    int deleteId = scanner.nextInt();
                    scanner.nextLine();
                    order.delete(deleteId);
                    break;
                case 5:
                    return;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }


    static void workWithProduct()
    {
        Product product = new Product();

        while (true) {
            switch (getOperation()) {
                case 1:
                    System.out.print("Enter price: ");
                    BigDecimal price = scanner.nextBigDecimal();
                    scanner.nextLine(); // consume newline
                    System.out.print("Enter name: ");
                    String name = scanner.nextLine();
                    System.out.print("Enter manufacturer: ");
                    String manufacturer = scanner.nextLine();
                    System.out.println("Choose product type:");
                    for (ProductType type : ProductType.values()) {
                        System.out.println(type.ordinal() + ". " + type);
                    }
                    System.out.print("Enter product type (number): ");
                    int typeIndex = scanner.nextInt();
                    scanner.nextLine();
                    ProductType type = ProductType.values()[typeIndex];
                    int processorId = 0;
                    int motherboardId = 0;
                    int graphicCardId = 0;

                    switch (type) {
                        case Processor:
                            System.out.print("Enter processor ID: ");
                            processorId = scanner.nextInt();
                            scanner.nextLine();
                            break;
                        case Motherboard:
                            System.out.print("Enter motherboard ID: ");
                            motherboardId = scanner.nextInt();
                            scanner.nextLine();
                            break;
                        case GraphicCard:
                            System.out.print("Enter graphic card ID: ");
                            graphicCardId = scanner.nextInt();
                            break;
                    }

                    scanner.nextLine();

                    product.create(price, name, manufacturer, type, processorId, motherboardId, graphicCardId);
                    break;
                case 2:
                    System.out.print("Enter product ID: ");
                    int readId = scanner.nextInt();
                    scanner.nextLine();
                    product.read(readId);
                    break;
                case 3:
                    System.out.print("Enter product ID: ");
                    int updateId = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Enter new price: ");
                    BigDecimal newPrice = scanner.nextBigDecimal();
                    scanner.nextLine();
                    System.out.print("Enter new name: ");
                    String newName = scanner.nextLine();
                    System.out.print("Enter new manufacturer: ");
                    String newManufacturer = scanner.nextLine();
                    System.out.println("Choose new product type:");
                    for (ProductType t : ProductType.values()) {
                        System.out.println(t.ordinal() + ". " + t);
                    }
                    System.out.print("Enter new product type (number): ");
                    int newTypeIndex = scanner.nextInt();
                    ProductType newType = ProductType.values()[newTypeIndex];
                    System.out.print("Enter new processor ID: ");
                    int newProcessorId = 0;
                    int newMotherboardId = 0;
                    int newGraphicCardId = 0;

                    switch (newType) {
                        case Processor:
                            System.out.print("Enter new processor ID: ");
                            newProcessorId = scanner.nextInt();
                            break;
                        case Motherboard:
                            System.out.print("Enter new motherboard ID: ");
                            newMotherboardId = scanner.nextInt();
                            break;
                        case GraphicCard:
                            System.out.print("Enter new graphic card ID: ");
                            newGraphicCardId = scanner.nextInt();
                            break;
                    }
                    scanner.nextLine();

                    product.update(updateId, newPrice, newName, newManufacturer, newType, newProcessorId, newMotherboardId, newGraphicCardId);
                    break;
                case 4:
                    System.out.print("Enter product ID: ");
                    int deleteId = scanner.nextInt();
                    scanner.nextLine();
                    product.delete(deleteId);
                    break;
                case 5:
                    return;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }


    static void workWithProductOrder()
    {
        ProductOrder productOrder = new ProductOrder();

        while (true) {
            switch (getOperation()) {
                case 1:
                    System.out.print("Enter product ID: ");
                    int productId = scanner.nextInt();
                    System.out.print("Enter order ID: ");
                    int orderId = scanner.nextInt();
                    scanner.nextLine();
                    productOrder.create(productId, orderId);
                    break;
                case 2:
                    System.out.print("Enter product order ID: ");
                    int readId = scanner.nextInt();
                    scanner.nextLine();
                    productOrder.read(readId);
                    break;
                case 3:
                    System.out.print("Enter product order ID: ");
                    int updateId = scanner.nextInt();
                    System.out.print("Enter new product ID: ");
                    int newProductId = scanner.nextInt();
                    System.out.print("Enter new order ID: ");
                    int newOrderId = scanner.nextInt();
                    scanner.nextLine();
                    productOrder.update(updateId, newProductId, newOrderId);
                    break;
                case 4:
                    System.out.print("Enter product order ID: ");
                    int deleteId = scanner.nextInt();
                    scanner.nextLine();
                    productOrder.delete(deleteId);
                    break;
                case 5:
                    return;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }


    static void workWithProcessor()
    {
        Processor processor = new Processor();

        while (true) {
            switch (getOperation()) {
                case 1:
                    System.out.print("Enter threads count: ");
                    int threadsCount = scanner.nextInt();
                    System.out.print("Enter clock frequency: ");
                    double clockFrequency = scanner.nextDouble();
                    System.out.print("Enter max frequency: ");
                    double maxFrequency = scanner.nextDouble();
                    System.out.print("Enter CPU count: ");
                    int cpuCount = scanner.nextInt();
                    scanner.nextLine();
                    processor.create(threadsCount, clockFrequency, maxFrequency, cpuCount);
                    break;
                case 2:
                    System.out.print("Enter processor ID: ");
                    int readId = scanner.nextInt();
                    scanner.nextLine();
                    processor.read(readId);
                    break;
                case 3:
                    System.out.print("Enter processor ID: ");
                    int updateId = scanner.nextInt();
                    System.out.print("Enter new threads count: ");
                    int newThreadsCount = scanner.nextInt();
                    System.out.print("Enter new clock frequency: ");
                    double newClockFrequency = scanner.nextDouble();
                    System.out.print("Enter new max frequency: ");
                    double newMaxFrequency = scanner.nextDouble();
                    System.out.print("Enter new CPU count: ");
                    int newCpuCount = scanner.nextInt();
                    scanner.nextLine();
                    processor.update(updateId, newThreadsCount, newClockFrequency, newMaxFrequency, newCpuCount);
                    break;
                case 4:
                    System.out.print("Enter processor ID: ");
                    int deleteId = scanner.nextInt();
                    scanner.nextLine();
                    processor.delete(deleteId);
                    break;
                case 5:
                    return;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }


    static void workWithGraphicCard()
    {
        GraphicCard graphicCard = new GraphicCard();
        while (true) {
            switch (getOperation()) {
                case 1:
                    System.out.print("Enter GPU count: ");
                    int gpuCount = scanner.nextInt();
                    System.out.print("Enter GPU frequency: ");
                    double gpuFrequency = scanner.nextDouble();
                    System.out.print("Enter memory count: ");
                    int memoryCount = scanner.nextInt();
                    System.out.print("Enter memory frequency: ");
                    double memoryFrequency = scanner.nextDouble();
                    scanner.nextLine();
                    graphicCard.create(gpuCount, gpuFrequency, memoryCount, memoryFrequency);
                    break;
                case 2:
                    System.out.print("Enter graphic card ID: ");
                    int readId = scanner.nextInt();
                    scanner.nextLine();
                    graphicCard.read(readId);
                    break;
                case 3:
                    System.out.print("Enter graphic card ID: ");
                    int updateId = scanner.nextInt();
                    System.out.print("Enter new GPU count: ");
                    int newGpuCount = scanner.nextInt();
                    System.out.print("Enter new GPU frequency: ");
                    double newGpuFrequency = scanner.nextDouble();
                    System.out.print("Enter new memory count: ");
                    int newMemoryCount = scanner.nextInt();
                    System.out.print("Enter new memory frequency: ");
                    double newMemoryFrequency = scanner.nextDouble();
                    scanner.nextLine();
                    graphicCard.update(updateId, newGpuCount, newGpuFrequency, newMemoryCount, newMemoryFrequency);
                    break;
                case 4:
                    System.out.print("Enter graphic card ID: ");
                    int deleteId = scanner.nextInt();
                    scanner.nextLine();
                    graphicCard.delete(deleteId);
                    break;
                case 5:
                    return;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }


    static void workWithMotherboard()
    {
        Motherboard motherboard = new Motherboard();

        while (true) {
            switch (getOperation()) {
                case 1:
                    System.out.print("Enter memory slots: ");
                    int memorySlots = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Enter chipset: ");
                    String chipset = scanner.nextLine();
                    System.out.print("Enter form factor: ");
                    String formFactor = scanner.nextLine();
                    motherboard.create(memorySlots, chipset, formFactor);
                    break;
                case 2:
                    System.out.print("Enter motherboard ID: ");
                    int readId = scanner.nextInt();
                    scanner.nextLine();
                    motherboard.read(readId);
                    break;
                case 3:
                    System.out.print("Enter motherboard ID: ");
                    int updateId = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Enter new memory slots: ");
                    int newMemorySlots = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Enter new chipset: ");
                    String newChipset = scanner.nextLine();
                    System.out.print("Enter new form factor: ");
                    String newFormFactor = scanner.nextLine();
                    motherboard.update(updateId, newMemorySlots, newChipset, newFormFactor);
                    break;
                case 4:
                    System.out.print("Enter motherboard ID: ");
                    int deleteId = scanner.nextInt();
                    scanner.nextLine();
                    motherboard.delete(deleteId);
                    break;
                case 5:
                    return;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }


    static void workWithReview()
    {
        Review review = new Review();
        while(true) {
            switch (getOperation()) {
                case 1:
                    System.out.print("Enter client ID: ");
                    int clientId = scanner.nextInt();
                    System.out.print("Enter product ID: ");
                    int productId = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Enter review text: ");
                    String reviewText = scanner.nextLine();
                    review.create(clientId, productId, reviewText);
                    break;
                case 2:
                    System.out.print("Enter review ID: ");
                    int readId = scanner.nextInt();
                    scanner.nextLine();
                    review.read(readId);
                    break;
                case 3:
                    System.out.print("Enter review ID: ");
                    int updateId = scanner.nextInt();
                    System.out.print("Enter new client ID: ");
                    int newClientId = scanner.nextInt();
                    System.out.print("Enter new product ID: ");
                    int newProductId = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Enter new review text: ");
                    String newReviewText = scanner.nextLine();
                    review.update(updateId, newClientId, newProductId, newReviewText);
                    break;
                case 4:
                    System.out.print("Enter review ID: ");
                    int deleteId = scanner.nextInt();
                    scanner.nextLine();
                    review.detele(deleteId);
                    break;
                case 5:
                    return;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }
}