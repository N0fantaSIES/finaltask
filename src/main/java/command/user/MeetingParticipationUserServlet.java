package command.user;

import db.Entity.MainUser;
import db.MeetingUserDAO;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class MeetingParticipationUserServlet extends HttpServlet {

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        MainUser mainUser = (MainUser) session.getAttribute("mainUser");
        long meeting_id = Long.parseLong(req.getParameter("id"));
        MeetingUserDAO meetingUserDAO = new MeetingUserDAO();
        meetingUserDAO.createNewMeetingUser(meeting_id, mainUser.getId());
        req.getRequestDispatcher("/participationMeetingUserPage").forward(req, resp);
    }
}
