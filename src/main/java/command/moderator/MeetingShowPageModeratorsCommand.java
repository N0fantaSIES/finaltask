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

    public static final int MEETING_SHOW_PAGE_MODERATORS_SPLIT_SIZE = 10;
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        MeetingDAO meetingDAO = new MeetingDAO();
        int pageNumber = 1;
        if (request.getParameter("pagNumber") != null){
            pageNumber = Integer.parseInt(request.getParameter("pagNumber"));
        }
        double numberOfAllMeetings = meetingDAO.numberOfAllMeetings();
        ArrayList<Meeting> meetingArrayList = meetingDAO.showAllMeetings(pageNumber);
        int numberOfSplits = (int) Math.ceil(numberOfAllMeetings / MEETING_SHOW_PAGE_MODERATORS_SPLIT_SIZE);
        request.setAttribute("meetingList", meetingArrayList);
        request.setAttribute("split", numberOfSplits);
        return "/meeting_show_page_for_moderator.jsp";
    }
}
