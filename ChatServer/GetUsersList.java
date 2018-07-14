package ua.kiev.prog;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;

public class GetUsersList extends HttpServlet {
    private UsersList usersList = UsersList.getInstance();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String responseForClient = usersList.getUsersLogins();

        OutputStream os = resp.getOutputStream();
        os.write(responseForClient.getBytes());
    }
}
