import java.util.Scanner;
import java.util.concurrent.atomic.AtomicInteger;

public class AdminMenu {

    private static final AtomicInteger ID_GENERATOR = new AtomicInteger(1000);

    public static void adminMenu(Scanner sc, ItemManager itemManager, ReportManager reportManager) {
        int option;

        do {
            System.out.println("\n--- ADMIN MENU ---");
            System.out.println("[1] View Pending Reports");
            System.out.println("[2] Approve Report");
            System.out.println("[3] Reject Report");
            System.out.println("[4] List Official Items");
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
                    reportManager.listReports();
                    break;

                case 2:
                    reportManager.listReports();
                    if (reportManager.getReport(0) == null) break;

                    System.out.print("Enter report index to APPROVE: ");

                    if (!sc.hasNextInt()) {
                        System.out.println("Invalid input. Please enter a number for the index.");
                        sc.nextLine();
                        break;
                    }

                    int approveIndex = sc.nextInt();
                    sc.nextLine();

                    ReportedItem reportToApprove = reportManager.getReport(approveIndex);

                    if (reportToApprove != null) {
                        int newId = ID_GENERATOR.getAndIncrement();
                        String name = reportToApprove.getCategory() + " - " + reportToApprove.getSpecification();

                        Item newItem = new Item(
                                newId,
                                name,
                                reportToApprove.getDescription(),
                                reportToApprove.getLocation(),
                                reportToApprove.getDateFound()
                        );

                        itemManager.addItem(newItem);
                        reportManager.removeReport(approveIndex);
                        System.out.println("Report approved (ID: " + newId + ") and added to item list!");
                    } else {
                        System.out.println("Invalid index!");
                    }
                    break;

                case 3:
                    reportManager.listReports();
                    if (reportManager.getReport(0) == null) break;

                    System.out.print("Enter report index to REJECT: ");

                    if (!sc.hasNextInt()) {
                        System.out.println("Invalid input. Please enter a number for the index.");
                        sc.nextLine();
                        break;
                    }

                    int reject = sc.nextInt();
                    sc.nextLine();

                    reportManager.removeReport(reject);
                    break;

                case 4:
                    itemManager.listItems();
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