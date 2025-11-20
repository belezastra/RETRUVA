import java.util.HashMap;
import java.util.Map;

public class AccountManager {

    private Map<String, Account> accounts = new HashMap<>();

    public AccountManager() {
        // Default admin is still added
        accounts.put("admin", new Admin("admin", "admin123"));
    }

    public void registerUser(String username, String password) {
        if (accounts.containsKey(username)) {
            System.out.println("Username already exists!");
            return;
        }
        accounts.put(username, new User(username, password));
    }

    public Admin loginAdmin(String username, String password) {
        Account acc = accounts.get(username);
        if (acc instanceof Admin && acc.getPassword().equals(password)) {
            return (Admin) acc;
        }
        return null;
    }

    public User loginUser(String username, String password) {
        Account acc = accounts.get(username);
        if (acc instanceof User && acc.getPassword().equals(password)) {
            return (User) acc;
        }
        return null;
    }
}