package command.user;

import command.Command;
import db.Entity.MainUser;
import db.MeetingUserDAO;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class MeetingParticipationUserCommand extends Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        HttpSession session = request.getSession();
        MainUser mainUser = (MainUser) session.getAttribute("mainUser");
        long meeting_id = Long.parseLong(request.getParameter("id"));
        MeetingUserDAO meetingUserDAO = new MeetingUserDAO();
        meetingUserDAO.createNewMeetingUser(meeting_id, mainUser.getId());
        return "/participationMeetingUserPage";
    }
}
