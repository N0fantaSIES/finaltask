package command.speakers;

import command.Command;
import db.ReportsDAO;
import db.Entity.Report;
import db.Entity.MainUser;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;

public class ReportShowPageSpeakerCommand extends Command {

    public static final int SPEAKER_REPORTS_SPLIT_SIZE = 10;
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        ReportsDAO reportsDAO = new ReportsDAO();
        int pageNumber = 1;
        if (request.getParameter("pagNumber") != null){
            pageNumber = Integer.parseInt(request.getParameter("pagNumber"));
        }
        HttpSession session = request.getSession();
        MainUser mainUser = (MainUser) session.getAttribute("mainUser");
        long userId = mainUser.getId();
        double numberOfSpeakerReports = reportsDAO.numberOfSpeakerReports(userId);
        ArrayList<Report> reportArrayList = reportsDAO.findReportsByUserId(userId, pageNumber);
        int numberOfSplits = (int) Math.ceil(numberOfSpeakerReports / SPEAKER_REPORTS_SPLIT_SIZE);
        request.setAttribute("reportArrayList", reportArrayList);
        request.setAttribute("split", numberOfSplits);
        return "/speaker_reports_show_page.jsp";
    }
}
