package src;

import java.io.IOException;
import java.util.Scanner;

public class Main {
    private static final String URL = Utils.getURL();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        try {
            String login = "";
            String password = "";
            UserAuthorisation checkedUser;
            boolean isUserLogged = false;
            while (!isUserLogged) {
                System.out.print("Enter your login: ");
                login = scanner.nextLine();
                System.out.print("Enter your password: ");
                password = scanner.nextLine();
                checkedUser = new UserAuthorisation();
                if (checkedUser.isUserDataValid(login, password)) {
                    User currentUser = new User(login, password);
                    String usersDBResponse = checkedUser.addUser(currentUser, URL);
                    System.out.println(usersDBResponse.split("_")[1]);
                    if (usersDBResponse.split("_")[0].equals("ok")) {
                        isUserLogged = true;
                    }
                } else {
                    System.out.println("Your data contains wrong symbols. Please repeat.");
                }
            }
            Thread th = new Thread(new GetThread());
            th.setName("DaemonMonitor");
            th.setDaemon(true);
            th.start();
            System.out.println("<info> Enter  #list   to get all Users list.");
            System.out.println("<info> Press just Enter to finish.");
            System.out.println("Enter your message: ");
            while (true) {
                String text = scanner.nextLine();
                if (text.isEmpty()) break;
                if (text.equals("#list")) {
                    Utils accessToUsersDB = new Utils();
                    String usersList = accessToUsersDB.getUsersList(URL + "/userslist");
                    int userCounter = 1;
                    System.out.println("\nUsers who are registered in DataBase:");
                    for (String eachUser : usersList.split(" ")) {
                        System.out.println(userCounter++ + ") " + eachUser);
                    }
                } else {
                    Message m = new Message(login, text);
                    int res = m.send(URL + "/add");

                    if (res != 200) { // 200 OK
                        System.out.println("HTTP error occured: " + res);
                        return;
                    }
                }
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            scanner.close();
        }
    }
}
