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
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        ReportsDAO reportsDAO = new ReportsDAO();
        ArrayList<Report> reportArrayList = reportsDAO.showAllAvailableReportsForSpeakers();
        request.setAttribute("reportList", reportArrayList);
        return "/available_report_show_page_for_speaker.jsp";
    }

//    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        ReportsDAO reportsDAO = new ReportsDAO();
//        ArrayList<Report> reportArrayList = reportsDAO.showAllAvailableReportsForSpeakers();
//        req.setAttribute("reportList", reportArrayList);
//        req.getRequestDispatcher("/available_report_show_page_for_speaker.jsp").forward(req, resp);
//    }
}
