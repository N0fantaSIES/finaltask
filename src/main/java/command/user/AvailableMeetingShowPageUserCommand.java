package command.user;

import command.Command;
import db.Entity.MainUser;
import db.Entity.Meeting;
import db.MeetingDAO;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;

public class AvailableMeetingShowPageUserCommand extends Command {

    public static final int AVAILABLE_MEETING_USER_SPLIT_SIZE = 10;
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        HttpSession session = request.getSession();
        MainUser mainUser = (MainUser) session.getAttribute("mainUser");
        MeetingDAO meetingDAO = new MeetingDAO();
        int pageNumber = 1;
        if (request.getParameter("pagNumber") != null){
            pageNumber = Integer.parseInt(request.getParameter("pagNumber"));
        }
        long user_id = mainUser.getId();
        double numberOfAvailableMeetings = meetingDAO.numberOfAvailableMeetings(user_id);
        ArrayList<Meeting> meetingArrayList = meetingDAO.showAllAvailableMeetingsForUsers(user_id, pageNumber);
        int numberOfSplits = (int) Math.ceil(numberOfAvailableMeetings / AVAILABLE_MEETING_USER_SPLIT_SIZE);
        request.setAttribute("meetingList", meetingArrayList);
        request.setAttribute("split", numberOfSplits);
        return "/available_meeting_show_page_for_user.jsp";
    }
}
