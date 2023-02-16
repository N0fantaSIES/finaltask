package command.moderator;

import command.Command;
import db.Entity.Report;
import db.ReportsDAO;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

public class ReportShowPageModeratorsCommand extends Command {

    public static final int REPORT_SHOW_PAGE_MODERATORS_SPLIT_SIZE = 10;
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        ReportsDAO reportsDAO = new ReportsDAO();
        int pageNumber = 1;
        if (request.getParameter("pagNumber") != null){
            pageNumber = Integer.parseInt(request.getParameter("pagNumber"));
        }
        double numberOfAllReports = reportsDAO.countNumberOfAllReports();
        ArrayList<Report> reportArrayList = reportsDAO.showAllReports(pageNumber);
        int numberOfSplits = (int) Math.ceil(numberOfAllReports / REPORT_SHOW_PAGE_MODERATORS_SPLIT_SIZE);
        request.setAttribute("reportList", reportArrayList);
        request.setAttribute("split", numberOfSplits);
        return "/report_show_page_for_moderators.jsp";
    }
}
