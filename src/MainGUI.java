package RETRUVA;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;

public class MainGUI extends JFrame {

    private RETRUVA.AccountManager accountManager = new RETRUVA.AccountManager();
    private RETRUVA.ItemManager itemManager = new RETRUVA.ItemManager();
    private RETRUVA.User currentUser = null;
    private RETRUVA.Admin currentAdmin = null;

    // Color Palette
    private final Color PRIMARY_RED = new Color(178, 34, 34);
    private final Color DIRTY_WHITE = new Color(245, 245, 245);
    private final Color BUTTON_BG = new Color(220, 20, 60);

    public MainGUI() {
        setTitle("RETRUVA - Batangas State University");
        setSize(800, 550);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
        showMainMenu();
        setVisible(true);
    }

    // ---------------- Main Menu ----------------
    private void showMainMenu() {
        getContentPane().removeAll();
        getContentPane().setBackground(DIRTY_WHITE);

        // Header panel with logo
        JPanel headerPanel = new JPanel(new BorderLayout());
        headerPanel.setBackground(DIRTY_WHITE);
        headerPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // University logo (replace with actual path)
        ImageIcon logo = new ImageIcon("batstateu_logo.png");
        Image img = logo.getImage().getScaledInstance(80, 80, Image.SCALE_SMOOTH);
        JLabel logoLabel = new JLabel(new ImageIcon(img));
        headerPanel.add(logoLabel, BorderLayout.WEST);

        // Title and campus
        JPanel textPanel = new JPanel(new GridLayout(2, 1));
        textPanel.setBackground(DIRTY_WHITE);
        JLabel title = new JLabel("RETRUVA", SwingConstants.CENTER);
        title.setFont(new Font("Segoe UI", Font.BOLD, 32));
        title.setForeground(PRIMARY_RED);

        JLabel campus = new JLabel("Batangas State University - Lipa Campus", SwingConstants.CENTER);
        campus.setFont(new Font("Segoe UI", Font.PLAIN, 18));
        campus.setForeground(PRIMARY_RED);

        textPanel.add(title);
        textPanel.add(campus);
        headerPanel.add(textPanel, BorderLayout.CENTER);

        add(headerPanel, BorderLayout.NORTH);

        // Button panel
        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(DIRTY_WHITE);
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(20, 250, 20, 250));

        JButton adminBtn = createMenuButton("Login as Admin");
        JButton userBtn = createMenuButton("Login as User");
        JButton registerBtn = createMenuButton("Register User");
        JButton exitBtn = createMenuButton("Exit");

        adminBtn.addActionListener(e -> loginAdmin());
        userBtn.addActionListener(e -> loginUser());
        registerBtn.addActionListener(e -> registerUser());
        exitBtn.addActionListener(e -> System.exit(0));

        buttonPanel.add(adminBtn);
        buttonPanel.add(Box.createRigidArea(new Dimension(0, 15)));
        buttonPanel.add(userBtn);
        buttonPanel.add(Box.createRigidArea(new Dimension(0, 15)));
        buttonPanel.add(registerBtn);
        buttonPanel.add(Box.createRigidArea(new Dimension(0, 15)));
        buttonPanel.add(exitBtn);

        add(buttonPanel, BorderLayout.CENTER);

        // Footer with university motto/tagline
        JLabel footer = new JLabel("Excellence in Technology and Innovation", SwingConstants.CENTER);
        footer.setFont(new Font("Segoe UI", Font.ITALIC, 14));
        footer.setForeground(PRIMARY_RED);
        footer.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
        add(footer, BorderLayout.SOUTH);

