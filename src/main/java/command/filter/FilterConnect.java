package command.filter;

import db.Entity.MainUser;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class FilterConnect implements Filter {

    private FilterConfig config = null;
    private boolean active = false;

    private HashMap<Integer, ArrayList<String>> idMap;

    private HashMap<Integer, String> workingPageMap;

    public FilterConnect() {
        idMap = new HashMap<>();
        workingPageMap = new HashMap<>();

        ArrayList<String> adminJspList = new ArrayList<>();
        adminJspList.add("createMeetingModeratorCommand");
        adminJspList.add("moderatorTableCommand");
        adminJspList.add("moderatorCountParticipantsTable");
        adminJspList.add("moderReportTableCommand");
        adminJspList.add("moderatorReportCreationCommand");
        adminJspList.add("meetingSortingForModeratorsCommand");
        adminJspList.add("transferUrlCommand");
        adminJspList.add("languageSwitcherCommand");
        adminJspList.add("logOutCommand");
        adminJspList.add("working_page_for_moderators.jsp");

        ArrayList<String> userJspList = new ArrayList<>();
        userJspList.add("userTableCommand");
        userJspList.add("userMeetingParticipation");
        userJspList.add("languageSwitcherCommand");
        userJspList.add("participationMeetingUserPage");
        userJspList.add("logOutCommand");
        userJspList.add("working_page_for_users.jsp");

        ArrayList<String> speakerJspList = new ArrayList<>();
        speakerJspList.add("speakerTableCommand");
        speakerJspList.add("speakerReportTableCommand");
        speakerJspList.add("languageSwitcherCommand");
        speakerJspList.add("reassignSpeakerCommand");
        speakerJspList.add("logOutCommand");
        speakerJspList.add("working_page_for_speakers.jsp");

        idMap.put(1, adminJspList);
        idMap.put(2, userJspList);
        idMap.put(3, speakerJspList);

        workingPageMap.put(1, "working_page_for_moderators.jsp");
        workingPageMap.put(2, "working_page_for_users.jsp");
        workingPageMap.put(3, "working_page_for_speakers.jsp");
    }

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
        if (active) {
            if (mainUser != null) {
                int userRoleId = mainUser.getRole_id();
                ArrayList<String> command = idMap.get(userRoleId);
                if (!command.contains(httpReq.getParameter("command"))) {
                    String workingPageUser = workingPageMap.get(userRoleId);
                    servletRequest.getRequestDispatcher(workingPageUser).forward(servletRequest, servletResponse);
                }
            }
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {
        config = null;
    }
}
