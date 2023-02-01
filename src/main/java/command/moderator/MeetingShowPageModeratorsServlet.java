package command.moderator;

import db.Entity.Meeting;
import db.MeetingDAO;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

public class MeetingShowPageModeratorsServlet extends HttpServlet {

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        MeetingDAO meetingDAO = new MeetingDAO();
        ArrayList<Meeting> meetingArrayList = meetingDAO.showAllMeetings();
        req.setAttribute("meetingList", meetingArrayList);
        req.getRequestDispatcher("/meeting_show_page_for_moderator.jsp").forward(req, resp);
    }
}
