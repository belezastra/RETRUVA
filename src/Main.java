package RETRUVA;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        RETRUVA.AccountManager accountManager = new RETRUVA.AccountManager();
        RETRUVA.ItemManager itemManager = new RETRUVA.ItemManager();

        while (true) {
            System.out.println("=== LOST & FOUND SYSTEM ===");
            System.out.println("[1] Login as Admin");
            System.out.println("[2] Login as User");
            System.out.println("[3] Register User");
            System.out.println("[0] Exit");
            System.out.print("Choose option: ");

            int choice = sc.nextInt();
            sc.nextLine(); // consume newline

            switch (choice) {
                case 1:
                    System.out.print("Enter Admin Username: ");
                    String adminUser = sc.nextLine();
                    System.out.print("Enter Admin Password: ");
                    String adminPass = sc.nextLine();
                    RETRUVA.Admin admin = accountManager.loginAdmin(adminUser, adminPass);
                    if (admin != null) {
                        System.out.println("Admin Login Successful!");
                        adminMenu(sc, itemManager);
                    } else {
                        System.out.println("Invalid admin credentials!");
                    }
                    break;
                case 2:
                    System.out.print("Enter Username: ");
                    String user = sc.nextLine();
                    System.out.print("Enter Password: ");
                    String pass = sc.nextLine();
                    User loggedUser = accountManager.loginUser(user, pass);
                    if (loggedUser != null) {
                        System.out.println("User Login Successful!");
                        userMenu(sc, itemManager);
                    } else {
                        System.out.println("Invalid username or password!");
                    }
                    break;
                case 3:
                    System.out.print("Choose new username: ");
                    String newUser = sc.nextLine();
                    System.out.print("Choose new password: ");
                    String newPass = sc.nextLine();
                    accountManager.registerUser(newUser, newPass);
                    System.out.println("User registered successfully!");
                    break;
                case 0:
                    System.out.println("Exiting system...");
                    System.exit(0);
                default:
                    System.out.println("Invalid option!");
            }
            System.out.println();
        }
    }

    // ---------------- Admin Menu ----------------
    private static void adminMenu(Scanner sc, RETRUVA.ItemManager itemManager) {
        boolean exit = false;
        while (!exit) {
            System.out.println("\n--- ADMIN MENU ---");
            System.out.println("[1] Add Item");
            System.out.println("[2] Remove Item");
            System.out.println("[3] List Items");
            System.out.println("[0] Logout");
            System.out.print("Choose option: ");

            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1:
                    try {
                        System.out.print("Enter Item ID: ");
                        int id = sc.nextInt();
                        sc.nextLine();
                        System.out.print("Enter Item Name: ");
                        String name = sc.nextLine();
                        System.out.print("Enter Description: ");
                        String desc = sc.nextLine();
                        System.out.print("Enter Location: ");
                        String loc = sc.nextLine();
                        System.out.print("Enter Date Found: ");
                        String date = sc.nextLine();
                        itemManager.addItem(new RETRUVA.Item(id, name, desc, loc, date));
                    } catch (Exception e) {
                        System.out.println("Invalid input!");
                        sc.nextLine();
                    }
                    break;
                case 2:
                    System.out.print("Enter Item ID to remove: ");
                    int removeId = sc.nextInt();
                    sc.nextLine();
                    if (itemManager.removeItem(removeId)) {
                        System.out.println("Item removed successfully!");
                    } else {
                        System.out.println("Item not found!");
                    }
                    break;
                case 3:
                    itemManager.listItems();
                    break;
                case 0:
                    exit = true;
                    System.out.println("Logging out...");
                    break;
                default:
                    System.out.println("Invalid option!");
            }
        }
    }

    // ---------------- User Menu ----------------
    private static void userMenu(Scanner sc, RETRUVA.ItemManager itemManager) {
        boolean exit = false;
        while (!exit) {
            System.out.println("\n--- USER MENU ---");
            System.out.println("[1] Search Item");
            System.out.println("[2] Claim Item");
            System.out.println("[3] List Items");
            System.out.println("[0] Logout");
            System.out.print("Choose option: ");

            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1:
                    System.out.print("Enter keyword to search: ");
                    String keyword = sc.nextLine();
                    var results = itemManager.searchItem(keyword);
                    if (results.isEmpty()) {
                        System.out.println("No items found!");
                    } else {
                        for (RETRUVA.Item item : results) {
                            System.out.println("ID: " + item.getId() + " Name: " + item.getName());
                        }
                    }
                    break;
                case 2:
                    System.out.print("Enter Item ID to claim: ");
                    int claimId = sc.nextInt();
                    sc.nextLine();
                    boolean claimed = itemManager.claimItem(claimId);
                    if (claimed) {
                        System.out.println("Item claimed successfully!");
                    } else {
                        System.out.println("Item is either already claimed or not found!");
                    }
                    break;
                case 3:
                    itemManager.listItems();
                    break;
                case 0:
                    exit = true;
                    System.out.println("Logging out...");
                    break;
                default:
                    System.out.println("Invalid option!");
            }
        }
    }
}
