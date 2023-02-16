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

    public static final int USER_MEETING_PARTICIPATION_SPLIT_SIZE = 10;
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        MeetingDAO meetingDAO = new MeetingDAO();
        int pageNumber = 1;
        if (request.getParameter("pagNumber") != null){
            pageNumber = Integer.parseInt(request.getParameter("pagNumber"));
        }
        HttpSession session = request.getSession();
        MainUser mainUser = (MainUser) session.getAttribute("mainUser");
        long user_id = mainUser.getId();
        double numberOfAvailableMeetings = meetingDAO.numberOfMeetingsWhereUserParticipates(user_id);
        ArrayList<Meeting> meetingArrayList = meetingDAO.showAllMeetingsWhereUserParticipates(user_id, pageNumber);
        int numberOfSplits = (int) Math.ceil(numberOfAvailableMeetings / USER_MEETING_PARTICIPATION_SPLIT_SIZE);
        request.setAttribute("meetingArrayList", meetingArrayList);
        request.setAttribute("split", numberOfSplits);
        return "/user_participation_meeting_show_page.jsp";
    }
}
