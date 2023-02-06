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
import java.sql.Date;
import java.time.LocalDate;


public class RegisterCommand extends Command {
    private static final int USER_ROLE_GUEST = 2;

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        MainUser mainUser = new MainUser();
        mainUser.setLogin(request.getParameter("login"));
        mainUser.setPassword(request.getParameter("password"));
        mainUser.setDate(Date.valueOf(LocalDate.now()));
        mainUser.setName(request.getParameter("name"));
        mainUser.setSurname(request.getParameter("surname"));
        mainUser.setRole_id(USER_ROLE_GUEST);
        System.out.println(mainUser);
        UserDAO userDAO = new UserDAO();
        userDAO.createNewUser(mainUser);
        HttpSession session = request.getSession();
        session.setAttribute("mainUser", mainUser);
        return "/main.jsp";
    }
}
