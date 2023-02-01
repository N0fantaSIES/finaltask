package command.filter;

import db.Entity.MainUser;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class FilterUserConnect implements Filter {

    private FilterConfig config = null;
    private boolean active = false;


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
            if (mainUser == null || mainUser.getRole_id() != 2L){
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

