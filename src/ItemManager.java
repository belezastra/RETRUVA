package RETRUVA;

import java.util.ArrayList;

public class ItemManager {

    private ArrayList<RETRUVA.Item> items = new ArrayList<>();

    public ItemManager() {
        // default items
        items.add(new RETRUVA.Item(1, "Wallet", "Black leather wallet", "Library", "2025-01-20"));
        items.add(new RETRUVA.Item(2, "Umbrella", "Blue umbrella with white stripes", "Cafeteria", "2025-02-05"));
        items.add(new RETRUVA.Item(3, "USB Flash Drive", "16GB Kingston USB", "Computer Lab", "2025-02-10"));
        items.add(new RETRUVA.Item(4, "Water Bottle", "Red metal bottle", "Gym", "2025-02-12"));
    }

    // ---------------- Add Item ----------------
    public void addItem(RETRUVA.Item item) {
        items.add(item);
        System.out.println("Item added successfully!");
    }

    // ---------------- Remove Item ----------------
    public boolean removeItem(int id) {
        for (RETRUVA.Item item : items) {
            if (item.getId() == id) {
                items.remove(item);
                return true; // removed successfully
            }
        }
        return false; // item not found
    }

    // ---------------- Search Item ----------------
    public ArrayList<RETRUVA.Item> searchItem(String keyword) {
        ArrayList<RETRUVA.Item> results = new ArrayList<>();
        String lowerKeyword = keyword.toLowerCase();

        for (RETRUVA.Item item : items) {
            if (item.getName().toLowerCase().contains(lowerKeyword) ||
                    item.getDescription().toLowerCase().contains(lowerKeyword)) {
                results.add(item);
            }
        }
        return results;
    }

    // ---------------- Claim Item ----------------
    public boolean claimItem(int id) {
        for (RETRUVA.Item item : items) {
            if (item.getId() == id) {
                if (!item.isClaimed()) {
                    item.claim();
                    return true; // successfully claimed
                } else {
                    return false; // already claimed
                }
            }
        }
        return false; // item not found
    }

    // ---------------- List All Items ----------------
    public void listItems() {
        if (items.isEmpty()) {
            System.out.println("No items available.");
            return;
        }
        for (RETRUVA.Item item : items) {
            System.out.println("----------------------------");
            System.out.println("ID: " + item.getId());
            System.out.println("Name: " + item.getName());
            System.out.println("Description: " + item.getDescription());
            System.out.println("Location Found: " + item.getLocation());
            System.out.println("Date Found: " + item.getDateFound());
            System.out.println("Claimed: " + (item.isClaimed() ? "Yes" : "No"));
            System.out.println("----------------------------");
        }
    }

    // ---------------- Get Items for GUI ----------------
    public ArrayList<RETRUVA.Item> getItems() {
        return items;
    }
}
