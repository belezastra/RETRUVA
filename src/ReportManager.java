import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

public class ReportManager {

    private AtomicInteger reportIndexCounter = new AtomicInteger(0);
    private Map<Integer, ReportedItem> reports = new HashMap<>();

    public void addReport(ReportedItem report) {
        int index = reportIndexCounter.getAndIncrement();
        reports.put(index, report);
        System.out.println("Report submitted! Waiting for admin approval. (Index: " + index + ")");
    }

    public void listReports() {
        if (reports.isEmpty()) {
            System.out.println("No pending reports.");
            return;
        }

        System.out.println("\n--- PENDING REPORTS ---");
        for (Map.Entry<Integer, ReportedItem> entry : reports.entrySet()) {
            System.out.println("Index " + entry.getKey() + ":");
            System.out.println(entry.getValue());
        }
    }

    public ReportedItem getReport(int index) {
        return reports.get(index);
    }

    public void removeReport(int index) {
        if (reports.remove(index) != null) {
            System.out.println("Report index " + index + " successfully removed.");
        } else {
            System.out.println("Invalid index for report removal.");
        }
    }
}