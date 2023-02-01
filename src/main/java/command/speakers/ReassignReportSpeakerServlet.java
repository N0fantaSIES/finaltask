package command.speakers;

import db.Entity.MainUser;
import db.ReportsDAO;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class ReassignReportSpeakerServlet extends HttpServlet {
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ReportsDAO reportsDAO = new ReportsDAO();
        HttpSession session = req.getSession();
        MainUser mainUser = (MainUser) session.getAttribute("mainUser");
        long reportId = Long.parseLong(req.getParameter("id"));
        reportsDAO.updateUserIdByReportId(reportId, mainUser.getId());
        req.getRequestDispatcher("/speakerReportTableServlet").forward(req, resp);
    }
}
