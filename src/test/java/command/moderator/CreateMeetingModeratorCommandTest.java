package command.moderator;

import db.Entity.MainUser;
import db.Entity.Meeting;
import db.MeetingDAO;
import db.UserDAO;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.MockedConstruction;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.IOException;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class CreateMeetingModeratorCommandTest {

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void execute() {
        try {
            MockedConstruction<MeetingDAO> mocked = Mockito.mockConstruction(MeetingDAO.class,
                    (mock, context) -> {
                        // further stubbings ...
                    });
            CreateMeetingModeratorCommand createMeetingModeratorCommand = new CreateMeetingModeratorCommand();
            HttpServletRequest mockReq = spy(HttpServletRequest.class);
            HttpServletResponse mockResp = spy(HttpServletResponse.class);
            HttpSession mockSession = spy(HttpSession.class);
            MainUser mainUser = spy(MainUser.class);

            when(mockReq.getSession()).thenReturn(mockSession);
            when(mockSession.getAttribute("mainUser")).thenReturn(mainUser);
            when(mockReq.getParameter("name")).thenReturn("testName");
            when(mockReq.getParameter("topic")).thenReturn("testTopic");
            when(mockReq.getParameter("date")).thenReturn("2023-01-29");
            when(mockReq.getParameter("participants")).thenReturn("10");
            when(mainUser.getId()).thenReturn(12345L);
            when(mainUser.getLogin()).thenReturn("testLogin");

            assertEquals(createMeetingModeratorCommand.execute(mockReq, mockResp), "/working_page_for_moderators.jsp");
            MeetingDAO meetingDAO = mocked.constructed().get(0);

            assertEquals(1, mocked.constructed().size());
            ArgumentCaptor<Meeting> captor = ArgumentCaptor.forClass(Meeting.class);
            verify(meetingDAO).createNewMeeting(captor.capture());
            Meeting invocationArg = captor.getValue();

            assertEquals("testName", invocationArg.getName());
            assertEquals("testTopic", invocationArg.getTopic());
            assertEquals(10 , invocationArg.getNumber_of_participants_who_came());
            assertEquals(12345L, invocationArg.getCreator_id());
            assertEquals("testLogin", invocationArg.getCreator_name());

            verify(meetingDAO, times(1)).createNewMeeting(any(Meeting.class));

            mocked.close();
        } catch (ServletException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}