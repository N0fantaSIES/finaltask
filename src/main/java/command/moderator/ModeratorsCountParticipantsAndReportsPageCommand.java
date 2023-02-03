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

public class ModeratorsCountParticipantsAndReportsPageCommand extends Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        MeetingDAO meetingDAO = new MeetingDAO();
        ReportsDAO reportsDAO = new ReportsDAO();
        ArrayList<Meeting> meetingArrayList = meetingDAO.showAllMeetings();
        MeetingUserDAO meetingUserDAO = new MeetingUserDAO();
        for (Meeting m:meetingArrayList) {
            int number_of_participants = meetingUserDAO.countNumberOfUsersPerMeeting(m.getId());
            int number_of_reports = reportsDAO.countNumberOfReportsPerMeeting(m.getId());
            m.setNumber_of_participants(number_of_participants);
            m.setNumber_of_reports(number_of_reports);
            m.setNumber_of_participants_who_came(m.getNumber_of_participants_who_came());
        }
        request.setAttribute("meetingList", meetingArrayList);
        return "/show_number_of_participants_per_meeting_moderators.jsp";
    }
//    @Override
//    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        MeetingDAO meetingDAO = new MeetingDAO();
//        ReportsDAO reportsDAO = new ReportsDAO();
//        ArrayList<Meeting> meetingArrayList = meetingDAO.showAllMeetings();
//        MeetingUserDAO meetingUserDAO = new MeetingUserDAO();
//        for (Meeting m:meetingArrayList) {
//          int number_of_participants = meetingUserDAO.countNumberOfUsersPerMeeting(m.getId());
//          int number_of_reports = reportsDAO.countNumberOfReportsPerMeeting(m.getId());
//          m.setNumber_of_participants(number_of_participants);
//          m.setNumber_of_reports(number_of_reports);
//        }
//        req.setAttribute("meetingList", meetingArrayList);
//        req.getRequestDispatcher("/show_number_of_participants_per_meeting_moderators.jsp").forward(req, resp);
//    }
}
