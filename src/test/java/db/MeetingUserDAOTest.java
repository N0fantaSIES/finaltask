package db;

import db.Entity.MainUser;
import db.Entity.Meeting;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;

import static org.junit.Assert.*;

public class MeetingUserDAOTest {
    @Mock
    private Meeting meeting;

    @Mock
    private MeetingDAO meetingDAO;

    @Mock
    private UserDAO userDAO;

    @Mock
    private MainUser mainUser;

    @Mock
    private MeetingUserDAO meetingUserDAO;

    @Before
    public void setUp(){
        MockitoAnnotations.openMocks(this);
    }


    @Test
    public void createNewMeetingUser() {
        long userId = mainUser.getId();
        long meetingId = meeting.getId();
        meetingUserDAO.createNewMeetingUser(meetingId, userId);
        Mockito.doReturn(1).when(meetingUserDAO).countNumberOfUsersPerMeeting(meetingId);
        Assert.assertEquals(1, meetingUserDAO.countNumberOfUsersPerMeeting(meetingId));
    }

    @Test
    public void countNumberOfUsersPerMeeting() {
        long userId = mainUser.getId();
        long meetingId = meeting.getId();
        meetingUserDAO.createNewMeetingUser(meetingId, userId);
        Mockito.doReturn(1).when(meetingUserDAO).countNumberOfUsersPerMeeting(meetingId);
        Assert.assertEquals(1, meetingUserDAO.countNumberOfUsersPerMeeting(meetingId));
    }

}