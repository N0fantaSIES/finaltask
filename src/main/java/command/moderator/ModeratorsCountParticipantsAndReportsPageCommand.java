package command.moderator;

import command.Command;
import db.Entity.Meeting;
import db.MeetingDAO;
import db.MeetingUserDAO;
import db.ReportsDAO;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

import static command.moderator.MeetingShowPageModeratorsCommand.MEETING_SHOW_PAGE_MODERATORS_SPLIT_SIZE;

public class ModeratorsCountParticipantsAndReportsPageCommand extends Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        MeetingDAO meetingDAO = new MeetingDAO();
        ReportsDAO reportsDAO = new ReportsDAO();
        int pageNumber = 1;
        if (request.getParameter("pagNumber") != null){
            pageNumber = Integer.parseInt(request.getParameter("pagNumber"));
        }
        double numberOfAllMeetings = meetingDAO.numberOfAllMeetings();
        ArrayList<Meeting> meetingArrayList = meetingDAO.showAllMeetings(pageNumber);
        int numberOfSplits = (int) Math.ceil(numberOfAllMeetings / MEETING_SHOW_PAGE_MODERATORS_SPLIT_SIZE);
        MeetingUserDAO meetingUserDAO = new MeetingUserDAO();
        request.setAttribute("meetingList", meetingArrayList);
        request.setAttribute("split", numberOfSplits);
        request.setAttribute("page", pageNumber);
        return "/show_number_of_participants_per_meeting_moderators.jsp";
    }
}
