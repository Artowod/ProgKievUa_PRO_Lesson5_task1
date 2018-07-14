package src;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class Utils {
    private static final String URL = "http://127.0.0.1";
    private static final int PORT = 8080;

    public Utils() {
    }

    public static String getURL() {
        return URL + ":" + PORT;
    }

    public String getUsersList(String url){
        StringBuilder returnedResponse = new StringBuilder();
        try {
            URL usersDatabaseUrl = new URL(url);

            HttpURLConnection conn = (HttpURLConnection) usersDatabaseUrl.openConnection();

            conn.setRequestMethod("POST");
            conn.setDoOutput(false);

            try (InputStream is = conn.getInputStream()) {

                byte[] b = new byte[1];
                for(;is.read(b)!=-1;){
                    returnedResponse.append((char)b[0]);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return returnedResponse.toString();
    }

}
