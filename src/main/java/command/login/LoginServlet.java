package command.login;

import db.Entity.MainUser;
import db.UserDAO;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class LoginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        String requestDispatcher = null;
        UserDAO userDAO = new UserDAO();
        MainUser mainUser = userDAO.findUserByLoginAndPassword(login, password);
        HttpSession session = req.getSession();
        session.setAttribute("mainUser", mainUser);
        switch (mainUser.getRole_id()) {
            case 1:
                requestDispatcher = "/working_page_for_moderators.jsp";
                break;
            case 2:
                requestDispatcher = "/working_page_for_users.jsp";
                break;
            case 3:
                requestDispatcher = "/working_page_for_speakers.jsp";
                break;
            default:
                requestDispatcher = "/error_page.jsp";
                break;
        }
        req.getRequestDispatcher(requestDispatcher).forward(req, resp);

    }

}
