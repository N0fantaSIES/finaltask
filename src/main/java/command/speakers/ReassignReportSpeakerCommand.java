package command.speakers;

import command.Command;
import db.Entity.MainUser;
import db.ReportsDAO;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class ReassignReportSpeakerCommand extends Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        ReportsDAO reportsDAO = new ReportsDAO();
        HttpSession session = request.getSession();
        MainUser mainUser = (MainUser) session.getAttribute("mainUser");
        long reportId = Long.parseLong(request.getParameter("id"));
        reportsDAO.updateUserIdByReportId(reportId, mainUser.getId());
        return "/controller?command=speakerReportTableCommand";
    }
}
