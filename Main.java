import java.util.Scanner;

public class Main {
    public static Scanner scanner = new Scanner(System.in);
    private static TravelPackage newPackage;

    public static void clearConsole() {
        for (int i = 0; i < 100; i++) System.out.println();
    }

    public static void printSeparator(int length) {
        for (int i = 0; i < length; i++) System.out.print("*");
        System.out.println();
    }

    public static void anythingToContinue() {
        System.out.println("\nEnter anything to continue...");
        scanner.next();
    }

    public static void printHeading(String title) {
        printSeparator(60);
        System.out.println(title);
        printSeparator(60);
    }

    public static void main(String[] args) {
        MtBullerResort resort = new MtBullerResort();
        Scanner scanner = new Scanner(System.in);
        
        // Adding initial customers
        resort.addCustomer(new Customer("C1", "Alice", "555-1234", "beginner", "101"));
        resort.addCustomer(new Customer("C2", "Bob", "555-5678", "intermediate", "202"));
        resort.addCustomer(new Customer("C3", "Charlie", "555-9876", "expert", "303"));

        
        // Interaction with the user
        boolean running = true;
        while (running) {
            clearConsole();
            printHeading("Mountain Buller Resort");
            System.out.println("1. Display all accommodations");
            System.out.println("2. Available Accommodations and Rooms");
            System.out.println("3. Add customer");
            System.out.println("4. List customers");
            System.out.println("5. Create package");
            System.out.println("6. Add Lift Pass to Package");
            System.out.println("7. List packages");
            System.out.println("8. Add lesson fees to package");
            System.out.println("9. Save packages to file");
            System.out.println("10. Read packages from file");
            System.out.println("11. Quit");

            int choice = scanner.nextInt();
            scanner.nextLine();  
            switch (choice) {
                case 1:
                    clearConsole();
                    printSeparator(60);
                    resort.showRooms();
                    printSeparator(60);
                    anythingToContinue();
                    break;

                case 2:
                    clearConsole();
                    printHeading("Available Accommodations and Rooms");
                    resort.displayRooms(); // Show available rooms for each accommodation
                    anythingToContinue();
                    break;

                    case 3:
                    clearConsole();
                    printHeading("Add Customer");
                
                    // Collect customer details
                    System.out.println("Enter customer ID:");
                    String id = scanner.next();  // Read customer ID
                
                    System.out.println("Enter customer name:");
                    String name = scanner.next();  // Read customer name
                
                    System.out.println("Enter skiing level (beginner, intermediate, expert):");
                    String level = scanner.next();  // Read skiing level
                
                    System.out.println("Enter phone number:");
                    String phoneNumber = scanner.next();  // Read phone number
                
                    // Handle room number input and format
                    System.out.println("Enter room number to assign (e.g., 101):");
                    String roomNumber = scanner.next();  // Read room number as input
                    String formattedRoomNumber = "Room " + roomNumber;  // Add "Room " prefix
                    clearConsole();
                    
                    // Check if the room is available and assign it to the customer
                    if (resort.assignRoom(formattedRoomNumber)) {
                        // If the room was successfully assigned, create the customer
                        resort.addCustomer(new Customer(id, name, phoneNumber, level, roomNumber));
                        
                        System.out.println("Customer added with room number: " + roomNumber);
                    } else {
                        // If room assignment failed
                        System.out.println("Failed to assign the room. The room may already be assigned or not available.");
                    }
                
                    anythingToContinue();  // Pause to allow the user to read the output
                    break;

                    case 4:
                    clearConsole();
                    printHeading("Customer List");
                    resort.listCustomers();  // Call the listCustomers method to display the customers
                    printSeparator(60);
                    anythingToContinue();  // Pause to allow the user to read the output
                    break;
                

                    case 5:
                    clearConsole();
                    // Prompt for Customer ID
                    System.out.print("Enter Customer ID for the package: ");
                    String customerId = scanner.nextLine();
                
                    // Search for the customer
                    Customer foundCustomer = resort.findCustomerById(customerId);
                
                    // Check if the customer exists
                    if (foundCustomer != null) {
                        // Customer found
                        System.out.println("Customer found: " + foundCustomer.getName());
                
                        // Ask for start date and duration
                        System.out.print("Enter start date (YYYY-MM-DD): ");
                        String startDate = scanner.nextLine();
                
                        System.out.print("Enter duration (days): ");
                        int duration = scanner.nextInt();
                        scanner.nextLine();  // Clear the newline
                        // Create the package
                        TravelPackage newPackage = resort.createPackage(customerId, startDate, duration);
                        System.out.println("Done...!");
                        anythingToContinue();
                    } else {
                        clearConsole();
                        // Customer not found, display an error message
                        System.out.println("Error: Customer with ID " + customerId + " not found. Please try again.");
                        anythingToContinue();
                    }
                    break;
                    case 6:
                        clearConsole();
                        printHeading("Add Lift Pass to Package");

                            // Reuse the existing customerId variable (do not declare it again)
                            System.out.print("Enter Customer ID for the package: ");
                            customerId = scanner.nextLine();  // Reassign value to existing variable

                            // Search for the package
                            TravelPackage foundPackage = resort.findPackageByCustomerId(customerId);

                            // Check if the package exists
                            if (foundPackage != null) {
                            // Package found
                            System.out.println("Package found for Customer ID: " + customerId);

                            // Ask for lift pass details
                            System.out.print("Enter the number of days for the lift pass: ");
                            int liftPassDays = scanner.nextInt();
                            scanner.nextLine();  // Clear the newline

                            // Add the lift pass to the found package
                            foundPackage.addLiftPass(liftPassDays);
                            System.out.println("Lift pass for " + liftPassDays + " days has been added to the package.");
                        } else {
                            // Package not found
                            System.out.println("No package found for Customer ID: " + customerId);
                        }

                        anythingToContinue();
                        break;

                
                
                case 7:
                    clearConsole();
                    resort.listPackages();
                    anythingToContinue();
                    break;
                    
                 case 8:
                    clearConsole();
                    printHeading("Add Lesson Fees to Package");
                
                    // Ask for Customer ID to find the package
                    System.out.print("Enter Customer ID for the package: ");
                    String lessonCustomerId = scanner.nextLine();
                
                    // Search for the package
                    TravelPackage lessonPackage = resort.findPackageByCustomerId(lessonCustomerId);
                
                    // Check if the package exists
                    if (lessonPackage != null) {
                        System.out.println("Package found for Customer ID: " + lessonCustomerId);
                        
                        // Ask for skiing level
                        System.out.print("Enter skiing level (beginner, intermediate, expert): ");
                        String skiingLevel = scanner.nextLine();
                
                        // Ask for number of lessons
                        System.out.print("Enter the number of lessons: ");
                        int numberOfLessons = scanner.nextInt();
                        scanner.nextLine();  // Clear the newline
                
                        // Add lesson fees to the package
                        resort.addLessonFeesToPackage(lessonCustomerId, skiingLevel, numberOfLessons);
                    } else {
                        System.out.println("No package found for Customer ID: " + lessonCustomerId);
                    }
                
                    anythingToContinue();
                    break;
                

                case 9:
                    clearConsole();
                    System.out.println("Saving all packages to file...");
                    resort.savePackagesToFile("packages.dat");  // Specify the filename
                    anythingToContinue();
                    break;
                case 10:
                    clearConsole();
                    System.out.println("Reading packages from file...");
                    resort.readPackagesFromFile("packages.dat");  // Specify the filename
                    anythingToContinue();
                    break;

                case 11:
                    running = false;
                    break;

                default:
                    System.out.println("Invalid choice, please try again.");
                    break;
            }
        }

        scanner.close();
    }
}