package ua.kiev.prog;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class UserAuthorisation extends HttpServlet {
    private UsersList usersList = UsersList.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }

    protected void doPost(HttpServletRequest req, HttpServletResponse response) throws ServletException, IOException {
        InputStream is = req.getInputStream();
        StringBuilder sb = new StringBuilder();
        for (int i = is.read(); i != -1; i = is.read()) {
            sb.append((char) i);
            System.out.print((char) i);
        }
        String login = sb.toString().split(" ")[0];
        String password = sb.toString().split(" ")[1];
        String responseForClient = usersList.userCredentialsCheck(login, password);

        OutputStream os = response.getOutputStream();
        os.write(responseForClient.getBytes());
    }
}
