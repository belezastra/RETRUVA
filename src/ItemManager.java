import java.util.HashMap;
import java.util.Map;
import java.util.ArrayList;

public class ItemManager {

    private Map<Integer, Item> items = new HashMap<>();

    public void addItem(Item item) {
        items.put(item.getId(), item);
        System.out.println("Item added successfully!");
    }

    public boolean removeItem(int id) {
        if (items.remove(id) != null) {
            System.out.println("Item removed successfully!");
            return true;
        }
        System.out.println("Item not found!");
        return false;
    }

    public ArrayList<Item> searchItem(String keyword) {
        ArrayList<Item> results = new ArrayList<>();
        for (Item item : items.values()) {
            if (item.getName().toLowerCase().contains(keyword.toLowerCase()) ||
                    item.getDescription().toLowerCase().contains(keyword.toLowerCase())) {
                results.add(item);
            }
        }
        return results;
    }

    public boolean claimItem(int id) {
        Item item = items.get(id);

        if (item != null) {
            if (!item.isClaimed()) {
                item.claim();
                System.out.println("Item successfully claimed!");
                return true;
            } else {
                System.out.println("Item is already claimed!");
                return false;
            }
        }
        System.out.println("Item not found!");
        return false;
    }

    public void listItems() {
        if (items.isEmpty()) {
            System.out.println("No items available.");
            return;
        }
        for (Item item : items.values()) {
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
}