        revalidate();
        repaint();
    }

    private JButton createMenuButton(String text) {
        JButton b = new JButton(text);
        b.setAlignmentX(Component.CENTER_ALIGNMENT);
        b.setFont(new Font("Segoe UI", Font.BOLD, 18));
        b.setMaximumSize(new Dimension(Integer.MAX_VALUE, 50));
        b.setFocusPainted(false);
        b.setBackground(BUTTON_BG);
        b.setForeground(DIRTY_WHITE);
        b.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        return b;
    }

    // ---------------- Admin Login ----------------
    private void loginAdmin() {
        String user = JOptionPane.showInputDialog(this, "Admin Username:");
        String pass = JOptionPane.showInputDialog(this, "Admin Password:");
        RETRUVA.Admin admin = accountManager.loginAdmin(user, pass);

        if (admin != null) {
            currentAdmin = admin;
            showAdminMenu();
        } else {
            JOptionPane.showMessageDialog(this, "Invalid admin credentials!", "Login Failed", JOptionPane.ERROR_MESSAGE);
        }
    }

    // ---------------- User Login ----------------
    private void loginUser() {
        String user = JOptionPane.showInputDialog(this, "Username:");
        String pass = JOptionPane.showInputDialog(this, "Password:");
        RETRUVA.User loggedUser = accountManager.loginUser(user, pass);

        if (loggedUser != null) {
            currentUser = loggedUser;
            showUserMenu();
        } else {
            JOptionPane.showMessageDialog(this, "Invalid username or password!", "Login Failed", JOptionPane.ERROR_MESSAGE);
        }
    }

    // ---------------- Register User ----------------
    private void registerUser() {
        String newUser = JOptionPane.showInputDialog(this, "Choose Username:");
        String newPass = JOptionPane.showInputDialog(this, "Choose Password:");
        accountManager.registerUser(newUser, newPass);
        JOptionPane.showMessageDialog(this, "User registered successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
    }

    // ---------------- Admin Menu ----------------
    private void showAdminMenu() {
        showMenu("ADMIN MENU",
                new String[]{"Add Item", "Remove Item", "List Items", "Logout"},
                new Runnable[]{() -> addItem(), () -> removeItem(), () -> listItems(), () -> logoutAdmin()});
    }

    // ---------------- User Menu ----------------
    private void showUserMenu() {
        showMenu("USER MENU",
                new String[]{"Search Item", "Claim Item", "List Items", "Logout"},
                new Runnable[]{() -> searchItem(), () -> claimItem(), () -> listItems(), () -> logoutUser()});
    }

    // ---------------- Generic Menu ----------------
    private void showMenu(String menuTitle, String[] buttonLabels, Runnable[] actions) {
        getContentPane().removeAll();
        getContentPane().setBackground(DIRTY_WHITE);

        JLabel title = new JLabel(menuTitle, SwingConstants.CENTER);
        title.setFont(new Font("Segoe UI", Font.BOLD, 28));
        title.setForeground(PRIMARY_RED);
        title.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0));

        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(DIRTY_WHITE);
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(20, 250, 20, 250));

        for (int i = 0; i < buttonLabels.length; i++) {
            JButton b = createMenuButton(buttonLabels[i]);
            int index = i;
            b.addActionListener(e -> actions[index].run());
            buttonPanel.add(b);
            buttonPanel.add(Box.createRigidArea(new Dimension(0, 15)));
        }

        add(title, BorderLayout.NORTH);
        add(buttonPanel, BorderLayout.CENTER);
        revalidate();
        repaint();
    }

    private void logoutAdmin() {
        currentAdmin = null;
        showMainMenu();
    }

    private void logoutUser() {
        currentUser = null;
        showMainMenu();
    }

    // ---------------- Admin Actions ----------------
    private void addItem() {
        try {
            int id = Integer.parseInt(JOptionPane.showInputDialog(this, "Enter Item ID:"));
            String name = JOptionPane.showInputDialog(this, "Enter Item Name:");
            String desc = JOptionPane.showInputDialog(this, "Enter Description:");
            String loc = JOptionPane.showInputDialog(this, "Enter Location:");
            String date = JOptionPane.showInputDialog(this, "Enter Date Found (YYYY-MM-DD):");
            itemManager.addItem(new RETRUVA.Item(id, name, desc, loc, date));
            JOptionPane.showMessageDialog(this, "Item added successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Invalid input!", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void removeItem() {
        try {
            int id = Integer.parseInt(JOptionPane.showInputDialog(this, "Enter Item ID to remove:"));
            if (itemManager.removeItem(id)) {
                JOptionPane.showMessageDialog(this, "Item removed successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this, "Item not found!", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Invalid input!", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    // ---------------- User Actions ----------------
    private void searchItem() {
        String keyword = JOptionPane.showInputDialog(this, "Enter keyword to search:");
        ArrayList<RETRUVA.Item> results = itemManager.searchItem(keyword);

        if (results.isEmpty()) {
            JOptionPane.showMessageDialog(this, "No items found!", "Search Result", JOptionPane.INFORMATION_MESSAGE);
            return;
        }

        StringBuilder sb = new StringBuilder();
        for (RETRUVA.Item item : results) {
            sb.append("ID: ").append(item.getId())
                    .append(" | Name: ").append(item.getName())
                    .append("\nDescription: ").append(item.getDescription())
                    .append("\nLocation: ").append(item.getLocation())
                    .append("\nDate Found: ").append(item.getDateFound())
                    .append("\nClaimed: ").append(item.isClaimed() ? "Yes" : "No")
                    .append("\n---------------------------\n");
        }

        JTextArea textArea = new JTextArea(sb.toString());
        textArea.setEditable(false);
        textArea.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        JOptionPane.showMessageDialog(this, new JScrollPane(textArea), "Search Results", JOptionPane.INFORMATION_MESSAGE);
    }

    private void claimItem() {
        try {
            int id = Integer.parseInt(JOptionPane.showInputDialog(this, "Enter Item ID to claim:"));
            if (itemManager.claimItem(id)) {
                JOptionPane.showMessageDialog(this, "Item claimed successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this, "Item is already claimed or not found!", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Invalid input!", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    // ---------------- Common ----------------
    private void listItems() {
        ArrayList<RETRUVA.Item> items = itemManager.getItems();
        if (items.isEmpty()) {
            JOptionPane.showMessageDialog(this, "No items available.", "Items List", JOptionPane.INFORMATION_MESSAGE);
            return;
        }

        String[] columns = {"ID", "Name", "Description", "Location", "Date Found", "Claimed"};
        DefaultTableModel model = new DefaultTableModel(columns, 0);

        for (RETRUVA.Item item : items) {
            model.addRow(new Object[]{
                    item.getId(),
                    item.getName(),
                    item.getDescription(),
                    item.getLocation(),
                    item.getDateFound(),
                    item.isClaimed() ? "Yes" : "No"
            });
        }

        JTable table = new JTable(model);
        table.setFillsViewportHeight(true);
        table.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        table.setRowHeight(25);

        JScrollPane scrollPane = new JScrollPane(table);

        JFrame tableFrame = new JFrame("Items List");
        tableFrame.setSize(750, 450);
        tableFrame.setLayout(new BorderLayout());
        tableFrame.add(scrollPane, BorderLayout.CENTER);
        tableFrame.setLocationRelativeTo(this);
        tableFrame.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(MainGUI::new);
    }
}
