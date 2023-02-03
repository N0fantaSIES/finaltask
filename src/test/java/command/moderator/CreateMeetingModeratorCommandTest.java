package command.moderator;

import db.Entity.MainUser;
import db.Entity.Meeting;
import org.junit.Before;
import org.junit.Test;
import org.mockito.MockitoAnnotations;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.IOException;

import static org.mockito.Mockito.*;

public class CreateMeetingModeratorCommandTest {

    private String path = "/working_page_for_moderators.jsp";

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void doGet() throws ServletException, IOException {
//        CreateMeetingModeratorCommand servlet = mock(CreateMeetingModeratorCommand.class);
//        HttpServletRequest req = mock(HttpServletRequest.class);
//        HttpServletResponse resp = mock(HttpServletResponse.class);
//        RequestDispatcher dispatcher = mock(RequestDispatcher.class);
//        MainUser mainUser = mock(MainUser.class);
//        Meeting meeting = mock(Meeting.class);
//        HttpSession session = mock(HttpSession.class);
////        Date date = mock(Date.class);
////        LocalDate localDate = mock(LocalDate.class);
////        MeetingDAO meetingDAO = mock(MeetingDAO.class);
//
//        when(req.getSession()).thenReturn(session);
//        when(session.getAttribute("mainUser")).thenReturn(mainUser);
//        when(req.getParameter("name")).thenReturn("testName");
//        when(req.getParameter("topic")).thenReturn("testTopic");
////        when(Date.valueOf(LocalDate.now())).thenReturn(Date.valueOf(LocalDate.now()));
//        when(mainUser.getId()).thenReturn(1050L);
//        when(mainUser.getLogin()).thenReturn("testLogin");
////        when(meetingDAO.createNewMeeting(meeting)).thenReturn(meeting);
//
//        when(req.getRequestDispatcher(path)).thenReturn(dispatcher);
//
//
//        servlet.doGet(req, resp);
//
//        verify(dispatcher).forward(req, resp);
    }
}