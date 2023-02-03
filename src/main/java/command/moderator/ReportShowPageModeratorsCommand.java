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
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        ReportsDAO reportsDAO = new ReportsDAO();
        ArrayList<Report> reportArrayList = reportsDAO.showAllReports();
        request.setAttribute("reportList", reportArrayList);
        return "/report_show_page_for_moderators.jsp";
    }
//    @Override
//    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        ReportsDAO reportsDAO = new ReportsDAO();
//        ArrayList<Report> reportArrayList = reportsDAO.showAllReports();
//        req.setAttribute("reportList", reportArrayList);
//        req.getRequestDispatcher("/report_show_page_for_moderators.jsp").forward(req, resp);
//    }
}
