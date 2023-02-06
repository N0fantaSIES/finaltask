package command.user;

import command.Command;
import db.Entity.MainUser;
import db.Entity.Meeting;
import db.MeetingDAO;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;

public class UserParticipationMeetingPageCommand extends Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        MeetingDAO meetingDAO = new MeetingDAO();
        HttpSession session = request.getSession();
        MainUser mainUser = (MainUser) session.getAttribute("mainUser");
        ArrayList<Meeting> meetingArrayList = meetingDAO.showAllMeetingsWhereUserParticipates(mainUser.getId());
        request.setAttribute("meetingArrayList", meetingArrayList);
        return "/user_participation_meeting_show_page.jsp";
    }
}
