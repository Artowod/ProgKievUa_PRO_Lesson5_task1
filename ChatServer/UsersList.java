package ua.kiev.prog;

import java.util.HashMap;
import java.util.Map;

public class UsersList {

    private static final UsersList usersList = new UsersList();
    private final Map<String, String> usersCredentials = new HashMap<>();

    private UsersList() {
    }

    public static UsersList getInstance() {
        return usersList;
    }

    public String getUsersLogins() {
        StringBuilder allLogins = new StringBuilder();
        for (String key : usersCredentials.keySet()) {
            allLogins.append(key + " ");
        }
        return allLogins.toString();
    }

    public synchronized String userCredentialsCheck(String login, String password) {
        if (usersCredentials.containsKey(login)) {
            if (usersCredentials.get(login).equals(password)) {
                return "ok_Welcome " + login + "!";
            } else {
                return "wrong_Sorry, but the password is Incorrect!";
            }
        } else {
            usersCredentials.put(login, password);
            return "ok_You (" + login + ") has been added to DataBase";
        }
    }


}
