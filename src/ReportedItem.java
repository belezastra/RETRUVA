public class ReportedItem {
    private String category;
    private String specification;
    private String description;
    private String location;
    private String dateFound;

    public ReportedItem(String category, String specification, String description,
                        String location, String dateFound) {
        this.category = category;
        this.specification = specification;
        this.description = description;
        this.location = location;
        this.dateFound = dateFound;
    }

    public String getCategory() {
        return category;
    }

    public String getSpecification() {
        return specification;
    }

    public String getDescription() {
        return description;
    }

    public String getLocation() {
        return location;
    }

    public String getDateFound() {
        return dateFound;
    }

    @Override
    public String toString() {
        return "\nPending Item Report:" +
                "\nCategory: " + category +
                "\nSpecification: " + specification +
                "\nDescription: " + description +
                "\nLocation: " + location +
                "\nDate Found: " + dateFound + "\n";
    }
}