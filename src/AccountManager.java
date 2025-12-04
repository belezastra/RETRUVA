package RETRUVA;

import java.util.ArrayList;

public class AccountManager {

    private ArrayList<RETRUVA.Account> accounts = new ArrayList<>();

    public AccountManager() {
        accounts.add(new Admin("admin", "admin123")); // default admin
    }

    public void registerUser(String username, String password) {
        accounts.add(new User(username, password));
    }

    public Admin loginAdmin(String username, String password) {
        for (RETRUVA.Account acc : accounts) {
            if (acc instanceof Admin &&
                    acc.getUsername().equals(username) &&
                    acc.getPassword().equals(password)) {
                return (Admin) acc;
            }
        }
        return null;
    }

    public User loginUser(String username, String password) {
        for (RETRUVA.Account acc : accounts) {
            if (acc instanceof User &&
                    acc.getUsername().equals(username) &&
                    acc.getPassword().equals(password)) {
                return (User) acc;
            }
        }
        return null;
    }
}
