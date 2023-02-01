package command.moderator;

import db.Entity.Report;
import db.ReportsDAO;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class CreateReportPageModeratorServlet extends HttpServlet {
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Report report = new Report();
        HttpSession session = req.getSession();
//        MainUser mainUser = (MainUser) session.getAttribute("mainUser");
        report.setMeeting_id(Long.parseLong(req.getParameter("id")));
        report.setTitle_of_report(req.getParameter("title_of_report"));
        System.out.println(report);
        ReportsDAO reportsDAO = new ReportsDAO();
        reportsDAO.createNewReport(report);
        req.getRequestDispatcher("/moderatorTableServlet").forward(req, resp);

    }
}
