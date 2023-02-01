package command;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class TransferServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
       long id = Long.parseLong(req.getParameter("id"));
        req.setAttribute("id", id);
        req.getRequestDispatcher("/report_create_page_for_moderators.jsp").forward(req, resp);
    }
}
