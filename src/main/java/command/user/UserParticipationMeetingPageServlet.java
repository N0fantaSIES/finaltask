package command.user;

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

public class UserParticipationMeetingPageServlet extends HttpServlet {

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        MeetingDAO meetingDAO = new MeetingDAO();
        HttpSession session = req.getSession();
        MainUser mainUser = (MainUser) session.getAttribute("mainUser");
        ArrayList<Meeting> meetingArrayList = meetingDAO.showAllMeetingsWhereUserParticipates(mainUser.getId());
        req.setAttribute("meetingArrayList", meetingArrayList);
        req.getRequestDispatcher("/user_participation_meeting_show_page.jsp").forward(req, resp);
    }
}
