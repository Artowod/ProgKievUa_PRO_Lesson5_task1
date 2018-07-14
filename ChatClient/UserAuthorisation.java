package src;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.Charset;

public class UserAuthorisation {
    private User user;
    private String url;

    public UserAuthorisation(String url) {
        this.url = url;
    }

    public UserAuthorisation() {
    }

    public boolean isUserDataValid(String login, String password) {
        String wrongCars = "<,>.?/\"\\':;()=+";
        String checkedData = login + password;
        if (login.equals("") || password.equals("")) {
            return false;
        }
        for (char ch : checkedData.toCharArray())
            if (wrongCars.contains(String.valueOf(ch)) || ch == ' ') {
                return false;
            }
        return true;
    }

    public String addUser(User user, String url) {
        this.url = url;
        URL obj;
        StringBuilder returnedResponse = new StringBuilder();
        try {
            obj = new URL(url + "/login");

            HttpURLConnection conn = (HttpURLConnection) obj.openConnection();

            conn.setRequestMethod("POST");
            conn.setDoOutput(true);

            try (OutputStream os = conn.getOutputStream()) {
                os.write((user.getUserLogin() + " " + user.getUserPassword()).getBytes(Charset.forName("UTF-8")));
            }
            conn.getResponseMessage();

            try (InputStream is = conn.getInputStream()) {

                byte[] b = new byte[1];
                for (; is.read(b) != -1; ) {
                    returnedResponse.append((char) b[0]);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return returnedResponse.toString();
    }


}
