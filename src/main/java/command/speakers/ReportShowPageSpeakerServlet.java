package command.speakers;

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

public class ReportShowPageSpeakerServlet extends HttpServlet {

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ReportsDAO reportsDAO = new ReportsDAO();
        HttpSession session = req.getSession();
        MainUser mainUser = (MainUser) session.getAttribute("mainUser");
        ArrayList<Report> reportArrayList = reportsDAO.findReportsByUserId(mainUser.getId());
        req.setAttribute("reportArrayList", reportArrayList);
        req.getRequestDispatcher("/speaker_reports_show_page.jsp").forward(req, resp);
    }
}
