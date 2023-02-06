package command.login;

import command.Command;
import db.Entity.MainUser;
import db.UserDAO;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class LoginCommand extends Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String login = request.getParameter("login");
        String password = request.getParameter("password");
        String requestDispatcher = null;
        UserDAO userDAO = new UserDAO();
        MainUser mainUser = userDAO.findUserByLoginAndPassword(login, password);
        HttpSession session = request.getSession();
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
        return requestDispatcher;
    }
}
