package command.moderator;

import command.Command;
import db.Entity.Report;
import db.MeetingDAO;
import db.ReportsDAO;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class CreateReportPageModeratorCommand extends Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        Report report = new Report();
        MeetingDAO meetingDAO = new MeetingDAO();
        long meeting_id = Long.parseLong(request.getParameter("id"));
        report.setMeeting_id(meeting_id);
        report.setTitle_of_report(request.getParameter("title_of_report"));
        System.out.println(report);
        ReportsDAO reportsDAO = new ReportsDAO();
        reportsDAO.createNewReport(report);
        meetingDAO.updateNumberOfReports(meeting_id);
        return "/controller?command=moderReportTableCommand";
    }
}
