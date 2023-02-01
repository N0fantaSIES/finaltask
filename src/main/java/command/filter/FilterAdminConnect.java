package command.filter;

import db.Entity.MainUser;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class FilterAdminConnect implements Filter {

    private FilterConfig config = null;
    private boolean active = false;

    private HashMap<Long, ArrayList<String>> idMap;

//    public FilterAdminConnect() {
//        ArrayList<String> adminJspList = new ArrayList<>();
//        adminJspList.add("meeting_creation_page_for_moderators.jsp");
//        adminJspList.add("meeting_show_page_for_moderator.jsp");
//        adminJspList.add("report_create_page_for_moderators.jsp");
//        adminJspList.add("report_show_page_for_moderators.jsp");
//        adminJspList.add("show_number_of_participants_per_meeting_moderators.jsp");
//        adminJspList.add("working_page_for_moderators.jsp");
//
//        ArrayList<String> userJspList = new ArrayList<>();
//        userJspList.add("available_meeting_show_page_for_user.jsp");
//        userJspList.add("user_participation_meeting_show_page.jsp");
//        userJspList.add("working_page_for_users.jsp");
//
//        ArrayList<String> speakerJspList = new ArrayList<>();
//        speakerJspList.add("available_report_show_page_for_speaker.jsp");
//        speakerJspList.add("speaker_reports_show_page.jsp");
//        speakerJspList.add("working_page_for_speakers.jsp");
//
//        idMap.put(1L, adminJspList);
//        idMap.put(2L, userJspList);
//        idMap.put(3L, speakerJspList);
//    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        this.config = filterConfig;
        String act = config.getInitParameter("active");
        if (act != null)
            active = (act.toUpperCase().equals("TRUE"));
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpReq = (HttpServletRequest) servletRequest;
        HttpSession session = httpReq.getSession();
        MainUser mainUser = (MainUser) session.getAttribute("mainUser");
        if (active){
            if (mainUser == null || mainUser.getRole_id() != 1L){
                servletRequest.getRequestDispatcher("/main.jsp").forward(servletRequest, servletResponse);
            }
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {
        config = null;
    }
}
