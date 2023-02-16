package command.moderator;

import db.Entity.Meeting;
import db.MeetingDAO;
import org.junit.Before;
import org.junit.Test;
import org.mockito.MockedConstruction;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.ArrayList;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

public class MeetingShowPageModeratorsCommandTest {

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
            MeetingShowPageModeratorsCommand meetingShowPageModeratorsCommand = new MeetingShowPageModeratorsCommand();
            HttpServletRequest mockReq = spy(HttpServletRequest.class);
            HttpServletResponse mockResp = spy(HttpServletResponse.class);
            ArrayList<Meeting> meetingArrayList = new ArrayList<>();

            assertEquals(meetingShowPageModeratorsCommand.execute(mockReq, mockResp), "/meeting_show_page_for_moderator.jsp");
            MeetingDAO meetingDAO = mocked.constructed().get(0);

            when(mockReq.getParameter("pagNumber")).thenReturn("1");
            when(meetingDAO.numberOfAllMeetings()).thenReturn(10);
            when(meetingDAO.showAllMeetings(1)).thenReturn(meetingArrayList);

            verify(mockReq, times(1)).setAttribute("meetingList", meetingArrayList);
            verify(mockReq, times(1)).setAttribute("split", 0);

            mocked.close();
        } catch (ServletException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}