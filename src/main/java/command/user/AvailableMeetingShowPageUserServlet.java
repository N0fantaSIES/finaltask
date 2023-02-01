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

public class AvailableMeetingShowPageUserServlet extends HttpServlet {

    public static final int SPLIT_SIZE = 5;

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        MainUser mainUser = (MainUser) session.getAttribute("mainUser");
        MeetingDAO meetingDAO = new MeetingDAO();
        int pageNumber = 1;
        if (req.getParameter("pagNumber") != null){
            pageNumber = Integer.parseInt(req.getParameter("pagNumber"));
        }
        long user_id = mainUser.getId();
        double numberOfAvailableMeetings = meetingDAO.numberOfAvailableMeetings(user_id);
        ArrayList<Meeting> meetingArrayList = meetingDAO.showAllAvailableMeetingsForUsers(user_id, pageNumber);
        int numberOfSplits = (int) Math.ceil(numberOfAvailableMeetings / SPLIT_SIZE);
        req.setAttribute("meetingList", meetingArrayList);
        req.setAttribute("split", numberOfSplits);
        req.getRequestDispatcher("/available_meeting_show_page_for_user.jsp").forward(req, resp);
    }
}
