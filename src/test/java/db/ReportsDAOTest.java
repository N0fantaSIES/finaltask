package db;

import db.Entity.MainUser;
import db.Entity.Meeting;
import db.Entity.Report;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;

@ExtendWith(MockitoExtension.class)
public class ReportsDAOTest {

    @Mock
    private Report report;

    @Mock
    private Meeting meeting;

    @Mock
    private MainUser mainUser;

    @Mock
    private ReportsDAO reportsDAO;

    @Mock
    private MeetingDAO meetingDAO;

    @Mock
    private UserDAO userDAO;

    @Mock
    ArrayList<Report> reportArrayList;

    @Before
    public void setUp(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void countNumberOfReportsPerMeeting() {
        reportsDAO.createNewReport(report);
        meetingDAO.createNewMeeting(meeting);
        long meetingId = meeting.getId();
        report.setMeeting_id(meetingId);
        Mockito.doReturn(1).when(reportsDAO).countNumberOfReportsPerMeeting(meetingId);
        Assert.assertEquals(1, reportsDAO.countNumberOfReportsPerMeeting(meetingId));
    }


    @Test
    public void findReportsByUserId() {
        long userId = mainUser.getId();
        Mockito.doReturn(reportArrayList).when(reportsDAO).findReportsByUserId(userId);
        Assert.assertEquals(reportArrayList, reportsDAO.findReportsByUserId(userId));
    }

    @Test
    public void showAllAvailableReportsForSpeakers() {
        Mockito.doReturn(reportArrayList).when(reportsDAO).showAllAvailableReportsForSpeakers();
        Assert.assertEquals(reportArrayList, reportsDAO.showAllAvailableReportsForSpeakers());
    }

    @Test
    public void showAllReports() {
        Mockito.doReturn(reportArrayList).when(reportsDAO).showAllAvailableReportsForSpeakers();
        Assert.assertEquals(reportArrayList, reportsDAO.showAllAvailableReportsForSpeakers());
    }

}