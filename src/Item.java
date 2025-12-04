package RETRUVA;

public class Item {
    private int id;
    private String name;
    private String description;
    private String location;
    private String dateFound;
    private boolean isClaimed;

    public Item(int id, String name, String description, String location, String dateFound) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.location = location;
        this.dateFound = dateFound;
        this.isClaimed = false;
    }

    public int getId() { return id; }
    public String getName() { return name; }
    public String getDescription() { return description; }
    public String getLocation() { return location; }
    public String getDateFound() { return dateFound; }
    public boolean isClaimed() { return isClaimed; }
    public void claim() { this.isClaimed = true; }

    public void setName(String name) { this.name = name; }
    public void setDescription(String description) { this.description = description; }
    public void setLocation(String location) { this.location = location; }
    public void setDateFound(String dateFound) { this.dateFound = dateFound; }
}
