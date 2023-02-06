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

public class MeetingSortingForModeratorsCommand extends Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String sortingIndex = request.getParameter("sortingIndex");
        MeetingDAO meetingDAO = new MeetingDAO();
        ReportsDAO reportsDAO = new ReportsDAO();
        ArrayList<Meeting> meetingArrayList = meetingDAO.showAllMeetings();
        MeetingUserDAO meetingUserDAO = new MeetingUserDAO();
        for (Meeting m : meetingArrayList) {
            int number_of_participants = meetingUserDAO.countNumberOfUsersPerMeeting(m.getId());
            int number_of_reports = reportsDAO.countNumberOfReportsPerMeeting(m.getId());
            m.setNumber_of_participants(number_of_participants);
            m.setNumber_of_reports(number_of_reports);
        }
        if (!meetingArrayList.isEmpty()) {
            switch (sortingIndex) {
                case "date":
                    Collections.sort(meetingArrayList, new Comparator<Meeting>() {
                        @Override
                        public int compare(Meeting o1, Meeting o2) {
                            return o1.getDate().compareTo(o2.getDate());
                        }
                    });
                    break;
                case "numberOfParticipants":
                    Collections.sort(meetingArrayList, new Comparator<Meeting>() {
                        @Override
                        public int compare(Meeting o1, Meeting o2) {
                            return o1.getNumber_of_participants() - o2.getNumber_of_participants();
                        }
                    });
                    break;
                case "numberOfReports":
                    Collections.sort(meetingArrayList, new Comparator<Meeting>() {
                        @Override
                        public int compare(Meeting o1, Meeting o2) {
                            return o1.getNumber_of_reports() - o2.getNumber_of_reports();
                        }
                    });
                    break;
            }
        }
        request.setAttribute("meetingList", meetingArrayList);
        return "/show_number_of_participants_per_meeting_moderators.jsp";
    }
}
