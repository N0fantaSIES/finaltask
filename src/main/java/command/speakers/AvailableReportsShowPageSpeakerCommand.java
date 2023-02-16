package command.speakers;

import command.Command;
import db.Entity.Report;
import db.ReportsDAO;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

public class AvailableReportsShowPageSpeakerCommand extends Command {

    public static final int AVAILABLE_REPORT_SPEAKER_SPLIT_SIZE = 10;
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        ReportsDAO reportsDAO = new ReportsDAO();
        int pageNumber = 1;
        if (request.getParameter("pagNumber") != null){
            pageNumber = Integer.parseInt(request.getParameter("pagNumber"));
        }
        double numberOfAvailableReports = reportsDAO.numberOfAvailableReports();
        ArrayList<Report> reportArrayList = reportsDAO.showAllAvailableReportsForSpeakers(pageNumber);
        int numberOfSplits = (int) Math.ceil(numberOfAvailableReports / AVAILABLE_REPORT_SPEAKER_SPLIT_SIZE);
        request.setAttribute("reportList", reportArrayList);
        request.setAttribute("split", numberOfSplits);
        return "/available_report_show_page_for_speaker.jsp";
    }
}
