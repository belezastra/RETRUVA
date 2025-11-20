import java.util.ArrayList;
import java.util.Scanner;

public class UserMenu {
    public static void userMenu(Scanner sc, ItemManager itemManager, ReportManager reportManager) {
        int option;

        do {
            System.out.println("\n--- USER MENU ---");
            System.out.println("[1] Report Found Item");
            System.out.println("[2] View All Official Lost Items");
            System.out.println("[3] Search Item");
            System.out.println("[4] Claim Item (By ID)");
            System.out.println("[0] Back");
            System.out.print("Choose: ");

            if (!sc.hasNextInt()) {
                System.out.println("Invalid input. Please enter a number.");
                sc.nextLine();
                option = -1;
                continue;
            }

            option = sc.nextInt();
            sc.nextLine();

            switch (option) {

                case 1:
                    System.out.println("\n--- REPORTING NEW ITEM ---");
                    System.out.print("Category (e.g., Phone, Wallet): ");
                    String c = sc.nextLine();
                    System.out.print("Brand/Specification (e.g., Samsung S21, Brown Leather): ");
                    String b = sc.nextLine();
                    System.out.print("Detailed Description: ");
                    String d = sc.nextLine();
                    System.out.print("Location Found (e.g., Library 3rd floor): ");
                    String loc = sc.nextLine();
                    System.out.print("Date Found (MM/DD/YYYY): ");
                    String date = sc.nextLine();

                    ReportedItem report = new ReportedItem(c, b, d, loc, date);
                    reportManager.addReport(report);
                    break;

                case 2:
                    System.out.println("\n--- OFFICIAL LOST ITEMS ---");
                    itemManager.listItems();
                    break;

                case 3:
                    System.out.print("Enter search keyword: ");
                    String key = sc.nextLine();
                    ArrayList<Item> results = itemManager.searchItem(key);

                    if (results.isEmpty()) {
                        System.out.println("No items found matching '" + key + "'.");
                    } else {
                        System.out.println("\n--- SEARCH RESULTS ---");
                        for (Item item : results) {
                            System.out.println("ID: " + item.getId() + " | Name: " + item.getName() + " | Location: " + item.getLocation() + " | Claimed: " + (item.isClaimed() ? "Yes" : "No"));
                        }
                    }
                    break;

                case 4:
                    System.out.print("Enter the ID of the item you want to claim: ");

                    if (!sc.hasNextInt()) {
                        System.out.println("Invalid input. Please enter a number for the ID.");
                        sc.nextLine();
                        break;
                    }

                    int claimId = sc.nextInt();
                    sc.nextLine();
                    itemManager.claimItem(claimId);
                    break;


                case 0:
                    System.out.println("Returning to main menu...");
                    break;

                default:
                    System.out.println("Invalid option!");
            }

        } while (option != 0);
    }
}