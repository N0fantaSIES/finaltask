package db;

import db.Entity.MainUser;
import db.Entity.Meeting;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;

import static org.junit.Assert.*;

@ExtendWith(MockitoExtension.class)
public class MeetingDAOTest {

    @Mock
    Meeting meeting;

    @Mock
    ArrayList<Meeting> meetingArrayList;

    @Mock
    MeetingDAO meetingDAO;

    @Mock
    UserDAO userDAO;

    @Mock
    MainUser mainUser;

    @Before
    public void setUp(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void createNewMeeting() {
        meetingDAO.createNewMeeting(meeting);
        long id = meeting.getId();
        Mockito.doReturn(meeting).when(meetingDAO).findMeetingByMeetingId(id);
        Assert.assertEquals(meeting, meetingDAO.findMeetingByMeetingId(id));
    }

    @Test
    public void showAllMeetings() {
        Mockito.doReturn(meetingArrayList).when(meetingDAO).showAllMeetings();
        assertEquals(meetingArrayList, meetingDAO.showAllMeetings());
    }

    @Test
    public void showAllMeetingsWhereUserParticipates() {
        long id = mainUser.getId();
        Mockito.doReturn(meetingArrayList).when(meetingDAO).showAllMeetingsWhereUserParticipates(id);
        assertEquals(meetingArrayList, meetingDAO.showAllMeetingsWhereUserParticipates(id));
    }

    @Test
    public void showAllAvailableMeetingsForUsers() {
        long id = mainUser.getId();
        Mockito.doReturn(meetingArrayList).when(meetingDAO).showAllMeetingsWhereUserParticipates(id);
        assertEquals(meetingArrayList, meetingDAO.showAllMeetingsWhereUserParticipates(id));
    }

    @Test
    public void findMeetingByMeetingId() {
        meetingDAO.createNewMeeting(meeting);
        long id = meeting.getId();
        Mockito.doReturn(meeting).when(meetingDAO).findMeetingByMeetingId(id);
        Assert.assertEquals(meeting, meetingDAO.findMeetingByMeetingId(id));
    }

}
