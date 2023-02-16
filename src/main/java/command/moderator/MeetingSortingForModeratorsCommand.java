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
import java.util.Collections;
import java.util.Comparator;

import static command.moderator.MeetingShowPageModeratorsCommand.MEETING_SHOW_PAGE_MODERATORS_SPLIT_SIZE;

public class MeetingSortingForModeratorsCommand extends Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String sortingIndex = request.getParameter("sortingIndex");
        MeetingDAO meetingDAO = new MeetingDAO();
        ReportsDAO reportsDAO = new ReportsDAO();
        int pageNumber = 1;
        if (request.getParameter("pagNumber") != null && !request.getParameter("pagNumber").isEmpty()) {
            pageNumber = Integer.parseInt(request.getParameter("pagNumber"));
        }
        double numberOfAllMeetings = meetingDAO.numberOfAllMeetings();
        ArrayList<Meeting> meetingArrayList = new ArrayList<>();
        int numberOfSplits = (int) Math.ceil(numberOfAllMeetings / MEETING_SHOW_PAGE_MODERATORS_SPLIT_SIZE);
        MeetingUserDAO meetingUserDAO = new MeetingUserDAO();
        switch (sortingIndex) {
            case "date":
                meetingArrayList = meetingDAO.showAllMeetings(pageNumber, "date");
                break;
            case "numberOfParticipants":
                meetingArrayList = meetingDAO.showAllMeetings(pageNumber, "number_of_participants");
                break;
            case "numberOfReports":
                meetingArrayList = meetingDAO.showAllMeetings(pageNumber, "number_of_reports");
                break;
            default:
                meetingArrayList = meetingDAO.showAllMeetings(pageNumber);
                break;
        }
        request.setAttribute("meetingList", meetingArrayList);
        request.setAttribute("split", numberOfSplits);
        request.setAttribute("page", pageNumber);
        return "/show_number_of_participants_per_meeting_moderators.jsp";
    }
}
