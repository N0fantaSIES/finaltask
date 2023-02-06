package command.moderator;

import command.Command;
import db.Entity.MainUser;
import db.Entity.Meeting;
import db.MeetingDAO;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Date;


public class CreateMeetingModeratorCommand extends Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        Meeting meeting = new Meeting();
        HttpSession session = request.getSession();
        MainUser mainUser = (MainUser) session.getAttribute("mainUser");
        meeting.setName(request.getParameter("name"));
        meeting.setTopic(request.getParameter("topic"));
        meeting.setDate(Date.valueOf(request.getParameter("date")));
        meeting.setCreator_id(mainUser.getId());
        meeting.setCreator_name(mainUser.getLogin());
        System.out.println(meeting);
        MeetingDAO meetingDAO = new MeetingDAO();
        meetingDAO.createNewMeeting(meeting);
        return  "/working_page_for_moderators.jsp";
    }
}
