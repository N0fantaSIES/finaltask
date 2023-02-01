package command.login;

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


public class RegisterServlet extends HttpServlet {
    private static final int USER_ROLE_GUEST = 2;

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        MainUser mainUser = new MainUser();
        mainUser.setLogin(req.getParameter("login"));
        mainUser.setPassword(req.getParameter("password"));
        mainUser.setDate(Date.valueOf(LocalDate.now()));
        mainUser.setName(req.getParameter("name"));
        mainUser.setSurname(req.getParameter("surname"));
        mainUser.setRole_id(USER_ROLE_GUEST);
        System.out.println(mainUser);
        UserDAO userDAO = new UserDAO();
        userDAO.createNewUser(mainUser);
        HttpSession session = req.getSession();
        session.setAttribute("mainUser", mainUser);
        req.getRequestDispatcher("/main_page.jsp").forward(req, resp);

    }
}
