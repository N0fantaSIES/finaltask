package command.moderator;

import command.Command;
import db.Entity.Meeting;
import db.MeetingDAO;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

public class MeetingShowPageModeratorsCommand extends Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        MeetingDAO meetingDAO = new MeetingDAO();
        ArrayList<Meeting> meetingArrayList = meetingDAO.showAllMeetings();
        request.setAttribute("meetingList", meetingArrayList);
        return "/meeting_show_page_for_moderator.jsp";
    }
}
