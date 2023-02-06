package command;

import db.Entity.MainUser;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class LocalCommand extends Command {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        HttpSession session = request.getSession();
        String newLocal = request.getParameter("newLocal");
        MainUser user = (MainUser) session.getAttribute("mainUser");
        String returnPath = null;
        if (user == null){
            returnPath = "main.jsp";
        } else {
            int role_id = user.getRole_id();
            switch (role_id) {
                case 1:
                    returnPath = "working_page_for_moderators.jsp";
                    break;
                case 2:
                    returnPath = "working_page_for_users.jsp";
                    break;
                case 3:
                    returnPath = "working_page_for_speakers.jsp";
                    break;
            }
        }
        session.setAttribute("newLocale", newLocal);
        return returnPath;
    }
}
