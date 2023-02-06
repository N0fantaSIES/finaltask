package command.moderator;

import command.Command;
import db.Entity.Report;
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
        report.setMeeting_id(Long.parseLong(request.getParameter("id")));
        report.setTitle_of_report(request.getParameter("title_of_report"));
        System.out.println(report);
        ReportsDAO reportsDAO = new ReportsDAO();
        reportsDAO.createNewReport(report);
        return "/controller?command=moderReportTableCommand";
    }
}
