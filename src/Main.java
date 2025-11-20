import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        AccountManager accountManager = new AccountManager();
        ItemManager itemManager = new ItemManager();
        ReportManager reportManager = new ReportManager();

        while (true) {
            System.out.println("\n=== LOST & FOUND SYSTEM ===");
            System.out.println("[1] Login as Admin");
            System.out.println("[2] Login as User");
            System.out.println("[3] Register User");
            System.out.println("[0] Exit");
            System.out.print("Choose option: ");

            if (!sc.hasNextInt()) {
                System.out.println("Invalid input. Please enter a number.");
                sc.nextLine();
                continue;
            }

            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {

                case 1: // Admin login
                    System.out.print("Enter Admin Username: ");
                    String adminUser = sc.nextLine();

                    System.out.print("Enter Admin Password: ");
                    String adminPass = sc.nextLine();

                    Admin admin = accountManager.loginAdmin(adminUser, adminPass);

                    if (admin != null) {
                        System.out.println("Admin Login Successful!");
                        AdminMenu.adminMenu(sc, itemManager, reportManager);
                    } else {
                        System.out.println("Invalid admin credentials!");
                    }
                    break;

                case 2: // User login
                    System.out.print("Enter Username: ");
                    String user = sc.nextLine();

                    System.out.print("Enter Password: ");
                    String pass = sc.nextLine();

                    User loggedUser = accountManager.loginUser(user, pass);

                    if (loggedUser != null) {
                        System.out.println("User Login Successful!");
                        UserMenu.userMenu(sc, itemManager, reportManager);
                    } else {
                        System.out.println("Invalid username or password!");
                    }
                    break;

                case 3: // Register user
                    System.out.print("Choose new username: ");
                    String newUser = sc.nextLine();

                    System.out.print("Choose new password: ");
                    String newPass = sc.nextLine();

                    accountManager.registerUser(newUser, newPass);
                    System.out.println("User registered successfully!");
                    break;

                case 0:
                    System.out.println("System shutting down. Goodbye!");
                    sc.close();
                    return;

                default:
                    System.out.println("Invalid option!");
            }
        }
    }
}